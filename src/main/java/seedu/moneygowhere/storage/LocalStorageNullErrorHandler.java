package seedu.moneygowhere.storage;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

//@@author LokQiJun
public class LocalStorageNullErrorHandler implements ErrorHandler {
    @Override
    public void fatalError(SAXParseException e) {
    }

    @Override
    public void error(SAXParseException e) {
    }

    @Override
    public void warning(SAXParseException e) {
    }
}
