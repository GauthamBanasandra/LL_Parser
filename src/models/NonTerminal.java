package models;
import java.util.ArrayList;
import java.util.List;

public class NonTerminal extends Symbol {
	public List<List<Symbol>> mProds = new ArrayList<>();
	public List<Terminal> mFirst = new ArrayList<>();
	public List<Terminal> mFollow = new ArrayList<>();
	
	
	protected NonTerminal(String v) {
		super(v);		
	}
	
	public List<List<Symbol>> getProductions() {
		return mProds;
	}
	
	public void addProduction(List<Symbol> l) {
		mProds.add(l);
	}
	
	public void setVal(String val) {
		super.val = val;
	}
	public String toString() {
		return super.val;
	}
    public void addFirst(List<Terminal> first)
    {
        this.mFirst =first;
    }

    public void addFollow(List<Terminal> terminals)
    {
    	mFollow =terminals;
    }
}