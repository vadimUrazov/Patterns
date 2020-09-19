package org.example;

import com.sun.webkit.dom.NodeImpl;
import com.sun.xml.internal.messaging.saaj.soap.ver1_1.SOAPPart1_1Impl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPPart;
import java.util.*;

/**
 * Hello world!
 */
interface INodeAccessor {
    Node getChildNodeByName(Node parentNode, String nodeName);

    List getSiblingChildNodesByName(Node parentNode, String nodeName);

    Map getChildNodesMap(Node parentNode);

    Node createChildNode(Node parentNode, String nodeName);

    void setNodeValue(Node node, String value);

    String getNodeValue(Node node);

    Node getParentNode(Node node);

    Node removeChildNode(Node parentNode, Node childNode);

    boolean hasValue(Node node);
}

class DefaultNodeAccessor implements INodeAccessor {
    public Node getChildNodeByName(Node parent, String nodeName) {
        for (Node node = parent.getFirstChild();
             node != null; node = node.getNextSibling()) {
            if (node.getNodeType() == Node.ELEMENT_NODE
                    && node.getNodeName().equals(nodeName)) {
                return node;
            }
        }
        return null;
    }

    public List getSiblingChildNodesByName(Node parent, String nodeName) {
        List foundNodes = new ArrayList();
        for (Node node = parent.getFirstChild();
             node != null; node = node.getNextSibling()) {
            if (node.getNodeType() == Node.ELEMENT_NODE
                    && node.getNodeName().equals(nodeName)) {
                foundNodes.add(node);
            }
        }
        return foundNodes;
    }

    public Map getChildNodesMap(Node parent) {
        Map foundNodes = new HashMap();
        for (Node node = parent.getFirstChild();
             node != null; node = node.getNextSibling()) {
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Object obj = foundNodes.get(node.getNodeName());
                if (obj == null) {
                    foundNodes.put(node.getNodeName(), node);
                } else {
                    if (obj instanceof Node) {
                        List nodeList = new ArrayList();
                        nodeList.add(obj);
                        nodeList.add(node);
                        foundNodes.put(node.getNodeName(), nodeList);
                    } else {
                        ((List) obj).add(node);
                    }
                }
            }
        }
        return foundNodes;
    }

    public String getNodeValue(Node node) {
        if (hasValue(node)) {
            return node.getFirstChild().getNodeValue();
        }
        return "";
    }

    public void setNodeValue(Node node, String value) {
        if (node.getFirstChild() != null) {
            node.getFirstChild().setNodeValue(value);
        } else {
            Node textNode = node.getOwnerDocument().createTextNode(value);
            node.appendChild(textNode);
        }
    }

    public Node getParentNode(Node node) {
        return node.getParentNode();
    }

    public Node removeChildNode(Node parent, Node child) {
        return parent.removeChild(child);
    }

    public Node createChildNode(Node parentNode, String nodeName) {
        Document ownerDocument;
        if (parentNode.getNodeType() == Node.DOCUMENT_NODE) {
            ownerDocument = (Document) parentNode;
        } else {
            ownerDocument = parentNode.getOwnerDocument();
        }
        Node node = ownerDocument.createElement(nodeName);
        parentNode.appendChild(node);
        return node;
    }

    public boolean hasValue(Node node) {
        if (node != null) {
            if (node.getFirstChild() != null) {
                if (node.getFirstChild().getNodeValue() != null &&
                        node.getFirstChild().getNodeValue().trim().length() != 0) {
                    return true;
                }
            }
        }
        return false;
    }


}

class Client {
    public static void main(String[] args) {
        SOAPPart s = new SOAPPart1_1Impl();
        DefaultNodeAccessor defaultNodeAccessor = new DefaultNodeAccessor();
        System.out.println(defaultNodeAccessor.hasValue(s));
    }
}