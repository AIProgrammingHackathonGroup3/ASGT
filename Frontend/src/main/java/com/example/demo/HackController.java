package com.example.demo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HackController {

    @RequestMapping(value = "/index")
    private String indexPage() {
        return "/index.html";
    }

    @RequestMapping(value = "/formStudent")
    private String formStudent(Model model) {

        return "/formStudent.html";
    }

    @RequestMapping(value = "/formTeacher")
    private String formTeacher(Model model) {

        return "/formTeacher.html";
    }

    @RequestMapping(value = "/postStudent")
    private String postStudent(
        boolean day1time1, boolean day1time2, boolean day1time3,
        boolean day2time1, boolean day2time2, boolean day2time3,
        boolean day3time1, boolean day3time2, boolean day3time3,
        boolean day4time1, boolean day4time2, boolean day4time3,
        boolean day5time1, boolean day5time2, boolean day5time3,
        boolean day6time1, boolean day6time2, boolean day6time3,
        boolean day7time1, boolean day7time2, boolean day7time3,
        String subject, int grade, String likeTeacher, String dislikeTeacher,
        Model model
    ) {
        


        return "/submit.html";
    }

    @RequestMapping(value = "postTeacher")
    private String postTeacher(
        boolean day1time1, boolean day1time2, boolean day1time3,
        boolean day2time1, boolean day2time2, boolean day2time3,
        boolean day3time1, boolean day3time2, boolean day3time3,
        boolean day4time1, boolean day4time2, boolean day4time3,
        boolean day5time1, boolean day5time2, boolean day5time3,
        boolean day6time1, boolean day6time2, boolean day6time3,
        boolean day7time1, boolean day7time2, boolean day7time3,
        String subject, String grade,
        Model model
    ) {
        

        return "submit.html";
    }

    @RequestMapping(value = "/schedule")
    private String schedulePage(Model model) {

        return "/schedule.html";
    }
}