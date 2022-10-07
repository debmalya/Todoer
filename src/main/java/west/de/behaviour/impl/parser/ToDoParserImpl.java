package west.de.behaviour.impl.parser;

import west.de.behaviour.parser.ToDoParser;
import west.de.model.ToDo;
import west.de.utility.Constants;

public class ToDoParserImpl implements ToDoParser {

	@Override
	public ToDo parse(String eachLine) {
		ToDo parsedToDo = null;
		if (eachLine != null && eachLine.trim().length() > 0) {
			if (eachLine.startsWith(Constants.COMPLETION_MARK)) {
				parsedToDo = new CompletedToDoParserImpl().parse(eachLine);
			} else {
				parsedToDo = new InCompleteToDoParserImpl().parse(eachLine);
			}
		}
		return parsedToDo;
	}

}
