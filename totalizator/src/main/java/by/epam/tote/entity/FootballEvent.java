package by.epam.tote.entity;

import java.sql.Timestamp;

public class FootballEvent{
	
	/** The id. */
	private int id;
	
	/** The football name. */
	private String footballName;
	
	/** The start time. */
	private Timestamp startTime;
	
	/** The team 1 name. */
	private String team1Name;
	
	/** The team 2 name. */
	private String team2Name;
	
	/** The team 1 win coef. */
	private double team1WinCoef;
	
	/** The team 2 win coef. */
	private double team2WinCoef;
	
	/** The draw coef. */
	private double drawCoef;
	
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
	 * Gets the football name.
	 *
	 * @return the football name
	 */
	public String getFootballName() {
		return footballName;
	}
	
	/**
	 * Sets the football name.
	 *
	 * @param footballName the new football name
	 */
	public void setFootballName(String footballName) {
		this.footballName = footballName;
	}
	
	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public Timestamp getStartTime() {
		return startTime;
	}
	
	/**
	 * Sets the start time.
	 *
	 * @param startTime the new start time
	 */
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * Gets the team 1 name.
	 *
	 * @return the team 1 name
	 */
	public String getTeam1Name() {
		return team1Name;
	}
	
	/**
	 * Sets the team 1 name.
	 *
	 * @param team1Name the new team 1 name
	 */
	public void setTeam1Name(String team1Name) {
		this.team1Name = team1Name;
	}
	
	/**
	 * Gets the team 2 name.
	 *
	 * @return the team 2 name
	 */
	public String getTeam2Name() {
		return team2Name;
	}
	
	/**
	 * Sets the team 2 name.
	 *
	 * @param team2Name the new team 2 name
	 */
	public void setTeam2Name(String team2Name) {
		this.team2Name = team2Name;
	}
	
	/**
	 * Gets the team 1 win coef.
	 *
	 * @return the team 1 win coef
	 */
	public double getTeam1WinCoef() {
		return team1WinCoef;
	}
	
	/**
	 * Sets the team 1 win coef.
	 *
	 * @param team1WinCoef the new team 1 win coef
	 */
	public void setTeam1WinCoef(double team1WinCoef) {
		this.team1WinCoef = team1WinCoef;
	}
	
	/**
	 * Gets the team 2 win coef.
	 *
	 * @return the team 2 win coef
	 */
	public double getTeam2WinCoef() {
		return team2WinCoef;
	}
	
	/**
	 * Sets the team 2 win coef.
	 *
	 * @param team2WinCoef the new team 2 win coef
	 */
	public void setTeam2WinCoef(double team2WinCoef) {
		this.team2WinCoef = team2WinCoef;
	}
	
	/**
	 * Gets the draw coef.
	 *
	 * @return the draw coef
	 */
	public double getDrawCoef() {
		return drawCoef;
	}
	
	/**
	 * Sets the draw coef.
	 *
	 * @param drawCoef the new draw coef
	 */
	public void setDrawCoef(double drawCoef) {
		this.drawCoef = drawCoef;
	}
	
	
	

}
