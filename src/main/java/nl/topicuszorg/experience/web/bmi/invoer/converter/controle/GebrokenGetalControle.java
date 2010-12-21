package nl.topicuszorg.experience.web.bmi.invoer.converter.controle;


public class GebrokenGetalControle implements InputControle
{

	@Override
	public boolean correcteInput(String input)
	{
		return input.matches("(0|-?[1-9]\\d*)(\\.\\d*)?||\\.\\d+");
	}
}