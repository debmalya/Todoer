package west.de.behaviour.impl.parser;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import west.de.model.ToDo;

class ToDoParserImplTest {

	@DisplayName("To test parsing of completed task")
	@Test
	void testParseComplete() {
		ToDo parsedToDo = new ToDoParserImpl().parse("x 2011-03-03 Call Mom");
		assertNotNull(parsedToDo);
		assertAll(() -> assertTrue(parsedToDo.isCompleted()),
				() -> assertEquals(LocalDate.of(2011, 03, 03), parsedToDo.getCompletionDate()),
				() -> assertEquals("Call Mom", parsedToDo.getDescription()));
	}

	@DisplayName("To test parsing of incomplete task")
	@Test
	void testParseInComplete() {
		ToDo parsedToDo = new ToDoParserImpl().parse("(A) Call Mom");
		assertNotNull(parsedToDo);
		assertAll(() -> assertFalse(parsedToDo.isCompleted()), () -> assertEquals("(A)", parsedToDo.getPriority()),
				() -> assertEquals("Call Mom", parsedToDo.getDescription()));
	}

}
