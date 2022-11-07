package hack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Paths;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
	public static void main(String[] args) {
		String filePath1 = "src/sample_students.json";
		String filePath2 = "src/sample_teachers.json";
		try {
			String inJson1 = getJson(filePath1);
			String inJson2 = getJson(filePath2);
			List<Object> List1 = jsonToMap(inJson1);
			List<Object> List2 = jsonToMap(inJson2);
			List<Student> students = getStudentData(List1);
			List<Teacher> teachers = getTeacherData(List2);
			List<String> data = shapeData( teachers, students);
			System.out.println(data);
			//String outJson = mapToJson(List);
			//outputJson(outJson, "src/output.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static String getJson(String filePath) throws IOException {
		ObjectMapper  objectMapper = new ObjectMapper();
        String json = Files.readString(Paths.get(filePath));	
		return json;
	}

	public static List<Object> jsonToMap(String json) {
		ObjectMapper mapper = new ObjectMapper();
		List<Object> list = new ArrayList<Object>();
		list = null;
		try {
			list = mapper.readValue(json, new TypeReference<List<Object>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String mapToJson(Object ob) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
            //JSON文字列に変換
            json = mapper.writeValueAsString(ob);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return json;
	}
	
	public static void outputJson(String json, String filePath) {
		try{
				File file = new File(filePath);
				FileWriter filewriter = new FileWriter(file);
				filewriter.write(json);
				filewriter.close();
			}catch(IOException e){
				System.out.println(e);
			}
	}
	
	public static List<Teacher> getTeacherData(List<Object> list) {
		List<Teacher> teachers = new ArrayList<>();
		List<Map<String, Object>> nlist = (List<Map<String, Object>>)(List<?>)list;
		for(Map<String, Object> map : nlist) {
			String name = (String)map.get("name");
			List<String> subjects = (List<String>)map.get("subject");
			Map<String, List<String>> timetable = (Map<String, List<String>>)map.get("timetable");
			Teacher teacher = new Teacher(name, subjects, timetable);
			teachers.add(teacher);
		}
		return teachers;
	}
	
	public static List<Student> getStudentData(List<Object> list) {
		List<Student> students = new ArrayList<>();
		List<Map<String, Object>> nlist = (List<Map<String, Object>>)(List<?>)list;
		for(Map<String, Object> map : nlist) {
			String name = (String)map.get("name");
			List<String> subjects = (List<String>)map.get("subject");
			Map<String, List<String>> timetable = (Map<String, List<String>>)map.get("timetable");
			Student student = new Student(name, subjects, timetable);
			students.add(student);
		}
		return students;
	}
	
	public static List<String> shapeData(List<Teacher> teachers, List<Student> students){
		List<String> datas = new ArrayList<>();
		for(Teacher teacher : teachers) {
			String name = teacher.getName();
			List<String> subjects = teacher.getSubjects();
			Map<String, List<String>> timetable = teacher.getTimetable();
			for(String subject : subjects) {
				datas.add(name + " can " + subject);
			}
			for(String day : timetable.keySet()) {
				List<String> timeset = (List<String>)timetable.get(day);
				for(String time : timeset) {
					datas.add(name + " vacant " + day + "|" + time);
				}
			}
		}
		for(Student student : students) {
			String name = student.getName();
			List<String> subjects = student.getSubjects();
			Map<String, List<String>> timetable = student.getTimetable();
			for(String subject : subjects) {
				datas.add(name + " take " + subject);
			}
			for(String day : timetable.keySet()) {
				List<String> timeset = (List<String>)timetable.get(day);
				for(String time : timeset) {
					datas.add(name + " convenient " + day + "|" + time);
				}
			}
		}
		return datas;
	}
	
}
