package com.example.demo;

import java.util.*;

import planner.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerator;

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
            String subject, int grade, String likeTeacher,
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

        ArrayList<String> goalString = new ArrayList<>();

        goalString.addAll(method.getStudentDay(name, timetable));
        goalString.add(method.getTakeSubject(name, subject));

        sampleInitialState.addAll(Utils.list(goalString));

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

        ArrayList<String> initString = new ArrayList<>();

        initString.addAll(method.getTeacherDay(name, timetable));
        initString.add(method.getTeachSubject(name, subject));

        sampleInitialState.addAll(Utils.list(initString));

        return "submit.html";
    }

    @RequestMapping(value = "/schedule")
    private String schedulePage(Model model) {

        Problem p = new Schedule(sampleInitialState, sampleGoalState);
        List<Operator> op = new ForwardPlanner().solve(p);

        model.addAttribute("ops", op);

        return "/schedule.html";
    }
}
