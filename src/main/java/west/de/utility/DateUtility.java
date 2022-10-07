package west.de.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtility {

	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

	public static LocalDate parseDate(String date) {
		LocalDate parsedDate = null;
		try {
			parsedDate = LocalDate.parse(date, dateFormatter);
		} catch (DateTimeParseException dtpe) {

		}
		return parsedDate;
	}

	public static String format(LocalDate creationDate) {
		String formattedDate = "";
		if (creationDate != null) {
			try {
				formattedDate = creationDate.format(dateFormatter);
			} catch (DateTimeParseException dtpe) {

			}
		}
		return formattedDate;
	}
}
