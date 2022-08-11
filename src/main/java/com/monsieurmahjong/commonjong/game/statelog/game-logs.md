# Game logs

The idea with game logs is to have a log of every move that's done in a game.

## General

A game log is prepended by the player who made a given action. That player is represented by their seat.

The seats are as follows:
* east
* south
* west
* north

The following actions are possible in every variant of mahjong:

* draw
* discard

A log will be made of generally three components: the seat, the action and the tile (if relevant).

The tiles are represented using the MPSZ notation. The current tile kinds are supported:
* characters/cracks (as m)
* circles/dots (as p)
* bamboos/bams (as s)
* honours (as z)

See the MahjongTileKind class for more info.

## Riichi

The following calls are possible in riichi:

* tsumo
* ron
* pon
* kan
* chii
* riichi
* tenpai
* noten
* nagashimangan

### Examples of logs in riichi

* east-draw-3p
* east-discard-3p
* east-tsumo-4p
* east-riichi-3s
* south-pon-3p
* south-kan-1z
* west-chii-7p
* north-ron-7z
* east-tenpai
* south-noten
* west-tenpai
* north-nagashimangan