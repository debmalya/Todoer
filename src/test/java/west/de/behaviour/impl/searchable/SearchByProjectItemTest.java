package west.de.behaviour.impl.searchable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import west.de.behaviour.impl.parser.ToDoParserImpl;
import west.de.behaviour.parser.ToDoParser;
import west.de.model.ToDo;

class SearchByProjectItemTest {

	private static final String TODO_TXT_TOUCH = "+TodoTxtTouch";
	private static List<ToDo> todoLists = new ArrayList<>();
	private static String[] tasks = new String[] { "x 2011-03-03 Call Mom",
			"x 2011-03-02 2011-03-01 Review Tim's pull request +TodoTxtTouch @github",
			"x 2011-03-02 2011-03-01 Review submitter's code +TodoTxtTouch @github pri:A", "xylophone lesson",
			"X 2012-01-01 Make resolutions", "(A) x Find ticket prices", "(A) Call Mom",
			"Really gotta call Mom (A) @phone @someday", "(b) Get back to the boss", "(B)->Submit TPS report",
			"2011-03-02 Document +TodoTxt task format", "(A) Call Mom +Family +PeaceLoveAndHappiness @iphone @phone",
			"Email SoAndSo at soandso@example.com" };

	@BeforeAll
	static void prepareToDoList() {
		ToDoParser toDoParser = new ToDoParserImpl();
		for (int i = 0; i < tasks.length; i++) {
			todoLists.add(toDoParser.parse(tasks[i]));
		}
	}

	@Test
	void testSearchByPriority() {
		SearchByProjectItem searchByProjectItem = new SearchByProjectItem();
		List<ToDo> searchResult = searchByProjectItem.searchByPriority(todoLists, TODO_TXT_TOUCH);
		assertNotNull(searchResult);
	    
	    searchResult.forEach(each -> each.getProjectTags().stream().anyMatch(tag -> tag.contains(TODO_TXT_TOUCH)));
	}

}
