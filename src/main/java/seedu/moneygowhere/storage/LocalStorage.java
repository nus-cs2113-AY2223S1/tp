package seedu.moneygowhere.storage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import seedu.moneygowhere.commands.ConsoleCommandSortExpense;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.data.expense.Expense;
import seedu.moneygowhere.data.expense.ExpenseManager;
import seedu.moneygowhere.data.income.Income;
import seedu.moneygowhere.data.recurringpayments.RecurringPayment;
import seedu.moneygowhere.data.target.Target;
import seedu.moneygowhere.exceptions.LocalStorageLoadDataInputError;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static seedu.moneygowhere.common.Configurations.LOCAL_STORAGE_DATA_FILE_PATH;
import static seedu.moneygowhere.common.Configurations.LOCAL_STORAGE_DIRECTORY;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.DEFAULT_CURRENCY;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_AMOUNT_CURRENCY_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_AMOUNT_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_CATEGORY_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_DATETIME_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_DESCRIPTION_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_ID_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_NAME_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_REMARKS_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_INCOME_AMOUNT_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_INCOME_CURRENCY_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_INCOME_DATETIME_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_INCOME_DESCRIPTION_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_INCOME_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_INCOME_ID_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_INCOME_NAME_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_RECURRING_PAYMENT_AMOUNT_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_RECURRING_PAYMENT_CATEGORY_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_RECURRING_PAYMENT_CURRENCY_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_RECURRING_PAYMENT_DESCRIPTION_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_RECURRING_PAYMENT_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_RECURRING_PAYMENT_ID_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_RECURRING_PAYMENT_INTERVAL_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_RECURRING_PAYMENT_NAME_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_ROOT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_SORTCONFIG_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_SORTCONFIG_ORDER_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_SORTCONFIG_TYPE_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_TARGET_AMOUNT_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_TARGET_CURRENCY_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_TARGET_CURRENT_AMOUNT_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_TARGET_DATETIME_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_TARGET_DESCRIPTION_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_TARGET_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_TARGET_ID_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_TARGET_NAME_ELEMENT;

/**
 * Stores and load data to and from storage.
 */
public class LocalStorage {
    private File saveFile;

    public LocalStorage() {
        initialiseFile();
    }

    /**
     * Create data file and its directory.
     */
    private void initialiseFile() {
        File directory = new File(LOCAL_STORAGE_DIRECTORY);
        directory.mkdir();
        String newFilePath = new File(LOCAL_STORAGE_DATA_FILE_PATH).getAbsolutePath();
        this.saveFile = new File(newFilePath);
    }

