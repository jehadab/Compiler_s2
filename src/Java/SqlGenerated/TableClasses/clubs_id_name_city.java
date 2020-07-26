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
 public class clubs_id_name_city implements Cloneable {
  	public city    $clubs_city ;
  	public double    $clubs_id ;
  	public String    $clubs_name ;   
 	 public void set$clubs_city(city value){
 	this.$clubs_city  = value ; 
 	} 
 	 public void set$clubs_id(double value){
 	this.$clubs_id  = value ; 
 	} 
 	 public void set$clubs_name(String value){
 	this.$clubs_name  = value ; 
 	}   
 	 public city get$clubs_city(){
 	return $clubs_city ;   
 	} 
 	 public double get$clubs_id(){
 	return $clubs_id ;   
 	} 
 	 public String get$clubs_name(){
 	return $clubs_name ;   
 	}   
	static List<clubs_id_name_city> entityObject  = new ArrayList<>();
 	public void load() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException ,CloneNotSupportedException, IOException{ 
	
	 clubs_id_name_city objclubs_id_name_city = new clubs_id_name_city();
	for(int clubscounter = 0 ; clubscounter < clubs.entityObject.size(); clubscounter++){
		 objclubs_id_name_city.$clubs_city = clubs.entityObject.get(clubscounter).city; 
		 objclubs_id_name_city.$clubs_id = clubs.entityObject.get(clubscounter).id; 
		 objclubs_id_name_city.$clubs_name = clubs.entityObject.get(clubscounter).name; 
		
	 try{
		entityObject.add((clubs_id_name_city)objclubs_id_name_city.clone()); 
 } 
catch (CloneNotSupportedException c){
			 c.printStackTrace();
	 }
 }  }
  
	 public List<clubs_id_name_city> readJsonFile(){
	return null;
	
	 }public List<clubs_id_name_city> readCsvFile() throws IOException{
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
System.out.printf("%10s " ,"$clubs_city");
System.out.printf("%10s " ,"$clubs_id");
System.out.printf("%10s " ,"$clubs_name");
	System.out.println();

	System.out.println("-----------------------------------------------------------------------------");
	
	for(clubs_id_name_city obj:entityObject)
	{
	System.out.format(" %10s " , obj.getname());
	 System.out.format(" %10s " , obj.getid());
	 System.out.format(" %10s " , obj.getname());
	 System.out.format(" %10s " , obj.getregion());
	 System.out.format(" %10s " , obj.getcountry());
	 System.out.format(" %10s " , obj.getname());
	 System.out.format(" %10s " , obj.getid());
	 System.out.format(" %10s " , obj.get$clubs_city());
	 System.out.format(" %10s " , obj.get$clubs_id());
	 System.out.format(" %10s " , obj.get$clubs_name());
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
boolean fill_region_name = false ; 
if (object.getAsJsonObject().get("name") != null){
region.setname(object.getAsJsonObject().get("name").getAsString());
fill_region_name = true ; 
}
if(fill_country_name)
return (T) country;
if(fill_country_id)
return (T) country;
if(fill_country_region)
return (T) country;
if(fill_region_name)
return (T) region;
return null;
}
 }