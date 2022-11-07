package hack;

import java.util.List;
import java.util.Map;

public class Student {
	private String name;
	private List<String> subjects;
	private Map<String, List<String>> timetable;
	
	Student(String name, List<String> subjects, Map<String, List<String>> timetable){
		this.name = name;
		this.subjects = subjects;
		this.timetable = timetable;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}
	
	public void setTimetable(Map<String, List<String>> timetable) {
		this.timetable = timetable;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<String> getSubjects() {
		return this.subjects;
	}
	
	public Map<String, List<String>> getTimetable() {
		return this.timetable;
	}

}
