package nl.topicuszorg.experience.domain.meting.eenheid.voorvoegsel;

import java.math.BigDecimal;

import nl.topicuszorg.experience.domain.meting.eenheid.Eenheid;

public class Voorvoegsel<T extends Eenheid> extends Eenheid
{
	private static final long serialVersionUID = 37L;

	private T eenheid;

	public static <E extends Eenheid> Voorvoegsel<E> kilo(E eenheid)
	{
		return new Voorvoegsel<E>(eenheid);
	}

	private Voorvoegsel(T eenheid)
	{
		this.eenheid = eenheid;
	}

	@Override
	public Eenheid basisEenheid()
	{
		return eenheid.basisEenheid();
	}

	@Override
	public BigDecimal vermenigvuldiger()
	{
		return BigDecimal.valueOf(1000);
	}

	@Override
	public String toString()
	{
		return String.format("k%s", eenheid);
	}
}