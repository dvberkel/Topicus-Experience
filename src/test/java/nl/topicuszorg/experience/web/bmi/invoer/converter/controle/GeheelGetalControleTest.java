package nl.topicuszorg.experience.web.bmi.invoer.converter.controle;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

public class GeheelGetalControleTest
{
	private GeheelGetalControle controle;

	@Before
	public void maakGeheelGetalControle()
	{
		controle = new GeheelGetalControle();
	}

	@Test
	public void geheelGetalControleKanWordenAangemaakt()
	{
		assertNotNull(controle);
	}

	@Test
	public void geheelGetalControleVindGeheleGetallenCorrect()
	{
		assertTrue(controle.correcteInput("37"));
		assertTrue(controle.correcteInput("51"));
	}

	@Test
	public void geheelGetalControleVindNegatieveGeheleGetallenCorrect()
	{
		assertTrue(controle.correcteInput("-37"));
		assertTrue(controle.correcteInput("-51"));
	}

	@Test
	public void geheelGetalControleVindNulCorrect()
	{
		assertTrue(controle.correcteInput("0"));
	}

	@Test
	public void geheelGetalControleVindMinNulNietCorrect()
	{
		assertFalse(controle.correcteInput("-0"));
	}

	@Test
	public void geheelGetalControleVindKommaGetallenNietCorrect()
	{
		assertFalse(controle.correcteInput("3.7"));
		assertFalse(controle.correcteInput("5.1"));
	}

	@Test
	public void geheelGetalControleVindStringWaarGeheleGetallenInVoorkomtNietCorrect()
	{
		assertFalse(controle.correcteInput("Dit is niet correct 37"));
	}
}
