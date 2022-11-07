package seedu.duke;

import seedu.duke.exception.FinanceException;


import static seedu.duke.CurrencyList.currencyList;
import static seedu.duke.newcurrency.PersonalCurrencyList.personalCurrencyList;

public class Currency2D {
    //can add up to 30 new currencies
    static String[] currencies = {
            "usd,us dollar,$,1.0",
            "eur,euro,€,1.0152",
            "gbp,british pound sterling,£,0.8818",
            "inr,indian rupee,₹,82.2626",
            "arp,argentine peso,arp$,152.5054",
            "aud,australian dollar,aud$,1.5866",
            "bhd,bahraini dinar,bd,0.376",
            "bwp,botswana pula,p,13.402",
            "brl,brazilian real,r$,5.2813",
            "bnd,brunei darussalam dollar,bnd$,1.4191",
            "bgn,bulgarian lev,лв,1.9856",
            "cad,canadian dollar,cad$,1.3714",
            "clp,chilean peso,clp$,971.9051",
            "cny,chinese yuan renminbi,¥,7.1939",
            "cop,colombian peso,cop$,4734.0812",
            "hrk,croatian kuna,kn,7.6422",
            "czk,czech koruna,kc,24.9592",
            "dkk,danish krone,kr,7.5517",
            "aed,emirati dirham,dh,3.6725",
            "hkd,hong kong dollar,hkd$,7.85",
            "huf,hungarian forint,ft,419.3132",
            "isk,icelandic krona,kr,143.263",
            "idr,indonesian rupiah,rp,15469.1372",
            "irr,iranian rial,ri,42319.7166",
            "ils,israeli shekel,₪,3.5217",
            "jpy,japanese yen,¥,148.9819",
            "kzt,kazakhstani tenge,лв,472.9139",
            "kwd,kuwaiti dinar,kd,0.31",
            "lyd,libyan dinar,ld,5.0393",
            "myr,malayasian ringgit,rm,4.7156",
            "mur,mauritian rupee,rs,44.5118",
            "mxn,mexican peso,mxn$,19.9734",
            "npr,nepalese rupee,rs,131.6818",
            "nzd,new zealand dollar,$,1.7573",
            "nok,norwegian krone,kr,10.5266",
            "omr,omani rial,ro,0.385",
            "pkr,pakistani rupee,rp,219.5066",
            "php,philippines peso,₱,58.8967",
            "pln,polish zloty,zł,4.8907",
            "qar,qatari riyal,qr,3.64",
            "ron,romanian new leu,lei,5.0123",
            "rub,russia ruble,₽,61.7474",
            "sar,saudi arabian riyal,sar,3.75",
            "sgd,singapore dollar,s$,1.4191",
            "zar,south african ran,r,18.0022",
            "krw,south korean won,₩,1422.9983",
            "lkr,sri lankan rupee,rs,366.9941",
            "sek,swedish krona,kr,11.0958",
            "chf,swish frank,chf,0.994",
            "twd,taiwan new dollar,nt$,31.9973",
            "thb,thailand bath,฿,38.0681",
            "ttd,trinidad and tobago dollar,tt$,6.7784",
            "try,turkish turkey,lira ₺,18.5928",
            "vef,venezuela bolivar,bs,829225.2443",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "","", "", "", "", "", ""
    };

    public static String[] getArray() {
        return currencies;
    }

    public static int getEmptyIndex() throws FinanceException {
        for (int i = 0; i < currencies.length; i++) {
            if (currencies[i].equals("")) {
                return i;
            }
        }
        throw new FinanceException(FinanceException.ExceptionCollection.CURRENCY_ARRAY_FULL);
    }

    public static void addNewCurrency(int index, String line) {
        currencies[index] = line;
    }

    public static void removeCurrency(CurrencyStructure currency) throws FinanceException {

        //CurrencyStructure currencyListCurrency = CurrencyList.findCurrencyByAbbrName(currency.getAbbrName());
        int index = currencyList.indexOf(currency);

        currencyList.remove(currency);
        personalCurrencyList.remove(currency);

        currencies[index] = "";
    }

}
