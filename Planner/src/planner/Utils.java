package planner;

import static java.util.stream.Collectors.*;

import java.util.*;

public class Utils {
	static int count = 0;

	public static List<Predicate> list(List<String> texts) {
		return texts.stream()
				.map(Predicate::new)
				.collect(toList());
	}

	public static List<Predicate> list(String... texts) {
		return list(Arrays.asList(texts));
	}

	public static boolean isGround(List<Predicate> preds) {
		return preds.stream()
				.filter(p -> !p.isGround()).findFirst().isEmpty();
	}

	//リストの連結
	public static <T> List<T> concat(List<T> xs, List<T> ys) {
		var zs = new ArrayList<T>(xs);
		zs.addAll(ys);
		return zs;
	}

	//第一引数のリストから、第二引数のリストの要素を削除する
	public static <T> List<T> subtract(List<T> xs, List<T> ys) {
		var zs = new ArrayList<>(xs);
		zs.removeAll(ys);
		return zs;
	}

	//String...の...は可変引数であり、引数の数が何個でも良い
	//Arrays.asListは配列をリストに変換
	// syntactic sugar
	public static List<String> _if(String... args) {
		return Arrays.asList(args);
	}

	public static List<String> add(String... args) {
		return Arrays.asList(args);
	}

	public static List<String> del(String... args) {
		return Arrays.asList(args);
	}

	//表示用
	public static void println(Object o) {
		System.out.printf("%d| %s\n", ++count, o.toString());
	}
}