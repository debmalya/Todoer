package west.de.behaviour.searchable;

import java.util.List;

import west.de.model.ToDo;

@FunctionalInterface
public interface SearchableByPriority {
     List<ToDo> searchByPriority(List<ToDo> todos);
}
