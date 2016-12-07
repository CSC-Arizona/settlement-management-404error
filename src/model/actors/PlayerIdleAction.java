package model.actors;

import java.util.ArrayList;
import java.util.Random;

import model.game.Game;

public class PlayerIdleAction extends Action {
	/**
	 * 
	 */
	private static final long serialVersionUID = -438415060255114844L;
	private static Random rand = new Random();
	private MoveAction move;
	private static int count = 0;
	@Override
	public int execute(Actor performer) {
		ArrayList<Position> valid = new ArrayList<>();
		int x = rand.nextInt(40) - 20;

		for (int y = performer.getPosition().getRow() - 30; y < performer
				.getPosition().getRow() + 30; y++) {
			if (y >= Game.getMap().getTotalHeight() || y < 0)
				continue;
			Position pos = new Position(y, Math.floorMod(x, Game.getMap()
					.getTotalWidth()));
			if (Game.validActorLocation(pos.getRow(), pos.getCol())
					&& !Game.getMap().getBuildingBlock(pos).getID()
							.equals("Ant tunnel")
					&& Game.getMap().getBuildingBlock(pos).isOccupiable()) {
				valid.add(pos);
			}
		}
		if (valid.size() > 0)
			move = new MoveAction(valid.get(rand.nextInt(valid.size())));
		if (move != null) {
			if (count == 10) {
				count = 0;
				return move.execute(performer);
			} else {
				count += 1;
				return Action.MADE_PROGRESS;
			}
		} else
			return Action.MADE_PROGRESS;
	}

}
