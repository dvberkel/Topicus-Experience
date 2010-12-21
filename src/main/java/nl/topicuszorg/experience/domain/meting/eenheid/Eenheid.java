package nl.topicuszorg.experience.domain.meting.eenheid;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class Eenheid implements Comparable<Eenheid>, Serializable
{
	private static final long serialVersionUID = 37L;

	private static Map<Class<? extends Eenheid>, Object> eenheden = createMap();

	private static <K, V> Map<K, V> createMap()
	{
		return new HashMap<K, V>();
	}

	@SuppressWarnings("unchecked")
	private static <T extends Eenheid> T geefEenheidVanType(Class<T> eenheidClass)
	{

		if (!eenheden.containsKey(eenheidClass))
		{
			try
			{
				eenheden.put(eenheidClass, eenheidClass.newInstance());
			}
			catch (InstantiationException e)
			{
				throw new IllegalStateException(e);
			}
			catch (IllegalAccessException e)
			{
				throw new IllegalStateException(e);
			}
		}
		return (T) eenheden.get(eenheidClass);
	}

	public static Gram gram()
	{
		return geefEenheidVanType(Gram.class);
	}

	public static Meter meter()
	{
		return geefEenheidVanType(Meter.class);
	}

	public static SamengesteldeEenheid samengesteld(Eenheid eenEenheid, Eenheid andereEenheid)
	{
		return new SamengesteldeEenheid(eenEenheid, andereEenheid);
	}

	public static QuotientEenheid gedeeld(Eenheid eenEenheid, Eenheid andereEenheid)
	{
		return new QuotientEenheid(eenEenheid, andereEenheid);
	}

	public Eenheid maal(Eenheid eenheid)
	{
		return samengesteld(this, eenheid);
	}

	public Eenheid gedeeldDoor(Eenheid eenheid)
	{
		return gedeeld(this, eenheid);
	}

	public Eenheid basisEenheid()
	{
		return this;
	}

	public BigDecimal vermenigvuldiger()
	{
		return BigDecimal.ONE;
	}

	@Override
	public int compareTo(Eenheid o)
	{
		return this.getClass().getName().compareTo(o.getClass().getName());
	}
}

final class SamengesteldeEenheid extends Eenheid
{
	private static final long serialVersionUID = 37L;

	final private List<Eenheid> eenheden = new ArrayList<Eenheid>();

	public SamengesteldeEenheid(Eenheid... eenheden)
	{
		this.eenheden.addAll(Arrays.asList(eenheden));
		Collections.sort(this.eenheden);
	}

	@Override
	public Eenheid basisEenheid()
	{
		Eenheid resultaat = null;
		for (Eenheid eenheid : eenheden)
		{
			if (resultaat == null)
			{
				resultaat = eenheid.basisEenheid();
			}
			else
			{
				resultaat = resultaat.maal(eenheid.basisEenheid());
			}
		}
		return resultaat;
	}

	@Override
	public BigDecimal vermenigvuldiger()
	{
		BigDecimal resultaat = null;
		for (Eenheid eenheid : eenheden)
		{
			if (resultaat == null)
			{
				resultaat = eenheid.vermenigvuldiger();
			}
			else
			{
				resultaat = resultaat.multiply(eenheid.vermenigvuldiger());
			}
		}
		return resultaat;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eenheden == null) ? 0 : eenheden.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SamengesteldeEenheid other = (SamengesteldeEenheid) obj;
		if (eenheden == null)
		{
			if (other.eenheden != null)
				return false;
		}
		else if (!eenheden.equals(other.eenheden))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		for (Eenheid eenheid : eenheden)
		{
			sb.append(eenheid);
		}
		return sb.toString();
	}
}

final class QuotientEenheid extends Eenheid
{
	private static final long serialVersionUID = 37L;

	private Eenheid teller;

	private Eenheid noemer;

	public QuotientEenheid(Eenheid teller, Eenheid noemer)
	{
		this.teller = teller;
		this.noemer = noemer;
	}

	@Override
	public Eenheid basisEenheid()
	{
		return teller.basisEenheid().gedeeldDoor(noemer.basisEenheid());
	}

	@Override
	public BigDecimal vermenigvuldiger()
	{
		return teller.vermenigvuldiger().divide(noemer.vermenigvuldiger());
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((noemer == null) ? 0 : noemer.hashCode());
		result = prime * result + ((teller == null) ? 0 : teller.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuotientEenheid other = (QuotientEenheid) obj;
		if (noemer == null)
		{
			if (other.noemer != null)
				return false;
		}
		else if (!noemer.equals(other.noemer))
			return false;
		if (teller == null)
		{
			if (other.teller != null)
				return false;
		}
		else if (!teller.equals(other.teller))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format("%s/%s", teller.toString(), noemer.toString());
	}
}