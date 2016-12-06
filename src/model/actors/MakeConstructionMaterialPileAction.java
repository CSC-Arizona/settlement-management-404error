/**
 * 
 */

package model.actors;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import model.furniture.ConstructionMaterialPile;
import model.furniture.Crate;
import model.furniture.Furniture;
import model.game.Game;
import model.items.Item;
import model.room.Room;

/**
 * This class deals with actually gathering the required materials before building a 
 * room.
 * 
 * @author Katherine Walters
 *
 */
public class MakeConstructionMaterialPileAction extends Action {

	private static final long serialVersionUID = 1454588966658079359L;
	private Position pilePos;
	private ConstructionMaterialPile pile;
	private List<Item> itemList;
	private MoveAction move;
	private MoveAction comeBack;
	private VisitCrateAction goToCrate;
	private Room room;
	private boolean upgrading;

	/**
	 * 
	 * @param room: The Room object (which contains the position of its top left corner)
	 * @param pileLoc: the corner of the room which is the location of the construction 
	 *              material pile
	 */
	public MakeConstructionMaterialPileAction(Room room, Position pileLoc, boolean upgrading) {
		this.pilePos = pileLoc;
		this.room = room;
		this.upgrading = upgrading;
		pile = (ConstructionMaterialPile) Game.getMap().getBuildingBlock(pilePos).getFurniture();
	}

	/**
	 * If the pile doesn't require more materials to be completed, create a new 
	 * PostMaterialGatheringConstructionAction and return Action.COMPLETED. Else, an 
	 * actor which is at the pile location either deposits items from their inventory to the pile or 
	 * grabs the needed item from a crate (if there are any). If there are no actors by the pile, one 
	 * moves to it to 'check the required materials' and performs the above actions. If nobody has
	 * the item and there aren't any crates with it, it's returned to the pool until later.
	 */
	@Override
	public int execute(Actor performer) {
		if (pile == null || pile.getRequiredMaterials().isEmpty()) {
			if (upgrading)
				PlayerControlledActor.addActionToPlayerPool(new UpgradeRoomAction(room));
			else
		        PlayerControlledActor.addActionToPlayerPool(new PostMaterialGatheringConstructionAction(room));
			return Action.COMPLETED;
		}
		if (performer.getPosition().equals(pilePos)) {
			if (!pile.getRequiredMaterials().isEmpty()) {
				Item i = pile.getRequiredMaterials().get(0);
				if (performer.getInventory().contains(i)) {
					pile.addItem(i);
					performer.getInventory().removeItem(i);
				} else {
					goToCrate = new VisitCrateAction(i);
					int act = goToCrate.execute(performer);
					if (act == Action.COMPLETED) {
						comeBack = new MoveAction(pilePos);
						int comeBackResult = comeBack.execute(performer);
						if (comeBackResult == Action.COMPLETED) {
							pile.addItem(i);
							performer.getInventory().removeItem(i);
						} else {
							//return comeBackResult;
							return Action.Pool;
						}
					} else {
						return Action.Pool;
					}
				}
			}
		} else {
			if (move == null)
				move = new MoveAction(pilePos);
			int action = move.execute(performer);
			if (action == Action.COMPLETED) {
				return Action.DELAY;
			}
			return Action.Pool;
		}
		return Action.Pool;
	}
}
