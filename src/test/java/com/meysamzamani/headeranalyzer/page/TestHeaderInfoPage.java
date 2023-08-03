package com.meysamzamani.headeranalyzer.page;

import com.meysamzamani.headeranalyzer.application.WicketApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHeaderInfoPage
{
	private WicketTester tester;

	@BeforeEach
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void headerInfoPageRendersSuccessfully()
	{
		tester.startPage(HeaderInfoPage.class);

		tester.assertRenderedPage(HeaderInfoPage.class);
	}
}
