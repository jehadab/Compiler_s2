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
 public class colors implements Cloneable {
  	public String    color ;
  	public double    id ;
  	public String    value ;   
 	 public void setcolor(String value){
 	this.color  = value ; 
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
 	 public double getid(){
 	return id ;   
 	} 
 	 public String getvalue(){
 	return value ;   
 	}    
 	String tablePath = "src/Data/colors.json";
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
	File csvFile = new File("src/Data/colors.json");
	csvReader = new BufferedReader(new FileReader(csvFile));
	if(csvFile.isFile())
	{
	 String row; 
	colors classname = new colors();
	CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT.withHeader().withIgnoreHeaderCase().withTrim());
	 for (CSVRecord csvRecord: csvParser)
	{
	}
	 result.add(classname);
	}
	return result;}
	 public void printContentFunction()
	{
	System.out.println("-----------------------------------------------------------------------------");
	System.out.printf("%30s %30s %30s " ,  "color" ,  "id" ,  "value" ) ;	System.out.println();

	for(colors obj:entityObject)
	{
	System.out.format("%30s %30s %30s " , obj.getcolor(),obj.getid(),obj.getvalue());
	System.out.println();
	}
	System.out.println("-----------------------------------------------------------------------------");
	}
 }