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
  	public String    name ;
  	public double    id ;
  	public String    name ;
  	public region    region ;
  	public country    country ;
  	public String    name ;
  	public double    id ;
  	public city    city ;
  	public String    name ;
  	public double    id ;   
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
 	 public void setcountry(country value){
 	this.country  = value ; 
 	} 
 	 public void setname(String value){
 	this.name  = value ; 
 	} 
 	 public void setid(double value){
 	this.id  = value ; 
 	} 
 	 public void setcity(city value){
 	this.city  = value ; 
 	} 
 	 public void setname(String value){
 	this.name  = value ; 
 	} 
 	 public void setid(double value){
 	this.id  = value ; 
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
 	 public country getcountry(){
 	return country ;   
 	} 
 	 public String getname(){
 	return name ;   
 	} 
 	 public double getid(){
 	return id ;   
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
	if(j.get(i).getAsJsonObject().get("name") != null);
	{
	tableName.setname(j.get(i).getAsJsonObject().get("name").getAsString());
	}
	 if(j.get(i).getAsJsonObject().get("id") != null);
	{
	tableName.setid(j.get(i).getAsJsonObject().get("id").getAsDouble());
	}
	 if(j.get(i).getAsJsonObject().get("name") != null);
	{
	tableName.setname(j.get(i).getAsJsonObject().get("name").getAsString());
	}
	 if(j.get(i).getAsJsonObject().get("region") != null);
	{
	if (j.get(i).getAsJsonObject().get("region").isJsonObject() == true)
	{
	}
	}
	 if(j.get(i).getAsJsonObject().get("country") != null);
	{
	if (j.get(i).getAsJsonObject().get("country").isJsonObject() == true)
	{
	}
	}
	 if(j.get(i).getAsJsonObject().get("name") != null);
	{
	tableName.setname(j.get(i).getAsJsonObject().get("name").getAsString());
	}
	 if(j.get(i).getAsJsonObject().get("id") != null);
	{
	tableName.setid(j.get(i).getAsJsonObject().get("id").getAsDouble());
	}
	 if(j.get(i).getAsJsonObject().get("city") != null);
	{
	if (j.get(i).getAsJsonObject().get("city").isJsonObject() == true)
	{
	}
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
	CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT.withHeader( "name",    "id",    "name"    "region"    "country",    "name",    "id"    "city",    "name",    "id"   ).withIgnoreHeaderCase().withTrim());
	 for (CSVRecord csvRecord: csvParser)
	{
	if(csvRecord.get("name") != null){
	classname.setname(csvRecord.get("name"));
	}
	if(csvRecord.get("id") != null){
	classname.setid(Double.parseDouble(csvRecord.get("id")));
	}
	if(csvRecord.get("name") != null){
	classname.setname(csvRecord.get("name"));
	}
	if(csvRecord.get("region") != null){
	classname.setregion(new Object());
	}
	if(csvRecord.get("country") != null){
	classname.setcountry(new Object());
	}
	if(csvRecord.get("name") != null){
	classname.setname(csvRecord.get("name"));
	}
	if(csvRecord.get("id") != null){
	classname.setid(Double.parseDouble(csvRecord.get("id")));
	}
	if(csvRecord.get("city") != null){
	classname.setcity(new Object());
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
	System.out.printf(" %10s    %20s   %10s    %20s   %10s    %20s   %20s   %10s    %20s   %20s  " ,  "name" ,  "id" ,  "name"  "region"  "country" ,  "name" ,  "id"  "city" ,  "name" ,  "id" ) ;	System.out.println();

	System.out.println("-----------------------------------------------------------------------------");
	
	for(clubs obj:entityObject)
	{
	System.out.format("  %5s    %20s    %5s    %20s    %5s    %20s    %20s    %5s    %20s    %20s  " ,  obj.getname(), obj.getid(),obj.getname() obj.getregion()  obj.getcountry(), obj.getname(),obj.getid()  obj.getcity(), obj.getname(),obj.getid() );
	System.out.println();
	}
	System.out.println("-----------------------------------------------------------------------------");
	}public <T> T get_types(JsonElement object)
	{
}
 }