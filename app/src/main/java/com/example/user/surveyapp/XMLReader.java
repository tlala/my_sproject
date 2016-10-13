package com.example.user.surveyapp;

import android.provider.DocumentsContract;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by User on 28/08/2016.
 */
public class XMLReader {
    public Document document;

    public XMLReader(String xml){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try
        {
            builder = factory.newDocumentBuilder();
            document = builder.parse( new InputSource( new StringReader( xml ) ) );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getElementContentByName(String name){
        //return the first element's content with the recieved name

        //Element e = document.getElementsByTagName(name);
        NodeList nList;
        try {
            nList = document.getElementsByTagName(name);
        }catch (Exception e){
            return "NULL";
        }
        return nList.item(0).getTextContent();
    }

    public String getElementAttribute(String name,String attr){
        NodeList nList;
        try {
            nList = document.getElementsByTagName(name);
        }catch (Exception e){
            return "NULL";
        }
        return  ((Element)nList.item(0)).getAttribute(attr);
    }

}
