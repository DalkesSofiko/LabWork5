package utils;

import models.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.*;
import org.w3c.dom.*;

/**
 * Утилитный класс для работы с XML-файлами.
 * Обеспечивает загрузку коллекции организаций из файла и её сохранение в формате XML.
 */
public class XMLHandler {

    /**
     * Загружает коллекцию организаций из указанного XML-файла.
     * Если файл не найден, возвращает пустую коллекцию.
     *
     * @param filename путь к файлу с данными
     * @return HashMap с загруженными организациями, где ключ — ID организации
     */
    public static HashMap<Integer, Organization> loadFromFile(String filename) {
        HashMap<Integer, Organization> collection = new HashMap<>();

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename))) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(bis);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("organization");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element orgElement = (Element) nodeList.item(i);
                Organization org = parseOrganization(orgElement);
                if (org != null) {
                    collection.put(org.getId(), org);
                }
            }

            System.out.println("Загружено " + collection.size() + " организаций");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден, создаем новую пустую коллекцию");
        } catch (Exception e) {
            System.err.println("Ошибка при загрузке: " + e.getMessage());
        }

        return collection;
    }

    /**
     * Сохраняет коллекцию организаций в указанный XML-файл.
     *
     * @param filename путь к файлу для сохранения
     * @param collection коллекция организаций для сохранения
     */
    public static void save(String filename, HashMap<Integer, Organization> collection) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("collection");
            document.appendChild(root);

            for (Organization org : collection.values()) {
                Element orgElement = createOrganizationElement(document, org);
                root.appendChild(orgElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            try (FileWriter writer = new FileWriter(filename)) {
                StreamResult result = new StreamResult(writer);
                transformer.transform(source, result);
            }

            System.out.println("Коллекция сохранена в файле " + filename);
        } catch (IOException e) {
            System.err.println("Ошибка записи в файле: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    /**
     * Парсит элемент XML в объект Organization.
     *
     * @param element XML-элемент, содержащий данные организации
     * @return объект Organization или null, если данные некорректны
     */
    private static Organization parseOrganization(Element element) {
        try {
            Organization org = new Organization();

            int id = Integer.parseInt(getElementText(element, "id"));
            org.setId(id);

            String name = getElementText(element, "name");
            if (name == null || name.isEmpty()) {
                System.err.println("Организация без имени пропущена");
                return null;
            }
            org.setName(name);

            Coordinates coords = parseCoordinates(element);
            if (coords == null) return null;
            org.setCoordinates(coords);

            long creationTime = Long.parseLong(getElementText(element, "creationDate"));
            org.setCreationDate(new java.util.Date(creationTime));

            long turnover = Long.parseLong(getElementText(element, "annualTurnover"));
            org.setAnnualTurnover(turnover);

            org.setFullName(getElementText(element, "fullName"));

            String typeStr = getElementText(element, "type");
            if (typeStr != null && !typeStr.isEmpty()) {
                org.setType(OrganizationType.valueOf(typeStr));
            }

            Address address = parseAddress(element);
            if (address == null) return null;
            org.setPostalAddress(address);

            return org;
        } catch (Exception e) {
            System.err.println("Ошибка при разборе организации: " + e.getMessage());
            return null;
        }
    }

    /**
     * Парсит вложенный элемент coordinates в объект Coordinates.
     *
     * @param coordParent родительский XML-элемент организации
     * @return объект Coordinates или null при ошибке
     */
    private static Coordinates parseCoordinates(Element coordParent) {
        try {
            Element coordElement = (Element) coordParent.getElementsByTagName("coordinates").item(0);
            if (coordElement == null) {
                return null;
            }

            long x = Long.parseLong(getElementText(coordElement, "x"));
            double y = Double.parseDouble(getElementText(coordElement, "y"));

            return new Coordinates(x, y);
        } catch (Exception e) {
            System.err.println("Ошибка при разборе координат: " + e.getMessage());
            return null;
        }
    }

    /**
     * Парсит вложенный элемент postalAddress в объект Address.
     *
     * @param parent родительский XML-элемент организации
     * @return объект Address или null при ошибке
     */
    private static Address parseAddress(Element parent) {
        try {
            Element addrElement = (Element) parent.getElementsByTagName("postalAddress").item(0);
            if (addrElement == null) return null;

            String street = getElementText(addrElement, "street");
            return new Address(street);
        } catch (Exception e) {
            System.err.println("Ошибка при разборе адреса: " + e.getMessage());
            return null;
        }
    }

    /**
     * Создает XML-элемент для объекта Organization.
     *
     * @param doc документ XML
     * @param org объект организации
     * @return созданный XML-элемент organization
     */
    private static Element createOrganizationElement(Document doc, Organization org) {
        Element orgElement = doc.createElement("organization");

        addElement(doc, orgElement, "id", String.valueOf(org.getId()));
        addElement(doc, orgElement, "name", org.getName());
        addElement(doc, orgElement, "creationDate", String.valueOf(org.getCreationDate().getTime()));
        addElement(doc, orgElement, "annualTurnover", String.valueOf(org.getAnnualTurnover()));
        addElement(doc, orgElement, "fullName", org.getFullName());

        if (org.getType() != null) {
            addElement(doc, orgElement, "type", org.getType().name());
        }

        Element coordsElement = doc.createElement("coordinates");
        addElement(doc, coordsElement, "x", String.valueOf(org.getCoordinates().getX()));
        addElement(doc, coordsElement, "y", String.valueOf(org.getCoordinates().getY()));
        orgElement.appendChild(coordsElement);

        Element addrElement = doc.createElement("postalAddress");
        String street = org.getPostalAddress().getStreet();
        addElement(doc, addrElement, "street", street != null ? street : "");
        orgElement.appendChild(addrElement);

        return orgElement;
    }

    /**
     * Добавляет дочерний текстовый элемент в родительский XML-элемент.
     *
     * @param doc документ XML
     * @param parent родительский элемент
     * @param tagName имя нового тега
     * @param text текстовое содержимое элемента
     */
    private static void addElement(Document doc, Element parent, String tagName, String text) {
        Element element = doc.createElement(tagName);
        element.setTextContent(text != null ? text : "");
        parent.appendChild(element);
    }

    /**
     * Извлекает текстовое содержимое первого дочернего элемента с заданным именем.
     *
     * @param parent родительский элемент
     * @param tagName имя искомого тега
     * @return текст элемента или null, если элемент не найден
     */
    private static String getElementText(Element parent, String tagName) {
        NodeList list = parent.getElementsByTagName(tagName);
        if (list.getLength() > 0) {
            String text = list.item(0).getTextContent();
            return text != null ? text.trim() : null;
        }
        return null;
    }
}