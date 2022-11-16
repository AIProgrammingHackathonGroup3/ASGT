package planner;

import java.util.*;

public abstract class Planner {
	List<Operator> operators;
	List<Predicate> init;
	List<Predicate> goal;

	public abstract List<Output> solve(Problem problem);
}