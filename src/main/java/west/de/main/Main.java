package west.de.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import west.de.behaviour.formatter.FormatToDo;
import west.de.behaviour.impl.formatter.FormatToDoImpl;
import west.de.behaviour.impl.parser.ToDoParserImpl;
import west.de.behaviour.impl.searchable.OverallSearchImpl;
import west.de.behaviour.impl.searchable.SearchByContextualtemImpl;
import west.de.behaviour.impl.searchable.SearchByProjectItem;
import west.de.behaviour.parser.ToDoParser;
import west.de.behaviour.searchable.SearchableByPriority;
import west.de.behaviour.searchable.SearchableByPriorityNContext;
import west.de.model.ToDo;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		if (args != null && args.length > 0) {
			List<ToDo> toDos = createToDoListFromFile(args[0]);
			SearchableByPriority searchableByPriority = new OverallSearchImpl();
			List<ToDo> searchResult = searchableByPriority.searchByPriority(toDos);
			System.out.println(
					String.format("Imported ToDo from file %s total task imported %d. Arranged by priority below:",
							args[0], toDos.size()));
			FormatToDo formatToDo = new FormatToDoImpl();
			searchResult.forEach(eachToDo -> System.out.println(formatToDo.format(eachToDo)));
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\n\nPrinting Tasks with project item '+GarageSale'");
			SearchByProjectItem searchByProjectItem = new SearchByProjectItem();
			searchResult = searchByProjectItem.searchByPriority(toDos, "+GarageSale");
			searchResult.forEach(eachToDo -> System.out.println(formatToDo.format(eachToDo)));

			SearchableByPriorityNContext searchByContextualItem = new SearchByContextualtemImpl();
			searchResult = searchByContextualItem.searchByPriority(toDos, "@phone");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n\n\nPrinting Tasks with contextual item '@phone'");
			searchResult.forEach(eachToDo -> System.out.println(formatToDo.format(eachToDo)));
		} else {
			System.err.println("Usage: please provide input todo.txt file name with location");

		}

	}

	private static List<ToDo> createToDoListFromFile(String fileName) throws FileNotFoundException {
		List<ToDo> todos = null;

		try (Scanner scanner = new Scanner(new File(fileName))) {
			todos = new ArrayList<>();
			ToDoParser toDoParser = new ToDoParserImpl();
			while (scanner.hasNext()) {
				String nextLine = scanner.nextLine();

				ToDo toDo = toDoParser.parse(nextLine);
				if (toDo != null) {
					todos.add(toDo);
				}
			}
		}

		return todos;
	}

}
