package nl.topicuszorg.experience.web.bmi.invoer.validator;

import java.util.Map;

import nl.topicuszorg.experience.domain.meting.Meting;
import nl.topicuszorg.experience.domain.meting.eenheid.Eenheid;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;

public class MetingValidator<T extends Eenheid> extends AbstractValidator<Meting<T>>
{
	private static final long serialVersionUID = 37L;

	public static <E extends Eenheid> MetingValidator<E> minimaal(Meting<E> meting)
	{
		return new MetingValidator<E>(meting);
	}

	private Meting<T> minimum;

	private MetingValidator(Meting<T> minimum)
	{
		this.minimum = minimum;
	}

	@Override
	protected void onValidate(IValidatable<Meting<T>> validatable)
	{
		Meting<T> value = validatable.getValue();
		if (value.compareTo(minimum) < 0)
		{
			error(validatable);
		}
	}

	@Override
	protected Map<String, Object> variablesMap(IValidatable<Meting<T>> validatable)
	{
		Map<String, Object> map = super.variablesMap(validatable);
		map.put("minimum", minimum);
		map.put("waarde", validatable.getValue());
		return map;
	}
}
