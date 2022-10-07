package west.de.behaviour.impl.formatter;

import west.de.behaviour.formatter.FormatIncompleteToDo;
import west.de.model.ToDo;
import west.de.utility.DateUtility;
import west.de.utility.FormatterUtility;

public class FormatIncompleteToDoImpl implements FormatIncompleteToDo {

	@Override
	public String format(ToDo toDo) {
		StringBuilder toDoString = new StringBuilder();
		if (toDo != null) {
			// Rule 1: If priority exists, it ALWAYS appears first.
			if (toDo.getPriority() != null) {
				toDoString.append(String.format("%s ", toDo.getPriority()));
			}
			// Rule 2: A task's creation date may optionally appear directly afterpriority
			// and a space.
			if (toDo.getCreationDate() != null) {
				toDoString.append(String.format("%s ", DateUtility.format(toDo.getCreationDate())));
			}

			// Rule 3: Contexts and Projects may appear anywhere in the line after
			// priority/prepended date.
			if (toDo.getDescription() != null) {
				toDoString.append(String.format("%s ", toDo.getDescription().trim()));
			}

			FormatterUtility.formatL2S(toDo.getProjectTags(), toDoString);
			FormatterUtility.formatL2S(toDo.getContextTags(), toDoString);
			FormatterUtility.formatM2S(toDo.getSpecificKeyValue(), toDoString);

		}
		return toDoString.toString().trim();
	}

}
