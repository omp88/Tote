package by.epam.tote.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import by.epam.tote.pool.ConnectionPool;


@WebListener
public class ServletContexListenerImpl implements ServletContextListener {
	
	
	/**
	 * Creating Connection Pool
	 */
    @Override
    public void contextInitialized(ServletContextEvent ev) {
        ServletContext context = ev.getServletContext();

        ConnectionPool.getInstance();
        context.log("Connection Pool Initioalized");
       
    }

    /**
	 * Destroying Connection Pool
	 */
    @Override
    public void contextDestroyed(ServletContextEvent ev) {
        ServletContext context = ev.getServletContext();

        ConnectionPool.getInstance().destroy();
        context.log("Connection Pool destroyed");
        
    }
}

