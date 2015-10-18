import javax.swing.*;
import java.awt.*;

/**
 * Created by gauth_000 on 17-Oct-15.
 */
public class ParsingTable extends JFrame
{
    public ParsingTable()
    {
        super("Parsing table");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.GRAY);

        JPanel topPanel=new JPanel(new BorderLayout());
        getContentPane().add(topPanel);

        String[] columns={"\t", "+", "-", "$"};
        String[][] data=
                {
                    {"A", "B>a, C>d", "C>a", "A>1"},
                    {"B", "D>a", "E>a", "A>a"}
                };

        JTable parsingTable=new JTable(data, columns);
        JScrollPane scrollPane=new JScrollPane(parsingTable);

        topPanel.add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ParsingTable();
    }
}
