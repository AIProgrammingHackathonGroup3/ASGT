package planner;

import java.util.List;
import static planner.Utils.*;

public class EX {

	public static void main(String argv[]) {
		// initialState, goalState = new Adapter("sample.json");

		List<Predicate> sampleInitialState = list(
				"Sanji can math",
				"Sanji can english",
				"Sanji vacant 1001",
				"Sanji vacant 1002",
				"Sanji vacant 1003",
				"Kim take math",
				"Kim convenient 1001",
				"Kim convenient 1002",
				"Kim don't hate Sanji",
				"Kim attend 2 times",
				"Mitsuya take english",
				"Mitsuya convenient 1003",
				"Mitsuya like Sanji",
				"Mitsuya attend 1 times");
		List<Predicate> sampleGoalState = list(
				"Kim attend 0 times",
				"Mitsuya attend 0 times");

		Problem p = new Schedule(sampleInitialState, sampleGoalState);
		new ForwardPlanner().solve(p);
	}
}
