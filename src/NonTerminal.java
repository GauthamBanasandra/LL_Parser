import java.util.ArrayList;

/**
 * Created by gauth_000 on 06-Oct-15.
 */
class NonTerminal extends Symbol
{
	public final String val;
	public ArrayList<ArrayList<Object>> productions = new ArrayList<ArrayList<Object>>();
	public ArrayList<Object> first = new ArrayList<Object>();
	public ArrayList<Object> follow = new ArrayList<Object>();

	public NonTerminal(String val, ArrayList<ArrayList<Object>> productions, ArrayList<Object> first, ArrayList<Object> follow)
	{
		this.val = val;
		this.productions = productions;
		this.first = first;
		this.follow = follow;
	}

	public boolean equals(Object that)
	{
		if(that instanceof NonTerminal)
		{
			NonTerminal term_obj = (NonTerminal)that;
			if(this.val.equals(term_obj.val))
				return true;
		}
		return false;
	}
}
