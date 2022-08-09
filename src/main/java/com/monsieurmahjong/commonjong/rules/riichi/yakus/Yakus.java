package com.monsieurmahjong.commonjong.rules.riichi.yakus;

import java.util.ArrayList;
import java.util.List;

import com.monsieurmahjong.commonjong.game.Hand;
import com.monsieurmahjong.commonjong.rules.generic.waits.TileGroup;
import com.monsieurmahjong.commonjong.rules.riichi.scoring.RiichiScoringParameters;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Chanta;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Chiitoitsu;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.ChuurenPoutou;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Daisangen;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Iipeikou;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Ittsu;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Junchan;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.KokushiMusou;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Pinfu;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Ryanpeikou;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Sanankou;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Sankantsu;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.SanshokuDoujun;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.SanshokuDoukou;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Shousangen;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Suuankou;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Suukantsu;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.Toitoi;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai.GreenDragonYakuhai;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai.RedDragonYakuhai;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai.SeatWindYakuhai;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai.TableWindYakuhai;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.groupbased.yakuhai.WhiteDragonYakuhai;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased.Chinitsu;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased.Chinroutou;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased.Honitsu;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased.Honroutou;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased.Ryuuiisou;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased.Tanyao;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.tilebased.Tsuuiisou;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.Chankan;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.Chihou;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.Haitei;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.Houtei;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.Ippatsu;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.MenzenTsumo;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.NagashiMangan;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.Renhou;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.Riichi;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.RinshanKaihou;
import com.monsieurmahjong.commonjong.rules.riichi.yakus.timingbased.Tenhou;

public class Yakus
{
    // Factory method for the standard yakus
    public static List<Yaku> getStandardYakus(Hand hand, List<TileGroup> tileGroups, RiichiScoringParameters parameters)
    {
        var yakuList = new ArrayList<Yaku>();

        // group-based yakus
        yakuList.add(new GreenDragonYakuhai(hand, tileGroups));
        yakuList.add(new RedDragonYakuhai(hand, tileGroups));
        yakuList.add(new SeatWindYakuhai(hand, tileGroups));
        yakuList.add(new TableWindYakuhai(hand, tileGroups));
        yakuList.add(new WhiteDragonYakuhai(hand, tileGroups));
        yakuList.add(new Chanta(hand, tileGroups));
        yakuList.add(new Chiitoitsu(hand, tileGroups));
        yakuList.add(new ChuurenPoutou(hand, tileGroups));
        yakuList.add(new Daisangen(hand, tileGroups));
        yakuList.add(new Iipeikou(hand, tileGroups));
        yakuList.add(new Ittsu(hand, tileGroups));
        yakuList.add(new Junchan(hand, tileGroups));
        yakuList.add(new KokushiMusou(hand, tileGroups));
        yakuList.add(new Pinfu(hand, tileGroups));
        yakuList.add(new Ryanpeikou(hand, tileGroups));
        yakuList.add(new Sanankou(hand, tileGroups));
        yakuList.add(new Sankantsu(hand, tileGroups));
        yakuList.add(new SanshokuDoujun(hand, tileGroups));
        yakuList.add(new SanshokuDoukou(hand, tileGroups));
        yakuList.add(new Shousangen(hand, tileGroups));
        yakuList.add(new Suuankou(hand, tileGroups));
        yakuList.add(new Suukantsu(hand, tileGroups));
        yakuList.add(new Toitoi(hand, tileGroups));

        // tile-based yakus
        yakuList.add(new Chinitsu(hand));
        yakuList.add(new Chinroutou(hand));
        yakuList.add(new Honitsu(hand));
        yakuList.add(new Honroutou(hand));
        yakuList.add(new Ryuuiisou(hand));
        yakuList.add(new Tanyao(hand));
        yakuList.add(new Tsuuiisou(hand));

        // timing-based yakus
        yakuList.add(new Chankan(parameters));
        yakuList.add(new Chihou(parameters));
        yakuList.add(new Haitei(parameters));
        yakuList.add(new Houtei(parameters));
        yakuList.add(new Ippatsu(parameters));
        yakuList.add(new MenzenTsumo(parameters));
        yakuList.add(new NagashiMangan(parameters));
        yakuList.add(new Renhou(parameters));
        yakuList.add(new Riichi(parameters));
        yakuList.add(new RinshanKaihou(parameters));
        yakuList.add(new Tenhou(parameters));

        return yakuList;
    }
}
