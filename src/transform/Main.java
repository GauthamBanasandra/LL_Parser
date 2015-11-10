package transform;

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

        Grammar grammar = InputParser.parseInput(l);
        new GenerateParsingTable(grammar);
    }
}