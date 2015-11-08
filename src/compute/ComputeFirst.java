package compute;

import models.Grammar;
import models.NonTerminal;
import models.Terminal;

import java.util.ArrayList;

/**
 * Created by gauth_000 on 08-Nov-15.
 */
public class ComputeFirst
{
    private Grammar grammar;

    public ComputeFirst(Grammar grammar)
    {
        this.grammar=grammar;

        for(NonTerminal nonTerminal:grammar.nonTerminals)
        {
            //Debug.
            System.out.println(nonTerminal);

            nonTerminal.addFirst(computeFirst(nonTerminal));
        }
    }

    private ArrayList<Terminal> computeFirst(NonTerminal nonTerminal)
    {
        ArrayList<Terminal> terminals=new ArrayList<>();

        terminals.add(new Terminal("a"));
        terminals.add(new Terminal("b"));

        return terminals;
    }
}