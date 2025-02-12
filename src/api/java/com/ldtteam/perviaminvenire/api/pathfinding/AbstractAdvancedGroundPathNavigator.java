package com.ldtteam.perviaminvenire.api.pathfinding;

import com.ldtteam.perviaminvenire.api.pathfinding.stuckhandling.IStuckHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractAdvancedGroundPathNavigator extends GroundPathNavigator implements IAdvancedPathNavigator
{
    //  Parent class private members
    protected final MobEntity    ourEntity;
    @Nullable
    protected       BlockPos     destination;
    protected       double       walkSpeed = 1.0D;
    protected       double       requestedSpeed = walkSpeed;
    @Nullable
    protected       BlockPos     originalDestination;

    /**
     * The navigators node costs
     */
    private PathingOptions pathingOptions = new PathingOptions();

    public AbstractAdvancedGroundPathNavigator(
      final MobEntity entityLiving,
      final World worldIn)
    {
        super(entityLiving, worldIn);
        this.ourEntity = entity;
    }

    /**
     * Get the destination from the path.
     *
     * @return the destination position.
     */
    @Override
    @Nullable
    public BlockPos getDestination()
    {
        return destination;
    }

    /**
     * Used to path away from a position.
     *
     * @param currentPosition the position to avoid.
     * @param range the range he should move out of.
     * @param speed the speed to run at.
     * @return the result of the pathing.
     */
    public abstract PathResult moveAwayFromXYZ(final BlockPos currentPosition, final double range, final double speed);

    /**
     * Try to move to a certain position.
     *
     * @param x     the x target.
     * @param y     the y target.
     * @param z     the z target.
     * @param speed the speed to walk.
     * @return the PathResult.
     */
    public abstract PathResult moveToXYZ(final double x, final double y, final double z, final double speed);

    /**
     * Used to path away from a ourEntity.
     *
     * @param target        the ourEntity.
     * @param distance the distance to move to.
     * @param combatMovementSpeed    the speed to run at.
     * @return the result of the pathing.
     */
    public abstract PathResult moveAwayFromLivingEntity(final Entity target, final double distance, final double combatMovementSpeed);

    /**
     * Attempt to move to a specific pos.
     * @param position the position to move to.
     * @param speed the speed.
     * @return true if successful.
     */
    public abstract boolean tryMoveToBlockPos(final BlockPos position, final double speed);
    /**
     * Used to move a living ourEntity with a speed.
     *
     * @param e     the ourEntity.
     * @param speed the speed.
     * @return the result.
     */
    public abstract PathResult moveToLivingEntity(@NotNull final Entity e, final double speed);

    /**
     * Get the pathing options
     *
     * @return the pathing options.
     */
    @Override
    public PathingOptions getPathingOptions()
    {
        return pathingOptions;
    }

    /**
     * Get the entity of this navigator
     *
     * @return mobentity
     */
    @Override
    public MobEntity getOurEntity()
    {
        return ourEntity;
    }
}