    /**
     * Reads saved data and configurations from a load file in a fixed directory, parse it and convert them into objects
     * and add them to the arraylist that stores expenses. Sort the current arraylist afterwards based on saved
     * configuration.
     *
     * @param expenseManager arraylist to store expenses
     */
    public void loadFromFile(ExpenseManager expenseManager) {
        Expense loadExpense;
        boolean hasParsedSortconfig = false;
        int itr = 0;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.setErrorHandler(new NullErrorHandler());
            Document doc = db.parse(saveFile);
            doc.getDocumentElement().normalize();
            NodeList expenseList = doc.getElementsByTagName(XML_EXPENSE_ELEMENT);
            NodeList sortConfig = doc.getElementsByTagName(XML_SORTCONFIG_ELEMENT);
            ConsoleCommandSortExpense defaultSortCommandSetting = loadSortCommandSetting(sortConfig);
            hasParsedSortconfig = true;
            for (itr = 0; itr < expenseList.getLength(); itr++) {
                Node node = expenseList.item(itr);
                loadExpense = createExpense(node);
                expenseManager.addExpense(loadExpense);
            }
            expenseManager.updateSortExpenses(defaultSortCommandSetting);
            System.out.println(Messages.LOCAL_STORAGE_LOAD_SUCCESS);
        } catch (FileNotFoundException e) {
            initialiseFile();
            System.out.println(Messages.LOCAL_STORAGE_ERROR_NO_LOAD_FILE);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            System.out.println(Messages.LOCAL_STORAGE_ERROR_CORRUPTED_OR_EMPTY_LOAD_FILE);
        } catch (LocalStorageLoadDataInputError | NumberFormatException
                 | NullPointerException | DateTimeParseException e) {
            if (!hasParsedSortconfig) {
                System.out.println(Messages.LOCAL_STORAGE_SORTCONFIG_ERROR_IN_LOAD_FILE);
            } else {
                System.out.println(Messages.LOCAL_STORAGE_EXPENSE_ERROR_IN_LOAD_FILE + (itr + 1));
            }
        }
    }

    /**
     * Reads saved data from a load file in the given directory, parse it and convert them into objects and add it to
     * the arraylist that stores expenses.
     *
     * @param expenseManager arraylist to store expenses
     * @param filePath path to save file to merge
     */
    public void loadFromExternalFile(ExpenseManager expenseManager, String filePath) {
        Expense loadExpense;
        int itr = 0;
        try {
            File externalFile = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.setErrorHandler(new NullErrorHandler());
            Document doc = db.parse(externalFile);
            doc.getDocumentElement().normalize();
            NodeList expenseList = doc.getElementsByTagName(XML_EXPENSE_ELEMENT);
            for (itr = 0; itr < expenseList.getLength(); itr++) {
                Node node = expenseList.item(itr);
                loadExpense = createExpense(node);
                expenseManager.addExpense(loadExpense);
            }
            System.out.println(Messages.LOCAL_STORAGE_MERGE_EXTERNAL_DATA_SUCCESSFUL);
        } catch (FileNotFoundException e) {
            System.out.println(Messages.LOCAL_STORAGE_ERROR_NO_LOAD_FILE);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            System.out.println(Messages.LOCAL_STORAGE_ERROR_CORRUPTED_OR_EMPTY_LOAD_FILE);
        } catch (LocalStorageLoadDataInputError | NumberFormatException
                 | NullPointerException | DateTimeParseException e) {
            System.out.println(Messages.LOCAL_STORAGE_EXPENSE_ERROR_IN_LOAD_FILE + (itr + 1));
        }
    }

    /**
     * Returns a command to sort expense based on saved configuration for sorting obtained from parsing node.
     *
     * @param sortConfig node containing sorting configuration
     * @return command to sort expense
     */
    private ConsoleCommandSortExpense loadSortCommandSetting(NodeList sortConfig) {
        String type = sortConfig.item(0).getAttributes()
                .getNamedItem(XML_SORTCONFIG_TYPE_ATTRIBUTE).getTextContent();
        String order = sortConfig.item(0).getAttributes()
                .getNamedItem(XML_SORTCONFIG_ORDER_ATTRIBUTE).getTextContent();
        ConsoleCommandSortExpense defaultSortCommandSetting
                = new ConsoleCommandSortExpense(type, order);
        return defaultSortCommandSetting;
    }

    /**
     * Returns an expense object based on information obtained from parsing an Expense node.
     *
     * @param node containing information about an expense
     * @return an Expense object
     * @throws LocalStorageLoadDataInputError if type of input node is incorrect
     */
    private Expense createExpense(Node node) throws LocalStorageLoadDataInputError {
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            throw new LocalStorageLoadDataInputError();
        }
        Element element = (Element) node;
        String name = element.getElementsByTagName(XML_EXPENSE_NAME_ELEMENT)
                .item(0).getTextContent();
        assert name != null : "There must be a name";

        LocalDateTime dateTime = LocalDateTime.parse(element
                .getElementsByTagName(XML_EXPENSE_DATETIME_ELEMENT).item(0).getTextContent());

        String description = element.getElementsByTagName(XML_EXPENSE_DESCRIPTION_ELEMENT)
                .item(0).getTextContent();
        if (description.isEmpty() || description.trim().isEmpty()) {
            description = null;
        }
        NodeList amountNodeList = element.getElementsByTagName(XML_EXPENSE_AMOUNT_ELEMENT);
        BigDecimal amount = new BigDecimal(amountNodeList.item(0).getTextContent());
        String currency = amountNodeList.item(0).getAttributes()
                .getNamedItem(XML_EXPENSE_AMOUNT_CURRENCY_ATTRIBUTE).getTextContent();
        assert currency != null : "There must be a currency";

        String category = element.getElementsByTagName(XML_EXPENSE_CATEGORY_ELEMENT)
                .item(0).getTextContent();
        if (category.isEmpty() || category.trim().isEmpty()) {
            category = null;
        }

        String remark = element.getElementsByTagName(XML_EXPENSE_REMARKS_ELEMENT)
                .item(0).getTextContent();
        if (remark.isEmpty() || remark.trim().isEmpty()) {
            remark = null;
        }
        return new Expense(name, dateTime, description, amount, category, remark, currency);
    }

    /**
     * Returns a Target object based on information obtained from parsing a Target node.
     *
     * @param node containing information about a target
     * @return a Target object
     * @throws LocalStorageLoadDataInputError if type of input node is incorrect
     */
    private Target createTarget(Node node) throws LocalStorageLoadDataInputError {
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            throw new LocalStorageLoadDataInputError();
        }
        Element element = (Element) node;
        String name = element.getElementsByTagName(XML_TARGET_NAME_ELEMENT)
                .item(0).getTextContent();
        assert name != null : "There must be a name";

        LocalDateTime dateTime = LocalDateTime.parse(element
                .getElementsByTagName(XML_TARGET_DATETIME_ELEMENT).item(0).getTextContent());

        String description = element.getElementsByTagName(XML_TARGET_DESCRIPTION_ELEMENT)
                .item(0).getTextContent();
        if (description.isEmpty() || description.trim().isEmpty()) {
            description = null;
        }

        NodeList amountNodeList = element.getElementsByTagName(XML_TARGET_AMOUNT_ELEMENT);
        BigDecimal amount = new BigDecimal(amountNodeList.item(0).getTextContent());
        String currencyAmount = amountNodeList.item(0).getAttributes()
                .getNamedItem(XML_TARGET_CURRENCY_ATTRIBUTE).getTextContent();
        assert currencyAmount != null : "There must be a currency for amount";

        NodeList currentAmountNodeList = element.getElementsByTagName(XML_TARGET_CURRENT_AMOUNT_ELEMENT);
        BigDecimal currentAmount = new BigDecimal(currentAmountNodeList.item(0).getTextContent());
        String currencyCurrentAmount = currentAmountNodeList.item(0).getAttributes()
                .getNamedItem(XML_TARGET_CURRENCY_ATTRIBUTE).getTextContent();
        assert currencyCurrentAmount != null : "There must be a currency for current amount";

        return new Target(name, dateTime, description, amount, currentAmount);
    }

    /**
     * Returns a RecurringPayment object based on information obtained from parsing a RecurringPayment node.
     *
     * @param node containing information about a recurring payment
     * @return a RecurringPayment object
     * @throws LocalStorageLoadDataInputError if type of input node is incorrect
     */
    private RecurringPayment createRecurringPayment(Node node) throws LocalStorageLoadDataInputError {
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            throw new LocalStorageLoadDataInputError();
        }
        Element element = (Element) node;
        String name = element.getElementsByTagName(XML_RECURRING_PAYMENT_NAME_ELEMENT)
                .item(0).getTextContent();
        assert name != null : "There must be a name";

        int interval = Integer.parseInt(element.getElementsByTagName(XML_RECURRING_PAYMENT_INTERVAL_ELEMENT)
                .item(0).getTextContent());

        String description = element.getElementsByTagName(XML_RECURRING_PAYMENT_DESCRIPTION_ELEMENT)
                .item(0).getTextContent();
        if (description.isEmpty() || description.trim().isEmpty()) {
            description = null;
        }

        NodeList amountNodeList = element.getElementsByTagName(XML_RECURRING_PAYMENT_AMOUNT_ELEMENT);
        BigDecimal amount = new BigDecimal(amountNodeList.item(0).getTextContent());
        String currency = amountNodeList.item(0).getAttributes()
                .getNamedItem(XML_RECURRING_PAYMENT_CURRENCY_ATTRIBUTE).getTextContent();
        assert currency != null : "There must be a currency for amount";

        String category = element.getElementsByTagName(XML_EXPENSE_CATEGORY_ELEMENT)
                .item(0).getTextContent();
        if (category.isEmpty() || category.trim().isEmpty()) {
            category = null;
        }
        
        return new RecurringPayment(name, interval, description, amount, category, currency);
    }

    /**
     * Returns an Income object based on information obtained from parsing a income node.
     *
     * @param node containing information about an income
     * @return an Income object
     * @throws LocalStorageLoadDataInputError if type of input node is incorrect
     */
    private Income createIncome(Node node) throws LocalStorageLoadDataInputError {
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            throw new LocalStorageLoadDataInputError();
        }
        Element element = (Element) node;
        String name = element.getElementsByTagName(XML_INCOME_NAME_ELEMENT)
                .item(0).getTextContent();
        LocalDateTime dateTime = LocalDateTime.parse(element
                .getElementsByTagName(XML_INCOME_DATETIME_ELEMENT).item(0).getTextContent());
        String description = element.getElementsByTagName(XML_INCOME_DESCRIPTION_ELEMENT)
                .item(0).getTextContent();
        NodeList amountNodeList = element.getElementsByTagName(XML_INCOME_AMOUNT_ELEMENT);
        BigDecimal amount = new BigDecimal(amountNodeList.item(0).getTextContent());
        String currency = amountNodeList.item(0).getAttributes()
                .getNamedItem(XML_EXPENSE_AMOUNT_CURRENCY_ATTRIBUTE).getTextContent();
        return new Income(name, dateTime, description, amount);
    }

    /**
     * Parse current expenses and configurations for sorting expenses and saves it to a xml file in storage.
     *
     * @param savedExpenses arraylist containing all expenses
     * @param sortCommandSetting configurations for sorting
     */
    public void saveToFile(ArrayList<Expense> savedExpenses, ConsoleCommandSortExpense sortCommandSetting) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(XML_ROOT);
            doc.appendChild(rootElement);
            Element sortConfig = doc.createElement(XML_SORTCONFIG_ELEMENT);
            sortConfig.setAttribute(XML_SORTCONFIG_TYPE_ATTRIBUTE, sortCommandSetting.getType());
            sortConfig.setAttribute(XML_SORTCONFIG_ORDER_ATTRIBUTE, sortCommandSetting.getOrder());
            rootElement.appendChild(sortConfig);
            parseExpenseToXml(doc, rootElement, savedExpenses);
            writeXml(doc);
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println(Messages.LOCAL_STORAGE_ERROR_WRITING_DATA);
        }
    }

    private void parseExpenseToXml(Document doc, Element rootElement, ArrayList<Expense> savedExpenses) {
        int index = 1;
        for (Expense expense : savedExpenses) {
            Element expenseElement = doc.createElement(XML_EXPENSE_ELEMENT);
            expenseElement.setAttribute(XML_EXPENSE_ID_ATTRIBUTE, Integer.toString(index));
            rootElement.appendChild(expenseElement);
            Element name = doc.createElement(XML_EXPENSE_NAME_ELEMENT);
            name.setTextContent(expense.getName());
            expenseElement.appendChild(name);
            Element dateTime = doc.createElement(XML_EXPENSE_DATETIME_ELEMENT);
            dateTime.setTextContent(expense.getDateTime().toString());
            expenseElement.appendChild(dateTime);
            Element description = doc.createElement(XML_EXPENSE_DESCRIPTION_ELEMENT);
            description.setTextContent(expense.getDescription());
            expenseElement.appendChild(description);
            Element amount = doc.createElement(XML_EXPENSE_AMOUNT_ELEMENT);
            amount.setAttribute(XML_EXPENSE_AMOUNT_CURRENCY_ATTRIBUTE, expense.getCurrency());
            amount.setTextContent(expense.getAmount().toString());
            expenseElement.appendChild(amount);
            Element category = doc.createElement(XML_EXPENSE_CATEGORY_ELEMENT);
            category.setTextContent(expense.getCategory());
            expenseElement.appendChild(category);
            Element remark = doc.createElement(XML_EXPENSE_REMARKS_ELEMENT);
            remark.setTextContent(expense.getRemarks());
            expenseElement.appendChild(remark);
            index++;
        }
    }

    private void parseTargetToXml(Document doc, Element rootElement, ArrayList<Target> savedTargets) {
        int index = 1;
        for (Target target : savedTargets) {
            Element targetElement = doc.createElement(XML_TARGET_ELEMENT);
            targetElement.setAttribute(XML_TARGET_ID_ATTRIBUTE, Integer.toString(index));
            rootElement.appendChild(targetElement);
            Element name = doc.createElement(XML_TARGET_NAME_ELEMENT);
            name.setTextContent(target.getName());
            targetElement.appendChild(name);
            Element dateTime = doc.createElement(XML_TARGET_DATETIME_ELEMENT);
            dateTime.setTextContent(target.getDateTime().toString());
            targetElement.appendChild(dateTime);
            Element description = doc.createElement(XML_TARGET_DESCRIPTION_ELEMENT);
            description.setTextContent(target.getDescription());
            targetElement.appendChild(description);
            Element amount = doc.createElement(XML_TARGET_AMOUNT_ELEMENT);
            amount.setAttribute(XML_TARGET_CURRENCY_ATTRIBUTE, DEFAULT_CURRENCY);
            amount.setTextContent(target.getAmount().toString());
            targetElement.appendChild(amount);
            Element currentAmount = doc.createElement(XML_TARGET_CURRENT_AMOUNT_ELEMENT);
            currentAmount.setAttribute(XML_TARGET_CURRENCY_ATTRIBUTE, DEFAULT_CURRENCY);
            currentAmount.setTextContent(target.getCurrentAmount().toString());
            targetElement.appendChild(currentAmount);
            index++;
        }
    }

    private void parseRecurringPaymentToXml(Document doc, Element rootElement,
                                            ArrayList<RecurringPayment> savedRecurringPayments) {
        int index = 1;
        for (RecurringPayment payment : savedRecurringPayments) {
            Element paymentElement = doc.createElement(XML_RECURRING_PAYMENT_ELEMENT);
            paymentElement.setAttribute(XML_RECURRING_PAYMENT_ID_ATTRIBUTE, Integer.toString(index));
            rootElement.appendChild(paymentElement);
            Element name = doc.createElement(XML_RECURRING_PAYMENT_NAME_ELEMENT);
            name.setTextContent(payment.getName());
            paymentElement.appendChild(name);
            Element interval = doc.createElement(XML_RECURRING_PAYMENT_INTERVAL_ELEMENT); // interval in days/month/year
            // ??
            interval.setTextContent("" + payment.getInterval()); // to append unit of time after implemented
            paymentElement.appendChild(interval);
            Element description = doc.createElement(XML_RECURRING_PAYMENT_DESCRIPTION_ELEMENT);
            description.setTextContent(payment.getDescription());
            paymentElement.appendChild(description);
            Element amount = doc.createElement(XML_RECURRING_PAYMENT_AMOUNT_ELEMENT);
            amount.setAttribute(XML_RECURRING_PAYMENT_CURRENCY_ATTRIBUTE, DEFAULT_CURRENCY);
            amount.setTextContent(payment.getAmount().toString());
            paymentElement.appendChild(amount);
            index++;
        }
    }

    private void parseIncomeToXml(Document doc, Element rootElement, ArrayList<Income> savedIncomes) {
        int index = 1;
        for (Income income : savedIncomes) {
            Element incomeElement = doc.createElement(XML_INCOME_ELEMENT);
            incomeElement.setAttribute(XML_INCOME_ID_ATTRIBUTE, Integer.toString(index));
            rootElement.appendChild(incomeElement);
            Element name = doc.createElement(XML_INCOME_NAME_ELEMENT);
            name.setTextContent(income.getName());
            incomeElement.appendChild(name);
            Element dateTime = doc.createElement(XML_INCOME_DATETIME_ELEMENT);
            dateTime.setTextContent(income.getDateTime().toString());
            incomeElement.appendChild(dateTime);
            Element description = doc.createElement(XML_INCOME_DESCRIPTION_ELEMENT);
            description.setTextContent(income.getDescription());
            incomeElement.appendChild(description);
            Element amount = doc.createElement(XML_INCOME_AMOUNT_ELEMENT);
            amount.setAttribute(XML_INCOME_CURRENCY_ATTRIBUTE, DEFAULT_CURRENCY);
            amount.setTextContent(income.getAmount().toString());
            incomeElement.appendChild(amount);
            index++;
        }
    }

    private void writeXml(Document doc)
            throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(saveFile);
        transformer.transform(source, result);
    }
}

