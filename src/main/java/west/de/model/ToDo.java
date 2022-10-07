package west.de.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ToDo {
	private boolean completed;
	private String priority;
	private LocalDate completionDate;
	private LocalDate creationDate;
	private String description;
	private List<String> projectTags;
	private List<String> contextTags;
    private Map<String,String> specificKeyValue;
}
