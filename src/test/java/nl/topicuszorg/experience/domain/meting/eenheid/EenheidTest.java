package nl.topicuszorg.experience.domain.meting.eenheid;

import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.gram;
import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.meter;
import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.samengesteld;
import static nl.topicuszorg.experience.domain.meting.eenheid.voorvoegsel.Voorvoegsel.kilo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.math.BigDecimal;


import org.junit.Test;

public class EenheidTest
{
	@Test
	public void gramIsEenEenheid()
	{
		assertNotNull(gram());
	}

	@Test
	public void deEneGramIsPreciesDeAndereGram()
	{
		assertSame(gram(), gram());
	}

	@Test
	public void meterIsEenEenheid()
	{
		assertNotNull(meter());
	}

	@Test
	public void deEneMeterIsPreciesDeAndereMeter()
	{
		assertSame(meter(), meter());
	}

	@Test
	public void erZijnSamengesteldeEenheden()
	{
		assertNotNull(samengesteld(gram(), meter()));
	}

	@Test
	public void samenGesteldeEenhedenZijnCommutatief()
	{
		assertEquals(samengesteld(gram(), meter()), samengesteld(meter(), gram()));
	}

	@Test
	public void eenhedenKunnenWordenVermenigvuldigd()
	{
		assertEquals(samengesteld(gram(), meter()), gram().maal(meter()));
	}

	@Test
	public void eenhedenHebbenEenZinnigeToString()
	{
		assertEquals("g", gram().toString());
		assertEquals("m", meter().toString());
	}

	@Test
	public void samengesteldeEenhedenHebbenEenZinnigeToString()
	{
		assertEquals("gm", samengesteld(gram(), meter()).toString());
	}

	@Test
	public void eenhedenZijnSerialiseerbaar()
	{
		assertTrue(gram() instanceof Serializable);
	}

	@Test
	public void eenhedenKunnenGedeeldWorden()
	{
		assertEquals(Eenheid.gedeeld(gram(), meter()), gram().gedeeldDoor(meter()));
	}

	@Test
	public void eenhedenHebbenEenBasisEenheid()
	{
		assertEquals(gram(), gram().basisEenheid());
		assertEquals(meter(), meter().basisEenheid());
	}

	@Test
	public void eenhedenHebbenEenVermenigvuldiger()
	{
		assertEquals(BigDecimal.ONE, gram().vermenigvuldiger());
		assertEquals(BigDecimal.ONE, meter().vermenigvuldiger());
	}

	@Test
	public void gedeeldeEenhedenHebbenEenCorrectBasisEenheid()
	{
		assertEquals(gram().gedeeldDoor(meter()), kilo(gram()).gedeeldDoor(meter()).basisEenheid());
	}

	@Test
	public void samengesteldeEenhedenHebbenEenCorrectBasisEenheid()
	{
		assertEquals(gram().maal(meter()), kilo(gram()).maal(meter()).basisEenheid());
	}

	@Test
	public void samengesteldeEenhedenHebbenEenCorrectVermenigvuldiger()
	{
		assertEquals(BigDecimal.valueOf(1000000), kilo(gram()).maal(kilo(meter())).vermenigvuldiger());
	}

	@Test
	public void gedeeldeEenhedenHebbenEenCorrectVermenigvuldiger()
	{
		assertEquals(BigDecimal.valueOf(1000), kilo(gram()).gedeeldDoor(meter()).vermenigvuldiger());
	}
}
