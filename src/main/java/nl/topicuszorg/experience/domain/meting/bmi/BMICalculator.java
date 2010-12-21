package nl.topicuszorg.experience.domain.meting.bmi;

import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.gram;
import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.meter;
import static nl.topicuszorg.experience.domain.meting.eenheid.voorvoegsel.Voorvoegsel.kilo;
import nl.topicuszorg.experience.domain.meting.Meting;
import nl.topicuszorg.experience.domain.meting.bmi.builder.BMICalculatorBuilder;
import nl.topicuszorg.experience.domain.meting.bmi.builder.CurriedBMICalculatorBuilder;
import nl.topicuszorg.experience.domain.meting.eenheid.Gram;
import nl.topicuszorg.experience.domain.meting.eenheid.Meter;

public final class BMICalculator
{

	private final Meting<Gram> gewichtMeting;

	private final Meting<Meter> lengteMeting;

	public static BMICalculatorBuilder bereken()
	{
		return new BMICalculatorBuilder();
	}

	public BMICalculator(CurriedBMICalculatorBuilder curriedBMICalculatorBuilder)
	{
		this.gewichtMeting = curriedBMICalculatorBuilder.getGewichtMeting();
		this.lengteMeting = curriedBMICalculatorBuilder.getLengteMeting();
	}

	public Meting<?> bmi()
	{
		Meting<?> gewichtMetingInKg = gewichtMeting.rekenOmNaar(kilo(gram()));
		Meting<?> lengteMetingInM = lengteMeting.rekenOmNaar(meter());
		return gewichtMetingInKg.gedeeldDoor(lengteMetingInM.maal(lengteMetingInM));
	}
}