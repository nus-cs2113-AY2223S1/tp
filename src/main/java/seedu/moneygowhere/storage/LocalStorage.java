package seedu.moneygowhere.storage;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import seedu.moneygowhere.commands.ConsoleCommandSortExpense;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.data.expense.Expense;
import seedu.moneygowhere.data.income.Income;
import seedu.moneygowhere.data.recurringpayments.RecurringPayment;
import seedu.moneygowhere.data.target.Target;
import seedu.moneygowhere.exceptions.storage.LocalStorageLoadDataException;

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
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_EXPENSE_MODE_OF_PAYMENT_ELEMENT;
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
import static seedu.moneygowhere.storage.LocalStorageConfigurations.XML_RECURRING_PAYMENT_MODE_OF_PAYMENT_ELEMENT;
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

//@@author LokQiJun

/**
 * Stores and load data to and from storage.
 */
public class LocalStorage {
    private static File saveFile;

    /**
     * Create data file and its directory.
     */
    private static void initialiseFile() {
        File directory = new File(LOCAL_STORAGE_DIRECTORY);
        directory.mkdir();
        String newFilePath = new File(LOCAL_STORAGE_DATA_FILE_PATH).getAbsolutePath();
        saveFile = new File(newFilePath);
    }

    /**
     * Reads saved data and configurations from a load file in a fixed directory, parse it and convert them into objects
     * and add them to the arraylist that stores the corresponding objects. Sort the current arraylist afterwards based
     * on saved configuration.
     */
    public static ConsoleCommandSortExpense loadFromFile(
            ArrayList<Expense> savedExpenses,
            ConsoleCommandSortExpense sortCommandSetting,
            ArrayList<RecurringPayment> savedRecurringPayments,
            ArrayList<Target> savedTargets,
            ArrayList<Income> savedIncomes
    ) {
        initialiseFile();
        Expense loadExpense;
        RecurringPayment loadRecurringPayment;
        Target loadTarget;
        Income loadIncome;
        boolean hasParsedSortconfig = false;
        boolean hasParsedExpenses = false;
        boolean hasParsedRecurringPayment = false;
        boolean hasParsedTarget = false;
        int itr = 0;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.setErrorHandler(new LocalStorageNullErrorHandler());
            Document doc = db.parse(saveFile);
            doc.getDocumentElement().normalize();

            NodeList sortConfig = doc.getElementsByTagName(XML_SORTCONFIG_ELEMENT);
            sortCommandSetting = loadSortCommandSetting(sortConfig);
            hasParsedSortconfig = true;

            NodeList expenseList = doc.getElementsByTagName(XML_EXPENSE_ELEMENT);
            if (expenseList.getLength() > 0) {
                for (itr = 0; itr < expenseList.getLength(); itr++) {
                    Node node = expenseList.item(itr);
                    loadExpense = createExpense(node);
                    savedExpenses.add(loadExpense);
                }
            }
            hasParsedExpenses = true;

            NodeList recurringPaymentList = doc.getElementsByTagName(XML_RECURRING_PAYMENT_ELEMENT);
            if (recurringPaymentList.getLength() > 0) {
                for (itr = 0; itr < recurringPaymentList.getLength(); itr++) {
                    Node node = recurringPaymentList.item(itr);
                    loadRecurringPayment = createRecurringPayment(node);
                    savedRecurringPayments.add(loadRecurringPayment);
                }
            }
            hasParsedRecurringPayment = true;

            NodeList targetList = doc.getElementsByTagName(XML_TARGET_ELEMENT);
            if (recurringPaymentList.getLength() > 0) {
                for (itr = 0; itr < targetList.getLength(); itr++) {
                    Node node = targetList.item(itr);
                    loadTarget = createTarget(node);
                    savedTargets.add(loadTarget);
                }
            }
            hasParsedTarget = true;

            NodeList incomeList = doc.getElementsByTagName(XML_INCOME_ELEMENT);
            if (recurringPaymentList.getLength() > 0) {
                for (itr = 0; itr < incomeList.getLength(); itr++) {
                    Node node = incomeList.item(itr);
                    loadIncome = createIncome(node);
                    savedIncomes.add(loadIncome);
                }
            }

            System.out.println(Messages.LOCAL_STORAGE_MESSAGE_LOAD_SUCCESS);

            return sortCommandSetting;
        } catch (FileNotFoundException e) {
            initialiseFile();
            System.out.println(Messages.LOCAL_STORAGE_ERROR_NO_LOAD_FILE);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            System.out.println(Messages.LOCAL_STORAGE_ERROR_CORRUPTED_OR_EMPTY_LOAD_FILE);
        } catch (LocalStorageLoadDataException | NumberFormatException
                 | NullPointerException | DateTimeParseException e) {
            if (!hasParsedSortconfig) {
                System.out.println(Messages.LOCAL_STORAGE_SORTCONFIG_ERROR_IN_LOAD_FILE);
            } else if (!hasParsedExpenses) {
                System.out.println(Messages.LOCAL_STORAGE_EXPENSE_ERROR_IN_LOAD_FILE
                        + (itr + 1));
            } else if (!hasParsedRecurringPayment) {
                System.out.println(Messages.LOCAL_STORAGE_RECURRING_PAYMENT_ERROR_IN_LOAD_FILE
                        + (itr + 1));
            } else if (!hasParsedTarget) {
                System.out.println(Messages.LOCAL_STORAGE_TARGET_ERROR_IN_LOAD_FILE
                        + (itr + 1));
            } else {
                System.out.println(Messages.LOCAL_STORAGE_INCOME_ERROR_IN_LOAD_FILE
                        + (itr + 1));
            }
        }
        return null;
    }

    /**
     * Reads saved data from a load file in the given directory, parse it and convert them into objects and add it to
     * the arraylist that stores the corresponding object.
     *
     * @param filePath path to save file to merge
     */
    public static void loadFromExternalFile(
            String filePath,
            ArrayList<Expense> savedExpenses,
            ArrayList<RecurringPayment> savedRecurringPayments,
            ArrayList<Target> savedTargets,
            ArrayList<Income> savedIncomes) {
        Expense loadExpense;
        RecurringPayment loadRecurringPayment;
        Target loadTarget;
        Income loadIncome;
        boolean hasParsedExpenses = false;
        boolean hasParsedRecurringPayment = false;
        boolean hasParsedTarget = false;
        int itr = 0;
        try {
            File externalFile = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.setErrorHandler(new LocalStorageNullErrorHandler());
            Document doc = db.parse(externalFile);
            doc.getDocumentElement().normalize();

            NodeList expenseList = doc.getElementsByTagName(XML_EXPENSE_ELEMENT);
            if (expenseList.getLength() > 0) {
                for (itr = 0; itr < expenseList.getLength(); itr++) {
                    Node node = expenseList.item(itr);
                    loadExpense = createExpense(node);
                    savedExpenses.add(loadExpense);
                }
            }
            hasParsedExpenses = true;

            NodeList recurringPaymentList = doc.getElementsByTagName(XML_RECURRING_PAYMENT_ELEMENT);
            if (recurringPaymentList.getLength() > 0) {
                for (itr = 0; itr < recurringPaymentList.getLength(); itr++) {
                    Node node = recurringPaymentList.item(itr);
                    loadRecurringPayment = createRecurringPayment(node);
                    savedRecurringPayments.add(loadRecurringPayment);
                }
            }
            hasParsedRecurringPayment = true;

            NodeList targetList = doc.getElementsByTagName(XML_TARGET_ELEMENT);
            if (recurringPaymentList.getLength() > 0) {
                for (itr = 0; itr < targetList.getLength(); itr++) {
                    Node node = targetList.item(itr);
                    loadTarget = createTarget(node);
                    savedTargets.add(loadTarget);
                }
            }
            hasParsedTarget = true;

            NodeList incomeList = doc.getElementsByTagName(XML_INCOME_ELEMENT);
            if (recurringPaymentList.getLength() > 0) {
                for (itr = 0; itr < incomeList.getLength(); itr++) {
                    Node node = incomeList.item(itr);
                    loadIncome = createIncome(node);
                    savedIncomes.add(loadIncome);
                }
            }
            System.out.println(Messages.LOCAL_STORAGE_MESSAGE_MERGE_FILE_SUCCESS);
        } catch (FileNotFoundException e) {
            System.out.println(Messages.LOCAL_STORAGE_ERROR_NO_LOAD_FILE);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            System.out.println(Messages.LOCAL_STORAGE_ERROR_CORRUPTED_OR_EMPTY_LOAD_FILE);
        } catch (LocalStorageLoadDataException | NumberFormatException
                 | NullPointerException | DateTimeParseException e) {
            if (!hasParsedExpenses) {
                System.out.println(Messages.LOCAL_STORAGE_EXPENSE_ERROR_IN_LOAD_FILE
                        + (itr + 1));
            } else if (!hasParsedRecurringPayment) {
                System.out.println(Messages.LOCAL_STORAGE_RECURRING_PAYMENT_ERROR_IN_LOAD_FILE
                        + (itr + 1));
            } else if (!hasParsedTarget) {
                System.out.println(Messages.LOCAL_STORAGE_TARGET_ERROR_IN_LOAD_FILE
                        + (itr + 1));
            } else {
                System.out.println(Messages.LOCAL_STORAGE_INCOME_ERROR_IN_LOAD_FILE
                        + (itr + 1));
            }
        }
    }

    /**
     * Returns a command to sort expense based on saved configuration for sorting obtained from parsing node.
     *
     * @param sortConfig node containing sorting configuration
     * @return command to sort expense
     */
    private static ConsoleCommandSortExpense loadSortCommandSetting(NodeList sortConfig) {
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
     * @throws LocalStorageLoadDataException if type of input node is incorrect
     */
    private static Expense createExpense(Node node) throws LocalStorageLoadDataException {
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            throw new LocalStorageLoadDataException();
        }
        Element element = (Element) node;

        String description = element.getElementsByTagName(XML_EXPENSE_DESCRIPTION_ELEMENT)
                .item(0).getTextContent();
        if (description.isEmpty() || description.trim().isEmpty()) {
            description = null;
        }

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

        NodeList amountNodeList = element.getElementsByTagName(XML_EXPENSE_AMOUNT_ELEMENT);
        String currency = amountNodeList.item(0).getAttributes()
                .getNamedItem(XML_EXPENSE_AMOUNT_CURRENCY_ATTRIBUTE).getTextContent();
        BigDecimal amount = new BigDecimal(amountNodeList.item(0).getTextContent());

        LocalDateTime dateTime = LocalDateTime.parse(element
                .getElementsByTagName(XML_EXPENSE_DATETIME_ELEMENT).item(0).getTextContent());

        String name = element.getElementsByTagName(XML_EXPENSE_NAME_ELEMENT)
                .item(0).getTextContent();

        if (name.isEmpty() || name.trim().isEmpty() || currency.isEmpty() || currency.trim().isEmpty()) {
            throw new LocalStorageLoadDataException();
        }

        String modeOfPayment = element.getElementsByTagName(XML_EXPENSE_MODE_OF_PAYMENT_ELEMENT)
                .item(0).getTextContent();
        if (modeOfPayment.isEmpty() || modeOfPayment.trim().isEmpty()) {
            modeOfPayment = null;
        }
        return new Expense(name, dateTime, description, amount, category, remark, currency, modeOfPayment);
    }

    /**
     * Returns a Target object based on information obtained from parsing a Target node.
     *
     * @param node containing information about a target
     * @return a Target object
     * @throws LocalStorageLoadDataException if type of input node is incorrect
     */
    private static Target createTarget(Node node) throws LocalStorageLoadDataException {
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            throw new LocalStorageLoadDataException();
        }
        Element element = (Element) node;
        String name = element.getElementsByTagName(XML_TARGET_NAME_ELEMENT)
                .item(0).getTextContent();

        String description = element.getElementsByTagName(XML_TARGET_DESCRIPTION_ELEMENT)
                .item(0).getTextContent();
        if (description.isEmpty() || description.trim().isEmpty()) {
            description = null;
        }

        NodeList amountNodeList = element.getElementsByTagName(XML_TARGET_AMOUNT_ELEMENT);
        BigDecimal amount = new BigDecimal(amountNodeList.item(0).getTextContent());
        String currencyAmount = amountNodeList.item(0).getAttributes()
                .getNamedItem(XML_TARGET_CURRENCY_ATTRIBUTE).getTextContent();

        NodeList currentAmountNodeList = element.getElementsByTagName(XML_TARGET_CURRENT_AMOUNT_ELEMENT);
        BigDecimal currentAmount = new BigDecimal(currentAmountNodeList.item(0).getTextContent());
        String currencyCurrentAmount = currentAmountNodeList.item(0).getAttributes()
                .getNamedItem(XML_TARGET_CURRENCY_ATTRIBUTE).getTextContent();

        LocalDateTime dateTime = LocalDateTime.parse(element
                .getElementsByTagName(XML_TARGET_DATETIME_ELEMENT).item(0).getTextContent());

        if (name.isEmpty() || name.trim().isEmpty()
                || currencyCurrentAmount.isEmpty()
                || currencyCurrentAmount.trim().isEmpty()
                || currencyAmount.isEmpty()
                || currencyAmount.trim().isEmpty()) {
            throw new LocalStorageLoadDataException();
        }

        return new Target(name, dateTime, description, amount, currentAmount);
    }

    /**
     * Returns a RecurringPayment object based on information obtained from parsing a RecurringPayment node.
     *
     * @param node containing information about a recurring payment
     * @return a RecurringPayment object
     * @throws LocalStorageLoadDataException if type of input node is incorrect
     */
    private static RecurringPayment createRecurringPayment(Node node) throws LocalStorageLoadDataException {
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            throw new LocalStorageLoadDataException();
        }
        Element element = (Element) node;
        String name = element.getElementsByTagName(XML_RECURRING_PAYMENT_NAME_ELEMENT)
                .item(0).getTextContent();

        String description = element.getElementsByTagName(XML_RECURRING_PAYMENT_DESCRIPTION_ELEMENT)
                .item(0).getTextContent();
        if (description.isEmpty() || description.trim().isEmpty()) {
            description = null;
        }

        String category = element.getElementsByTagName(XML_RECURRING_PAYMENT_CATEGORY_ELEMENT)
                .item(0).getTextContent();
        if (category.isEmpty() || category.trim().isEmpty()) {
            category = null;
        }

        NodeList amountNodeList = element.getElementsByTagName(XML_RECURRING_PAYMENT_AMOUNT_ELEMENT);
        BigDecimal amount = new BigDecimal(amountNodeList.item(0).getTextContent());
        String currency = amountNodeList.item(0).getAttributes()
                .getNamedItem(XML_RECURRING_PAYMENT_CURRENCY_ATTRIBUTE).getTextContent();

        int interval = Integer.parseInt(element.getElementsByTagName(XML_RECURRING_PAYMENT_INTERVAL_ELEMENT)
                .item(0).getTextContent());

        if (name.isEmpty() || name.trim().isEmpty() || currency.isEmpty() || currency.trim().isEmpty()) {
            throw new LocalStorageLoadDataException();
        }

        String modeOfPayment = element.getElementsByTagName(XML_RECURRING_PAYMENT_MODE_OF_PAYMENT_ELEMENT)
                .item(0).getTextContent();
        if (modeOfPayment.isEmpty() || modeOfPayment.trim().isEmpty()) {
            modeOfPayment = null;
        }

        return new RecurringPayment(name, interval, description, amount, category, currency, modeOfPayment);
    }

    /**
     * Returns an Income object based on information obtained from parsing a income node.
     *
     * @param node containing information about an income
     * @return an Income object
     * @throws LocalStorageLoadDataException if type of input node is incorrect
     */
    private static Income createIncome(Node node) throws LocalStorageLoadDataException {
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            throw new LocalStorageLoadDataException();
        }
        Element element = (Element) node;
        String name = element.getElementsByTagName(XML_INCOME_NAME_ELEMENT)
                .item(0).getTextContent();

        LocalDateTime dateTime = LocalDateTime.parse(element
                .getElementsByTagName(XML_INCOME_DATETIME_ELEMENT).item(0).getTextContent());

        String description = element.getElementsByTagName(XML_INCOME_DESCRIPTION_ELEMENT)
                .item(0).getTextContent();
        if (description.isEmpty() || description.trim().isEmpty()) {
            description = null;
        }

        NodeList amountNodeList = element.getElementsByTagName(XML_INCOME_AMOUNT_ELEMENT);
        BigDecimal amount = new BigDecimal(amountNodeList.item(0).getTextContent());
        String currency = amountNodeList.item(0).getAttributes()
                .getNamedItem(XML_EXPENSE_AMOUNT_CURRENCY_ATTRIBUTE).getTextContent();

        if (name.isEmpty() || name.trim().isEmpty() || currency.isEmpty() || currency.trim().isEmpty()) {
            throw new LocalStorageLoadDataException();
        }

        return new Income(name, dateTime, description, amount);
    }

    /**
     * Parse current expenses and configurations for sorting expenses and saves it to a xml file in storage.
     */
    public static void saveToFile(
            ArrayList<Expense> savedExpenses,
            ConsoleCommandSortExpense sortCommandSetting,
            ArrayList<RecurringPayment> savedRecurringPayments,
            ArrayList<Target> savedTargets,
            ArrayList<Income> savedIncomes
    ) {
        try {
            initialiseFile();
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
            parseRecurringPaymentToXml(doc, rootElement, savedRecurringPayments);
            parseTargetToXml(doc, rootElement, savedTargets);
            parseIncomeToXml(doc, rootElement, savedIncomes);
            writeXml(doc);
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println(Messages.LOCAL_STORAGE_ERROR_WRITING_DATA);
        } catch (DOMException e) {
            System.out.println(Messages.LOCAL_STORAGE_ERROR_INVALID_CHARACTER_IN_SAVE_DATA);
        }
    }

    private static void parseExpenseToXml(Document doc, Element rootElement, ArrayList<Expense> savedExpenses) {
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
            Element modeOfPayment = doc.createElement(XML_EXPENSE_MODE_OF_PAYMENT_ELEMENT);
            modeOfPayment.setTextContent(expense.getModeOfPayment());
            expenseElement.appendChild(modeOfPayment);
            index++;
        }
    }

    private static void parseTargetToXml(Document doc, Element rootElement, ArrayList<Target> savedTargets) {
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

    private static void parseRecurringPaymentToXml(Document doc, Element rootElement,
                                                   ArrayList<RecurringPayment> savedRecurringPayments) {
        int index = 1;
        for (RecurringPayment recurringPayment : savedRecurringPayments) {
            Element recurringPaymentElement = doc.createElement(XML_RECURRING_PAYMENT_ELEMENT);
            recurringPaymentElement.setAttribute(XML_RECURRING_PAYMENT_ID_ATTRIBUTE, Integer.toString(index));
            rootElement.appendChild(recurringPaymentElement);
            Element name = doc.createElement(XML_RECURRING_PAYMENT_NAME_ELEMENT);
            name.setTextContent(recurringPayment.getName());
            recurringPaymentElement.appendChild(name);
            Element interval = doc.createElement(XML_RECURRING_PAYMENT_INTERVAL_ELEMENT);
            interval.setTextContent("" + recurringPayment.getInterval());
            recurringPaymentElement.appendChild(interval);
            Element description = doc.createElement(XML_RECURRING_PAYMENT_DESCRIPTION_ELEMENT);
            description.setTextContent(recurringPayment.getDescription());
            recurringPaymentElement.appendChild(description);
            Element amount = doc.createElement(XML_RECURRING_PAYMENT_AMOUNT_ELEMENT);
            amount.setAttribute(XML_RECURRING_PAYMENT_CURRENCY_ATTRIBUTE, recurringPayment.getCurrency());
            amount.setTextContent(recurringPayment.getAmount().toString());
            recurringPaymentElement.appendChild(amount);
            Element category = doc.createElement(XML_RECURRING_PAYMENT_CATEGORY_ELEMENT);
            category.setTextContent(recurringPayment.getCategory());
            recurringPaymentElement.appendChild(category);
            Element modeOfPayment = doc.createElement(XML_RECURRING_PAYMENT_MODE_OF_PAYMENT_ELEMENT);
            modeOfPayment.setTextContent(recurringPayment.getModeOfPayment());
            recurringPaymentElement.appendChild(modeOfPayment);
            index++;
        }
    }

    private static void parseIncomeToXml(Document doc, Element rootElement, ArrayList<Income> savedIncomes) {
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

    private static void writeXml(Document doc)
            throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(saveFile);
        transformer.transform(source, result);
    }
}

