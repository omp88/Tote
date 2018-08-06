package by.epam.tote.command;


public enum CommandEnum {

	
	/** The add event. */
	ADD_EVENT {
		{
			this.command = new AddEventCommand();
		}
		
	},
	
	/** The add result. */
	ADD_RESULT {
		{
			this.command = new AddResultCommand();
		}
		
	},
	
	/** The add money. */
	ADD_MONEY {
		{
			this.command = new AddMoneyCommand();
		}
		
	},
	
	/** The find clients. */
	FIND_CLIENTS {
		{
			this.command = new FindClientsCommand();
		}
		
	},
	
	/** The find client bets. */
	FIND_CLIENT_BETS {
		{
			this.command = new FindClientBetsCommand();
		}
		
	},
	
	/** The find event bets. */
	FIND_EVENT_BETS {
		{
			this.command = new FindEventBetsCommand();
		}
		
	},
	
	/** The delete client. */
	DELETE_CLIENT {
		{
			this.command = new DeleteClientCommand();
		}
		
	},
	
	/** The delete bet. */
	DELETE_BET {
		{
			this.command = new DeleteBetCommand();
		}
		
	},
	
	/** The delete event. */
	DELETE_EVENT {
		{
			this.command = new DeleteEventCommand();
		}
		
	},
	
	/** The admin. */
	ADMIN {
		{
			this.command = new AdminCommand();
		}
		
	},
	
	/** The client info. */
	CLIENT_INFO {
		{
			this.command = new ClientInfoCommand();
		}
		
	},
	
	/** The welcome. */
	WELCOME {
		{
			this.command = new WelcomeCommand();
		}
		
	},
	
	/** The bet. */
	BET {
		{
			this.command = new BetCommand();
		}
		
	},
	
	/** The bet done. */
	BET_DONE {
		{
			this.command = new BetdoneCommand();
		}
		
	},
	
	/** The registered. */
	REGISTERED {
		{
			this.command = new RegisteredCommand();
		}
		
	},
	
	/** The authorized. */
	AUTHORIZED {
		{
			this.command = new AuthorizedCommand();
		}
		
	},
	
	/** The log in. */
	LOG_IN {
		{
			this.command = new LoginCommand();
		}
		
	},
	
	/** The log out. */
	LOG_OUT {
		{
			this.command = new LogoutCommand();
		}
		
	},
	
	/** The register. */
	REGISTER {
		{
			this.command = new RegisterCommand();
		}
	};

	/** The command. */
	ActionCommand command;

	/**
	 * Gets the current command.
	 *
	 * @return the current command
	 */
	public ActionCommand getCurrentCommand() {
		return command;
	}

}
