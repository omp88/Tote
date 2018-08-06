package by.epam.tote.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class FootballBet {
	
	/** The id. */
	private int id;
	
	/** The event id. */
	private int eventId;
	
	/** The client id. */
	private int clientId;
	
	/** The condition. */
	private BetCondition condition;
	
	/** The amount. */
	private BigDecimal amount;
	
	/** The team 1 expected score. */
	private int team1ExpectedScore;
	
	/** The team 2 expected score. */
	private int team2ExpectedScore;
	
	/** The bet time. */
	private Timestamp betTime;
	
	/** The coeff. */
	private double coeff;
	
	/** The state. */
	private BetState state;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the event id.
	 *
	 * @return the event id
	 */
	public int getEventId() {
		return eventId;
	}
	
	/**
	 * Sets the event id.
	 *
	 * @param eventId the new event id
	 */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	/**
	 * Gets the client id.
	 *
	 * @return the client id
	 */
	public int getClientId() {
		return clientId;
	}
	
	/**
	 * Sets the client id.
	 *
	 * @param clientId the new client id
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	/**
	 * Gets the condition.
	 *
	 * @return the condition
	 */
	public BetCondition getCondition() {
		return condition;
	}
	
	/**
	 * Sets the condition.
	 *
	 * @param condition the new condition
	 */
	public void setCondition(BetCondition condition) {
		this.condition = condition;
	}
	
	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	
	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	/**
	 * Gets the team 1 expected score.
	 *
	 * @return the team 1 expected score
	 */
	public int getTeam1ExpectedScore() {
		return team1ExpectedScore;
	}
	
	/**
	 * Sets the team 1 expected score.
	 *
	 * @param team1ExpectedScore the new team 1 expected score
	 */
	public void setTeam1ExpectedScore(int team1ExpectedScore) {
		this.team1ExpectedScore = team1ExpectedScore;
	}
	
	/**
	 * Gets the team 2 expected score.
	 *
	 * @return the team 2 expected score
	 */
	public int getTeam2ExpectedScore() {
		return team2ExpectedScore;
	}
	
	/**
	 * Sets the team 2 expected score.
	 *
	 * @param team2ExpectedScore the new team 2 expected score
	 */
	public void setTeam2ExpectedScore(int team2ExpectedScore) {
		this.team2ExpectedScore = team2ExpectedScore;
	}
	
	/**
	 * Gets the bet time.
	 *
	 * @return the bet time
	 */
	public Timestamp getBetTime() {
		return betTime;
	}
	
	/**
	 * Sets the bet time.
	 *
	 * @param betTime the new bet time
	 */
	public void setBetTime(Timestamp betTime) {
		this.betTime = betTime;
	}
	
	/**
	 * Gets the coeff.
	 *
	 * @return the coeff
	 */
	public double getCoeff() {
		return coeff;
	}
	
	/**
	 * Sets the coeff.
	 *
	 * @param coeff the new coeff
	 */
	public void setCoeff(double coeff) {
		this.coeff = coeff;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public BetState getState() {
		return state;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(BetState state) {
		this.state = state;
	}
	
	
	
	

}
