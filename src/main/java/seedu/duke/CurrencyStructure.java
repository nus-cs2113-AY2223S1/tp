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
        boolean isSameCurrency = false;
        if (this.abbrName.equals(currency.getAbbrName())) {
            isSameCurrency = true;
        }
        return isSameCurrency;
    }

    public boolean isMatchedCurrencyByAbbrName(String abbrName) {
        boolean isMatch = false;
        if (this.abbrName.equals(abbrName)) {
            isMatch = true;
        }
        return isMatch;
    }

    public boolean isMatchedCurrencyByAnyPart(String anyPart) {
        boolean isMatch = false;
        if (this.abbrName.contains(anyPart) || this.fullName.contains(anyPart) || this.symbol.contains(anyPart)) {
            isMatch = true;
        }
        return isMatch;
    }

    public boolean isMatchedCurrencyByAnyExact(String anyExact) {
        boolean isMatch = false;
        if (this.abbrName.equals(anyExact) || this.fullName.equals(anyExact) || this.symbol.equals(anyExact)) {
            isMatch = true;
        }
        return isMatch;
    }
}
