package west.de.utility;

import java.util.Collection;
import java.util.Map;

public class FormatterUtility {

	public static void formatL2S(Collection<String> strCollection, StringBuilder stringBuilder) {
		if (strCollection != null && !strCollection.isEmpty()) {
			strCollection.forEach(each -> stringBuilder.append(String.format("%s ", each.trim())));
		}
	}

	public static void formatM2S(Map<String, String> specificKeyValue, StringBuilder toDoString) {
		if (specificKeyValue != null && !specificKeyValue.isEmpty()) {
			specificKeyValue.forEach((key, value) -> {
				toDoString.append(String.format("%s:%s ", key, value));
			});
		}

	}

}
