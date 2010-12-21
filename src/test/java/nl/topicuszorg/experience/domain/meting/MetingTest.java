package nl.topicuszorg.experience.domain.meting;

import static nl.topicuszorg.experience.domain.meting.Meting.meting;
import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.gram;
import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.meter;
import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.samengesteld;
import static nl.topicuszorg.experience.domain.meting.eenheid.voorvoegsel.Voorvoegsel.kilo;
import static nl.topicuszorg.experience.domain.meting.meetwaarde.Meetwaarde.meetwaarde;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import nl.topicuszorg.experience.domain.meting.eenheid.Gram;
import nl.topicuszorg.experience.domain.meting.eenheid.Meter;

import org.junit.Test;

public class MetingTest
{
	@Test
	public void metingKanWordenAangemaakt()
	{
		assertNotNull(meting(37, gram()));
	}

	@Test
	public void metingZijnGelijkBijGelijkeWaardeEnEenheid()
	{
		assertEquals(meting(37, gram()), meting(37, gram()));
	}

	@Test
	public void metingZijnOngelijkBijOngelijkeWaarde()
	{
		assertFalse(meting(37, gram()).equals(meting(51, gram())));
	}

	@Test
	public void metingZijnOngelijkBijOngelijkeEenheden()
	{
		assertFalse(meting(37, gram()).equals(meting(37, meter())));
	}

	@Test
	public void metingenKunnenWordenVermenigvuldigd()
	{
		assertEquals(meting(6, samengesteld(gram(), meter())), meting(2, gram()).maal(meting(3, meter())));
	}

	@Test
	public void metingenHebbenZinnigeToString()
	{
		assertEquals("37g", meting(37, gram()).toString());
		assertEquals("51m", meting(51, meter()).toString());
	}

	@Test
	public void specifiekeMetingenKunnenWordenGedeclareerd()
	{
		Meting<Gram> gewichtMeting = meting(37, gram());
		Meting<Meter> lengteMeting = meting(51, meter());

		assertNotNull(gewichtMeting);
		assertNotNull(lengteMeting);
	}

	@Test
	public void metingenZijnSerialiseerbaar()
	{
		assertTrue(meting(37, gram()) instanceof Serializable);
	}

	@Test
	public void metingenKunnenOokNietGeheelZijn()
	{
		assertNotNull(meting(1.8, meter()));
	}

	@Test
	public void metingenKunnenGedeeldWorden()
	{
		assertEquals(meting(meetwaarde(37).gedeeldDoor(meetwaarde(51)), gram().gedeeldDoor(meter())),
			meting(37, gram()).gedeeldDoor(meting(51, meter())));
	}

	@Test
	public void metingenKunnenWordenVergeleken()
	{
		assertTrue(meting(37, gram()) instanceof Comparable<?>);
	}

	@Test
	public void metingenMetKleinereMeetwaardeZijnKleiner()
	{
		assertTrue(meting(37, gram()).compareTo(meting(51, gram())) < 0);
	}

	@Test
	public void metingenMetEenVoorvoegselKunnenGelijkZijnAanMetingenZonderVoorvoegsel()
	{
		assertTrue(meting(3700, gram()).equals(meting(3.7, kilo(gram()))));
	}

	@Test
	public void metingenMetEenVoorvoegselWordenVergeleken()
	{
		assertTrue(meting(51, gram()).compareTo(meting(37, kilo(gram()))) < 0);
	}

	@Test
	public void metingenKunnenOmgerekendWordenNaarAndereVoorvoegsels()
	{
		assertEquals(meting(1, kilo(gram())).getMeetwaarde(), meting(1000, gram()).rekenOmNaar(kilo(gram()))
			.getMeetwaarde());
	}

	@Test(expected = IllegalArgumentException.class)
	public void metingenKunnenAlleenOmgerekendWordenNaarDezelfdeBasisEenheid()
	{
		meting(1, kilo(gram())).rekenOmNaar(meter());
	}
}
