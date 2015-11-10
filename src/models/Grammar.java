package models;
import java.util.ArrayList;
import java.util.List;


public class Grammar {
	public final List<NonTerminal> nonTerminals = new ArrayList<>();
	public final List<Terminal> terminals = new ArrayList<>();
	
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
	
    public boolean isNonTerminal(Symbol symbol)
    {
        return this.nonTerminals.contains(symbol);
    }

    public boolean isTerminal(Symbol symbol)
    {
        return this.terminals.contains(symbol);
    }
	
	public String toString() {		
		return "Terminals: "+terminals+" Non Terminals: "+nonTerminals;
	}
}
