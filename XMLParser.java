package com.jainpuja.androidmusicplayer_ver2;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.os.Environment;
import android.util.Log;

public class XMLParser {
    InputSource inputSource = null;
    // constructor
    public XMLParser() {

    }

    /**
     * Getting XML from URL making HTTP request
     //* @param url string
     * */

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }
    public String getxmlfromSDcard(String path) {
        String xml = null;
        try
        {
            File fl = new File(path);
            FileInputStream fin = new FileInputStream(fl);
            xml = convertStreamToString(fin);
            //Make sure you close all streams.
            fin.close();
            System.out.println(""+xml);
            //return ret;


        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return xml;


    }

    /**
     * Getting XML DOM element
   //  * @param XML string
     * */
    public Document getDomElement(String xml){
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            File xmlFile = new File( Environment.getExternalStorageDirectory()+ "/jainpuja/jainpuja.xml");
            FileInputStream xmlFileInputStream = new FileInputStream(xmlFile);
            inputSource = new InputSource(xmlFileInputStream);
            doc = db.parse(inputSource);

        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        }

        return doc;
    }

    /** Getting node value
     * @param elem element
     */
    public final String getElementValue( Node elem ) {
        Node child;
        if( elem != null){
            if (elem.hasChildNodes()){
                for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
                    if( child.getNodeType() == Node.TEXT_NODE  ){
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

    /**
     * Getting node value
    // * @param Element node
    // * @param key string
     * */
    public String getValue(Element item, String str) {
        NodeList n = item.getElementsByTagName(str);
        return this.getElementValue(n.item(0));
    }
}
