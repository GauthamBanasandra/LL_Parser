/**
 * Created by gauth_000 on 06-Oct-15.
 */
class Terminal extends Symbol
{
	public final String name;

	public Terminal(String name)
	{
		this.name = name;
	}

	public boolean equals(Object that)
	{
		if(that instanceof Terminal)
		{
			Terminal term_obj = (Terminal)that;
			if(this.name.equals(term_obj.name))
				return true;
		}
		return false;
	}
}
