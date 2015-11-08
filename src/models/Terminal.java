package models;


public class Terminal extends Symbol
{
    public Terminal(String v)
    {
        super(v);
    }

    @Override
    public String toString()
    {
        return "NT: " + val;
    }
}
