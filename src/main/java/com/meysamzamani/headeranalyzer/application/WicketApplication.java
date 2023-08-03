package com.meysamzamani.headeranalyzer.application;

import com.meysamzamani.headeranalyzer.page.HeaderInfoPage;
import org.apache.wicket.csp.CSPDirective;
import org.apache.wicket.csp.CSPDirectiveSrcValue;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;


/**
 * The WicketApplication class represents the entry point of the Wicket web application. It extends the WebApplication
 * class provided by Wicket and is responsible for defining the application's configuration, including the home page and
 * other initialization settings.
 */
public class WicketApplication extends WebApplication
{

	/**
	 * Retrieves the home page class that will be displayed when the application is launched.
	 *
	 * @return the home page class for the application.
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HeaderInfoPage.class;
	}

	/**
	 * Initializes the Wicket application by calling the super.init() method and configuring additional settings.
	 * In this implementation, the Content Security Policy (CSP) settings are updated to allow only self-hosted styles.
	 * This enhances security by specifying which resources the application is allowed to load and execute.
	 */
	@Override
	public void init()
	{
		super.init();
		getCspSettings().blocking().add(CSPDirective.STYLE_SRC, CSPDirectiveSrcValue.SELF);
	}
}
