package gui;
import javax.swing.*;
import java.awt.*;

/**
 * Created by gauth_000 on 17-Oct-15.
 */
public class ParsingTable extends JFrame
{
    public ParsingTable(String[] columns, String[][] data)
    {
        super("Parsing table");
        setSize(700, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.GRAY);

        JPanel topPanel=new JPanel(new BorderLayout());
        getContentPane().add(topPanel);



        JTable parsingTable=new JTable(data, columns);
        JScrollPane scrollPane=new JScrollPane(parsingTable);

        topPanel.add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        String[] columns={"\t", "+", "-", "$"};
        String[][] data=
                {
                        {"A", "B>a, C>d", "C>a", "A>1", "a"},
                        {"B", "D>a", "E>a", "A>a"}
                };
        new ParsingTable(columns, data);
    }
}
