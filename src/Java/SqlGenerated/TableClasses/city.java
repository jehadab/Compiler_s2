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
  	public region    region ;
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
 	 public void setregion(region value){
 	this.region  = value ; 
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
 	 public region getregion(){
 	return region ;   
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
	System.out.printf(" %10s    %10s    %20s   %20s   %20s   %20s  " ,  "country" ,  "name" ,  "id" ,  "region"  "name" ,  "id" ) ;	System.out.println();

	System.out.println("-----------------------------------------------------------------------------");
	
	for(city obj:entityObject)
	{
	System.out.format("  %5s    %5s    %20s    %20s    %20s    %20s  " ,  obj.getcountry(), obj.getname(), obj.getid(),obj.getregion()  obj.getname(),obj.getid() );
	System.out.println();
	}
	System.out.println("-----------------------------------------------------------------------------");
	}public <T> T get_table(JsonArray array)
	{
country t_country = new country();
for (int ii = 0; ii < array.size(); ii++)
	{
	if (array.get(ii).getAsJsonObject().get("name") != null)
{
	t_country.setname(array.get(ii).getAsJsonObject().get("name").getAsString());
	}
	if (array.get(ii).getAsJsonObject().get("id") != null)
{
	t_country.setid(array.get(ii).getAsJsonObject().get("id").getAsDouble());
	}
	if (array.get(ii).getAsJsonObject().get("region") != null)
{
	}
	}
	return (T)t_country;
}
 }