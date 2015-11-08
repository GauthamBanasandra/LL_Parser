package main;

import compute.ComputeFirst;
import compute.ComputeFollow;
import models.Grammar;
import models.NonTerminal;

import java.util.ArrayList;
import java.util.Scanner;


public class Main
{
    public static void main(String[] args)
    {
        ArrayList<String> l = new ArrayList<>();

        l.add("A>B x y | x");
        l.add("B>C D");
        l.add("C>x Cl | c Cl");
        l.add("C>D x y Cl | !");
        l.add("D>d");

        /*Here's the error - Doesn't work when the head of the production contains more than one character.
        l.add("C1>x C1 | c C1");*/

        Grammar grammar = InputParser.parseInput(l);
        ComputeFirst first=new ComputeFirst(grammar);
        ComputeFollow follow=new ComputeFollow(grammar);

        //Debug.
        for (NonTerminal nonTerminal:grammar.nonTerminals)
            System.out.println(nonTerminal.val+nonTerminal.getFollow());
    }
}