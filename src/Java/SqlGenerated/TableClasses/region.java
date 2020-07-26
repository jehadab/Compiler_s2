 package Java.SqlGenerated.TableClasses; 

import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
 import Java.Main;
import java.io.BufferedReader; 
import java.io.*; 
import com.google.gson.Gson; 
import com.google.gson.JsonArray; 
import com.google.gson.JsonElement; 
import com.google.gson.JsonObject; 
import com.google.gson.stream.JsonReader; 
import java.io.FileNotFoundException; 
import java.io.FileReader; 
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import Java.SymbolTable.Column;
import Java.SymbolTable.Type; 
import java.util.HashSet;
import java.util.Set;
 public class region implements Cloneable {
  	public String    region_name ;   
 	 public void setregion_name(String value){
 	this.region_name  = value ; 
 	}   
 	 public String getregion_name(){
 	return region_name ;   
 	}   
	static List<region> entityObject  = new ArrayList<>();
 	public void load() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException ,CloneNotSupportedException, IOException{ 
	
	 region objregion = new region();
	  List<clubs> clubsList = clubs.entityObject ;
  
	 
	for(int clubscounter = 0 ; clubscounter < clubsList.size(); clubscounter++){ 
	 try{
		 

			entityObject.add((region)objregion.clone()); 
 } 
catch (CloneNotSupportedException c){
			 c.printStackTrace();
	 }

 } 		  }
  
	 public List<region> readJsonFile(){
	return null;
	
	 }public List<region> readCsvFile() throws IOException{
	return null;
	}
	 public void printContentFunction()
	{
	System.out.println("-----------------------------------------------------------------------------");
	System.out.printf("%30s " ,  "region_name" ) ;	System.out.println();

	for(region obj:entityObject)
	{
	System.out.format("%30s " , obj.getregion_name());
	System.out.println();
	}
	System.out.println("-----------------------------------------------------------------------------");
	}public <T> T get_types(JsonElement object)
	{
return null;
}
 }