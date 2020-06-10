
package jtm.extra05;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.*;

import java.io.StringReader;
import java.io.StringWriter;

//  #1
// Import necessary classes from javax.xml.* and, if necessary org.w3c.dom.*

public class XMLCars {

	/*
	 * #2 Declare static variables to remember previously generated structure of XML
	 */
//	static final String schema = "http://www.w3.org/2001/XMLSchema";
//
//	static DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//	static DocumentBuilder dBuilder;
//
//	static {
//		try {
//			dBuilder = dbFactory.newDocumentBuilder();
//		} catch (ParserConfigurationException e) {
//			e.printStackTrace();
//		}
//	}
//
//	static Document doc = dBuilder.newDocument();
//	
//	static Element rootElement;
	
	static Document doc;
	static Element cars;

	public void addCar(int id, String model, String color, int year, float price, String notes) throws Exception {
		// TODO #3
		/*- Implement method which adds new car elements into XML structure.
		 * Note, that:
		 *   1. if method is called 1st time, one root element "car" should be
		 *      created, but if method is called again, just new "car" element is added into
		 *      "cars" tree.
		 *   2. Car id should be padded with leading zeroes if id is smaller than 1111.
		 *      E.g. if int id=33, then  attribute of XML should be id="0033".
		 *   3. At the end of car element XML comment should be added with value of passed notes
		 *      (This is not checked by validator using XSD schema,
		 *      but is checked when generated XML is produced as string.)
		 *      
		 * Hint:
		 *   Look at https://docs.oracle.com/javase/7/docs/api/javax/xml/parsers/DocumentBuilder.html and
		 *           https://docs.oracle.com/javase/7/docs/api/org/w3c/dom/package-summary.html
		 */

//		if (doc.hasChildNodes()) {
//			rootElement = doc.getDocumentElement();
//		} else {
//			rootElement = doc.createElement("cars");
//			doc.appendChild(rootElement);
//		}
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		
		
		if(doc == null) {
			doc = dBuilder.newDocument();
			cars = doc.createElement("cars");
			doc.appendChild(cars);
			
		}

		Element car = doc.createElement("car");
		cars.appendChild(car);

		Attr attr = doc.createAttribute("id");
		String id1 = String.format("%04d", id);
		attr.setValue(id1);
		car.setAttributeNode(attr);

		Attr notesAttr = doc.createAttribute("notes");
		notesAttr.setValue(notes);
		car.setAttributeNode(notesAttr);

		Element carModel = doc.createElement("model");
		carModel.appendChild(doc.createTextNode(model));
		car.appendChild(carModel);

		Element carColor = doc.createElement("color");
		carColor.appendChild(doc.createTextNode(color));
		car.appendChild(carColor);

		Element carYear = doc.createElement("year");
		carYear.appendChild(doc.createTextNode(Integer.toString(year)));
		car.appendChild(carYear);

		Element carPrice = doc.createElement("price");
		carPrice.appendChild(doc.createTextNode(Float.toString(price)));
		car.appendChild(carPrice);

		Comment comment = doc.createComment(notes);
		car.appendChild(comment);

	}

	public String getXML() throws Exception {

		/*
		 * No. 4: Write a code that will create String containing XML as that matches
		 * car.xsd requirements.
		 * 
		 * HINT look at:
		 * https://docs.oracle.com/javase/7/docs/api/javax/xml/parsers/DocumentBuilder.
		 * html
		 * 
		 * Note, that XML should be "prettied" with line breaks and indentations of 2
		 * spaces for internal elements
		 * 
		 * HINT: look at:
		 * https://docs.oracle.com/javase/7/docs/api/javax/xml/transform/Transformer.
		 * html
		 */
		DOMSource domSource = new DOMSource(doc);
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		transformer.transform(domSource, result);
		return sw.toString();

	}

	/*-
	 * @param schemaSource — String containing XSD schema definition from car.xsd file
	 * @param xmlSource — String containing XML for car
	 * @return — true, if xmlSource is valid
	 * @throws Exception — if xmlSource is invalid
	 *         (will be thrown by javax.xml.validation.Validator automatically)
	 */
	public static boolean validateXMLSchema(String schemaSource, String xmlSource) throws Exception {
//		return false;

		/*- No. 2: Write a code to validate prepared XML source according to schema source
		 * Note that Exception should be thrown, if passed XML file is invalid.
		 * HINT:
		 * Use https://docs.oracle.com/javase/7/docs/api/javax/xml/validation/Validator.html
		 */
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = factory.newSchema(new StreamSource(new StringReader(schemaSource)));
		Validator validator = schema.newValidator();
		validator.validate(new StreamSource(new StringReader(xmlSource)));

		return true;
	}

}
