package planner;

import static java.util.stream.Collectors.*;

import java.util.*;

public class Predicate implements Cloneable {
	List<String> terms = new ArrayList<>();

	//Predicate(述語)
	public Predicate(String text) {
		var terms = text.split(" ");
		this.terms = Arrays.asList(terms);
	}

	//追加
	public Predicate(List<String> terms) {
		this.terms.addAll(terms);
	}

	//複製
	public Predicate clone() {
		return new Predicate(this.terms);
	}

	public int hashCode() {
		return Objects.hash(this.terms);
	}

	//比較
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if ((obj instanceof Predicate) == false)
			return false;
		Predicate other = (Predicate) obj;
		return Objects.equals(this.terms, other.terms);
	}

	//文字烈表示
	public String toString() {
		return this.terms.stream().collect(joining(" "));
	}

	//サイズ返す
	public int size() {
		return this.terms.size();
	}

	//?で始まるなら変数
	public static boolean isVar(String term) {
		return term.startsWith("?");
	}

	public boolean isGround() {
		return this.terms.stream()
				.filter(t -> isVar(t))
				.findFirst()
				.isEmpty();
	}
}