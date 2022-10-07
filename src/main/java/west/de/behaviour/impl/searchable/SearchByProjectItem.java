package west.de.behaviour.impl.searchable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import west.de.behaviour.searchable.SearchableByPriorityNProject;
import west.de.model.ToDo;
import west.de.utility.SearchUtility;

public class SearchByProjectItem implements SearchableByPriorityNProject {

	@Override
	public List<ToDo> searchByPriority(List<ToDo> todos, String projectTag) {
		List<ToDo> todosByPriority = null;
		if (todos != null && !todos.isEmpty()) {
			List<ToDo> todosWithProjectTags = todos.stream()
					.filter(todo -> todo.getProjectTags() != null && !todo.getProjectTags().isEmpty()
							&& SearchUtility.containsTag(todo.getProjectTags(), projectTag))
					.collect(Collectors.toList());
			if (!todosWithProjectTags.isEmpty()) {
				todosByPriority = new ArrayList<>();
				SearchUtility.searchByPriority(todosWithProjectTags, todosByPriority);
			}

		}
		return todosByPriority;
	}
}
