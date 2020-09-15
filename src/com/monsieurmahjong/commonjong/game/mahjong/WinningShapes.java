package com.monsieurmahjong.commonjong.game.mahjong;

import java.util.List;

import com.monsieurmahjong.commonjong.game.Tile;

/**
 * Winning shapes is meant has a way to validate if a
 * hand has a winning shape or not. It may differ from
 * one type of mahjong ruleset to another.
 */
public interface WinningShapes
{
    public boolean hasWinningShape(List<Tile> hand);

    /**
     * Alias for hasWinningShape
     */
    public default boolean isMahjong(List<Tile> hand)
    {
        return hasWinningShape(hand);
    }
}
