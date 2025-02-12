package com.ldtteam.perviaminvenire.pathfinding;

import com.ldtteam.perviaminvenire.api.pathfinding.ICalculationResultRenderer;
import com.ldtteam.perviaminvenire.api.pathfinding.PathingCalculationData;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class CalculationResultRenderer implements ICalculationResultRenderer
{
    private static final CalculationResultRenderer INSTANCE = new CalculationResultRenderer();

    public static CalculationResultRenderer getInstance()
    {
        return INSTANCE;
    }

    private CalculationResultRenderer()
    {
    }

    @Override
    public void renderDataIntoWorld(final PathingCalculationData data, final ServerWorld world)
    {
        if (data.getPath().size() == 0)
            return;

        data.getWalkedPositions().forEach((source, target) -> {
            BlockPos delta = target.subtract(source);

            world.spawnParticle(ParticleTypes.END_ROD,
              source.getX() + 0.5, source.getY() + 0.5, source.getZ() + 0.5, 0,
              delta.getX(), delta.getY(), delta.getZ(), 0.1);

            world.spawnParticle(getParticleTypeFor(source, data),
              source.getX() + 0.5, source.getY() + 0.5, source.getZ() + 0.5, 0,
              0, 0, 0, 0);

            if (!data.getWalkedPositions().containsKey(target))
            {
                world.spawnParticle(getParticleTypeFor(target, data),
                  target.getX() + 0.5, target.getY() + 0.5, target.getZ() + 0.5, 0,
                  0, 0, 0, 0);
            }
        });
    }

    private IParticleData getParticleTypeFor(final BlockPos pos, final PathingCalculationData data) {
        if (data.getPath().getLast().equals(pos))
        {
            return data.isReachesDestination() ? ParticleTypes.HEART : ParticleTypes.DAMAGE_INDICATOR;
        }

        if (data.getInvalidNodeReasons().containsKey(pos))
        {
            switch (data.getInvalidNodeReasons().get(pos))
            {
                case SWIMMING_NODE:
                    return ParticleTypes.UNDERWATER;
                default:
                    return ParticleTypes.ANGRY_VILLAGER;
            }
        }

        if (data.getPath().contains(pos))
        {
            return ParticleTypes.HAPPY_VILLAGER;
        }

        if (data.getConsumedNodes().contains(pos))
        {
            return ParticleTypes.SOUL_FIRE_FLAME;
        }

        return ParticleTypes.DAMAGE_INDICATOR;
    }
}
