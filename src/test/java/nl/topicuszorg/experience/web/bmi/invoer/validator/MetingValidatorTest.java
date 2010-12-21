package nl.topicuszorg.experience.web.bmi.invoer.validator;

import static nl.topicuszorg.experience.domain.meting.Meting.meting;
import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.gram;
import static nl.topicuszorg.experience.web.bmi.invoer.validator.MetingValidator.minimaal;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import nl.topicuszorg.experience.domain.meting.Meting;
import nl.topicuszorg.experience.domain.meting.eenheid.Gram;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MetingValidatorTest
{
	private MetingValidator<Gram> metingValidator;

	@Before
	public void maakMinimaalMetingValidator()
	{
		metingValidator = minimaal(meting(0, gram()));
	}

	@Test
	public void minimaalMetingValidatorKanAangemaaktWorden()
	{
		assertNotNull(metingValidator);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void minimumMetingValidatorStaatGrotereMetingToe()
	{
		IValidatable<Meting<Gram>> validatable = mock(IValidatable.class);
		when(validatable.getValue()).thenReturn(meting(1, gram()));

		metingValidator.validate(validatable);

		verify(validatable, atLeastOnce()).getValue();
		verify(validatable, never()).error(Mockito.any(IValidationError.class));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void minimumMetingValidatorStaatKleinereMetingNietToe()
	{
		IValidatable<Meting<Gram>> validatable = mock(IValidatable.class);
		when(validatable.getValue()).thenReturn(meting(-1, gram()));

		metingValidator.validate(validatable);

		verify(validatable, atLeastOnce()).getValue();
		verify(validatable).error(any(IValidationError.class));
	}
}
