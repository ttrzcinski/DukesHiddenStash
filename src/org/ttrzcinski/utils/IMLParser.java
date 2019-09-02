package org.ttrzcinski.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Parser for IML path.
 */
public class IMLParser {

  /**
   * Passed IML path.
   */
  private String imlPath;

  /**
   * Parsed source path.
   */
  private String sourcePath;

  /**
   * Parsed test path.
   */
  private String testPath;

  /**
   * Parses given IML path.
   *
   * @param imlPath given IML path
   * @return handle of IML parser instance
   */
  public IMLParser of(String imlPath) {
    var fixedIMLPath = imlPath != null ? imlPath.trim() : "";
    if (ParamCheck.isPath(fixedIMLPath) || fixedIMLPath.endsWith(".iml")) {
      this.imlPath = imlPath;
      Document document = this.readXMLDocument(this.imlPath);
    }
    return this;
  }

  /**
   * Reads XML Document form given path.
   */
  private Document readXMLDocument(String path) {
    // Check entered path
    if (!ParamCheck.isPath(path)) {
      return null;
    }
    // Start processing XML
    Document document = parseXML(path);
    if (document != null) {
      String projectsPath = Path.of(path).toFile().getParent();
      HashMap gotten = this.parseXPath(
          document,
          "/module/component/content/sourceFolder",
          List.of("url", "isTestSource"));
      for (int i = 0; i < 2; i++) {
        if (gotten.containsKey(String.valueOf(i))) {
          String gottenValue = gotten.get(String.valueOf(i)).toString().replace("file://$MODULE_DIR$", projectsPath);
          if (gottenValue.endsWith("|true")) {
            this.testPath = gottenValue.substring(0, gottenValue.length() - 5);
          } else if (gottenValue.endsWith("|false")) {
            this.sourcePath = gottenValue.substring(0, gottenValue.length() - 6);
          }
        }
      }
      // Fix paths for Windows
      if (OSInfo.isWindows()) {
        if (this.testPath != null) {
          this.testPath = this.testPath.replaceAll("/", "\\\\");
        }
        if (this.sourcePath != null) {
          this.sourcePath = this.sourcePath.replaceAll("/", "\\\\");
        }
      }
    }
    return document;
  }

  /**
   * Parses XPath from given XML DOM.
   *
   * @param document passed document
   * @param expression given XPath expression
   * @param attributes set of attributes
   * @return list of requested values
   */
  private HashMap<String, String> parseXPath(Document document,
      String expression, String[] attributes) {
    List<String> list = Arrays.asList(attributes);
    return this.parseXPath(document, expression, list);
  }

  /**
   * Parses XPath from given XML DOM.
   *
   * @param document passed document
   * @param expression given XPath expression
   * @param attributes list of attributes
   * @return list of requested values
   */
  private HashMap<String, String> parseXPath(Document document,
      String expression, Set<String> attributes) {
    List<String> list = new ArrayList<String>(attributes);
    return this.parseXPath(document, expression, list);
  }

  /**
   * Parses XPath from given XML DOM.
   *
   * @param document passed document
   * @param expression given XPath expression
   * @param attributes list of attributes
   * @return list of requested values
   */
  private HashMap<String, String> parseXPath(Document document,
      String expression, List<String> attributes) {
    // Prepare list as response
    var response = new HashMap<String, String>();
    // Check entered parameters
    if (document == null || !ParamCheck.isSet(expression)
        || !ParamCheck.isSet(attributes)) {
      return response;
    }
    // Create new instance of XPath
    XPath xPath = XPathFactory.newInstance().newXPath();
    // Prepare list of nodes from XML
    NodeList nodeList = null;
    try {
      nodeList = (NodeList) xPath.compile(expression).evaluate(
          document, XPathConstants.NODESET);
    } catch (XPathExpressionException e) {
      nodeList = null;
    }
    // Stop, if node list is set
    if (nodeList == null) {
      return response;
    }
    // Start processing those attributes
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node nNode = nodeList.item(i);
      if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        String url = this.parseXMLAttribute_asString(nNode, "url");
        boolean test = this.parseXMLAttribute_asBool(nNode, "isTestSource");
        response.put(String.valueOf(i), String.format("%s|%s", url, test));
      }
    }
    return response;
  }

  private String parseXMLAttribute_asString(Node node, String attributeName) {
    return ((Element) node).getAttribute(attributeName);
  }

  /**
   * Returns boolean value from XML attribute.
   *
   * @param node passed XML node
   * @param attributeName pointed attribute's name
   * @return boolean value
   */
  private int parseXMLAttribute_asInt(Node node, String attributeName) {
    return Integer.valueOf(
        ((Element) node).getAttribute(attributeName)
    );
  }

  /**
   * Returns boolean value from XML attribute.
   *
   * @param node passed XML node
   * @param attributeName pointed attribute's name
   * @return boolean value
   */
  private boolean parseXMLAttribute_asBool(Node node, String attributeName) {
    return ((Element) node).getAttribute(attributeName)
        .trim().toLowerCase().equals("true");
  }

  /**
   * Parses XML from given file path.
   *
   * @param filePath given file path
   * @return handle to the XML document
   */
  private Document parseXML(String filePath) {
    Document document = null;
    // Checks entered file path
    if (!ParamCheck.isPath(filePath)) {
      return document;
    }
    // Start processing document
    try {
      document = DocumentBuilderFactory.newInstance()
          .newDocumentBuilder()
          .parse(filePath);
      document.getDocumentElement().normalize();
    } catch (SAXException | IOException | ParserConfigurationException e) {
      e.printStackTrace();
      document = null;
    }
    return document;
  }

  /**
   * Checks, if given IML Parser instance is ready for processing.
   *
   * @return true means ready, false otherwise
   */
  public boolean isReady() {
    return ParamCheck.isSet(this.imlPath)
        && ParamCheck.isSet(this.sourcePath)
        && ParamCheck.isSet(this.testPath);
  }

  /**
   * Returns parsed sources directory path.
   *
   * @return sources path
   */
  public String getSourcePath() {
    return this.sourcePath;
  }

  /**
   * Returns parsed test directory path.
   *
   * @return test path
   */
  public String getTestPath() {
    return this.testPath;
  }
}
