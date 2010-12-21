package nl.topicuszorg.experience.web.bmi;

import static nl.topicuszorg.experience.domain.meting.Meting.meting;
import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.gram;
import static nl.topicuszorg.experience.domain.meting.eenheid.Eenheid.meter;
import nl.topicuszorg.experience.domain.meting.Meting;
import nl.topicuszorg.experience.domain.meting.bmi.BMICalculator;
import nl.topicuszorg.experience.domain.meting.eenheid.Gram;
import nl.topicuszorg.experience.domain.meting.eenheid.Meter;
import nl.topicuszorg.experience.web.bmi.invoer.GewichtMetingInvoerVeld;
import nl.topicuszorg.experience.web.bmi.invoer.LengteMetingInvoerVeld;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

public class BMIPanel extends Panel
{
	private static final long serialVersionUID = 37L;

	public BMIPanel(String id)
	{
		super(id);
		add(new BMIForm("bmiform"));
	}

	class BMIForm extends Form<BMIForm>
	{
		private static final long serialVersionUID = 37L;

		private Meting<Gram> gewichtMeting = meting(80000, gram());

		private Meting<Meter> lengteMeting = meting(1.85, meter());

		public BMIForm(String id)
		{
			super(id);
			setModel(new CompoundPropertyModel<BMIForm>(this));
			add(new FeedbackPanel("feedbackpanel"));
			add(new GewichtMetingInvoerVeld("gewichtMeting"));
			add(new LengteMetingInvoerVeld("lengteMeting").setLabel(new Model<String>("lengte")));
			add(new Label("bmi"));
		}

		public Meting<?> getBmi()
		{
			return BMICalculator.bereken().met(gewichtMeting).en(lengteMeting).bmi();
		}
	}
}
