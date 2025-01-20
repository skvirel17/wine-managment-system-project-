package control;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import entity.Wine;
import enums.SweetnessLevel;
import enums.WineTypeE;
import org.w3c.dom.*;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileImporter {


    public List<Wine> importXML(File xmlFile) {
        List<Wine> wines = new ArrayList<>();
        try {




            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();


            Document document = builder.parse(xmlFile);


            document.getDocumentElement().normalize();


            Element root = document.getDocumentElement();
            System.out.println("Root element: " + root.getNodeName());


            NodeList nodeList = document.getElementsByTagName("Wine");


            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element wine = (Element) node;

                    String catalogNumber = wine.getElementsByTagName("wineCatalogNumber").item(0).getTextContent();
                    String name = wine.getElementsByTagName("wineName").item(0).getTextContent();
                    String wineProductionYear = wine.getElementsByTagName("wineProductionYear").item(0).getTextContent();
                    String winePricePerBootle = wine.getElementsByTagName("winePricePerBootle").item(0).getTextContent();
                    String wineTypeE = wine.getElementsByTagName("wineType").item(0).getTextContent();
                    String wineSweetnessLevel = wine.getElementsByTagName("wineSweetnessLevel").item(0).getTextContent();
                    String wineManufactureNumber = wine.getElementsByTagName("wineManufactureNumber").item(0).getTextContent();
                    String wineDescription = wine.getElementsByTagName("wineDescription").item(0).getTextContent();
                    wines.add(new Wine(catalogNumber, wineManufactureNumber, name, wineDescription, Integer.valueOf(wineProductionYear),
                            Float.valueOf(winePricePerBootle), SweetnessLevel.valueOf(wineSweetnessLevel), null,
                            WineTypeE.valueOf(wineTypeE)));
                }
            }
            return wines;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return wines;
    }
}

