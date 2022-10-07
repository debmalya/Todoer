package west.de.behaviour.impl.formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import west.de.behaviour.formatter.FormatToDo;
import west.de.model.ToDo;

class FormatIncompleteToDoImplTest {

	@Test
	@DisplayName("Rule 1: If priority exists, it ALWAYS appears first.")
	void testFormat() {
		ToDo toDo = new ToDo();
		toDo.setPriority("(A)");
		toDo.setDescription("Call Mom");

		FormatToDo formatToDo = new FormatIncompleteToDoImpl();
		String formatted = formatToDo.format(toDo);
		assertEquals("(A) Call Mom", formatted);
	}

	@Test
	@DisplayName("Rule 1: If priority exists, it ALWAYS appears first. Without actual priority")
	void testFormatRule1WithoutPriority() {
		ToDo toDo = new ToDo();
		toDo.setDescription("Really gotta call Mom (A)");
		toDo.setContextTags(Arrays.asList("@phone", "@someday"));

		FormatToDo formatToDo = new FormatIncompleteToDoImpl();
		String formatted = formatToDo.format(toDo);
		assertEquals("Really gotta call Mom (A) @phone @someday", formatted);
	}

	@Test
	@DisplayName("Rule 1: If priority exists, it ALWAYS appears first. Lowercase")
	void testFormatRule1PriorityInLowerCase() {
		ToDo toDo = new ToDo();
		toDo.setDescription("(b) Get back to the boss");

		FormatToDo formatToDo = new FormatIncompleteToDoImpl();
		String formatted = formatToDo.format(toDo);
		assertEquals("(b) Get back to the boss", formatted);
	}

	@Test
	@DisplayName("Rule 1: If priority exists, it ALWAYS appears first. Without space")
	void testFormatRule1PriorityWithoutSpace() {
		ToDo toDo = new ToDo();
		toDo.setDescription("(B)->Submit TPS report");

		FormatToDo formatToDo = new FormatIncompleteToDoImpl();
		String formatted = formatToDo.format(toDo);
		assertEquals("(B)->Submit TPS report", formatted);
	}

	@Test
	@DisplayName("Rule 2: A task's creation date may optionally appear directly afterpriority and a space.")
	void testFormatRule2() {
		ToDo toDo = new ToDo();
		toDo.setCreationDate(LocalDate.of(2011, 03, 02));
		toDo.setDescription("Document task format");
		toDo.setContextTags(Arrays.asList("+TodoTxt"));

		FormatToDo formatToDo = new FormatIncompleteToDoImpl();
		String formatted = formatToDo.format(toDo);
		assertEquals("2011-03-02 Document task format +TodoTxt", formatted);
	}

	@Test
	@DisplayName("Rule 2: A task's creation date may optionally appear directly afterpriority and a space. With priority.")
	void testFormatRule2WithPriority() {
		ToDo toDo = new ToDo();
		toDo.setPriority("(A)");
		toDo.setCreationDate(LocalDate.of(2011, 03, 02));
		toDo.setDescription("Call Mom");

		FormatToDo formatToDo = new FormatIncompleteToDoImpl();
		String formatted = formatToDo.format(toDo);
		assertEquals("(A) 2011-03-02 Call Mom", formatted);
	}

	@Test
	@DisplayName("Rule 3: Contexts and Projects may appear anywhere in the line after priority/prepended date.")
	void testFormatRule2WithPriorityWithoutCreationDate() {
		ToDo toDo = new ToDo();
		toDo.setPriority("(A)");
		toDo.setDescription("Call Mom");
		toDo.setContextTags(Arrays.asList("@phone", "@iphone"));
		toDo.setProjectTags(Arrays.asList("+Family", "+PeaceLoveAndHappiness"));

		FormatToDo formatToDo = new FormatIncompleteToDoImpl();
		String formatted = formatToDo.format(toDo);
		assertEquals("(A) Call Mom +Family +PeaceLoveAndHappiness @phone @iphone", formatted);
	}

}
