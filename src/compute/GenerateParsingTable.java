package compute;

import main.FirstNFollow;
import models.Grammar;
import models.NonTerminal;
import models.Symbol;
import models.Terminal;

import java.util.*;

/**
 * Created by gauth_000 on 08-Nov-15.
 */
public class GenerateParsingTable
{
    private Grammar grammar;
    private String[] columns;
    private String[][] data;
    private FirstNFollow firstNFollow;

    public GenerateParsingTable(Grammar grammar)
    {
        this.grammar = grammar;
        firstNFollow=new FirstNFollow(grammar);

        /*init_columns();
        init_rows();*/
        fill_table();
    }

    /*
    Initialize the column headers.
     */
    private void init_columns()
    {
        columns=new String[grammar.terminals.size()+1];

        for(int i=0; i<columns.length; ++i)
            columns[i]=grammar.terminals.get(i).val;
        columns[columns.length-1]="$";
    }

    private void init_rows()
    {
        data=new String[columns.length][grammar.terminals.size()+1];

        for (int i=0; i<data.length; ++i)
            data[i][0]=grammar.nonTerminals.get(i).val;
    }

    private void fill_table()
    {
        Dictionary<Terminal, ArrayList<List<Symbol>>> table=new Hashtable<>();

        for (Terminal terminal:grammar.terminals)
            table.put(terminal, new ArrayList<List<Symbol>>());

        for (NonTerminal nonTerminal:grammar.nonTerminals)
            for (List<Symbol> production:nonTerminal.getProduction())
            {
                if (grammar.isTerminal(production.get(0)))
                {
                    ArrayList<List<Symbol>> productions=table.get(production.get(0));
                    productions.add(production);
                    table.put((Terminal)production.get(0), productions);
                }
                else
                {
                    String first=firstNFollow.fst[grammar.nonTerminals.indexOf(production.get(0))];
                    for (char cterm:first.toCharArray())
                    {
                        for (Terminal terminal:grammar.terminals)
                            if (terminal.val.equals(cterm+""))
                            {
                                ArrayList<List<Symbol>> productions=table.get(terminal);
                                productions.add(production);
                                table.put(terminal, productions);
                                break;
                            }
                    }
                }
            }

        Dictionary<Terminal, ArrayList<String>> tableValues=new Hashtable<>();
        Enumeration<Terminal> terminals=table.keys();

        while (terminals.hasMoreElements())
        {
            Terminal terminal=terminals.nextElement();
            tableValues.put(terminal, new ArrayList<String>());

            //Debug.
            System.out.print(terminal.val + " : ");

            for (List<Symbol> production:table.get(terminal))
            {
                String sproduction="";
                for (Symbol symbol:production)
                    sproduction+=symbol.val;

                String sNonTerminal="";
                for (NonTerminal nonTerminal:grammar.nonTerminals)
                if (nonTerminal.getProduction().contains(production))
                    System.out.print(nonTerminal.val+"->");
                System.out.print(sproduction+", ");
            }
            System.out.println();
        }
    }
}