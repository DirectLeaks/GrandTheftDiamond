package me.micrjonas.grandtheftdiamond.api.event.player;

import me.micrjonas.grandtheftdiamond.api.event.AbstractEvent;

import org.bukkit.entity.Player;

/**
 * Super class of all player events
 */
public abstract class AbstractPlayerEvent extends AbstractEvent {

	private final Player who;
	
    /**
     * @param who The involved player
     * @throws IllegalArgumentException Thrown if {@code who} is {@code null}
     */
	protected AbstractPlayerEvent(Player who) throws IllegalArgumentException {
		if (who == null) {
			throw new IllegalArgumentException("Player is not allowed to be null");
		}
		this.who = who;
	}
	
	/**
	 * Returns the involved {@code Player}
	 * @return The involved {@code Player}. Never {@code null}
	 */
	public Player getPlayer() {
		return who;
	}

}
