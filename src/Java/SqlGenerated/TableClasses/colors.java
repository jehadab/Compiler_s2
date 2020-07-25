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
 public class colors implements Cloneable {
  	public String    color ;
  	public city    ci ;
  	public country    country ;
  	public String    name ;
  	public double    id ;
  	public double    id ;
  	public String    value ;   
 	 public void setcolor(String value){
 	this.color  = value ; 
 	} 
 	 public void setci(city value){
 	this.ci  = value ; 
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
 	 public void setid(double value){
 	this.id  = value ; 
 	} 
 	 public void setvalue(String value){
 	this.value  = value ; 
 	}   
 	 public String getcolor(){
 	return color ;   
 	} 
 	 public city getci(){
 	return ci ;   
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
 	 public double getid(){
 	return id ;   
 	} 
 	 public String getvalue(){
 	return value ;   
 	}    
 	String tablePath = "C:/Users/Dell/Desktop/Final/Data/Colors.json";
	String tableType = "json"; 
	static List<colors> entityObject  = new ArrayList<>();
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
  
	 public List<colors> readJsonFile(){
	List<colors> result = new ArrayList<>();
	FileReader fr = null;Gson json = new Gson();
	try {
	fr=new FileReader(tablePath);
	}catch(FileNotFoundException e) {
	e.printStackTrace();}
	JsonReader reader = new JsonReader(fr);
	JsonObject testing = json.fromJson(fr, JsonObject.class);
	JsonElement json_ele = testing.get("colors");
	JsonArray j = json_ele.getAsJsonArray();
	for (int i = 0 ; i < j.size() ; i++ ) {
	colors tableName = new colors();
	if(j.get(i).getAsJsonObject().get("color") != null);
	{
	tableName.setcolor(j.get(i).getAsJsonObject().get("color").getAsString());
	}
	 if(j.get(i).getAsJsonObject().get("ci") != null);
	{
	if (j.get(i).getAsJsonObject().get("ci").isJsonObject() == true)
	{
	}
	}
	 if(j.get(i).getAsJsonObject().get("country") != null);
	{
	}
	 if(j.get(i).getAsJsonObject().get("name") != null);
	{
	tableName.setname(j.get(i).getAsJsonObject().get("name").getAsString());
	}
	 if(j.get(i).getAsJsonObject().get("id") != null);
	{
	tableName.setid(j.get(i).getAsJsonObject().get("id").getAsDouble());
	}
	 if(j.get(i).getAsJsonObject().get("id") != null);
	{
	tableName.setid(j.get(i).getAsJsonObject().get("id").getAsDouble());
	}
	 if(j.get(i).getAsJsonObject().get("value") != null);
	{
	tableName.setvalue(j.get(i).getAsJsonObject().get("value").getAsString());
	}
	 result.add(tableName);
	}
	return result;
	 }public List<colors> readCsvFile() throws IOException{
	List<colors> result = new ArrayList<>(); 
	BufferedReader csvReader = null;
	String[] data = new String[0];
	File csvFile = new File("C:/Users/Dell/Desktop/Final/Data/Colors.json");
	csvReader = new BufferedReader(new FileReader(csvFile));
	if(csvFile.isFile())
	{
	 String row; 
	colors classname = new colors();
	CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT.withHeader( "color",    "ci",    "country",    "name",    "id"    "id",    "value"   ).withIgnoreHeaderCase().withTrim());
	 for (CSVRecord csvRecord: csvParser)
	{
	if(csvRecord.get("color") != null){
	classname.setcolor(csvRecord.get("color"));
	}
	if(csvRecord.get("ci") != null){
	classname.setci(new Object());
	}
	if(csvRecord.get("country") != null){
	classname.setcountry(
	}
	if(csvRecord.get("name") != null){
	classname.setname(csvRecord.get("name"));
	}
	if(csvRecord.get("id") != null){
	classname.setid(Double.parseDouble(csvRecord.get("id")));
	}
	if(csvRecord.get("id") != null){
	classname.setid(Double.parseDouble(csvRecord.get("id")));
	}
	if(csvRecord.get("value") != null){
	classname.setvalue(csvRecord.get("value"));
	}
	}
	 result.add(classname);
	}
	return result;}
	 public void printContentFunction()
	{
	System.out.println("-----------------------------------------------------------------------------");
	System.out.printf(" %10s    %20s   %10s    %20s   %20s   %20s   %20s  " ,  "color" ,  "ci" ,  "country" ,  "name" ,  "id"  "id" ,  "value" ) ;	System.out.println();

	System.out.println("-----------------------------------------------------------------------------");
	
	for(colors obj:entityObject)
	{
	System.out.format("  %5s    %20s    %5s    %20s    %20s    %20s    %20s  " ,  obj.getcolor(), obj.getci(), obj.getcountry(), obj.getname(),obj.getid()  obj.getid(),obj.getvalue() );
	System.out.println();
	}
	System.out.println("-----------------------------------------------------------------------------");
	}public <T> T get_table(JsonArray array)
	{
city t_ci = new city();
for (int ii = 0; ii < array.size(); ii++)
	{
	if (array.get(ii).getAsJsonObject().get("country") != null)
{
	}
	if (array.get(ii).getAsJsonObject().get("name") != null)
{
	t_ci.setname(array.get(ii).getAsJsonObject().get("name").getAsString());
	}
	if (array.get(ii).getAsJsonObject().get("id") != null)
{
	t_ci.setid(array.get(ii).getAsJsonObject().get("id").getAsDouble());
	}
	}
	return (T)t_ci;
}
 }