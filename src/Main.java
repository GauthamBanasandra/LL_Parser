import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import models.Grammar;


public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> l = new ArrayList<>();
		l.add("A>B G |C|  D|E F .");
		l.add("B>C");
		l.add("B>E");
		Grammar g = InputParser.parseInput(l);
		System.out.println(g);
	}
}
