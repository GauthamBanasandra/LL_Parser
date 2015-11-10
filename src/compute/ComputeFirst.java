package compute;

import models.Grammar;
import models.NonTerminal;
import models.Symbol;
import models.Terminal;

import java.util.*;

/**
 * Created by gauth_000 on 08-Nov-15.
 */
public class ComputeFirst
{
    private Grammar grammar;
    private Dictionary<Terminal, ArrayList<List<Symbol>>> first;

    public ComputeFirst(Grammar grammar)
    {
        this.grammar=grammar;
        first=new Hashtable<>();

        for(NonTerminal nonTerminal:grammar.nonTerminals)
        {
            //Debug.
//            System.out.println(nonTerminal);

            nonTerminal.addFirst(computeFirst(nonTerminal));
        }

        Enumeration<Terminal> terminals=first.keys();
        while (terminals.hasMoreElements())
        {
            Terminal terminal=terminals.nextElement();
            System.out.println(first.get(terminal));
        }
    }

    /*
    Method for computing first.
    It should take a non-terminal object as argument and return first which is a list of terminals.
     */
    private ArrayList<Terminal> computeFirst(NonTerminal nonTerminal)
    {
        ArrayList<Terminal> terminals=new ArrayList<>();

        for (Terminal terminal:grammar.terminals)
            first.put(terminal, new ArrayList<List<Symbol>>());

        for (List<Symbol> production:nonTerminal.getProduction())
        {
            //Debug.
//            System.out.println(nonTerminal.val+"-first("+production.get(0).val+")="+firstProduction(production.get(0)).val);
            Terminal terminal=(Terminal)firstProduction(production.get(0));
            first.get(terminal).add(production);
        }

        return terminals;
    }

    private Symbol firstProduction(Symbol symbol)
    {
        if (grammar.isTerminal(symbol))
            return symbol;

        for (List<Symbol> production : grammar.getNonTerminal(symbol.val).getProduction())
            return firstProduction(production.get(0));
        return null;
    }
}