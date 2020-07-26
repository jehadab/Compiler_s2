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
 public class country implements Cloneable {
  	public String    name ;
  	public double    id ;
  	public region    region ;   
 	 public void setname(String value){
 	this.name  = value ; 
 	} 
 	 public void setid(double value){
 	this.id  = value ; 
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
 	 public region getregion(){
 	return region ;   
 	}   
	static List<country> entityObject  = new ArrayList<>();
 	public void load() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException ,CloneNotSupportedException, IOException{ 
	
	 country objcountry = new country();
	  List<clubs> clubsList = clubs.entityObject ;
  
	  
	   	  clubsList.removeIf(clubss -> !  Objects.equals( clubss.city.country.region.region_name , "Asia" ) && !Objects.equals( clubss.city.country.region.region_name , "Africa" ) ) ;  
 		 
 
	for(int clubscounter = 0 ; clubscounter < clubsList.size(); clubscounter++){ 
	 try{
		 

			entityObject.add((country)objcountry.clone()); 
 } 
catch (CloneNotSupportedException c){
			 c.printStackTrace();
	 }

 } 		  }
  
	 public List<country> readJsonFile(){
	return null;
	
	 }public List<country> readCsvFile() throws IOException{
	return null;
	}
	 public void printContentFunction()
	{
	System.out.println("-----------------------------------------------------------------------------");
	System.out.printf("%30s %30s %30s " ,  "name" ,  "id" ,  "region" ) ;	System.out.println();

	for(country obj:entityObject)
	{
	System.out.format("%30s %30s %30s " , obj.getname(),obj.getid(),obj.getregion());
	System.out.println();
	}
	System.out.println("-----------------------------------------------------------------------------");
	}public <T> T get_types(JsonElement object)
	{
return null;
}
 }