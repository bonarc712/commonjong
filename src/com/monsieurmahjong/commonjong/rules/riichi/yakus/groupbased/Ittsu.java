package com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased;

import java.util.*;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.*;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;

public class Ittsu extends GroupBasedYaku
{
    public Ittsu(Hand hand, List<TileGroup> groups)
    {
        super(hand, groups);
    }

    @Override
    public boolean isValid()
    {
        List<TileGroup> charactersRunsCatalog = new ArrayList<>();
        List<TileGroup> circlesRunsCatalog = new ArrayList<>();
        List<TileGroup> bamboosRunsCatalog = new ArrayList<>();

        for (TileGroup group : groups)
        {
            if (group.isRun())
            {
                MahjongTileKind firstTile = group.getTileKindAt(0);
                TileFamily family = firstTile.getFamily();
                switch (family)
                {
                    case CHARACTERS:
                        charactersRunsCatalog.add(group);
                        break;
                    case CIRCLES:
                        circlesRunsCatalog.add(group);
                        break;
                    case BAMBOOS:
                        bamboosRunsCatalog.add(group);
                        break;
                    default:
                        break;
                }
            }
        }

        if (charactersContainIttsu(charactersRunsCatalog))
        {
            return true;
        }

        if (circlesContainIttsu(circlesRunsCatalog))
        {
            return true;
        }

        if (bamboosContainIttsu(bamboosRunsCatalog))
        {
            return true;
        }

        return false;
    }

    private boolean charactersContainIttsu(List<TileGroup> charactersRunsCatalog)
    {
        if (charactersRunsCatalog.size() < 3)
        {
            return false;
        }
        return charactersRunsCatalog.contains(TileGroup.of(MahjongTileKind.CHARACTERS_1, MahjongTileKind.CHARACTERS_2, MahjongTileKind.CHARACTERS_3)) && //
                charactersRunsCatalog.contains(TileGroup.of(MahjongTileKind.CHARACTERS_4, MahjongTileKind.CHARACTERS_5, MahjongTileKind.CHARACTERS_6)) && //
                charactersRunsCatalog.contains(TileGroup.of(MahjongTileKind.CHARACTERS_7, MahjongTileKind.CHARACTERS_8, MahjongTileKind.CHARACTERS_9));
    }

    private boolean circlesContainIttsu(List<TileGroup> circlesRunsCatalog)
    {
        if (circlesRunsCatalog.size() < 3)
        {
            return false;
        }
        return circlesRunsCatalog.contains(TileGroup.of(MahjongTileKind.CIRCLES_1, MahjongTileKind.CIRCLES_2, MahjongTileKind.CIRCLES_3)) && //
                circlesRunsCatalog.contains(TileGroup.of(MahjongTileKind.CIRCLES_4, MahjongTileKind.CIRCLES_5, MahjongTileKind.CIRCLES_6)) && //
                circlesRunsCatalog.contains(TileGroup.of(MahjongTileKind.CIRCLES_7, MahjongTileKind.CIRCLES_8, MahjongTileKind.CIRCLES_9));
    }

    private boolean bamboosContainIttsu(List<TileGroup> bamboosRunsCatalog)
    {
        if (bamboosRunsCatalog.size() < 3)
        {
            return false;
        }
        return bamboosRunsCatalog.contains(TileGroup.of(MahjongTileKind.BAMBOOS_1, MahjongTileKind.BAMBOOS_2, MahjongTileKind.BAMBOOS_3)) && //
                bamboosRunsCatalog.contains(TileGroup.of(MahjongTileKind.BAMBOOS_4, MahjongTileKind.BAMBOOS_5, MahjongTileKind.BAMBOOS_6)) && //
                bamboosRunsCatalog.contains(TileGroup.of(MahjongTileKind.BAMBOOS_7, MahjongTileKind.BAMBOOS_8, MahjongTileKind.BAMBOOS_9));
    }

    @Override
    public int getHanValue()
    {
        return hand.isClosed() ? 2 : 1;
    }
}
