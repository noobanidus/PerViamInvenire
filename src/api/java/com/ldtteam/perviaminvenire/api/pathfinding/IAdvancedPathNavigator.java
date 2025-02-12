package com.ldtteam.perviaminvenire.api.pathfinding;

import com.ldtteam.perviaminvenire.api.pathfinding.stuckhandling.IStuckHandler;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

/**
 * Interface to indicate that this is an advanced navigator.
 */
public interface IAdvancedPathNavigator
{
    /**
     * The destination of the current path finder.
     *
     * @return The current destination.
     */
    @Nullable
    BlockPos getDestination();

    /**
     * The options used during pathfinding.
     * @return
     */
    PathingOptions getPathingOptions();

    MobEntity getOurEntity();

    /**
     * Gets the desired to go position
     *
     * @return desired go to pos
     */
    BlockPos getDesiredPos();

    /**
     * Sets the stuck handler for this navigator
     *
     * @param stuckHandler handler to use
     */
    void setStuckHandler(IStuckHandler stuckHandler);
}
