package compute;

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
    private FirstFollow firstFollow;
    private Dictionary<String, ArrayList<String>> firstTable;

    public GenerateParsingTable(Grammar grammar)
    {
        this.grammar = grammar;
        firstFollow =new FirstFollow(grammar);

        init_columns();
        init_rows();
        compute_values();
        fill_table();

        //Debug.
        System.out.print("  ");
        for (int i=0; i<columns.length; ++i)
            System.out.print(columns[i]+" ");
        System.out.println();
        for (int i = 0; i < data.length; i++)
        {
            for (int j=0; j<data[i].length; ++j)
                System.out.print(data[i][j]);
            System.out.println();
        }
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
    }

    private void init_rows()
    {
        data=new String[grammar.nonTerminals.size()][columns.length+1];

        for (int i = 0; i < data.length; i++)
            for (int j=0; j<data[i].length; ++j)
                data[i][j]="-";

        for (int i=0; i<data.length; ++i)
            data[i][0]=grammar.nonTerminals.get(i).val;
    }

    private void compute_values()
    {
        firstTable=new Hashtable<>();

        for (NonTerminal nonTerminal:grammar.nonTerminals)
            for (List<Symbol> production:nonTerminal.getProduction())
            {
                String sProduction="";
                for (Symbol symbol:production)
                    sProduction+=symbol;
                sProduction=nonTerminal.val+"->"+sProduction;
                firstTable.put(sProduction, new ArrayList<String>());

                if (grammar.isTerminal(production.get(0)))
                    firstTable.get(sProduction).add(production.get(0).val);
                else
                {
                    String first= firstFollow.fst[grammar.nonTerminals.indexOf(production.get(0))];
                    for (char cterm:first.toCharArray())
                    {
                        for (Terminal terminal:grammar.terminals)
                            if (terminal.val.equals(cterm+""))
                            {
                                firstTable.get(sProduction).add(terminal.val);
                                break;
                            }
                    }
                }
            }

        //Debug.
        /*Enumeration<String> enumeration=firstTable.keys();
        while (enumeration.hasMoreElements())
        {
            String production=enumeration.nextElement();
            System.out.print(production+" : ");
            for (String terminal:firstTable.get(production))
                System.out.print(terminal+" ");
            System.out.println();
        }*/
    }

    private void fill_table()
    {
        Enumeration<String> productions=firstTable.keys();
        while (productions.hasMoreElements())
        {
            String production=productions.nextElement();
            String head=production.split("->")[0];

            int rowIndex=0;
            for (rowIndex=0; rowIndex<data.length; ++rowIndex)
                if (data[rowIndex][0].equals(head))
                    break;

            for (String terminal:firstTable.get(production))
            {
                int columnIndex=0;
                if (terminal.equals("!"))
                {
                    String follow=firstFollow.flw[grammar.nonTerminals.indexOf(grammar.getTerminal(head))];

                    for (char cfollow:follow.toCharArray())
                        for (columnIndex=0; columnIndex<columns.length; ++columnIndex)
                            if (columns[columnIndex].equals(String.valueOf(cfollow)))
                            {
                                data[rowIndex][columnIndex+1]=production;
                                break;
                            }
                }
                else
                {
                    for (columnIndex=0; columnIndex<columns.length; ++columnIndex)
                        if (columns[columnIndex].equals(terminal))
                            break;

                    data[rowIndex][columnIndex+1]=production;
                }
            }
        }
    }
}