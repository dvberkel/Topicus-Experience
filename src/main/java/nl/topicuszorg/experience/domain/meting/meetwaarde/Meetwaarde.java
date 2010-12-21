package nl.topicuszorg.experience.domain.meting.meetwaarde;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Meetwaarde implements Serializable, Comparable<Meetwaarde>
{
	private static final long serialVersionUID = 37L;

	public static Meetwaarde meetwaarde(int waarde)
	{
		return meetwaarde(BigDecimal.valueOf(waarde));
	}

	public static Meetwaarde meetwaarde(double waarde)
	{
		return meetwaarde(BigDecimal.valueOf(waarde));
	}

	public static Meetwaarde meetwaarde(BigDecimal waarde)
	{
		if (waarde == null)
		{
			throw new IllegalArgumentException("waarde mag niet null zijn");
		}
		return new Meetwaarde(waarde);
	}

	private final BigDecimal waarde;

	private Meetwaarde(BigDecimal waarde)
	{
		this.waarde = waarde.setScale(8, RoundingMode.HALF_DOWN);
	}

	public Meetwaarde maal(Meetwaarde meetwaarde)
	{
		return new Meetwaarde(waarde.multiply(meetwaarde.waarde));
	}

	public Meetwaarde maal(BigDecimal vermenigvuldiger)
	{
		if (vermenigvuldiger == null)
		{
			throw new IllegalArgumentException("vermenigvuldiger mag niet null zijn");
		}
		return new Meetwaarde(waarde.multiply(vermenigvuldiger));
	}

	public Meetwaarde gedeeldDoor(Meetwaarde meetwaarde)
	{
		return new Meetwaarde(waarde.divide(meetwaarde.waarde, BigDecimal.ROUND_HALF_DOWN));
	}

	@Override
	public int hashCode()
	{
		return waarde.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Meetwaarde))
			return false;
		Meetwaarde other = (Meetwaarde) obj;
		return this.waarde.equals(other.waarde);
	}

	@Override
	public String toString()
	{
		return getFormatter().format(waarde);
	}

	private DecimalFormat getFormatter()
	{
		DecimalFormat formatter = null;
		if (isGeheel())
		{
			formatter = new DecimalFormat("#0");
		}
		else
		{
			formatter = new DecimalFormat("#0.00");
		}
		return formatter;
	}

	private boolean isGeheel()
	{
		return waarde.stripTrailingZeros().scale() <= 0;
	}

	@Override
	public int compareTo(Meetwaarde andereMeetwaarde)
	{
		return this.waarde.compareTo(andereMeetwaarde.waarde);
	}
}