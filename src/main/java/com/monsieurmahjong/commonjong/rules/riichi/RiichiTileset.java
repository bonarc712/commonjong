package com.monsieurmahjong.commonjong.rules.riichi;

import java.util.ArrayList;
import java.util.List;

import com.monsieurmahjong.commonjong.game.Tileset;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;
import com.monsieurmahjong.commonjong.rules.generic.utils.TileKindUtils;

public class RiichiTileset extends Tileset
{
    @Override
    public List<MahjongTileKind> getTileList()
    {
        var tileList = new ArrayList<MahjongTileKind>();
        tileList.addAll(TileKindUtils.getAllCharacters());
        tileList.addAll(TileKindUtils.getAllCircles());
        tileList.addAll(TileKindUtils.getAllBamboos());
        tileList.addAll(TileKindUtils.getAllHonours());
        return tileList;
    }
}
