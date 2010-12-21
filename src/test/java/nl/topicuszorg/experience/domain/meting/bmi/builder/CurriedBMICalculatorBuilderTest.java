package nl.topicuszorg.experience.domain.meting.bmi.builder;

import static nl.topicuszorg.experience.domain.meting.Meting.meting;
import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.gram;
import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.meter;
import static org.junit.Assert.assertNotNull;


import org.junit.Test;

public class CurriedBMICalculatorBuilderTest
{
	@Test(expected = IllegalArgumentException.class)
	public void maakEenCurriedBMICalculatorMetNullGewichtMetingFaalt()
	{
		new CurriedBMICalculatorBuilder(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void eenCurriedBMICalculatorEnEenNullLengteMetingFaalt()
	{
		new CurriedBMICalculatorBuilder(meting(37, gram())).en(null);
	}

	@Test
	public void eenCurriedBMICalculatorEnEenLengteMetingLevertEenBMICalculator()
	{
		assertNotNull(new CurriedBMICalculatorBuilder(meting(37, gram())).en(meting(51, meter())));
	}
}
