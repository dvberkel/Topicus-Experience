package nl.topicuszorg.experience.web.bmi.invoer.converter.controle;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

public class GebrokenGetalControleTest
{
	private GebrokenGetalControle gebrokenGetalControle;

	@Before
	public void gebrokenGetalControleAanmaken()
	{
		gebrokenGetalControle = new GebrokenGetalControle();
	}

	@Test
	public void gebrokenGetalControleKanAangemaaktWorden()
	{
		assertNotNull(gebrokenGetalControle);
	}

	@Test
	public void gebrokenGetalControleVindEenGeheelGetalGoed()
	{
		assertTrue(gebrokenGetalControle.correcteInput("37"));
		assertTrue(gebrokenGetalControle.correcteInput("-37"));
		assertTrue(gebrokenGetalControle.correcteInput("0"));
		assertFalse(gebrokenGetalControle.correcteInput("-0"));
	}

	@Test
	public void gebrokenGetalControleVindEenGebrokenGetalGoed()
	{
		assertTrue(gebrokenGetalControle.correcteInput("1.85"));
		assertTrue(gebrokenGetalControle.correcteInput("0.75"));
	}

	@Test
	public void gebrokenGetalControleVindEenGebrokenGetalZonderVoorloopCijfersGoed()
	{
		assertTrue(gebrokenGetalControle.correcteInput(".85"));
	}

	@Test
	public void gebrokenGetalControleVindAlleenEenPuntNietGoed()
	{
		assertFalse(gebrokenGetalControle.correcteInput("."));
	}

	@Test
	public void gebrokenGetalControleVindEenStringWaarEenGebrokenGetalInVoorkomtNietGoed()
	{
		assertFalse(gebrokenGetalControle.correcteInput("Dit is een getal 1.85"));
	}
}
