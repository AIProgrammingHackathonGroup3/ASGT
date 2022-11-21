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
    private List<Lesson> lessonList;
    private List<String> dateList;
    private List<String> timeList;
    private List<String> teacherNameList;
    private String[][] table = new String[4][11]; // 0行目と0列目は未使用10日と3限のTimeTable

    ScheduleService(List<Lesson> lessonList) {
        Set<String> teacherNameSet = new HashSet<String>();

        this.lessonList = lessonList;

        // Initiate table
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (j == 0)
                    table[i][j] = Integer.toString(i);
                else
                    table[i][j] = " ";
            }
        }

        for (Lesson lesson : lessonList) {
            teacherNameSet.add(lesson.teacher);
            table[lesson.time][lesson.date] = lesson.teacher + " with " + lesson.student;
        }
        // Convert set to list
        teacherNameList = new ArrayList<>(teacherNameSet);
        // Sort list
        // Collections.sort(timeList);
        // Collections.sort(dateList);
        // Make table
    }

    List<String> getTeacherNameList() {
        return teacherNameList;
    }

    List<String> getTimeList() {
        return timeList;
    }

    List<String> getDateList() {
        return dateList;
    }

    String[][] getTable() {
        return table;
    }

    /**
     * Test用メソッド
     * 各LessonインスタンスのtoString()結果を返す
     * 
     * @return
     */
    List<String> getAllAsString() {
        List<String> lessonToStringList = new ArrayList<String>();
        for (Lesson lesson : this.lessonList) {
            lessonToStringList.add(lesson.toString());
        }

        return lessonToStringList;
    }
}
