package main;

import compute.GenerateParsingTable;
import models.Grammar;

import java.util.ArrayList;


public class Main
{
    public static void main(String[] args)
    {
        ArrayList<String> l = new ArrayList<>();

        l.add("E>T A");
        l.add("A>+ T A | !");
        l.add("T>F B");
        l.add("B>* F B | !");
        l.add("F>( E ) | i");

        /*Here's the error - Doesn't work when the head of the production contains more than one character.
        l.add("C1>x C1 | c C1");*/

        Grammar grammar = InputParser.parseInput(l);
        /*ComputeFirst first=new ComputeFirst(grammar);
        ComputeFollow follow=new ComputeFollow(grammar);
*/
        new GenerateParsingTable(grammar);
        //Debug.
        /*for (NonTerminal nonTerminal:grammar.nonTerminals)
            System.out.println(nonTerminal.val+">"+nonTerminal.getProduction());*/
        /*for (Terminal terminal:grammar.terminals)
            System.out.print(terminal.val+" ");*/

    }
}