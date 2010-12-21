package nl.topicuszorg.experience.domain.meting.bmi.builder;

import nl.topicuszorg.experience.domain.meting.Meting;
import nl.topicuszorg.experience.domain.meting.eenheid.Gram;

public final class BMICalculatorBuilder
{

	public CurriedBMICalculatorBuilder met(Meting<Gram> gewichtMeting)
	{
		return new CurriedBMICalculatorBuilder(gewichtMeting);
	}

}
