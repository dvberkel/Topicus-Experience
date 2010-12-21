package nl.topicuszorg.experience.domain.meting.bmi.builder;

import nl.topicuszorg.experience.domain.meting.Meting;
import nl.topicuszorg.experience.domain.meting.bmi.BMICalculator;
import nl.topicuszorg.experience.domain.meting.eenheid.Gram;
import nl.topicuszorg.experience.domain.meting.eenheid.Meter;

public final class CurriedBMICalculatorBuilder
{

	final private Meting<Gram> gewichtMeting;

	private Meting<Meter> lengteMeting;

	public CurriedBMICalculatorBuilder(Meting<Gram> gewichtMeting)
	{
		gooiExceptieAlsParameterNullIs(gewichtMeting, "gewichtMeting");
		this.gewichtMeting = gewichtMeting;
	}

	private void gooiExceptieAlsParameterNullIs(Meting<?> parameter, String parameterNaam)
	{
		if (parameter == null)
		{
			throw new IllegalArgumentException(String.format("%s mag niet null zijn.", "lengteMeting"));
		}
	}

	public BMICalculator en(Meting<Meter> lengteMeting)
	{
		gooiExceptieAlsParameterNullIs(lengteMeting, "lengteMeting");
		this.lengteMeting = lengteMeting;
		return new BMICalculator(this);
	}

	public Meting<Gram> getGewichtMeting()
	{
		return gewichtMeting;
	}

	public Meting<Meter> getLengteMeting()
	{
		return lengteMeting;
	}

}
