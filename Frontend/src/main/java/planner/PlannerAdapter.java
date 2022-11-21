package planner;

import java.util.List;
import static planner.Utils.*;

public class PlannerAdapter {

    public List<Lesson> test() {
        List<Predicate> sampleInitialState = list(
                "Sanji can math",
                "Sanji can english",
                "Sanji vacant 1 | 1",
                "Sanji vacant 1 | 2",
                "Sanji vacant 1 | 3",
                "Kim take math",
                "Kim convenient 1 | 1",
                "Kim convenient 1 | 2",
                // "Kim don't hate Sanji",
                "Kim attend 2 times",
                "Mitsuya take english",
                "Mitsuya convenient 1 | 3",
                // "Mitsuya like Sanji",
                "Mitsuya attend 1 times");
        List<Predicate> sampleGoalState = list(
                "Kim attend 0 times",
                "Mitsuya attend 0 times");
        Problem p = new Schedule(sampleInitialState, sampleGoalState);
        return new ForwardPlanner().solve(p);
    }

}
