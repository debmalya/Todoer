package west.de.behaviour.impl.parser;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import west.de.behaviour.parser.ToDoParser;
import west.de.model.ToDo;

class InCompleteToDoParserImplTest {

	@Test
	@DisplayName("Incomplete task with priority")
	void testParseSimple() {
		ToDoParser completedToDoParser = new InCompleteToDoParserImpl();
		ToDo parsedToDo = completedToDoParser.parse("(A) Call Mom");
		assertNotNull(parsedToDo);
		assertAll(() -> assertFalse(parsedToDo.isCompleted()), () -> assertEquals("(A)", parsedToDo.getPriority()),
				() -> assertEquals("Call Mom", parsedToDo.getDescription()));
	}

	@Test
	@DisplayName("Incomplete task without priority, have contexts")
	void testParseWithoutPriority() {
		ToDoParser completedToDoParser = new InCompleteToDoParserImpl();
		ToDo parsedToDo = completedToDoParser.parse("Really gotta call Mom (A) @phone @someday");
		assertNotNull(parsedToDo);
		assertAll(() -> assertFalse(parsedToDo.isCompleted()), () -> assertNull(parsedToDo.getPriority()),
				() -> assertEquals("Really gotta call Mom (A)", parsedToDo.getDescription()),
				() -> assertEquals(2, parsedToDo.getContextTags().size()),
				() -> assertEquals("@phone", parsedToDo.getContextTags().get(0)),
				() -> assertEquals("@someday", parsedToDo.getContextTags().get(1)));
	}

	@Test
	@DisplayName("Incomplete task having priority in lower case")
	void testParseWithLowerCasePriority() {
		ToDoParser completedToDoParser = new InCompleteToDoParserImpl();
		ToDo parsedToDo = completedToDoParser.parse("(b) Get back to the boss");
		assertNotNull(parsedToDo);
		assertAll(() -> assertFalse(parsedToDo.isCompleted()), () -> assertNull(parsedToDo.getPriority()),
				() -> assertEquals("(b) Get back to the boss", parsedToDo.getDescription()));
	}

	@Test
	@DisplayName("Incomplete task having priority but not followed by space")
	void testParseWithPriorityNotFollowedBySpace() {
		ToDoParser completedToDoParser = new InCompleteToDoParserImpl();
		ToDo parsedToDo = completedToDoParser.parse("(B)->Submit TPS report");
		assertNotNull(parsedToDo);
		assertAll(() -> assertFalse(parsedToDo.isCompleted()), () -> assertNull(parsedToDo.getPriority()),
				() -> assertEquals("(B)->Submit TPS report", parsedToDo.getDescription()));
	}

	@Test
	@DisplayName("Incomplete task starting with creation date")
	void testParseWithCreationDate() {
		ToDoParser completedToDoParser = new InCompleteToDoParserImpl();
		ToDo parsedToDo = completedToDoParser.parse("2011-03-02 Document +TodoTxt task format");
		assertNotNull(parsedToDo);
		assertAll(() -> assertFalse(parsedToDo.isCompleted()), () -> assertNull(parsedToDo.getPriority()),
				() -> assertEquals(LocalDate.of(2011, 03, 02), parsedToDo.getCreationDate()),
				() -> assertEquals("Document task format", parsedToDo.getDescription()),
				() -> assertEquals(1, parsedToDo.getProjectTags().size()),
				() -> assertEquals("+TodoTxt", parsedToDo.getProjectTags().get(0)));
	}

	@Test
	@DisplayName("Incomplete task with priority and creation date")
	void testParseWithCreationDateAndPriority() {
		ToDoParser completedToDoParser = new InCompleteToDoParserImpl();
		ToDo parsedToDo = completedToDoParser.parse("(A) 2011-03-02 Call Mom");
		assertNotNull(parsedToDo);
		assertAll(() -> assertFalse(parsedToDo.isCompleted()), () -> assertNotNull(parsedToDo.getPriority()),
				() -> assertEquals("(A)", parsedToDo.getPriority()),
				() -> assertEquals(LocalDate.of(2011, 03, 02), parsedToDo.getCreationDate()),
				() -> assertEquals("Call Mom", parsedToDo.getDescription()));
	}
	
	@Test
	@DisplayName("Incomplete task with priority and creation date in description")
	void testParseWithCreationDateInDescriptionAndPriority() {
		ToDoParser completedToDoParser = new InCompleteToDoParserImpl();
		ToDo parsedToDo = completedToDoParser.parse("(A) Call Mom 2011-03-02 ");
		assertNotNull(parsedToDo);
		assertAll(() -> assertFalse(parsedToDo.isCompleted()), () -> assertNotNull(parsedToDo.getPriority()),
				() -> assertEquals("(A)", parsedToDo.getPriority()),
				() -> assertEquals("Call Mom 2011-03-02", parsedToDo.getDescription()));
	}
	
	@Test
	@DisplayName("Incomplete task with priority and multiple context and projects")
	void testParseWithRule3() {
		ToDoParser completedToDoParser = new InCompleteToDoParserImpl();
		ToDo parsedToDo = completedToDoParser.parse("(A) Call Mom +Family +PeaceLoveAndHappiness @iphone @phone");
		assertNotNull(parsedToDo);
		assertAll(() -> assertFalse(parsedToDo.isCompleted()), () -> assertNotNull(parsedToDo.getPriority()),
				() -> assertEquals("(A)", parsedToDo.getPriority()),
				() -> assertEquals("Call Mom", parsedToDo.getDescription()),
				() -> assertEquals(2, parsedToDo.getProjectTags().size()),
				() -> assertEquals("+Family", parsedToDo.getProjectTags().get(0)),
				() -> assertEquals("+PeaceLoveAndHappiness", parsedToDo.getProjectTags().get(1)),
				() -> assertEquals(2, parsedToDo.getContextTags().size()),
				() -> assertEquals("@iphone", parsedToDo.getContextTags().get(0)),
				() -> assertEquals("@phone", parsedToDo.getContextTags().get(1)));
	}
	
	@Test
	@DisplayName("Incomplete task with email address no context")
	void testParseSimpleWithEmailAddress() {
		ToDoParser completedToDoParser = new InCompleteToDoParserImpl();
		ToDo parsedToDo = completedToDoParser.parse("Email SoAndSo at soandso@example.com");
		assertNotNull(parsedToDo);
		assertAll(() -> assertFalse(parsedToDo.isCompleted()),
				() -> assertEquals("Email SoAndSo at soandso@example.com", parsedToDo.getDescription()));
	}
	
	@Test
	@DisplayName("Incomplete task with plus sign but not project info")
	void testParseSimpleWithPlusSign() {
		ToDoParser completedToDoParser = new InCompleteToDoParserImpl();
		ToDo parsedToDo = completedToDoParser.parse("Learn how to add 2+2");
		assertNotNull(parsedToDo);
		assertAll(() -> assertFalse(parsedToDo.isCompleted()),
				() -> assertEquals("Learn how to add 2+2", parsedToDo.getDescription()));
	}
}
