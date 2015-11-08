package models;

import java.util.ArrayList;
import java.util.List;


public class NonTerminal extends Symbol
{
    private List<List<Symbol>> mProds = new ArrayList<>();
    private List<Terminal> mFirst = new ArrayList<>();
    private List<Terminal> mFollow = new ArrayList<>();

    public void addFirst(List<Terminal> first)
    {
        mFirst=first;
    }

    protected NonTerminal(String v)
    {
        super(v);
    }

    public List<List<Symbol>> getProductions()
    {
        return mProds;
    }

    public void addProduction(List<Symbol> l)
    {
        mProds.add(l);
    }

    @Override
    public String toString()
    {
        return val + ":" + mProds;
    }

}
