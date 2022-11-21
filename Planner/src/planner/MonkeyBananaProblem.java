package planner;

import static planner.Utils.*;

import java.util.*;

public class MonkeyBananaProblem implements Problem {
	//初期状態は猿がAの低い位置におり、バナナはBの高いところに、さらに箱はCにある。
	public List<Predicate> initialState() {
		return list(
				"Monkey at A",
				"Monkey in Low",
				"Banana at B",
				"Banana in High",
				"Box at C");
	}

	//目標状態は猿がバナナを持っている状態（部分状態)
	public List<Predicate> goalState() {
		return list(
				"Monkey has Banana");
	}

	public List<Operator> operators() {
		return List.of(
				// OPERATOR 1
				//?bを求めて?y(?bがある場所)に移動する
				new Operator("#1: go to ?y for ?b",
						_if("Monkey at ?x", "Monkey in Low", "?b at ?y"),
						add("Monkey at ?y"),
						del("Monkey at ?x")),

				// OPERATOR 2
				//箱を?yまで持っていく
				new Operator("#2: push Box to ?y",
						_if("Monkey at ?x", "Monkey in Low", "Box at ?x", "?b at ?y"),
						add("Monkey at ?y", "Box at ?y"),
						del("Monkey at ?x", "Box at ?x")),

				// OPERATOR 3
				//箱を登る
				new Operator("#3: climb up Box",
						_if("Monkey at ?x", "Box at ?x", "Monkey in Low"),
						add("Monkey in High"),
						del("Monkey in Low")),

				// OPERATOR 4
				//バナナを掴む
				new Operator("#4: grasp Banana",
						_if("Monkey at ?x", "Banana at ?x", "Monkey in ?y", "Banana in ?y"),
						add("Monkey has Banana"),
						del()));
	}
}