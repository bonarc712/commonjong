package game.mahjong;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import game.Player;
import utils.NameGenerator;

public class MahjongGame // riichi
{
	private MahjongTileset tileset;
	private List<MahjongPlayer> players;

	public MahjongGame()
	{
		tileset = new MahjongTileset();
		players = new ArrayList<>(4);
		for (int i = 0; i < 4; i++)
		{
			MahjongPlayer player = new MahjongPlayer(this);
			player.setName(NameGenerator.generateNameOtherThan(getPlayerNames()));
			players.add(player);
		}

		players.forEach(player -> {
			for (int i = 0; i < 13; i++)
				player.play();
		});

		players.get(0).play();
		players.forEach(Player::showHand);
	}

	public MahjongTileset getTileset()
	{
		return tileset;
	}

	private List<String> getPlayerNames()
	{
		return players.stream() //
				.filter(player -> player.getName() != null && !player.getName().equals("")) //
				.map(Player::getName) //
				.collect(Collectors.toList());
	}

}
