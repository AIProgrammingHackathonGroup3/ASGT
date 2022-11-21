package planner;

public class Lesson {
    public String teacher;
    public String student;
    public String subject;
    public int date;
    public int time;

    public Lesson(String teacher, String student, String subject, int date, int time) {
        this.teacher = teacher;
        this.student = student;
        this.subject = subject;
        this.date = date;
        this.time = time;
    }

    Lesson(Operator action) {
        // action.name から Output[]を作成
        // 例）#2: Sanji and Kim in 1 | 1 | subject math
        // インスタンスを初期化
        String[] Output = action.name.toString().split(" ");
        this.teacher = Output[1];
        this.student = Output[3];
        this.subject = Output[10];
        this.date = Integer.parseInt(Output[5]);
        this.time = Integer.parseInt(Output[7]);
    }

    boolean isSameTimeTable(int date, int time) {
        return (this.date == date && this.time == time);
    }

    public String toString() {
        return "teacher:" + this.teacher + "\n" +
                "student:" + this.student + "\n" +
                "subject:" + this.subject + "\n" +
                "date:" + this.date + "\n" +
                "time:" + this.time;
    }
}
