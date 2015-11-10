package gui;

import compute.GenerateParsingTable;
import transform.InputParser;
import models.Grammar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by gauth_000 on 13-Oct-15.
 */

public class InputGrammar extends JFrame implements ActionListener
{
    private ArrayList<String> productions;
    private JTextArea inputGrammarArea;

    public InputGrammar()
    {
        productions = new ArrayList<String>();

        setSize(250, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        inputGrammarArea = new JTextArea(10, 20);
        JScrollPane inputScrollPane = new JScrollPane(inputGrammarArea);
        JButton okButton = new JButton("Ok");

        okButton.addActionListener(this);

        pane.add(inputScrollPane);
        pane.add(okButton);

        setContentPane(pane);
        setVisible(true);
        centreWindow(this);
    }

    public ArrayList<String> getProductions()
    {
        return productions;
    }

    public static void main(String[] args)
    {
        new InputGrammar();
    }

    public static void centreWindow(Window frame)
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for (String production : inputGrammarArea.getText().split("\n"))
            productions.add(production);

        Grammar grammar = InputParser.parseInput(productions);
        GenerateParsingTable tableGenerator = new GenerateParsingTable(grammar);
        new ParsingTable(tableGenerator.getColumns(), tableGenerator.getData());

        this.dispose();
    }
}
