package models;

public abstract class Symbol {
	public final String val;
	public Symbol(String val) {
		this.val = val;
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((Symbol)obj).val.equals(val);
	}
	
	@Override
	public int hashCode() {
		return 1;
	}
}
