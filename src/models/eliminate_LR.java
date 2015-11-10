package models;
import java.util.*;
public class eliminate_LR
{
	Grammar g;
	public eliminate_LR(Grammar g)
	{
		this.g=g;
	}	
	
	public void immediate(NonTerminal t)
	{
		NonTerminal i = t;
		List<NonTerminal> nt = g.nonTerminals;
		NonTerminal[] Nt = new NonTerminal[nt.size()];
		
		for(int x=0;x<nt.size();x++)
		{
			Nt[x]=nt.get(x);
		}
		
		
			int flag=0;
			List<List<Symbol>> recursive = new ArrayList<>();
			List<List<Symbol>> nonRecursive = new ArrayList<>();
			for (List<Symbol> j : i.mProds )
			{
				if (i.equals(j.get(0)))
				{
					flag=1;
					recursive.add(j);
				}
				else
					nonRecursive.add(j);
			}
			if(flag==1)
			{
				NonTerminal temp = new NonTerminal(i.val+"'");
				for(List<Symbol> m : nonRecursive)
				{
					m.add(temp);
				}
				for(List<Symbol> m : recursive)
				{
					m.remove(0);
					m.add(temp);
				}
				i.mProds=nonRecursive;
				temp.mProds=recursive;
				g.nonTerminals.add(temp);
			}
	}
	
	public void all()
	{
		List<NonTerminal> nt = g.nonTerminals;
		int i=0,j=0,k=0;
		
		while(i<g.nonTerminals.size())
		{
			while(j<i)
			{
				while(k<nt.get(i).mProds.size())
				{
					List<List<Symbol>> rep = new ArrayList<>();
					if(nt.get(j).equals(nt.get(i).mProds.get(k).get(0)))
					{
						List<Symbol> temp = nt.get(i).mProds.get(k);
						nt.get(i).mProds.remove(k);
						temp.remove(0);
						for (List<Symbol> l : nt.get(j).mProds)
						{
							List<Symbol> x = new ArrayList<>();
							x.addAll(l);
							x.addAll(temp);
							rep.add(x);
						}
						nt.get(i).mProds.addAll(rep);
					}
					k++;	
				}
				j++;
			}
			immediate(nt.get(i));
			i++;
		}
	}
}