package by.epam.tote.dao;


import by.epam.tote.pool.ProxyConnection;

public class AbstractDao  {
	
	/** The proxy. */
	private ProxyConnection proxy;

	/**
	 * Gets the proxy.
	 *
	 * @return the proxy
	 */
	public ProxyConnection getProxy() {
		return proxy;
	}

	/**
	 * Sets the proxy.
	 *
	 * @param proxy the new proxy
	 */
	public void setProxy(ProxyConnection proxy) {
		this.proxy = proxy;
	}

	
	

}
