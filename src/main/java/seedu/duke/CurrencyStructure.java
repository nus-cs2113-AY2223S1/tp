package seedu.duke;

public class CurrencyStructure {
    protected String abbrName;
    protected String fullName;
    protected String symbol;
    protected double rate;

    public CurrencyStructure(String abbrName, String fullName, String symbol, double rate) {
        this.abbrName = abbrName;
        this.fullName = fullName;
        this.symbol = symbol;
        this.rate = rate;
    }

    public String getAbbrName(){
        return abbrName;
    }

    public String getFullName(){
        return fullName;
    }

    public String getSymbol(){
        return symbol;
    }

    public double getRate(){
        return rate;
    }

    public boolean isSameCurrency(CurrencyStructure currency){
        return abbrName.equals(currency.getAbbrName());

    }

    public boolean isMatchedCurrencyByAbbrName(String abbrName) {
        return this.abbrName.equals(abbrName);
    }

    public boolean isMatchedCurrencyByAnyPart(String anyPart) {
        return (this.abbrName.contains(anyPart) || this.fullName.contains(anyPart) || this.symbol.contains(anyPart));
    }

    public boolean isMatchedCurrencyByAnyExact(String anyExact) {
        return (this.abbrName.equals(anyExact) || this.fullName.equals(anyExact) || this.symbol.equals(anyExact));
    }
}
