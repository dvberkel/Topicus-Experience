package nl.topicuszorg.experience.web.bmi.invoer.converter;

import static nl.topicuszorg.experience.domain.meting.Meting.meting;
import static nl.topicuszorg.experience.domain.meting.meetwaarde.Meetwaarde.meetwaarde;

import java.math.BigDecimal;
import java.util.Locale;

import nl.topicuszorg.experience.domain.meting.Meting;
import nl.topicuszorg.experience.domain.meting.eenheid.Eenheid;
import nl.topicuszorg.experience.web.bmi.invoer.converter.controle.GeheelGetalControle;
import nl.topicuszorg.experience.web.bmi.invoer.converter.controle.InputControle;

import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.convert.converters.AbstractConverter;

public abstract class AbstractMetingConverter extends AbstractConverter implements IConverter
{
	private static final long serialVersionUID = 37L;

	private InputControle inputControle = new GeheelGetalControle();;

	public AbstractMetingConverter()
	{
		this(new GeheelGetalControle());
	}

	public AbstractMetingConverter(InputControle inputControle)
	{
		if (inputControle == null)
		{
			throw new IllegalArgumentException("inputControle mag niet null zijn.");
		}
		this.inputControle = inputControle;
	}

	@Override
	public Object convertToObject(String value, Locale locale)
	{
		if (getControle().correcteInput(value))
		{
			return meting(meetwaarde(new BigDecimal(value)), eenheid());
		}
		else
		{
			throw newConversionException("geen correcte input", value, null);
		}
	}

	private InputControle getControle()
	{
		return inputControle;
	}

	protected abstract Eenheid eenheid();

	@Override
	public String convertToString(Object value, Locale locale)
	{
		return ((Meting<?>) value).getMeetwaarde().toString();
	}

}
