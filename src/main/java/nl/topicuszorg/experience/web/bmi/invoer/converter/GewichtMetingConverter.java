package nl.topicuszorg.experience.web.bmi.invoer.converter;

import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.gram;
import nl.topicuszorg.experience.domain.meting.Meting;
import nl.topicuszorg.experience.domain.meting.eenheid.Eenheid;
import nl.topicuszorg.experience.web.bmi.invoer.converter.controle.InputControle;

public class GewichtMetingConverter extends AbstractMetingConverter
{
	private static final long serialVersionUID = 37L;

	public GewichtMetingConverter()
	{
		super();
	}

	public GewichtMetingConverter(InputControle inputControle)
	{
		super(inputControle);
	}

	@Override
	protected Eenheid eenheid()
	{
		return gram();
	}

	@Override
	protected Class<?> getTargetType()
	{
		return Meting.class;
	}

}
