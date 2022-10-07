package west.de.utility;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;

class DateUtilityTest {

	@Test
	void testParseDate() {
		LocalDate parsedDate = DateUtility.parseDate("2022-09-25");
		assertAll(() -> assertNotNull(parsedDate), () -> assertEquals(25, parsedDate.getDayOfMonth()),
				() -> assertEquals(Month.SEPTEMBER, parsedDate.getMonth()),
				() -> assertEquals(2022, parsedDate.getYear()));
	}

	@Test
	void testParse() {
		LocalDate parsedDate = DateUtility.parseDate("2022-25-09");
		assertNull(parsedDate);
	}

	@Test
	void testFormat() {
		LocalDate today = LocalDate.now();
		String formattedDate = DateUtility.format(today);
		assertNotNull(formattedDate);

		assertEquals(
				String.format("%d-%s-%s", today.getYear(), d2ss(today.getMonthValue()), d2ss(today.getDayOfMonth())),
				formattedDate);
	}

	private String d2ss(int day) {
		String dayStr;
		if (day < 10) {
			dayStr = String.format("0%d", day);
		} else {
			dayStr = String.format("%d", day);
		}
		return dayStr;
	}

}
