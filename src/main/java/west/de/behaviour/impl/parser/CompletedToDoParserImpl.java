package west.de.behaviour.impl.parser;

import java.time.LocalDate;

import west.de.behaviour.parser.CompletedToDoParser;
import west.de.model.ToDo;
import west.de.utility.Constants;
import west.de.utility.DateUtility;
import west.de.utility.ParserUtility;

public class CompletedToDoParserImpl implements CompletedToDoParser {

	@Override
	public ToDo parse(String eachLine) {
		ToDo parsedToDo = null;
		if (eachLine != null && eachLine.trim().length() > 0 && eachLine.startsWith(Constants.COMPLETION_MARK)) {
			String[] values = eachLine.split(Constants.SPACES);
			// Rule 1: A completed task starts with an lower case x character.
			parsedToDo = new ToDo();
			parsedToDo.setCompleted(true);
			// Rule 2: The date of completion appears directly after the x, separated by a
			// space.
			parsedToDo.setCompletionDate(DateUtility.parseDate(values[1]));
			int fieldIndex = 2;
			if (values.length > 1) {
				LocalDate creationDate = DateUtility.parseDate(values[2]);

				if (creationDate != null) {
					parsedToDo.setCreationDate(creationDate);
					fieldIndex++;
				}
			}
			parsedToDo.setDescription(values[fieldIndex++]);

			while (fieldIndex < values.length) {
				// may have context, project and special key value
				int previousFieldIndex = fieldIndex;
				fieldIndex = ParserUtility.handleProjectIndicators(parsedToDo, values, fieldIndex);
				fieldIndex = ParserUtility.handleContextIndicators(parsedToDo, values, fieldIndex);
				fieldIndex = ParserUtility.handleSpecialKeyValue(parsedToDo, values, fieldIndex);
				if (previousFieldIndex == fieldIndex) {
					StringBuilder descriptionBuilder = new StringBuilder(parsedToDo.getDescription());
					descriptionBuilder.append(" ");
					descriptionBuilder.append(values[fieldIndex++]);
					parsedToDo.setDescription(descriptionBuilder.toString());
				}
			}
		}
		return parsedToDo;
	}

}
