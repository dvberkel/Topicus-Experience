package nl.topicuszorg.experience.web;

import nl.topicuszorg.experience.web.bmi.BMIPanel;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;

public class BMIPage extends WebPage
{
	public BMIPage(final PageParameters pageParameters)
	{
		add(new BMIPanel("bmipanel"));
	}
}
