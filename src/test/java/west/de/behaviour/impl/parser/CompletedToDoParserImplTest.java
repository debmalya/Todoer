package west.de.behaviour.impl.parser;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import west.de.behaviour.impl.parser.CompletedToDoParserImpl;
import west.de.behaviour.parser.ToDoParser;
import west.de.model.ToDo;

class CompletedToDoParserImplTest {

	@Test
	void testParseSimple() {
		ToDoParser completedToDoParser = new CompletedToDoParserImpl();
		ToDo parsedToDo = completedToDoParser.parse("x 2011-03-03 Call Mom");
		assertNotNull(parsedToDo);
		assertAll(() -> assertTrue(parsedToDo.isCompleted()),
				() -> assertEquals(LocalDate.of(2011, 03, 03), parsedToDo.getCompletionDate()),
				() -> assertEquals("Call Mom", parsedToDo.getDescription()));

	}
	
	@Test
	void testParseComplete() {
		ToDoParser completedToDoParser = new CompletedToDoParserImpl();
		ToDo parsedToDo = completedToDoParser.parse("x 2011-03-02 2011-03-01 Review Tim's pull request +TodoTxtTouch @github");
		assertNotNull(parsedToDo);
		assertAll(() -> assertTrue(parsedToDo.isCompleted()),
				() -> assertEquals(LocalDate.of(2011, 03, 02), parsedToDo.getCompletionDate()),
				() -> assertEquals(LocalDate.of(2011, 03, 01), parsedToDo.getCreationDate()),
				() -> assertEquals("Review Tim's pull request", parsedToDo.getDescription()),
				() -> assertEquals(1, parsedToDo.getProjectTags().size()),
				() -> assertEquals("+TodoTxtTouch", parsedToDo.getProjectTags().get(0)),
				() -> assertEquals(1, parsedToDo.getContextTags().size()),
				() -> assertEquals("@github", parsedToDo.getContextTags().get(0)));

	}
	
	@Test
	void testParseCompleteWithSpecificKeyValue() {
		ToDoParser completedToDoParser = new CompletedToDoParserImpl();
		ToDo parsedToDo = completedToDoParser.parse("x 2011-03-02 2011-03-01 Review Tim's pull request +TodoTxtTouch @github pri:A");
		assertNotNull(parsedToDo);
		assertAll(() -> assertTrue(parsedToDo.isCompleted()),
				() -> assertEquals(LocalDate.of(2011, 03, 02), parsedToDo.getCompletionDate()),
				() -> assertEquals(LocalDate.of(2011, 03, 01), parsedToDo.getCreationDate()),
				() -> assertEquals("Review Tim's pull request", parsedToDo.getDescription()),
				() -> assertEquals(1, parsedToDo.getProjectTags().size()),
				() -> assertEquals("+TodoTxtTouch", parsedToDo.getProjectTags().get(0)),
				() -> assertEquals(1, parsedToDo.getContextTags().size()),
				() -> assertEquals("@github", parsedToDo.getContextTags().get(0)),
				() -> assertEquals(1, parsedToDo.getSpecificKeyValue().size()),
				() -> assertEquals("A", parsedToDo.getSpecificKeyValue().get("pri")));

	}

//	@ParameterizedTest
//	@ValueSource(strings = {"xylophone lesson", "X 2012-01-01 Make resolutions  ","(A) x Find ticket prices"})
//	void notCompletedTask(String eachTask) {
//		ToDoParser completedToDoParser = new CompletedToDoParserImpl();
//		ToDo parsedToDo = completedToDoParser.parse(eachTask);
//		assertNull(parsedToDo);
//	}
	@Test
	void notCompletedTask() {
		String[] args = {"xylophone lesson", "X 2012-01-01 Make resolutions  ","(A) x Find ticket prices"};
		for (String eachTask:args) {
			ToDoParser completedToDoParser = new CompletedToDoParserImpl();
			ToDo parsedToDo = completedToDoParser.parse(eachTask);
			assertNull(parsedToDo);
		}
	}
}
