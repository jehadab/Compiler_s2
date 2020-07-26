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
 public class clubs implements Cloneable {
  	public city    city ;
  	public String    name ;
  	public double    id ;   
 	 public void setcity(city value){
 	this.city  = value ; 
 	} 
 	 public void setname(String value){
 	this.name  = value ; 
 	} 
 	 public void setid(double value){
 	this.id  = value ; 
 	}   
 	 public city getcity(){
 	return city ;   
 	} 
 	 public String getname(){
 	return name ;   
 	} 
 	 public double getid(){
 	return id ;   
 	}    
 	String tablePath = "C:/Users/Dell/Desktop/Final02/02/clubs.json";
	String tableType = "json"; 
	static List<clubs> entityObject  = new ArrayList<>();
 	public void load() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException ,CloneNotSupportedException, IOException{ 
	if(tableType == "json")
	{
	entityObject = readJsonFile();
	}
	else 
	{
	entityObject = readCsvFile();
	}
	}
  
	 public List<clubs> readJsonFile(){
	List<clubs> result = new ArrayList<>();
	FileReader fr = null;Gson json = new Gson();
	try {
	fr=new FileReader(tablePath);
	}catch(FileNotFoundException e) {
	e.printStackTrace();}
	JsonReader reader = new JsonReader(fr);
	JsonObject testing = json.fromJson(fr, JsonObject.class);
	JsonElement json_ele = testing.get("clubs");
	JsonArray j = json_ele.getAsJsonArray();
	for (int i = 0 ; i < j.size() ; i++ ) {
	clubs tableName = new clubs();
	if (j.get(i).getAsJsonObject().get("city").isJsonObject() == true)
	{
	    city.setcountry(get_types(j.get(i).getAsJsonObject().get("city").getAsJsonObject().get("country").deepCopy()));
	city.setname(j.get(i).getAsJsonObject().get("city").getAsJsonObject().get("name").getAsString());
	city.setid(j.get(i).getAsJsonObject().get("city").getAsJsonObject().get("id").getAsDouble());
	}
	 if(j.get(i).getAsJsonObject().get("name") != null);
	{
	tableName.setname(j.get(i).getAsJsonObject().get("name").getAsString());
	}
	 if(j.get(i).getAsJsonObject().get("id") != null);
	{
	tableName.setid(j.get(i).getAsJsonObject().get("id").getAsDouble());
	}
	 result.add(tableName);
	}
	return result;
	 }public List<clubs> readCsvFile() throws IOException{
	List<clubs> result = new ArrayList<>(); 
	BufferedReader csvReader = null;
	String[] data = new String[0];
	File csvFile = new File("C:/Users/Dell/Desktop/Final02/02/clubs.json");
	csvReader = new BufferedReader(new FileReader(csvFile));
	if(csvFile.isFile())
	{
	 String row; 
	clubs classname = new clubs();
	CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT.withHeader( "city",    "name",    "id"   ).withIgnoreHeaderCase().withTrim());
	 for (CSVRecord csvRecord: csvParser)
	{
	if(csvRecord.get("city") != null){
	classname.setcity(city);
	}
	if(csvRecord.get("name") != null){
	classname.setname(csvRecord.get("name"));
	}
	if(csvRecord.get("id") != null){
	classname.setid(Double.parseDouble(csvRecord.get("id")));
	}
	}
	 result.add(classname);
	}
	return result;}
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
System.out.printf("%10s " ,"city");
System.out.printf("%10s " ,"name");
System.out.printf("%10s " ,"id");
	System.out.println();

	System.out.println("-----------------------------------------------------------------------------");
	
	for(clubs obj:entityObject)
	{
	System.out.format(" %10s " , obj.getname());
	 System.out.format(" %10s " , obj.getid());
	 System.out.format(" %10s " , obj.getname());
//	 System.out.format(" %10s " , obj.getregion());
//	 System.out.format(" %10s " , obj.getcountry());
	 System.out.format(" %10s " , obj.getname());
	 System.out.format(" %10s " , obj.getid());
	 System.out.format(" %10s " , obj.getcity());
	 System.out.format(" %10s " , obj.getname());
	 System.out.format(" %10s " , obj.getid());
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