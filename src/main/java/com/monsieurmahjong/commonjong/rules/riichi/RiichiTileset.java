package com.monsieurmahjong.commonjong.rules.riichi;

import java.util.ArrayList;
import java.util.List;

import com.monsieurmahjong.commonjong.game.Tileset;
import com.monsieurmahjong.commonjong.rules.generic.MahjongTileKind;

public class RiichiTileset extends Tileset
{
    @Override
    public List<MahjongTileKind> getTileList()
    {
        var tileList = new ArrayList<MahjongTileKind>();
        tileList.addAll(MahjongTileKind.getAllCharacters());
        tileList.addAll(MahjongTileKind.getAllCircles());
        tileList.addAll(MahjongTileKind.getAllBamboos());
        tileList.addAll(MahjongTileKind.getAllHonours());
        return tileList;
    }
}
