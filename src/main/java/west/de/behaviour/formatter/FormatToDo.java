package west.de.behaviour.formatter;

import west.de.model.ToDo;

@FunctionalInterface
public interface FormatToDo {
	
	String format(ToDo toDo);

}
