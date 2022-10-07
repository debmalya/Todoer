package west.de.utility;

import java.util.ArrayList;
import java.util.HashMap;

import west.de.model.ToDo;

public class ParserUtility {

	public static int handleContextIndicators(ToDo parsedToDo, String[] values, int fieldIndex) {
		if (fieldIndex < values.length && values[fieldIndex].startsWith(Constants.CONTEXT_INDICATOR)) {
			if (parsedToDo.getContextTags() == null) {
				parsedToDo.setContextTags(new ArrayList<>());
			}
			parsedToDo.getContextTags().add(values[fieldIndex++]);
		}
		return fieldIndex;
	}

	public static int handleProjectIndicators(ToDo parsedToDo, String[] values, int fieldIndex) {
		if (fieldIndex < values.length && values[fieldIndex].startsWith(Constants.PROJECT_INDICATOR)
				&& !values[fieldIndex].contains(Constants.EMAIL_MARKER)) {
			if (parsedToDo.getProjectTags() == null) {
				parsedToDo.setProjectTags(new ArrayList<>());
			}
			parsedToDo.getProjectTags().add(values[fieldIndex++]);
		}
		return fieldIndex;
	}

	public static int handleSpecialKeyValue(ToDo parsedToDo, String[] values, int fieldIndex) {
		if (fieldIndex < values.length && values[fieldIndex].contains(Constants.SPECIAL_KEY_VALUE_INDICATOR)) {
			String[] keyValue = values[fieldIndex].split(Constants.SPECIAL_KEY_VALUE_INDICATOR);
			if (parsedToDo.getSpecificKeyValue() == null) {
				parsedToDo.setSpecificKeyValue(new HashMap<>());
			}
			parsedToDo.getSpecificKeyValue().put(keyValue[0], keyValue[1]);
			fieldIndex++;
		}
		return fieldIndex;
	}

}
