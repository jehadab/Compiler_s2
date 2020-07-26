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
 public class clubs_id_name_city implements Cloneable {
  	public city    $clubs_COLLcity ;
  	public String    $clubs_COLLname ;
  	public double    $clubs_COLLid ;   
 	 public void set$clubs_COLLcity(city value){
 	this.$clubs_COLLcity  = value ; 
 	} 
 	 public void set$clubs_COLLname(String value){
 	this.$clubs_COLLname  = value ; 
 	} 
 	 public void set$clubs_COLLid(double value){
 	this.$clubs_COLLid  = value ; 
 	}   
 	 public city get$clubs_COLLcity(){
 	return $clubs_COLLcity ;   
 	} 
 	 public String get$clubs_COLLname(){
 	return $clubs_COLLname ;   
 	} 
 	 public double get$clubs_COLLid(){
 	return $clubs_COLLid ;   
 	}   
	static List<clubs_id_name_city> entityObject  = new ArrayList<>();
 	public void load() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException ,CloneNotSupportedException, IOException{ 
	
	 clubs_id_name_city objclubs_id_name_city = new clubs_id_name_city();
	  List<clubs> clubsList = clubs.entityObject ;
  
	 
	for(int clubscounter = 0 ; clubscounter < clubsList.size(); clubscounter++){
		 objclubs_id_name_city.$clubs_COLLcity = clubsList.get(clubscounter).city; 
		 objclubs_id_name_city.$clubs_COLLname = clubsList.get(clubscounter).name; 
		 objclubs_id_name_city.$clubs_COLLid = clubsList.get(clubscounter).id; 
		 
	 try{
		 

			entityObject.add((clubs_id_name_city)objclubs_id_name_city.clone()); 
 } 
catch (CloneNotSupportedException c){
			 c.printStackTrace();
	 }

 } 		  }
  
	 public List<clubs_id_name_city> readJsonFile(){
	return null;
	
	 }public List<clubs_id_name_city> readCsvFile() throws IOException{
	return null;
	}
	 public void printContentFunction()
	{
	System.out.println("-----------------------------------------------------------------------------");
	System.out.printf("%30s %30s %30s " ,  "$clubs_COLLcity" ,  "$clubs_COLLname" ,  "$clubs_COLLid" ) ;	System.out.println();

	for(clubs_id_name_city obj:entityObject)
	{
	System.out.format("%30s %30s %30s " , obj.get$clubs_COLLcity(),obj.get$clubs_COLLname(),obj.get$clubs_COLLid());
	System.out.println();
	}
	System.out.println("-----------------------------------------------------------------------------");
	}public <T> T get_types(JsonElement object)
	{
region region = new region();
	country country = new country();
	boolean fill_country_name = false ; 
if (object.getAsJsonObject().get("name") != null){
country.setname(object.getAsJsonObject().get("name").getAsString());
fill_country_name = true ; 
}
boolean fill_country_id = false ; 
if (object.getAsJsonObject().get("id") != null){
country.setid(object.getAsJsonObject().get("id").getAsDouble());
fill_country_id = true ; 
}
boolean fill_country_region = false ; 
if (object.getAsJsonObject().get("region") != null){
country.setregion(get_types(object.getAsJsonObject().get("region").deepCopy()));
fill_country_region = true ; 
}
boolean fill_region_region_name = false ; 
if (object.getAsJsonObject().get("region_name") != null){
region.setregion_name(object.getAsJsonObject().get("region_name").getAsString());
fill_region_region_name = true ; 
}
if(fill_country_name)
return (T) country;
if(fill_country_id)
return (T) country;
if(fill_country_region)
return (T) country;
if(fill_region_region_name)
return (T) region;
return null;
}
 }