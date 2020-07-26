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
 public class city implements Cloneable {
  	public country    country ;
  	public String    name ;
  	public double    id ;   
 	 public void setcountry(country value){
 	this.country  = value ; 
 	} 
 	 public void setname(String value){
 	this.name  = value ; 
 	} 
 	 public void setid(double value){
 	this.id  = value ; 
 	}   
 	 public country getcountry(){
 	return country ;   
 	} 
 	 public String getname(){
 	return name ;   
 	} 
 	 public double getid(){
 	return id ;   
 	}   
	static List<city> entityObject  = new ArrayList<>();
 	public void load() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException ,CloneNotSupportedException, IOException{ 
	
	 city objcity = new city();
	 try{
		entityObject.add((city)objcity.clone()); 
 } 
catch (CloneNotSupportedException c){
			 c.printStackTrace();
	 }
  }
  
	 public List<city> readJsonFile(){
	return null;
	
	 }public List<city> readCsvFile() throws IOException{
	return null;
	}
	 public void printContentFunction()
	{
	System.out.println("-----------------------------------------------------------------------------");
	System.out.printf("%10s " ,"name");
System.out.printf("%10s " ,"id");
System.out.printf("%10s " ,"name");
System.out.printf("%10s " ,"region");
System.out.printf("%10s " ,"country");
System.out.printf("%10s " ,"name");
System.out.printf("%10s " ,"id");
	System.out.println();

	System.out.println("-----------------------------------------------------------------------------");
	
	for(city obj:entityObject)
	{
	System.out.format(" %10s " , obj.getname());
	 System.out.format(" %10s " , obj.getid());
	 System.out.format(" %10s " , obj.getname());
	 System.out.format(" %10s " , obj.getregion());
	 System.out.format(" %10s " , obj.getcountry());
	 System.out.format(" %10s " , obj.getname());
	 System.out.format(" %10s " , obj.getid());
	 System.out.println();
	}
	System.out.println("-----------------------------------------------------------------------------");
	}public <T> T get_types(JsonElement object)
	{
region region = new region();
	boolean fill_region_name = false ; 
if (object.getAsJsonObject().get("name") != null){
region.setname(object.getAsJsonObject().get("name").getAsString());
fill_region_name = true ; 
}
if(fill_region_name)
return (T) region;
return null;
}
 }