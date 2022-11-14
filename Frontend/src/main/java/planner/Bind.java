package planner;

import static java.util.stream.Collectors.*;

import java.util.*;

public class Bind implements Cloneable {
	private static Bind UNSATISFIED = new Bind(null);

	Map<String, String> vars = new TreeMap<String, String>();

	public Bind() {
	}

	private Bind(Map<String, String> vars) {
		this.vars = vars;
	}

	public boolean isSatisfied() {
		return this.vars != null;
	}

	//複製
	public Bind clone() {
		var b = new Bind();
		b.vars.putAll(this.vars);
		return b;
	}

	public int hashCode() {
		return Objects.hash(this.vars);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		//引数がBind型なければfalse
		if ((obj instanceof Bind) == false)
			return false;
		//引数objをBind型に型変換したものを新しい変数に代入し、現在の変数束縛情報と比較する
		Bind other = (Bind) obj;
		return Objects.equals(this.vars, other.vars);
	}

	//終了しているなら変数束縛情報を文字列で表示
	public String toString() {
		if (this.isSatisfied())
			return this.vars.toString();
		return "failed";
	}
	
	//現在と引数の変数情報を合体
	public Bind merged(Bind other) {
		var b = new Bind();
		b.vars.putAll(this.vars);
		b.vars.putAll(other.vars);
		return b.resolved();
	}	

    //第一引数と第二引数の文字列をunify
	public boolean bind(String lhs, String rhs) {
		return unify(lhs, rhs, this);
	}

	public Bind unified(Predicate lhs, Predicate rhs) {
		if (lhs.size() != rhs.size())
			return UNSATISFIED;

		lhs = instantiate(lhs);
		rhs = instantiate(rhs);

		if (lhs.equals(rhs))
			return clone();

		var b = clone();
		//比較
		for (int i = 0; i < lhs.size(); i++) {
			if (unify(lhs.terms.get(i), rhs.terms.get(i), b) == false)
				return UNSATISFIED;
		}

		return b.resolved();
	}

	static boolean unify(String lhs, String rhs, Bind b) {
		lhs = b.instantiate(lhs);
		rhs = b.instantiate(rhs);

		if (lhs.equals(rhs))
			return true;

		var isVar1 = Predicate.isVar(lhs);
		var isVar2 = Predicate.isVar(rhs);

		if (isVar1 == false && isVar2 == false)
			return lhs.equals(rhs);

		if (isVar1 == false && isVar2 || isVar1 && isVar2 && lhs.compareTo(rhs) < 0)
			b.vars.put(rhs, lhs);
		else
			b.vars.put(lhs, rhs);

		return true;
	}

	//インスタンス化
	public Operator instantiate(Operator o) {
		return new Operator(instantiate(o.name),
				instantiate(o.ifList),
				instantiate(o.addList),
				instantiate(o.delList));
	}

	public List<Predicate> instantiate(List<Predicate> preds) {
		return preds.stream()
				.map(this::instantiate)
				.collect(toList());
	}

	public Predicate instantiate(Predicate p) {
		var instantiatedTerms = p.terms.stream()
				.map(this::instantiate)
				.collect(toList());
		return new Predicate(instantiatedTerms);
	}

	String instantiate(String term) {
		while (Predicate.isVar(term)) {
			var value = this.vars.get(term);
			if (value == null)
				break;
			term = value;
		}
		return term;
	}

	Bind resolved() {
		var b = new Bind();
		this.vars.keySet().forEach(term -> b.bind(term, instantiate(term)));
		return b;
	}
}