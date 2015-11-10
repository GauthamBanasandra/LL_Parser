package main;
import java.util.ArrayList;
import java.util.Scanner;

import models.Grammar;
import models.eliminate_LR;

import compute.GenerateParsingTable;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> l = new ArrayList<>();
		l.add("A''>A a");
		l.add("A>!");
		// l.add("B>A C");
		// l.add("G> a");
		//l.add("A>A B C ");
		Grammar g = InputParser.parseInput(l);
		System.out.println(g);
		System.out.println("Normalizing!");
		Normalizer.normalise(g);

		System.out.println(g);
		System.out.println(g.nonTerminals.get(0).getProductions());
		System.out.println(g.nonTerminals.get(1).getProductions());
		System.out.println("Eliminating!");
		eliminate_LR e = new eliminate_LR(g);
		e.all();
		System.out.println(g.nonTerminals.get(0).getProductions());
        new GenerateParsingTable(g);
	}
	
}
