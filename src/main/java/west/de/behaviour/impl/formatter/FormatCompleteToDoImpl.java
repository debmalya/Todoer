package west.de.behaviour.impl.formatter;

import west.de.model.ToDo;
import west.de.utility.DateUtility;
import west.de.utility.FormatterUtility;

public class FormatCompleteToDoImpl implements west.de.behaviour.formatter.FormatCompleteToDo {

	@Override
	public String format(ToDo toDo) {
		StringBuilder toDoString = new StringBuilder();
		if (toDo != null) {
			// Rule 1: A completed task starts with an lower case x character (x).
			if (toDo.isCompleted()) {
				toDoString.append("x ");
			}
			// Rule 2: The date of completion appears directly after the x, separated by a space.
			if (toDo.getCompletionDate() != null) {
				toDoString.append(String.format("%s ", DateUtility.format(toDo.getCompletionDate())));
			}
			
			if (toDo.getCreationDate() != null) {
				toDoString.append(String.format("%s ", DateUtility.format(toDo.getCreationDate())));
			}

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
