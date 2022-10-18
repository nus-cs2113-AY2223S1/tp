package seedu.moneygowhere.storage;

import seedu.moneygowhere.commands.ConsoleCommandSortExpense;
import seedu.moneygowhere.common.Messages;
import seedu.moneygowhere.data.expense.Expense;
import seedu.moneygowhere.data.expense.ExpenseManager;
import seedu.moneygowhere.exceptions.LocalStorageLoadDataInputError;

import static seedu.moneygowhere.common.Configurations.LOCAL_STORAGE_DIRECTORY_PATH;
import static seedu.moneygowhere.common.Configurations.LOCAL_STORAGE_DATA_FILE_PATH;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class LocalStorage {
    private final String XML_ROOT = "MoneyGoWhere";
    private final String XML_SORTCONFIG_ELEMENT = "Sort_Configuration";
    private final String XML_SORTCONFIG_TYPE_ATTRIBUTE = "Type";
    private final String XML_SORTCONFIG_ORDER_ATTRIBUTE = "Order";
    private final String XML_EXPENSE_ELEMENT = "Expense";
    private final String XML_EXPENSE_ID_ATTRIBUTE = "Id";
    private final String XML_EXPENSE_NAME_ELEMENT = "Name";
    private final String XML_EXPENSE_DATETIME_ELEMENT = "Datetime";
    private final String XML_EXPENSE_DESCRIPTION_ELEMENT = "Description";
    private final String XML_EXPENSE_AMOUNT_ELEMENT = "Amount";
    private final String XML_EXPENSE_AMOUNT_CURRENCY_ATTRIBUTE = "Currency";
    private final String XML_EXPENSE_CATEGORY_ELEMENT = "Category";
    private final String XML_EXPENSE_REMARKS_ELEMENT = "Remark";
    private File saveFile;

    public LocalStorage() {
        initialiseFile();
    }

    private void initialiseFile() {
        File directory = new File(LOCAL_STORAGE_DIRECTORY_PATH);
        directory.mkdir();
        String newFilePath = new File(LOCAL_STORAGE_DATA_FILE_PATH).getAbsolutePath();
        this.saveFile = new File(newFilePath);
    }

    public void loadFromFile(ExpenseManager expenseManager) {
        Expense loadExpense;
        boolean hasParsedSortconfig = false;
        int itr = 0;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
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
     * This method reads in the sortCommandSetting in text file.
     */
    private ConsoleCommandSortExpense loadSortCommandSetting(NodeList sortConfig) {
        String type = sortConfig.item(0).getAttributes()
                .getNamedItem(XML_SORTCONFIG_TYPE_ATTRIBUTE).getTextContent();
        String order = sortConfig.item(0).getAttributes()
                .getNamedItem(XML_SORTCONFIG_ORDER_ATTRIBUTE).getTextContent();
        return new ConsoleCommandSortExpense(type, order);
    }

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

    public void saveToFile(ArrayList<Expense> savedExpenses, ConsoleCommandSortExpense sortCommandSetting) {
        Integer index = 1;
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
            for (Expense expense : savedExpenses) {
                Element expenseElement = doc.createElement(XML_EXPENSE_ELEMENT);
                expenseElement.setAttribute(XML_EXPENSE_ID_ATTRIBUTE, index.toString());
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
            writeXml(doc);
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println(Messages.LOCAL_STORAGE_ERROR_WRITING_DATA);
        }
    }

    // write doc to output stream
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

