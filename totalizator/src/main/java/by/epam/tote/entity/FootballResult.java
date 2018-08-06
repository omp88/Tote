package by.epam.tote.entity;

public class FootballResult {
	
	/** The event id. */
	private int eventId;
	
	/** The type. */
	private EventResultType type;
	
	/** The team 1 score. */
	private int team1Score;
	
	/** The team 2 score. */
	private int team2Score;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return eventId;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.eventId = id;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public EventResultType getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(EventResultType type) {
		this.type = type;
	}
	
	/**
	 * Gets the team 1 score.
	 *
	 * @return the team 1 score
	 */
	public int getTeam1Score() {
		return team1Score;
	}
	
	/**
	 * Sets the team 1 score.
	 *
	 * @param team1Score the new team 1 score
	 */
	public void setTeam1Score(int team1Score) {
		this.team1Score = team1Score;
	}
	
	/**
	 * Gets the team 2 score.
	 *
	 * @return the team 2 score
	 */
	public int getTeam2Score() {
		return team2Score;
	}
	
	/**
	 * Sets the team 2 score.
	 *
	 * @param team2Score the new team 2 score
	 */
	public void setTeam2Score(int team2Score) {
		this.team2Score = team2Score;
	}
	
	
	

}
