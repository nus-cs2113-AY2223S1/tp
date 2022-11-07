package seedu.duke;

public class CurrencyStructure {
    protected String abbrName;
    protected String fullName;
    protected String symbol;
    protected double rate;

    public CurrencyStructure(String abbrName, String fullName, String symbol, Double rate) {
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

    public Double getRate(){
        return rate;
    }

    public Double setRate(Double rate){
        return this.rate = rate;
    }

    public Boolean isSameCurrency(CurrencyStructure currency){
        return this.abbrName.equals(currency.getAbbrName());
    }

    public Boolean isMatchedCurrencyByAbbrName(String abbrName) {
        return this.abbrName.equals(abbrName);
    }

    public Boolean isMatchedCurrencyByAnyPart(String anyPart) {
        return this.abbrName.contains(anyPart) || this.fullName.contains(anyPart) || this.symbol.contains(anyPart);
    }

    public Boolean isMatchedCurrencyByAnyExact(String anyExact) {
        return this.abbrName.equals(anyExact) || this.fullName.equals(anyExact) || this.symbol.equals(anyExact);
    }

    public int getTypeOfName(String type){
        if(type.equals(abbrName)){
            return 1;
        }
        else if(type.equals(fullName)){
            return 2;
        }
        else if(type.equals(symbol)){
            return 3;
        }
        else{
            return 0;
        }
    }
}
