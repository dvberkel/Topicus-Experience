package nl.topicuszorg.experience.web.bmi.invoer;

import static org.junit.Assert.assertNotNull;
import nl.topicuszorg.experience.web.bmi.BMIPanel;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class BMIFormTest
{
	private WicketTester tester;

	private FormTester formTester;

	@Before
	public void maakEenWicketTester()
	{
		tester = new WicketTester();
		tester.startPanel(BMIPanel.class);
		formTester = tester.newFormTester("panel:bmiform");
	}

	@Test
	public void testerKanAangemaaktWorden()
	{
		assertNotNull(tester);
	}

	@Test
	public void formtesterKanAangemaaktWorden()
	{
		assertNotNull(formTester);
	}

	@Test
	public void legeGewichtMetingIsNietCorrect()
	{
		formTester.setValue("gewichtMeting", "");
		formTester.submit();

		tester.assertErrorMessages(new String[] { "gewichtMeting is verplicht." });
	}

	@Test
	public void negatiefGewichtMetingIsNietCorrect()
	{
		formTester.setValue("gewichtMeting", "-1");
		formTester.submit();

		tester.assertErrorMessages(new String[] { "gewichtMeting moet minimaal 0.00g zijn. -1g voldoet niet." });
	}

	@Test
	public void geenGetalGewichtMetingIsNietCorrect()
	{
		formTester.setValue("gewichtMeting", "Dit is een getal: 37");
		formTester.submit();

		tester.assertErrorMessages(new String[] { "'Dit is een getal: 37' is geen valide gewichtMeting." });
	}

	@Test
	public void legeLengteMetingIsNietCorrect()
	{
		formTester.setValue("lengteMeting", "");
		formTester.submit();

		tester.assertErrorMessages(new String[] { "lengte is verplicht." });
	}

	@Test
	public void negatiefLengteMetingIsNietCorrect()
	{
		formTester.setValue("lengteMeting", "-1");
		formTester.submit();

		tester.assertErrorMessages(new String[] { "lengte moet minimaal 0.00m zijn. -1m voldoet niet." });
	}

	@Test
	public void geenGetalLengteMetingIsNietCorrect()
	{
		formTester.setValue("lengteMeting", "Dit is een getal: 51");
		formTester.submit();

		tester.assertErrorMessages(new String[] { "'Dit is een getal: 51' is geen valide lengte." });
	}

	@Test
	public void correcteInvoerProduceertGeenErrors()
	{
		formTester.setValue("gewichtMeting", "80000");
		formTester.setValue("lengteMeting", "1.85");
		formTester.submit();

		tester.assertNoErrorMessage();
	}

	@Test
	public void correcteInvoerProduceertCorrectBMI()
	{
		formTester.setValue("gewichtMeting", "80000");
		formTester.setValue("lengteMeting", "1.85");
		formTester.submit();

		tester.assertLabel("panel:bmiform:bmi", "23.37kg/mm");
	}
}
