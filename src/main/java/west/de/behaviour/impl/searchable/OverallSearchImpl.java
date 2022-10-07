package west.de.behaviour.impl.searchable;

import java.util.ArrayList;
import java.util.List;

import west.de.behaviour.searchable.SearchableByPriority;
import west.de.model.ToDo;
import west.de.utility.SearchUtility;

public class OverallSearchImpl implements SearchableByPriority {

	@Override
	public List<ToDo> searchByPriority(List<ToDo> todos) {
		List<ToDo> todosByPriority = null;
		if (todos != null && !todos.isEmpty()) {
			todosByPriority = new ArrayList<>();
			SearchUtility.searchByPriority(todos, todosByPriority);
		}
		return todosByPriority;
	}


}
