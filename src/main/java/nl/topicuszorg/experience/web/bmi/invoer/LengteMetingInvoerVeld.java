package nl.topicuszorg.experience.web.bmi.invoer;

import static nl.topicuszorg.experience.domain.meting.Meting.meting;
import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.meter;
import nl.topicuszorg.experience.domain.meting.Meting;
import nl.topicuszorg.experience.domain.meting.eenheid.Meter;
import nl.topicuszorg.experience.web.bmi.invoer.converter.LengteMetingConverter;
import nl.topicuszorg.experience.web.bmi.invoer.converter.controle.GebrokenGetalControle;

import org.apache.wicket.util.convert.IConverter;

public class LengteMetingInvoerVeld extends AbstractMetingInvoerVeld<Meter>
{
	private static final long serialVersionUID = 37L;

	public LengteMetingInvoerVeld(String id)
	{
		super(id);
	}

	@Override
	protected Meting<Meter> getMinimumMeting()
	{
		return meting(0, meter());
	}

	@Override
	public IConverter getConverter(Class<?> type)
	{
		return new LengteMetingConverter(new GebrokenGetalControle());
	}
}
