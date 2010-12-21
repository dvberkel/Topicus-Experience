package nl.topicuszorg.experience.web;

import nl.topicuszorg.experience.web.bmi.BMIPanel;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class BMIPageTest
{
	private WicketTester tester;

	@Before
	public void maakEenWicketTester()
	{
		tester = new WicketTester();
	}

	@Test
	public void testRenderMyPage()
	{
		tester.startPage(BMIPage.class);
		tester.assertNoErrorMessage();

		tester.assertComponent("bmipanel", BMIPanel.class);
	}

}
