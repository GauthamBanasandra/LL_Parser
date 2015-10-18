package models;
import java.util.ArrayList;
import java.util.List;


public class Grammar {
	final List<NonTerminal> nonTerminals = new ArrayList<>();
	final List<Terminal> terminals = new ArrayList<>();
	
	public NonTerminal getNonTerminal(String s) {
		NonTerminal nt = new NonTerminal(s);
		if (!nonTerminals.contains(nt)) {
			System.out.println("G:NT:"+s);
			nonTerminals.add(nt);
		}
		return nonTerminals.get(nonTerminals.indexOf(nt));
	}
	
	public Terminal getTerminal(String s) {
		Terminal t = new Terminal(s);
		if(!terminals.contains(t)) terminals.add(t);
		return t;
	}
	
	@Override
	public String toString() {		
		return ""+nonTerminals;
	}
}
