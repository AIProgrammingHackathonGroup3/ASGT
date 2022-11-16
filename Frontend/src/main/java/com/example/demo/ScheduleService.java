package com.example.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import planner.Lesson;

/**
 * プランニング結果（List<Lesson>）をウェブに適した形に変え、
 * 必要な情報を引き出すことができるようにするクラス
 */
public class ScheduleService {
    List<Lesson> lessenList;
    Set<String> teacherNameSet = new HashSet<String>();
    Set<String> timeSet = new HashSet<String>();
    Set<String> dateSet = new HashSet<String>();

    ScheduleService(List<Lesson> lessenList) {
        this.lessenList = lessenList;
        for (Lesson lesson : lessenList) {
            teacherNameSet.add(lesson.teacher);
            timeSet.add(String.valueOf(lesson.time));
            dateSet.add(String.valueOf(lesson.date));
        }
    }

    List<String> getTeacherNameList() {
        return new ArrayList<String>(teacherNameSet);
    }

    List<String> getTimeList() {
        return new ArrayList<String>(timeSet);
    }

    List<String> getDateList() {
        return new ArrayList<String>(dateSet);
    }

    /**
     * Test用メソッド
     * 各LessonインスタンスのtoString()結果を返す
     * 
     * @return
     */
    List<String> getAllAsString() {
        List<String> lessonToStringList = new ArrayList<String>();
        for (Lesson lesson : this.lessenList) {
            lessonToStringList.add(lesson.toString());
        }

        return lessonToStringList;
    }
}
