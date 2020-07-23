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
import java.util.List;
import Java.SymbolTable.Column;
import Java.SymbolTable.Type; 
import java.util.HashSet;
import java.util.Set;
 public class Street implements Cloneable {
  	public String    streetName ;
  	public double    streetId ;   
 	 public void setstreetName(String value){
 	this.streetName  = value ; 
 	} 
 	 public void setstreetId(double value){
 	this.streetId  = value ; 
 	}   
 	 public String getstreetName(){
 	return streetName ;   
 	} 
 	 public double getstreetId(){
 	return streetId ;   
 	}   
	static List<Street> entityObject  = new ArrayList<>();
 	public void load() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException ,CloneNotSupportedException, IOException{ 
	
	 Street objStreet = new Street();
	 try{
		entityObject.add((Street)objStreet.clone()); 
 } 
catch (CloneNotSupportedException c){
			 c.printStackTrace();
	 }
  }
  
	 public List<Street> readJsonFile(){
	return null;
	
	 }public List<Street> readCsvFile() throws IOException{
	return null;
	}
	 public void printContentFunction()
	{
	System.out.println("-----------------------------------------------------------------------------");
	System.out.printf(" %10s    %20s  " ,  "streetName" ,  "streetId" ) ;	System.out.println();

	System.out.println("-----------------------------------------------------------------------------");
	
	for(Street obj:entityObject)
	{
	System.out.format("  %5s    %20s  " ,  obj.getstreetName(),obj.getstreetId() );
	System.out.println();
	}
	System.out.println("-----------------------------------------------------------------------------");
	}public <T> T get_types(JsonElement object)
	{
return null;
}public <T> T get_table(JsonArray array)
	{
return null;
}
 }