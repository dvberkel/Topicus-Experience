package nl.topicuszorg.experience.domain.meting.meetwaarde;

import static nl.topicuszorg.experience.domain.meting.meetwaarde.Meetwaarde.meetwaarde;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.math.BigDecimal;

import org.junit.Test;

public class MeetWaardeTest
{
	@Test
	public void meetwaardeKunnenAangemaaktWorden()
	{
		assertNotNull(meetwaarde(37));
	}

	@Test
	public void meetwaardeZijnGelijkBijGelijkeWaarden()
	{
		assertEquals(meetwaarde(37), meetwaarde(37));
	}

	@Test
	public void meetwaardeZijnOnGelijkBijOnGelijkeWaarden()
	{
		assertFalse(meetwaarde(37).equals(meetwaarde(51)));
	}

	@Test
	public void meetwaardeKunnenVermenigvuldigdWorden()
	{
		assertEquals(meetwaarde(6), meetwaarde(2).maal(meetwaarde(3)));
	}

	@Test
	public void meetwaardeHebbenZinnigeToString()
	{
		assertEquals("37", meetwaarde(37).toString());
	}

	@Test
	public void meetwaardenZijnSerialiseerbaar()
	{
		assertTrue(meetwaarde(37) instanceof Serializable);
	}

	@Test
	public void meetwaardenKunnenNietGeheelZijn()
	{
		assertNotNull(meetwaarde(1.8));
	}

	@Test
	public void nietGeheleMeetwaardenZijnGelijkBijGelijkeWaarden()
	{
		assertEquals(meetwaarde(1.8), meetwaarde(1.8));
	}

	@Test
	public void nietGeheleMeetwaardenZijnOngelijkBijOngelijkWaarden()
	{
		assertFalse(meetwaarde(1.8).equals(meetwaarde(1.85)));
	}

	@Test
	public void nietGeheleMeetwaardenKunnenVermenigVuldigdWorden()
	{
		assertEquals(meetwaarde(0.01), meetwaarde(0.1).maal(meetwaarde(0.1)));
	}

	@Test
	public void nietGeheleMeetwaardenHebbenZinnigeToString()
	{
		assertEquals("1.80", meetwaarde(1.8).toString());
		assertEquals("1.85", meetwaarde(1.85).toString());
	}

	@Test
	public void meetwaardenKunnenGedeeldWorden()
	{
		assertEquals(meetwaarde(6 / 3), meetwaarde(6).gedeeldDoor(meetwaarde(3)));
		assertEquals(meetwaarde(2d / 3d), meetwaarde(2).gedeeldDoor(meetwaarde(3)));
	}

	@Test
	public void meetwaardenKunnenBigDecimalZijn()
	{
		assertNotNull(meetwaarde(BigDecimal.valueOf(37)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void meetwaardenKunnenNietNullZijn()
	{
		meetwaarde(null);
	}

	@Test
	public void meetwaardenKunnenVergelijkenWorden()
	{
		assertTrue(meetwaarde(1) instanceof Comparable<?>);
	}

	@Test
	public void meetwaardenVanKleinereWaardeZijnKleiner()
	{
		assertTrue(meetwaarde(1).compareTo(meetwaarde(2)) < 0);
		assertTrue(meetwaarde(1).compareTo(meetwaarde(1.1)) < 0);
	}

	@Test
	public void meetwaardenKunnenVermenigvuldigWordenMetBigDecimals()
	{
		assertEquals(meetwaarde(1000), meetwaarde(1).maal(BigDecimal.valueOf(1000)));
	}
}
