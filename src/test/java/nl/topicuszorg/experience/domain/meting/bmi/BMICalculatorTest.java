package nl.topicuszorg.experience.domain.meting.bmi;

import static nl.topicuszorg.experience.domain.meting.Meting.meting;
import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.gedeeld;
import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.gram;
import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.meter;
import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.samengesteld;
import static nl.topicuszorg.experience.domain.meting.eenheid.voorvoegsel.Voorvoegsel.kilo;
import static nl.topicuszorg.experience.domain.meting.meetwaarde.Meetwaarde.meetwaarde;
import static org.junit.Assert.assertEquals;
import nl.topicuszorg.experience.domain.meting.Meting;
import nl.topicuszorg.experience.domain.meting.eenheid.Gram;
import nl.topicuszorg.experience.domain.meting.eenheid.Meter;
import nl.topicuszorg.experience.domain.meting.meetwaarde.Meetwaarde;

import org.junit.Before;
import org.junit.Test;

public class BMICalculatorTest
{
	private Meting<Gram> gewichtMeting;

	private Meting<Meter> lengteMeting;

	@Before
	public void maakGewichtMeting()
	{
		gewichtMeting = meting(80000, gram());
	}

	@Before
	public void maakLengteMeting()
	{
		lengteMeting = meting(1.85, meter());
	}

	@Test
	public void berekenBMI()
	{
		Meetwaarde verwachteMeetwaarde = meetwaarde(80).gedeeldDoor(meetwaarde(1.85).maal(meetwaarde(1.85)));
		assertEquals(meting(verwachteMeetwaarde, gedeeld(kilo(gram()), samengesteld(meter(), meter()))), BMICalculator
			.bereken().met(gewichtMeting).en(lengteMeting).bmi());
	}
}
