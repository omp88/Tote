package by.epam.tote.router;


public class Router {
	
	/** The type. */
	private RouteType type;
	
	/** The route. */
	private String route;
	
	/**
	 * Instantiates a new router.
	 *
	 * @param type the type
	 * @param route the route
	 */
	public Router(RouteType type, String route) {
		super();
		this.type = type;
		this.route = route;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public RouteType getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(RouteType type) {
		this.type = type;
	}
	
	/**
	 * Gets the route.
	 *
	 * @return the route
	 */
	public String getRoute() {
		return route;
	}
	
	/**
	 * Sets the route.
	 *
	 * @param route the new route
	 */
	public void setRoute(String route) {
		this.route = route;
	}
	
	

}
