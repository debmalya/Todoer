package west.de.behaviour.impl.formatter;

import west.de.behaviour.formatter.FormatToDo;
import west.de.model.ToDo;

public class FormatToDoImpl implements FormatToDo {

	@Override
	public String format(ToDo toDo) {
		if (toDo.isCompleted()) {
			return new FormatCompleteToDoImpl().format(toDo);
		}
		return new FormatIncompleteToDoImpl().format(toDo);
	}

}
