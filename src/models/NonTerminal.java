package models;

import java.util.ArrayList;
import java.util.List;


public class NonTerminal extends Symbol
{
    private List<List<Symbol>> production = new ArrayList<>();
    private List<Terminal> first = new ArrayList<>();
    private List<Terminal> follow = new ArrayList<>();

    public void addFirst(List<Terminal> first)
    {
        this.first =first;
    }

    public void addFollow(List<Terminal> terminals)
    {
        follow =terminals;
    }

    public List<Terminal> getFirst()
    {
        return first;
    }

    public List<Terminal> getFollow()
    {
        return follow;
    }

    protected NonTerminal(String v)
    {
        super(v);
    }

    public List<List<Symbol>> getProduction()
    {
        return production;
    }

    public void addProduction(List<Symbol> l)
    {
        production.add(l);
    }

    @Override
    public String toString()
    {
        return val + ":" + production;
    }
}
