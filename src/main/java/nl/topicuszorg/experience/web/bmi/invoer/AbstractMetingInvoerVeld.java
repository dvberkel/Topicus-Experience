package nl.topicuszorg.experience.web.bmi.invoer;

import nl.topicuszorg.experience.domain.meting.Meting;
import nl.topicuszorg.experience.domain.meting.eenheid.Eenheid;
import nl.topicuszorg.experience.web.bmi.invoer.validator.MetingValidator;

import org.apache.wicket.markup.html.form.TextField;

public abstract class AbstractMetingInvoerVeld<T extends Eenheid> extends TextField<Meting<T>>
{

	private static final long serialVersionUID = 37L;

	public AbstractMetingInvoerVeld(String id)
	{
		super(id);
		setRequired(true);
		add(MetingValidator.minimaal(getMinimumMeting()));
	}

	protected abstract Meting<T> getMinimumMeting();
}