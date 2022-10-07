package west.de.behaviour.impl.parser;

import java.time.LocalDate;

import west.de.behaviour.parser.InCompleteToDoParser;
import west.de.model.ToDo;
import west.de.utility.Constants;
import west.de.utility.DateUtility;
import west.de.utility.ParserUtility;

public class InCompleteToDoParserImpl implements InCompleteToDoParser {

	@Override
	public ToDo parse(String eachLine) {
		ToDo parsedToDo = null;
		if (eachLine != null && eachLine.trim().length() > 0) {
			String[] values = eachLine.split(Constants.SPACES);
			parsedToDo = new ToDo();
			parsedToDo.setCompleted(false);
			int fieldIndex = 0;

//			Rule 1: If priority exists, it ALWAYS appears first.
			if (values[0].matches("\\([A-Z]\\)")) {
				parsedToDo.setPriority(values[fieldIndex++]);
			}
			
//			Rule 2: A task's creation date may optionally appear directly after priority and a space.
			LocalDate creationDate = DateUtility.parseDate(values[fieldIndex]);
			if (creationDate != null) {
				parsedToDo.setCreationDate(creationDate);
				fieldIndex++;
			}
			parsedToDo.setDescription(values[fieldIndex++]);

			while (fieldIndex < values.length) {
				// Rule 3: Contexts and Projects may appear anywhere in the line after priority/prepended date.
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
