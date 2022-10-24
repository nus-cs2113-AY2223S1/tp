package seedu.moneygowhere.data.currency;

import java.math.BigDecimal;

//@@author jeyvia

/**
 * Stores data associated with a currency.
 */
public class Currency {
    private String currencyCode;
    private BigDecimal rate;

    public Currency(String currencyCode, BigDecimal rate) {
        this.currencyCode = currencyCode;
        this.rate = rate;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

}
