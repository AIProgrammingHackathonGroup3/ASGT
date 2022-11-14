package planner;

import java.util.List;
import java.util.ArrayList;

public class method {
	public static List<String> getStudentDay(String name, List<Boolean> days) {
		List<String> dayday = new ArrayList<String>();
		for (int i = 0; i < days.size(); i++) {
			if (days.get(i) == true) {
				int d1;
				if ((i + 1) % 3 == 0) {
					d1 = (i + 1) / 3;
				} else {
					d1 = (i + 1) / 3 + 1;
				}
				int d2 = (i + 1) % 3;
				if (d2 == 0) {
					d2 = 3;
				}
				dayday.add(name + " convenient " + d1 + " | " + d2);
			}
		}
		return dayday;
	}

	public static List<String> getTeacherDay(String name, List<Boolean> days) {
		List<String> dayday = new ArrayList<String>();
		for (int i = 0; i < days.size(); i++) {
			if (days.get(i) == true) {
				int d1;
				if ((i + 1) % 3 == 0) {
					d1 = (i + 1) / 3;
				} else {
					d1 = (i + 1) / 3 + 1;
				}
				int d2 = (i + 1) % 3;
				if (d2 == 0) {
					d2 = 3;
				}
				dayday.add(name + " vacant " + d1 + " | " + d2);
			}
		}
		return dayday;
	}

	public static String getAttend(String name, int attend){
		return name + " attend "+attend+" times";
	}

	public static List<Predicate> getGoal(List<String> name) {
        List<Predicate> goal = new ArrayList<Predicate>();
        for(int i = 0;i<name.size(); i++) {
            goal.add(new Predicate(name.get(i) + " attend 0 times"));
        }
        return goal;
    }

	public static String getTakeSubject(String name, String subject) {
		return name + " take " + subject;
	}

	public static String getTeachSubject(String name, String subject) {
		return name + " can " + subject;
	}

	public static void main(String[] args) {
		List<Boolean> days = new ArrayList<Boolean>();
		List<String> names = new ArrayList<String>();
		List<String> ini = new ArrayList<String>();

		days.add(true);
		days.add(true);
		days.add(true);
		days.add(true);
		days.add(true);
		days.add(true);
		days.add(true);
		days.add(true);
		days.add(true);

		names.add("Sanji");
		names.add("Kim");

		System.out.println(getGoal(names));
		System.out.println(getStudentDay(names.get(1), days));
		System.out.println(getTeacherDay(names.get(0), days));
	}

}