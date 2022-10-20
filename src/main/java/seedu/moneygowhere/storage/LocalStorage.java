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
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_ROOT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_SORTCONFIG_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_SORTCONFIG_ORDER_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_SORTCONFIG_TYPE_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_AMOUNT_CURRENCY_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_AMOUNT_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_CATEGORY_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_DATETIME_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_DESCRIPTION_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_ID_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_NAME_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_REMARKS_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_TARGET_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_TARGET_NAME_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_TARGET_DATETIME_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_TARGET_DESCRIPTION_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_TARGET_AMOUNT_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_TARGET_CURRENT_AMOUNT_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_TARGET_CURRENCY_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_TARGET_ID_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_RECURRING_PAYMENT_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_RECURRING_PAYMENT_ID_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_RECURRING_PAYMENT_NAME_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_RECURRING_PAYMENT_INTERVAL_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_RECURRING_PAYMENT_DESCRIPTION_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_RECURRING_PAYMENT_AMOUNT_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_RECURRING_PAYMENT_CURRENCY_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_INCOME_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_INCOME_ID_ATTRIBUTE;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_INCOME_NAME_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_INCOME_DATETIME_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_INCOME_DESCRIPTION_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_INCOME_AMOUNT_ELEMENT;
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_INCOME_CURRENCY_ATTRIBUTE;

/**
 * Loads and save data to a xml file.
 */
public class LocalStorage {
    private File saveFile;

    public LocalStorage() {
        initialiseFile();
    }

    /**
     * Function to create data file and its directory.
     */
    private void initialiseFile() {
        File directory = new File(LOCAL_STORAGE_DIRECTORY);
        directory.mkdir();
        String newFilePath = new File(LOCAL_STORAGE_DATA_FILE_PATH).getAbsolutePath();
        this.saveFile = new File(newFilePath);
    }

    /**
     * This method reads saved data and configurations from a load file
     * and add it to the list that stores expenses.
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
     * This method reads in the sortCommandSetting in xml file.
     */
    private ConsoleCommandSortExpense loadSortCommandSetting(NodeList sortConfig) {
        String type = sortConfig.item(0).getAttributes()
                .getNamedItem(XML_SORTCONFIG_TYPE_ATTRIBUTE).getTextContent();
        String order = sortConfig.item(0).getAttributes()
                .getNamedItem(XML_SORTCONFIG_ORDER_ATTRIBUTE).getTextContent();
        return new ConsoleCommandSortExpense(type, order);
    }

    /**
     * This method takes in an expense node and convert it into an Expense object.
     *
     * @param node containing information from an expense
     * @return an expense created with data by node
     */
    private Expense createExpense(Node node) throws LocalStorageLoadDataInputError {
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            throw new LocalStorageLoadDataInputError();
        }
        Element element = (Element) node;
        String name = element.getElementsByTagName(XML_EXPENSE_NAME_ELEMENT)
                .item(0).getTextContent();
        LocalDateTime dateTime = LocalDateTime.parse(element
                .getElementsByTagName(XML_EXPENSE_DATETIME_ELEMENT).item(0).getTextContent());
        String description = element.getElementsByTagName(XML_EXPENSE_DESCRIPTION_ELEMENT)
                .item(0).getTextContent();
        NodeList amountNodeList = element.getElementsByTagName(XML_EXPENSE_AMOUNT_ELEMENT);
        BigDecimal amount = new BigDecimal(amountNodeList.item(0).getTextContent());
        String currency = amountNodeList.item(0).getAttributes()
                .getNamedItem(XML_EXPENSE_AMOUNT_CURRENCY_ATTRIBUTE).getTextContent();
        String category = element.getElementsByTagName(XML_EXPENSE_CATEGORY_ELEMENT)
                .item(0).getTextContent();
        String remark = element.getElementsByTagName(XML_EXPENSE_REMARKS_ELEMENT)
                .item(0).getTextContent();
        return new Expense(name, dateTime, description, amount, category, remark, currency);
    }

    /**
     * This method takes in a target node and convert it into a Target object.
     *
     * @param node containing information from a target
     * @return a target created with data by node
     */
    private Target createTarget(Node node) throws LocalStorageLoadDataInputError {
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            throw new LocalStorageLoadDataInputError();
        }
        Element element = (Element) node;
        String name = element.getElementsByTagName(XML_TARGET_NAME_ELEMENT)
                .item(0).getTextContent();
        LocalDateTime dateTime = LocalDateTime.parse(element
                .getElementsByTagName(XML_TARGET_DATETIME_ELEMENT).item(0).getTextContent());
        String description = element.getElementsByTagName(XML_TARGET_DESCRIPTION_ELEMENT)
                .item(0).getTextContent();
        NodeList amountNodeList = element.getElementsByTagName(XML_TARGET_AMOUNT_ELEMENT);
        BigDecimal amount = new BigDecimal(amountNodeList.item(0).getTextContent());
        String currencyAmount = amountNodeList.item(0).getAttributes()
                .getNamedItem(XML_TARGET_CURRENCY_ATTRIBUTE).getTextContent();
        NodeList currentAmountNodeList = element.getElementsByTagName(XML_TARGET_CURRENT_AMOUNT_ELEMENT);
        BigDecimal currentAmount = new BigDecimal(currentAmountNodeList.item(0).getTextContent());
        String currencyCurrentAmount = currentAmountNodeList.item(0).getAttributes()
                .getNamedItem(XML_TARGET_CURRENCY_ATTRIBUTE).getTextContent();
        return new Target(name, dateTime, description, amount, currentAmount);
    }

    /**
     * This method takes in a RecurringPayment node and convert it into a RecurringPayment object.
     *
     * @param node containing information from a recurring payment
     * @return a Recurring Payment created with data by node
     */
    private RecurringPayment createRecurringPayment(Node node) throws LocalStorageLoadDataInputError {
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            throw new LocalStorageLoadDataInputError();
        }
        Element element = (Element) node;
        String name = element.getElementsByTagName(XML_RECURRING_PAYMENT_NAME_ELEMENT)
                .item(0).getTextContent();
        int interval = Integer.parseInt(element.getElementsByTagName(XML_RECURRING_PAYMENT_INTERVAL_ELEMENT)
                .item(0).getTextContent());
        String description = element.getElementsByTagName(XML_RECURRING_PAYMENT_DESCRIPTION_ELEMENT)
                .item(0).getTextContent();
        NodeList amountNodeList = element.getElementsByTagName(XML_RECURRING_PAYMENT_AMOUNT_ELEMENT);
        BigDecimal amount = new BigDecimal(amountNodeList.item(0).getTextContent());
        String currency = amountNodeList.item(0).getAttributes()
                .getNamedItem(XML_RECURRING_PAYMENT_CURRENCY_ATTRIBUTE).getTextContent();
        return new RecurringPayment(name, interval, description, amount);
    }

    /**
     * This method takes in an Income node and convert it into an Income object.
     *
     * @param node containing information from an income
     * @return income created with data by node
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
     * This method saves current data into a xml file.
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
            parseExpenseToXML(doc, rootElement, savedExpenses);
            writeXml(doc);
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println(Messages.LOCAL_STORAGE_ERROR_WRITING_DATA);
        }
    }

    private void parseExpenseToXML(Document doc, Element rootElement, ArrayList<Expense> savedExpenses) {
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

    private void parseTargetToXML(Document doc, Element rootElement, ArrayList<Target> savedTargets) {
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

