package planner;

import static planner.Utils.*;
import static planner.Matcher.*;

import java.util.*;

public class ForwardPlanner extends Planner {
	public List<Output> solve(Problem problem) {
		this.operators = problem.operators();
		this.init = problem.initialState();
		this.goal = problem.goalState();
		Node root = new Node(this.init, this.goal);
		System.out.println("***** start forward search *****");
		System.out.println("init: " + this.init);
		System.out.println("goal: " + this.goal);

		Node goal = plan(root);
		// goalがなければ失敗
		if (goal == null) {
			System.out.println("**** failed ****");
			return null;
		}

		// 目標状態までの手順を表示
		System.out.println("***** This is a plan! *****");
		var plan = goal.toPlan();
		List<Output> outputResult = new ArrayList<Output>();
		for (var action : plan) {
			outputResult.add(new Output(action));
		}
		return outputResult;
	}

	Node plan(Node root) {
		final int maxDepthLimit = 10;
		int depthLimit = 4;
		// 深さ制限が最大を超えるまで深さ制限を増やしつつ探索を行う
		while (depthLimit < maxDepthLimit) {
			System.out.println("=================== " + depthLimit);
			Node goal = search(root, depthLimit);
			if (goal != null)
				return goal;
			depthLimit += 1;
		}
		return null;
	}

	Node search(Node root, int depthLimit) {
		List<Node> openList = new ArrayList<>();
		openList.add(root);

		while (openList.size() > 0) {
			Node s = openList.remove(0); // Listの一番目の要素を取り出す
			System.out.println("------------------");
			System.out.printf("visit (%d) %s\n", s.id, s.state); // 取り出した要素のidと状態を表示
			if (isGoal(s))
				return s; // 取り出した要素がゴールならreturn
			if (s.depth() < depthLimit) {
				System.out.println("->");
				var children = expand(s);
				openList = concat(openList, children);
			}
		}

		return null;
	}

	// ノードが目標状態であるかどうか
	boolean isGoal(Node node) {
		var unifiers = satisfy(this.goal, node.state);
		if (unifiers == null)
			return false;
		for (var b : unifiers) {
			var g = b.instantiate(this.goal);
			if (isGround(g))
				return true;
		}
		return false;
	}

	// 子ノードの作成
	List<Node> expand(Node node) {
		var children = new ArrayList<Node>();
		for (Operator op : this.operators) {
			op = op.renamed();
			var unifiers = satisfy(op.ifList, node.state);
			expand(node, op, unifiers, children);
		}
		return children;
	}

	void expand(Node node,
			Operator operator,
			List<Bind> unifiers,
			List<Node> children) {
		for (Bind b : unifiers) {
			Node child = new Node();
			b = node.bind.merged(b);
			child.bind = b;
			child.operator = b.instantiate(operator);
			child.state = child.operator.applyForward(b.instantiate(node.state));
			child.parent = node;
			children.add(child);
			System.out.println(child);
		}
	}
}