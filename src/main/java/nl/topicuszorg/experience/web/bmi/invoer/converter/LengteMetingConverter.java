package nl.topicuszorg.experience.web.bmi.invoer.converter;

import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.meter;
import nl.topicuszorg.experience.domain.meting.Meting;
import nl.topicuszorg.experience.domain.meting.eenheid.Eenheid;
import nl.topicuszorg.experience.web.bmi.invoer.converter.controle.InputControle;

public class LengteMetingConverter extends AbstractMetingConverter
{
	private static final long serialVersionUID = 37L;

	public LengteMetingConverter()
	{
		super();
	}

	public LengteMetingConverter(InputControle inputControle)
	{
		super(inputControle);
	}

	@Override
	protected Eenheid eenheid()
	{
		return meter();
	}

	@Override
	protected Class<?> getTargetType()
	{
		return Meting.class;
	}

}
