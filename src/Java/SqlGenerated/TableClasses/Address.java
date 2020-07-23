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
 public class Address implements Cloneable {
  	public double    id ;
  	public Street    streetInfo ;   
 	 public void setid(double value){
 	this.id  = value ; 
 	} 
 	 public void setstreetInfo(Street value){
 	this.streetInfo  = value ; 
 	}   
 	 public double getid(){
 	return id ;   
 	} 
 	 public Street getstreetInfo(){
 	return streetInfo ;   
 	}    
 	String tablePath = "C:/Users/Dell/Desktop/Final/Data/Colors.json";
	String tableType = "json"; 
	static List<Address> entityObject  = new ArrayList<>();
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
  
	 public List<Address> readJsonFile(){
	List<Address> result = new ArrayList<>();
	FileReader fr = null;Gson json = new Gson();
	try {
	fr=new FileReader(tablePath);
	}catch(FileNotFoundException e) {
	e.printStackTrace();}
	JsonReader reader = new JsonReader(fr);
	JsonObject testing = json.fromJson(fr, JsonObject.class);
	JsonElement json_ele = testing.get("Address");
	JsonArray j = json_ele.getAsJsonArray();
	for (int i = 0 ; i < j.size() ; i++ ) {
	Address tableName = new Address();
	if(j.get(i).getAsJsonObject().get("id") != null);
	{
	tableName.setid(j.get(i).getAsJsonObject().get("id").getAsDouble());
	}
	 if(j.get(i).getAsJsonObject().get("streetInfo") != null);
	{
	if (j.get(i).getAsJsonObject().get("streetInfo").getAsJsonArray() != null){
	JsonArray nested_one = j.get(i).getAsJsonObject().get("streetInfo").getAsJsonArray();
	setstreetInfo(get_table(nested_one));
	}
	if (j.get(i).getAsJsonObject().get("streetInfo").isJsonObject() == true)
	{
//	Java.SymbolTable.Type@5136d012 t_streetInfo = new Java.SymbolTable.Type@5136d012();}
	}}
	 result.add(tableName);
	}
	return result;
	 }public List<Address> readCsvFile() throws IOException{
	List<Address> result = new ArrayList<>(); 
	BufferedReader csvReader = null;
	String[] data = new String[0];
	File csvFile = new File("C:/Users/Dell/Desktop/Final/Data/Colors.json");
	csvReader = new BufferedReader(new FileReader(csvFile));
	if(csvFile.isFile())
	{
	 String row; 
	Address classname = new Address();
	CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT.withHeader( "id",    "streetInfo"   ).withIgnoreHeaderCase().withTrim());
	 for (CSVRecord csvRecord: csvParser)
	{
	if(csvRecord.get("id") != null){
	classname.setid(Double.parseDouble(csvRecord.get("id")));
	}
	if(csvRecord.get("streetInfo") != null){
//	classname.setstreetInfo(new Object());
	}
	}
	 result.add(classname);
	}
	return result;}
	 public void printContentFunction()
	{
	System.out.println("-----------------------------------------------------------------------------");
	System.out.printf(" %10s    %20s  " ,  "id" ,  "streetInfo" ) ;	System.out.println();

	System.out.println("-----------------------------------------------------------------------------");
	
	for(Address obj:entityObject)
	{
	System.out.format("  %5s    %20s  " ,  obj.getid(),obj.getstreetInfo() );
	System.out.println();
	}
	System.out.println("-----------------------------------------------------------------------------");
	}public <T> T get_types(JsonElement object)
	{
return null;
}public <T> T get_table(JsonArray array)
	{
return null;
}
 }