package planner;

import java.util.List;
import java.util.ArrayList;

public class method {
	static List<String> getDay(List<Boolean> days) {
		List<String> dayday = new ArrayList<String>();
		for(int i = 0;i<days.size(); i++) {
			if(days.get(i) == true) {
				int d1;
				if((i+1)%3 == 0) {
					d1 = (i+1)/3;
				}
				else {
					d1 = (i+1)/3 + 1;
				}
				int d2 = (i+1)%3;
				if(d2 == 0) {
					d2 = 3;
				}
				dayday.add(d1 +" | " + d2);
				
			}
		}
		return dayday;
	}
	
	static List<String> getGoal(List<String> name) {
		List<String> goal = new ArrayList<String>();
		for(int i = 0;i<name.size(); i++) {
			goal.add(name.get(i) + "attend 0 times");
		}
		return goal;
	}
	
	public static void main(String[] args) {
		List<Boolean> days = new ArrayList<Boolean>();
		days.add(true);
		days.add(true);
		days.add(true);
		days.add(true);
		days.add(true);
		days.add(true);
		days.add(true);
		days.add(true);
		days.add(true);
		
		System.out.println(getDay(days));
	}
		
}
