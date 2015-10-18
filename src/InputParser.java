import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Grammar;
import models.NonTerminal;
import models.Terminal;
import models.Symbol;

public class InputParser {
	public static Grammar parseInput(List<String> input) {
		Grammar g = new Grammar();
		for (String line : input) {
			String firstSplit[] = splitAndTrim(line, ">");
			NonTerminal head = g.getNonTerminal(firstSplit[0]);

			for (String prod : splitAndTrim(firstSplit[1], "|")) {
				String[] symbols = splitAndTrim(prod, " ");

				List<Symbol> l = new ArrayList<>();

				for (String sym : symbols) {
					if (!sym.equals(sym.toLowerCase())) {
						l.add(g.getNonTerminal(sym));
					} else {
						l.add(g.getTerminal(sym));
					}
				}

				head.addProduction(l);
			}
		}
		return g;
	}

	public static String[] splitAndTrim(String s, String delim) {
		s = s.trim();
		// System.out.println("S:"+s+" delim: " + delim);
		String pattern = "(?:(.+?)\\" + delim
				+ ")|(?:\\s*(.+)$)";
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(s);
		ArrayList<String> matches = new ArrayList<String>();
		while (matcher.find()) {
			if (matcher.group().equals(""))
				break;
			// System.out.println(matcher.group(1));
			matches.add(matcher.group(1) == null ? matcher.group(2).trim()
					: matcher.group(1).trim());
		}
		System.out.println("DEBUG: " + matches);
		return matches.toArray(new String[matches.size()]);
	}
}
