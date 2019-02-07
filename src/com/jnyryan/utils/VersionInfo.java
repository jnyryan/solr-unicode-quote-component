package com.jnyryan.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;


public class VersionInfo<T> {
	
	public String getVersion(T obj) { 
//    	getVersion1(obj);
//    	getVersion2(obj);
//    	getVersion3(obj);
    	getVersion4(obj);
    	return "";
    }
	
	public String getVersion1(T obj) { 
    	System.out.println(obj.getClass().getPackage().getSpecificationVersion());
    	System.out.println(obj.getClass().getPackage().getImplementationVersion()); 	
    	return obj.getClass().getPackage().getSpecificationVersion();
    }
	
	public String getVersion2(T obj) { 
    	try {
    	Enumeration<URL> resources = obj.getClass().getClassLoader().getResources("META-INF/MANIFEST.MF");
		while (resources.hasMoreElements()) {
		      Manifest manifest = new Manifest(resources.nextElement().openStream());
		      Attributes attributes = manifest.getMainAttributes();
		      System.out.println(attributes.getValue("Main-Class"));
		}		
        return "Solr Unicode Quote Search Component";
    	} 
    	catch (IOException e) {
    		return "Solr Unicode Quote Search Component error";
    	}
    }
    

    public String getVersion3(T obj) { 
    	try {
	    	// Example using HTMLEmail from Apache Commons Email 
	    	Class theClass = obj.getClass();
	    	  
	    	// Find the path of the compiled class 
	    	String classPath = theClass.getResource(theClass.getSimpleName() + ".class").toString(); 
	    	System.out.println("Class: " + classPath); 
	    	 
	    	// Find the path of the lib which includes the class 
	    	String libPath = classPath.substring(0, classPath.lastIndexOf("!")); 
	    	System.out.println("Lib:   " + libPath); 
	    	 
	    	// Find the path of the file inside the lib jar 
	    	String filePath = libPath + "!/META-INF/MANIFEST.MF"; 
	    	System.out.println("File:  " + filePath); 
	    	 
	    	// We look at the manifest file, getting two attributes out of it 
	    	Manifest manifest = new Manifest(new URL(filePath).openStream()); 
	    	Attributes attr = manifest.getMainAttributes(); 
	    	System.out.println("Manifest-Version: " + attr.getValue("Manifest-Version")); 
	    	System.out.println("Implementation-Version: " + attr.getValue("Implementation-Version"));
	    	return "";
    	}
    	catch (IOException E) {
    		return "Solr Unicode Quote Search Component error";
    	}
    }
    
    public String getVersion4(T obj) { 
    	String x = obj.getClass().getPackage().getImplementationVersion();
    	System.out.println("File:  " + x); 
    	return x;
    }
    
    

}
