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
 public class country implements Cloneable {
  	public String    name ;
  	public double    id ;
  	public String    name ;
  	public region    region ;   
 	 public void setname(String value){
 	this.name  = value ; 
 	} 
 	 public void setid(double value){
 	this.id  = value ; 
 	} 
 	 public void setname(String value){
 	this.name  = value ; 
 	} 
 	 public void setregion(region value){
 	this.region  = value ; 
 	}   
 	 public String getname(){
 	return name ;   
 	} 
 	 public double getid(){
 	return id ;   
 	} 
 	 public String getname(){
 	return name ;   
 	} 
 	 public region getregion(){
 	return region ;   
 	}   
	static List<country> entityObject  = new ArrayList<>();
 	public void load() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException ,CloneNotSupportedException, IOException{ 
	
	 country objcountry = new country();
	 try{
		entityObject.add((country)objcountry.clone()); 
 } 
catch (CloneNotSupportedException c){
			 c.printStackTrace();
	 }
  }
  
	 public List<country> readJsonFile(){
	return null;
	
	 }public List<country> readCsvFile() throws IOException{
	return null;
	}
	 public void printContentFunction()
	{
	System.out.println("-----------------------------------------------------------------------------");
	System.out.printf(" %10s    %20s   %10s    %20s  " ,  "name" ,  "id" ,  "name"  "region" ) ;	System.out.println();

	System.out.println("-----------------------------------------------------------------------------");
	
	for(country obj:entityObject)
	{
	System.out.format("  %5s    %20s    %5s    %20s  " ,  obj.getname(), obj.getid(),obj.getname() obj.getregion() );
	System.out.println();
	}
	System.out.println("-----------------------------------------------------------------------------");
	}public <T> T get_types(JsonElement object)
	{
}
 }