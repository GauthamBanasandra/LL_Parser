package transform;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by gauth_000 on 10-Nov-15.
 */
public class SymbolTransform
{
    private ArrayList<String> productions;
    private Dictionary<String, String> symbolsDictionary;

    public SymbolTransform(ArrayList<String> productions)
    {
        this.productions = productions;
        symbolsDictionary = new Hashtable<>();
    }

    private void transform_symbols()
    {
        String[] vSymbols =
                {
                        "`", "~", "1", "2", "@", "3", "#", "4", "$", "5", "%", "6", "^", "7", "&", "8", "*", "9",
                        "(", "0", ")", "-", "_", "=", "+", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                        "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
                };

        for (String production:productions)
        {
            String[] sSymbols=production.split(" ");
            String[] tempSSymbol=new String[sSymbols.length];

            for (int i=0; i<sSymbols.length; ++i)
                if (!sSymbols.equals(">")||!sSymbols.equals("|"))

        }
    }
}
