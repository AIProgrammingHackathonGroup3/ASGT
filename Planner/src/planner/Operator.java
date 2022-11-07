
package planner;

import static java.util.stream.Collectors.*;
import static planner.Utils.*;

import java.util.*;

public class Operator {
	static int lastID = 0;

	Predicate name;
	List<Predicate> ifList;
	List<Predicate> addList;
	List<Predicate> delList;

	//コンストラクタ
	public Operator(String name, List<String> ifList, List<String> addList, List<String> delList) {
		this.name = new Predicate(name);
		this.ifList = list(ifList);
		this.addList = list(addList);
		this.delList = list(delList);
	}

	public Operator(Predicate name,
			List<Predicate> ifList,
			List<Predicate> addList,
			List<Predicate> delList) {
		this.name = name.clone();
		this.ifList = new ArrayList<>(ifList);
		this.addList = new ArrayList<>(addList);
		this.delList = new ArrayList<>(delList);
	}

	//現在の状態を文字列表示
	public String toString() {
		return "NAME: " + this.name + "\n" +
				" IF : " + this.ifList + "\n" +
				" ADD: " + this.addList + "\n" +
				" DEL: " + this.delList;
	}

	public boolean isGround() {
		return this.name.isGround() &&
				Utils.isGround(this.ifList) &&
				Utils.isGround(this.addList) &&
				Utils.isGround(this.delList);
	}

	//変数の名前変え
	public Operator renamed() {
		Map<String, String> renamedVars = new HashMap<>();
		long id = ++Operator.lastID; //?xを?x_1にする(変数の添字を+1)
		return new Operator(rename(this.name, renamedVars, id),
				rename(this.ifList, renamedVars, id),
				rename(this.addList, renamedVars, id),
				rename(this.delList, renamedVars, id));
	}

	public List<Predicate> rename(List<Predicate> preds, Map<String, String> renamedVars,long index) {
		return preds.stream()
				.map(p -> rename(p, renamedVars, index))
				.collect(toList());
	}

	Predicate rename(Predicate p, Map<String, String> renamedVars, long index) {
		var renamedTerms = p.terms.stream()
				.map(term -> rename(term, renamedVars, index))
				.collect(toList());
		return new Predicate(renamedTerms);
	}

	String rename(String term, Map<String, String> renamedVars, long index) {
		if (Predicate.isVar(term) == false)
			return term;

		var newName = renamedVars.get(term);
		if (newName == null) {
			newName = String.format("%s_%d", term, index);
			renamedVars.put(term, newName);
		}
		return newName;
	}

	//前向き
	public List<Predicate> applyForward(List<Predicate> state) {
		state = subtract(state, this.delList); //削除
		state = concat(state, this.addList); //追加
		return state.stream().distinct().collect(toList());
	}

	//後ろ向き
	public List<Predicate> applyBackward(List<Predicate> goal) {
		goal = subtract(goal, this.addList);
		goal = concat(goal, this.ifList);
		return goal.stream().distinct().collect(toList());
	}
}