package nl.topicuszorg.experience.domain.meting.eenheid.voorvoegsel;

import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.gram;
import static nl.topicuszorg.experience.domain.meting.eenheid.voorvoegsel.Voorvoegsel.kilo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;

public class VoorvoegselTest
{
	@Test
	public void kiloIsEenVoorVoegsel()
	{
		assertNotNull(kilo(gram()));
	}

	@Test
	public void basisEenheidVanKilogramIsGram()
	{
		assertEquals(gram(), kilo(gram()).basisEenheid());
	}

	@Test
	public void vermenigvuldigerVanKilogramIs1000()
	{
		assertEquals(BigDecimal.valueOf(1000), kilo(gram()).vermenigvuldiger());
	}
}
