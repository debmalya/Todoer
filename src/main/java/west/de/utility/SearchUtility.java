package west.de.utility;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import west.de.model.ToDo;

public class SearchUtility {

	public static void searchByPriority(List<ToDo> todos, List<ToDo> todosByPriority) {
		List<ToDo> todosWithPriority = todos.stream().filter(todo -> todo.getPriority() != null)
				.collect(Collectors.toList());
		Collections.sort(todosWithPriority,
				(ToDo firstToDo, ToDo secondToDo) -> firstToDo.getPriority().compareTo(secondToDo.getPriority()));
		todosByPriority.addAll(todosWithPriority);
		List<ToDo> incompleteToDosWithoutPriority = todos.stream()
				.filter(todo -> todo.getPriority() == null && !todo.isCompleted()).collect(Collectors.toList());
		todosByPriority.addAll(incompleteToDosWithoutPriority);
		List<ToDo> completeToDos = todos.stream().filter(todo -> todo.isCompleted()).collect(Collectors.toList());
		todosByPriority.addAll(completeToDos);
	}

	public static boolean containsTag(List<String> contextTags, String contextTag) {
		return contextTags.stream().anyMatch(tag -> tag.contains(contextTag));
	}

}
