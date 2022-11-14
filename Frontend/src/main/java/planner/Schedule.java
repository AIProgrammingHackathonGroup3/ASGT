package planner;

import static planner.Utils.*;

import java.util.*;

public class Schedule implements Problem {
	List<Predicate> initial, goal;

	public Schedule(List<Predicate> initial, List<Predicate> goal) {
		this.initial = initial;
		this.goal = goal;
	}

	// 初期状態は
	// 入力画面から読み込んだ情報を初期状態のリストに格納
	public List<Predicate> initialState() {
		return this.initial;
	}

	// 目標状態は生徒の残り授業数が０
	public List<Predicate> goalState() {
		return this.goal;
	}

	public List<Operator> operators() {

		return List.of(
				// OPERATOR 1
				// 先生と生徒の科目と都合が一致
				// 1対2の授業にすると、先生のもう片方が空いているかどうかの判定を
				// する必要があり、?x vacant ?zを削除するかどうかの判定が複雑
				// 生徒の残り授業数をどう判定するか
				
				//like消した
				new Operator("#1: ?x and ?y in ?z | ?v | subject ?w",
						_if("?x vacant ?z | ?v", "?y convenient ?z | ?v", "?x can ?w",
								"?y take ?w", "?y attend 3 times"),
						add("?x in ?z | ?v", "?y in ?z | ?v", "?y attend 2 times"),
						del("?x vacant ?z | ?v", "?y convenient ?z | ?v", "?y attend 3 times")),
				new Operator("#2: ?x and ?y in ?z | ?v | subject ?w",
						_if("?x vacant ?z | ?v", "?y convenient ?z | ?v", "?x can ?w",
								"?y take ?w", "?y attend 2 times"),
						add("?x in ?z | ?v", "?y in ?z | ?v", "?y attend 1 times"),
						del("?x vacant ?z | ?v", "?y convenient ?z | ?v", "?y attend 2 times")),
				new Operator("#3: ?x and ?y in ?z | ?v | subject ?w",
						_if("?x vacant ?z | ?v", "?y convenient ?z | ?v", "?x can ?w",
								"?y take ?w", "?y attend 1 times"),
						add("?x in ?z | ?v", "?y in ?z | ?v", "?y attend 0 times"),
						del("?x vacant ?z | ?v", "?y convenient ?z | ?v", "?y attend 1 times")),
				
				new Operator("#1: ?x and ?y in ?z | ?v | subject ?w",
						_if("?x vacant ?z | ?v", "?y convenient ?z | ?v", "?x can ?w",
								"?y take ?w", "?y attend 3 times", "?y like ?x"),
						add("?x in ?z | ?v", "?y in ?z | ?v", "?y attend 2 times"),
						del("?x vacant ?z | ?v", "?y convenient ?z | ?v", "?y attend 3 times")),
				new Operator("#2: ?x and ?y in ?z | ?v | subject ?w",
						_if("?x vacant ?z | ?v", "?y convenient ?z | ?v", "?x can ?w",
								"?y take ?w", "?y attend 2 times", "?y like ?x"),
						add("?x in ?z | ?v", "?y in ?z | ?v", "?y attend 1 times"),
						del("?x vacant ?z | ?v", "?y convenient ?z | ?v", "?y attend 2 times")),
				new Operator("#3: ?x and ?y in ?z | ?v | subject ?w",
						_if("?x vacant ?z | ?v", "?y convenient ?z | ?v", "?x can ?w",
								"?y take ?w", "?y attend 1 times", "?y like ?x"),
						add("?x in ?z | ?v", "?y in ?z | ?v", "?y attend 0 times"),
						del("?x vacant ?z | ?v", "?y convenient ?z | ?v", "?y attend 1 times"))

				//嫌いな方はめんどくさいからけしてもよい
				//new Operator("#1: ?x and ?y in ?z | subject ?w",
				//		_if("?x vacant ?z", "?y convenient ?z", "?x can ?w",
				//				"?y take ?w", "?y attend 3 times", "?y don't hate ?x"),
				//		add("?x in ?z", "?y in ?z", "?y attend 2 times"),
				//		del("?x vacant ?z", "?y convenient ?z", "?y attend 3 times")),
				//new Operator("#2: ?x and ?y in ?z | subject ?w",
				//		_if("?x vacant ?z", "?y convenient ?z", "?x can ?w",
				//				"?y take ?w", "?y attend 2 times", "?y don't hate ?x"),
				//		add("?x in ?z", "?y in ?z", "?y attend 1 times"),
				//		del("?x vacant ?z", "?y convenient ?z", "?y attend 2 times")),
				//new Operator("#3: ?x and ?y in ?z | subject ?w",
				//		_if("?x vacant ?z", "?y convenient ?z", "?x can ?w",
				//				"?y take ?w", "?y attend 1 times", "?y don't hate ?x"),
				//		add("?x in ?z", "?y in ?z", "?y attend 0 times"),
				//		del("?x vacant ?z", "?y convenient ?z", "?y attend 1 times"))

		);
	}
}