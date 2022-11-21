package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import planner.Lesson;

@Controller
public class HackController {
    List<Predicate> sampleInitialState = new ArrayList<>();
    List<Predicate> sampleGoalState = new ArrayList<>();
    List<String> studentsName = new ArrayList<>();

    @RequestMapping(value = "/index")
    private String indexPage() {
        return "/index.html";
    }

    @RequestMapping(value = "/formStudent.html")
    private String formStudent(Model model) {

        return "/formStudent.html";
    }

    @RequestMapping(value = "/formTeacher.html")
    private String formTeacher(Model model) {

        return "/formTeacher.html";
    }

    @RequestMapping(value = "/postStudent")
    private String postStudent(
            String name,
            boolean day1time1, boolean day1time2, boolean day1time3,
            boolean day2time1, boolean day2time2, boolean day2time3,
            boolean day3time1, boolean day3time2, boolean day3time3,
            boolean day4time1, boolean day4time2, boolean day4time3,
            boolean day5time1, boolean day5time2, boolean day5time3,
            boolean day6time1, boolean day6time2, boolean day6time3,
            boolean day7time1, boolean day7time2, boolean day7time3,
            int attend, String subject, int grade, String likeTeacher,
            Model model) {
        ArrayList<Boolean> timetable = new ArrayList<>();
        timetable.add(day1time1);
        timetable.add(day1time2);
        timetable.add(day1time3);
        timetable.add(day2time1);
        timetable.add(day2time2);
        timetable.add(day2time3);
        timetable.add(day3time1);
        timetable.add(day3time2);
        timetable.add(day3time3);
        timetable.add(day4time1);
        timetable.add(day4time2);
        timetable.add(day4time3);
        timetable.add(day5time1);
        timetable.add(day5time2);
        timetable.add(day5time3);
        timetable.add(day6time1);
        timetable.add(day6time2);
        timetable.add(day6time3);
        timetable.add(day7time1);
        timetable.add(day7time2);
        timetable.add(day7time3);
        System.out.print(timetable);

        ArrayList<String> initString = new ArrayList<>();

        initString.addAll(method.getStudentDay(name, timetable));
        initString.add(method.getAttend(name, attend));
        initString.add(method.getTakeSubject(name, subject));

        sampleInitialState.addAll(Utils.list(initString));
        
        studentsName.add(name);

        return "/submit.html";
    }

    @RequestMapping(value = "postTeacher")
    private String postTeacher(
            String name,
            boolean day1time1, boolean day1time2, boolean day1time3,
            boolean day2time1, boolean day2time2, boolean day2time3,
            boolean day3time1, boolean day3time2, boolean day3time3,
            boolean day4time1, boolean day4time2, boolean day4time3,
            boolean day5time1, boolean day5time2, boolean day5time3,
            boolean day6time1, boolean day6time2, boolean day6time3,
            boolean day7time1, boolean day7time2, boolean day7time3,
            String subject, String grade,
            Model model) {
        ArrayList<Boolean> timetable = new ArrayList<>();
        timetable.add(day1time1);
        timetable.add(day1time2);
        timetable.add(day1time3);
        timetable.add(day2time1);
        timetable.add(day2time2);
        timetable.add(day2time3);
        timetable.add(day3time1);
        timetable.add(day3time2);
        timetable.add(day3time3);
        timetable.add(day4time1);
        timetable.add(day4time2);
        timetable.add(day4time3);
        timetable.add(day5time1);
        timetable.add(day5time2);
        timetable.add(day5time3);
        timetable.add(day6time1);
        timetable.add(day6time2);
        timetable.add(day6time3);
        timetable.add(day7time1);
        timetable.add(day7time2);
        timetable.add(day7time3);
        System.out.print(timetable);

        ArrayList<String> initString = new ArrayList<>();

        initString.addAll(method.getTeacherDay(name, timetable));
        initString.add(method.getTeachSubject(name, subject));

        sampleInitialState.addAll(Utils.list(initString));

        return "submit.html";
    }

    @RequestMapping(value = "/schedule")
    private String schedulePage(Model model) {
        // Define data list
        List<Lesson> lessonList = new ArrayList<Lesson>();
        // {"1", "2", ... , "10"}
        List<String> tableHead = IntStream.rangeClosed(1, 10)
                .boxed().toList().stream().map(Object::toString)
                .collect(Collectors.toList());

        // Add Sample Lessons.
        lessonList
                .add(new Lesson("Sanji", "Kim", "English", 1, 2));
        lessonList
                .add(new Lesson("Mitsuya", "Kim", "Math", 2, 1));

        // Make schedule from lessons
        ScheduleService service = new ScheduleService(lessonList);

        tableHead.add(0, "Time/Date");

        String[][] table = service.getTable();
        // List<String> tableRow1 = new ArrayList<String>();
        // List<String> tableRow2 = new ArrayList<String>();
        // List<String> tableRow3 = new ArrayList<String>();
        // tableRow1.addAll(Arrays.asList(table[1]));
        // tableRow1.addAll(Arrays.asList(table[2]));
        // tableRow1.addAll(Arrays.asList(table[3]));
        System.out.println(table);
        // System.out.println(tableRow1);
        // System.out.println(tableRow2);
        // System.out.println(tableRow3);
        // Add model attribution
        model.addAttribute("tableHead", tableHead);
        model.addAttribute("lessonInfo", service.getAllAsString());
        model.addAttribute("tableRow1", table[1]);
        model.addAttribute("tableRow2", table[2]);
        model.addAttribute("tableRow3", table[3]);

        return "/schedule.html";
    }

    /**
     * 目的状態の述語リストからシフト表を作成
     * @param goalState
     * @return
     */
    private Lecture[][] operator2Timetable(List<Operator> operators) {
        Lecture[][] timetable = new Lecture[7 + 1][3 + 1];
        for(int i=0;i<timetable.length;i++) {
        	for(int j=0;j<timetable[0].length;j++) {
        		timetable[i][j] = new Lecture();
        	}
        }
        System.out.println(timetable);

        if(operators != null && operators.size() != 0) {
        for (Operator op : operators) {
            Bind b = new Bind();
            b.unified(op.getName(), new Predicate("?n ?x and ?y in ?z | ?v | subject ?w"));
            if (b.isSatisfied()) {
            	System.out.println(b);
                int day = Integer.parseInt(b.instantiate(new Predicate("?z")).toString());
                int time = Integer.parseInt(b.instantiate(new Predicate("?v")).toString());
                String student = b.instantiate(new Predicate("?x")).toString();
                String teacher = b.instantiate(new Predicate("?y")).toString();
                
                timetable[day][time] = new Lecture(student, teacher);
                System.out.println(timetable);
                }
            }
        }

        return timetable;
    }
}
