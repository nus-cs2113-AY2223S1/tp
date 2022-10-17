package seedu.moneygowhere.data.currency;

/**
 * Stores data associated with a currency.
 */
public class Currency {
    private String currencyCode;
    private Float rate;

    public Currency(String currencyCode, Float rate) {
        this.currencyCode = currencyCode;
        this.rate = rate;
    }

    public Float getRate() {
        return rate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

}
