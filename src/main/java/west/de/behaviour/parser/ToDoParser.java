package west.de.behaviour.parser;

import west.de.model.ToDo;

@FunctionalInterface
public interface ToDoParser {
	ToDo parse(String eachLine);

}
