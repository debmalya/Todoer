package west.de.behaviour.impl.searchable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import west.de.behaviour.searchable.SearchableByPriorityNContext;
import west.de.model.ToDo;
import west.de.utility.SearchUtility;

public class SearchByContextualtemImpl implements SearchableByPriorityNContext {

	@Override
	public List<ToDo> searchByPriority(List<ToDo> todos, String contextTag) {
		List<ToDo> todosByPriority = null;
		if (todos != null && !todos.isEmpty()) {
			List<ToDo> todosWithContextTags = todos.stream()
					.filter(todo -> todo.getContextTags() != null && !todo.getContextTags().isEmpty()
							&& SearchUtility.containsTag(todo.getContextTags(), contextTag))
					.collect(Collectors.toList());
			if (!todosWithContextTags.isEmpty()) {
				todosByPriority = new ArrayList<>();
				SearchUtility.searchByPriority(todosWithContextTags, todosByPriority);
			}

		}
		return todosByPriority;
	}

}
