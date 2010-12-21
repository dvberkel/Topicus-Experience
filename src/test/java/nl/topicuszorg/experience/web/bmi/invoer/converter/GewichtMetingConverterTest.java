package nl.topicuszorg.experience.web.bmi.invoer.converter;

import static nl.topicuszorg.experience.domain.meting.Meting.meting;
import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.gram;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import nl.topicuszorg.experience.web.bmi.invoer.converter.controle.GebrokenGetalControle;

import org.apache.wicket.util.convert.ConversionException;
import org.junit.Before;
import org.junit.Test;

public class GewichtMetingConverterTest
{
	private GewichtMetingConverter gewichtMetingConverter;

	@Before
	public void maakGewichtMetingConverter()
	{
		gewichtMetingConverter = new GewichtMetingConverter();
	}

	@Test
	public void gewichtMetingConverterKanAangemaaktWorden()
	{
		assertNotNull(gewichtMetingConverter);
	}

	@Test
	public void converterenNaarObjectLevertEenMetingOp()
	{
		assertEquals(meting(37, gram()), gewichtMetingConverter.convertToObject("37", null));
	}

	@Test
	public void converterenNaarStringLevertGoedeRepresentatie()
	{
		assertEquals("51", gewichtMetingConverter.convertToString(meting(51, gram()), null));
	}

	@Test(expected = ConversionException.class)
	public void hetConverterenVanIetsAndersDanEenGetalGooitEenExceptie()
	{
		gewichtMetingConverter.convertToObject("een string", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void gewichtMetingConverterKanNietAangemaaktWordenMetEenNullInputControle()
	{
		new GewichtMetingConverter(null);
	}

	@Test
	public void gewichtMetingConverterMetEenGebrokenGetallenConverterLevertEenMetingOp()
	{
		gewichtMetingConverter = new GewichtMetingConverter(new GebrokenGetalControle());
		assertEquals(meting(1.85, gram()), gewichtMetingConverter.convertToObject("1.85", null));
	}
}
