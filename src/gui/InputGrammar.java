package gui;
import javax.swing.*;
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
        productions=new ArrayList<String>();

        setSize(250, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pane=new JPanel();
        inputGrammarArea =new JTextArea(10, 20);
        JScrollPane inputScrollPane=new JScrollPane(inputGrammarArea);
        JButton okButton=new JButton("Ok");

        okButton.addActionListener(this);

        pane.add(inputScrollPane);
        pane.add(okButton);

        setContentPane(pane);
        setVisible(true);
    }

    public ArrayList<String> getProductions()
    {
        return productions;
    }

    public static void main(String[] args)
    {
        new InputGrammar();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for(String production:inputGrammarArea.getText().split("\n"))
        {
//            System.out.println(production);
            productions.add(production);
        }
    }
}
