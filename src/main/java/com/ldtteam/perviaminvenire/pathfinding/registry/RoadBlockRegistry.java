package com.ldtteam.perviaminvenire.pathfinding.registry;

import java.util.List;

import com.ldtteam.perviaminvenire.api.adapters.passable.IPassableBlockCallback;
import com.ldtteam.perviaminvenire.api.adapters.registry.IPassableBlockRegistry;
import com.ldtteam.perviaminvenire.api.adapters.registry.IRoadBlockRegistry;
import com.ldtteam.perviaminvenire.api.adapters.road.IRoadBlockCallback;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;

public final class RoadBlockRegistry extends AbstractCallbackBasedRegistry<IRoadBlockRegistry, IRoadBlockCallback> implements IRoadBlockRegistry {

    private static final RoadBlockRegistry INSTANCE = new RoadBlockRegistry();

    public static RoadBlockRegistry getInstance() {
        return INSTANCE;
    }

    private RoadBlockRegistry() {
    }

    @Override
    public IRoadBlockRegistry getThis() {
        return this;
    }

    @Override
    protected IRoadBlockCallback getRunnerInternal(final List<IRoadBlockCallback> callbacks) {
        return (entity, block) -> callbacks.stream().anyMatch(c -> c.isRoad(entity, block));
    }
}
