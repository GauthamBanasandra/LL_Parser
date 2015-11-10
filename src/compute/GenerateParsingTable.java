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

        init_columns();
        init_rows();
        fill_table();
    }

    /*
    Initialize the column headers.
     */
    private void init_columns()
    {
        columns=new String[grammar.terminals.size()+1];

        for(int i=0; i<columns.length-1; ++i)
            columns[i]=grammar.terminals.get(i).val;
        columns[columns.length-1]="$";

        //Debug.
        /*for (int i=0; i<columns.length; ++i)
            System.out.print(columns[i]);*/
    }

    private void init_rows()
    {
        data=new String[grammar.nonTerminals.size()][columns.length];

        for (int i=0; i<data.length; ++i)
            data[i][0]=grammar.nonTerminals.get(i).val;

        //Debug.
        /*for (int i = 0; i < data.length; i++)
        {
            for (int j=0; j<data[i].length; ++j)
                System.out.print(data[i][j]);
            System.out.println();
        }*/
    }

    private void fill_table()
    {
        Dictionary<Terminal, ArrayList<List<Symbol>>> table=new Hashtable<>();
        Dictionary<String, ArrayList<String>> firstTable=new Hashtable<>();

        for (Terminal terminal:grammar.terminals)
            table.put(terminal, new ArrayList<List<Symbol>>());

        for (NonTerminal nonTerminal:grammar.nonTerminals)
            for (List<Symbol> production:nonTerminal.getProduction())
            {
                String sProduction="";
                for (Symbol symbol:production)
                    sProduction+=symbol;
                sProduction=nonTerminal.val+"->"+sProduction;
                firstTable.put(sProduction, new ArrayList<String>());

                if (grammar.isTerminal(production.get(0)))
                {
                    table.get(production.get(0)).add(production);
                    firstTable.get(sProduction).add(production.get(0).val);
                }
                else
                {
                    String first=firstNFollow.fst[grammar.nonTerminals.indexOf(production.get(0))];
                    for (char cterm:first.toCharArray())
                    {
                        for (Terminal terminal:grammar.terminals)
                            if (terminal.val.equals(cterm+""))
                            {
                                table.get(terminal).add(production);
                                firstTable.get(sProduction).add(terminal.val);
                                break;
                            }
                    }
                }
            }

        //Debug.
        Enumeration<String> enumeration=firstTable.keys();
        while (enumeration.hasMoreElements())
        {
            String production=enumeration.nextElement();
            System.out.print(production+" : ");
            for (String terminal:firstTable.get(production))
                System.out.print(terminal+" ");
            System.out.println();
        }
    }
}