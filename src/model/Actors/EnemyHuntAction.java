/**
 * 
 */
package model.Actors;

/**
 * The action used by the opposition to 
 * hunt and kill the play controlled charcters
 * @author Jonathon Davis
 *
 */
public class EnemyHuntAction extends Action {

	/* (non-Javadoc)
	 * @see model.Actors.Action#execute(model.Actors.Actor)
	 */
	@Override
	public int execute(Actor performer) {
		Actor prey = findNearestPlayerActor(performer);
		return new AttackAction(prey).execute(performer);
	}
	
	private Actor findNearestPlayerActor(Actor performer){
		Actor nearestActor = null;
		double closest = Integer.MAX_VALUE;
		for(Actor p : PlayerControlledActor.allActors){
			int x = performer.getPosition().getCol(), x2 = p.getPosition().getCol(),
					y = performer.getPosition().getRow(), y2 = p.getPosition().getRow();
			double distance = Math.sqrt((x2-x)*(x2-x)+(y2-y)*(y2-y));
			if(distance < closest){
				closest = distance;
				nearestActor = p;
			}
		}
		return nearestActor;
	}

}
