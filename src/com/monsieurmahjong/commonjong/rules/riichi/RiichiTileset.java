package com.monsieurmahjong.commonjong.rules.riichi;

import java.util.*;

import com.monsieurmahjong.commonjong.game.Tileset;
import com.monsieurmahjong.commonjong.rules.generic.*;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;

public class RiichiTileset extends Tileset
{
    @Override
    public List<MahjongTileKind> getTileList()
    {
        List<MahjongTileKind> tileList = new ArrayList<>();
        tileList.addAll(TileKindUtils.getAllCharacters());
        tileList.addAll(TileKindUtils.getAllCircles());
        tileList.addAll(TileKindUtils.getAllBamboos());
        tileList.addAll(TileKindUtils.getAllHonours());
        return tileList;
    }
}
