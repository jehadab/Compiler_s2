 package Java.SqlGenerated.TableClasses; 
import java.util.List; 
 import Java.Main;
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
 public class students {
  	String    name ;
  	double    id ;
  	double    mark ;   
 	 public void setname(String value){
 	this.name  = value ; 
 	} 
 	 public void setid(double value){
 	this.id  = value ; 
 	} 
 	 public void setmark(double value){
 	this.mark  = value ; 
 	}   
 	 public String getname(){
 	return name ;   
 	} 
 	 public double getid(){
 	return id ;   
 	} 
 	 public double getmark(){
 	return mark ;   
 	}    
 	String tablePath = "src/Colors.json";
	String tableType = "json"; 
 	static List<students> entityObject   ;

 	public void load()  { 
	if(tableType == "json")
	{
	entityObject = readJsonFile();

	}
	else 
	{
	}
	}
  
	 public List<students> readJsonFile(){
	List<students> result = new ArrayList<>();
	FileReader fr = null;Gson json = new Gson();
	try {
	fr=new FileReader(tablePath);
	}catch(FileNotFoundException e) {
	e.printStackTrace();}
	JsonReader reader = new JsonReader(fr);
	JsonObject testing = json.fromJson(fr, JsonObject.class);
	JsonElement json_ele = testing.get("students");
	JsonArray j = json_ele.getAsJsonArray();
	for (int i = 0 ; i < j.size() ; i++ ) {
	students tableName = new students();
	if(j.get(i).getAsJsonObject().get("name") != null);
	{
	tableName.setname(j.get(i).getAsJsonObject().get("name").getAsString());
	}
	 if(j.get(i).getAsJsonObject().get("id") != null);
	{
	tableName.setid(j.get(i).getAsJsonObject().get("id").getAsDouble());
	}
	 if(j.get(i).getAsJsonObject().get("mark") != null);
	{
	tableName.setmark(j.get(i).getAsJsonObject().get("mark").getAsDouble());
	}
	 result.add(tableName);
	}
	return result;
	 }
 }