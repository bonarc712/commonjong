# commonjong
An open-source mahjong engine in Java.

## What are the goals of commonjong?
The idea with commonjong is to drive the backend of a game of mahjong entirely. The following points are included in the scope.

### Game Engine
With Commonjong, the following will be possible.
* Have a running game structure.
* Make calls for a win, a triplet, a quad, or a run.
* A hand can be recognized as ready or X from ready.
* A hand can be scored as though it was played normally.

### Rules
The MVP of Commonjong is introducing `Riichi` rules first, as these are the rules the coders are most familiar with, but we thrive to be able to have a structure that allows to make your own rules.
* Riichi rules and winning conditions will come out of the box and cover WRC, Tenhou, Mahjong Soul and Montréal rules.
* A framework will be implemented to allow for tweaks to rules.
* We plan to eventually add other variants (eg. MCR, American, SBR). 

### API
* The idea is to have a common API that will be usable by any outside application to drive mahjong games effectively and quickly.

### Out of scope
* Network connectivity
* UI
* Social interactions between players
See the `Related Projects` section for info on the upcoming projects.

## Would you like to contribute?
We use Slack as an internal communication tool and Trello as an issue tracker.

Join the Slack here : [Link to Slack](https://join.slack.com/t/monsieurmahjo-7ov7889/shared_invite/zt-r1cluek3-sX2rQTPy3q5XZNc9PcqQSQ)

Join the Trello board here : [Link to Trello](https://trello.com/invite/b/WEuK88Ol/e2e603d9d1e9430d689d4c554921c562/commonjong)

## Related Projects
**Sanshoku** will be the UI on which players will be able to play mahjong through the commonjong engine.

**Sanman** will be the game servers on which the players will connect for their mahjong games.
 