package hack;

import java.util.List;
import java.util.Map;

public class Student {
	private String name;
	private List<String> subjects;
	private Map<String, List<String>> timetable;
	private int attend;
	private List<String> like;
	
	Student(String name, List<String> subjects, Map<String, List<String>> timetable, int attend, List<String> like){
		this.name = name;
		this.subjects = subjects;
		this.timetable = timetable;
		this.attend = attend;
		this.like = like;
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
	
	public void setAttend(int attend) {
		this.attend = attend;
	}
	
	public void setLike(List<String> like) {
		this.like = like;
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
	
	public int getAttend() {
		return this.attend;
	}
	
	public List<String> getLike() {
		return this.like;
	}

}

