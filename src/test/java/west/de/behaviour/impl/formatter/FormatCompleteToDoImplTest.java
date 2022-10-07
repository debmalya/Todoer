package west.de.behaviour.impl.formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import west.de.behaviour.formatter.FormatToDo;
import west.de.model.ToDo;

class FormatCompleteToDoImplTest {

	@Test
	void testFormat() {
		ToDo completedTodo = new ToDo();
		completedTodo.setCompleted(true);
		completedTodo.setCompletionDate(LocalDate.of(2011, 03, 02));
		completedTodo.setCreationDate(LocalDate.of(2011, 03, 01));
		completedTodo.setDescription("Review Tim's pull request");
		completedTodo.setProjectTags(Arrays.asList("+TodoTxtTouch"));
		completedTodo.setContextTags(Arrays.asList("@github"));

		FormatToDo formatToDo = new FormatCompleteToDoImpl();
		String formattedText = formatToDo.format(completedTodo);
		assertEquals("x 2011-03-02 2011-03-01 Review Tim's pull request +TodoTxtTouch @github", formattedText);
	}

	@Test
	void testFormatSpecificKeyValue() {
		ToDo completedTodo = new ToDo();
		completedTodo.setCompleted(true);
		completedTodo.setCompletionDate(LocalDate.of(2011, 03, 02));
		completedTodo.setCreationDate(LocalDate.of(2011, 03, 01));
		completedTodo.setDescription("Review Tim's pull request");
		completedTodo.setProjectTags(Arrays.asList("+TodoTxtTouch"));
		completedTodo.setContextTags(Arrays.asList("@github"));
		Map<String,String> specificKeyValueMap = new  HashMap<>();
		specificKeyValueMap.put("pri","A");
		completedTodo.setSpecificKeyValue(specificKeyValueMap);
		
		FormatToDo formatToDo = new FormatCompleteToDoImpl();
		String formattedText = formatToDo.format(completedTodo);
		assertEquals("x 2011-03-02 2011-03-01 Review Tim's pull request +TodoTxtTouch @github pri:A",formattedText);
	}

}
