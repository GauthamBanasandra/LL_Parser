package compute;

import models.Grammar;
import models.NonTerminal;
import models.Terminal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gauth_000 on 08-Nov-15.
 */
public class ComputeFollow
{
    private Grammar grammar;

    public ComputeFollow(Grammar grammar)
    {
        this.grammar = grammar;

        for(NonTerminal nonTerminal:grammar.nonTerminals)
        {
            //Debug.
//            System.out.println(nonTerminal);

            nonTerminal.addFollow(computeFollow(nonTerminal));
        }
    }

    /*
    Method for computing first.
    It should take a non-terminal object as argument and return follow which is a list of terminals.
     */
    private List<Terminal> computeFollow(NonTerminal nonTerminal)
    {
        ArrayList<Terminal> terminals=new ArrayList<>();

        terminals.add(new Terminal("a"));
        terminals.add(new Terminal("$"));

        return terminals;
    }
}
