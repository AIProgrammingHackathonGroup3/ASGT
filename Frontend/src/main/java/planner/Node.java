package planner;

import java.util.*;

class Node {
	static int lastId = 0;
	final int id = lastId++;

	Node parent;
	Operator operator;
	Bind bind;
	List<Predicate> goal;
	List<Predicate> state;

	Node() {
	}

	//コンストラクタ
	Node(List<Predicate> state, List<Predicate> goal) {
		this.state = state;
		this.goal = goal;
		this.bind = new Bind();
	}

	//idと述語（オペレーター）の表示
	public String toString() {
		return String.format("(%d) %s", this.id, this.operator.name);
	}

	//深さを返す
	int depth() {
		return this.parent == null ? 0 : this.parent.depth() + 1;
	}

	List<Operator> toPlan() {
		List<Operator> plan = new ArrayList<>();
		Node node = this;
		while (node != null && node.operator != null) {
			var operator = this.bind.instantiate(node.operator);
			plan.add(operator);
			node = node.parent;
		}
		Collections.reverse(plan); //planに格納されている要素の順番を逆にする
		return plan;
	}
}
