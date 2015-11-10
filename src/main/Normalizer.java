package main;

import java.util.List;

import models.Grammar;
import models.NonTerminal;

public class Normalizer {
	public static void normalise(Grammar g) {
		List<NonTerminal> nts = g.nonTerminals;
		char ctr = 'A';
		for(NonTerminal nt:nts) {
			nt.setVal(""+ctr++);
		}
	}
}
