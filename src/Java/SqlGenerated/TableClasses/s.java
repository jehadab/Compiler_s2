 package Java.SqlGenerated.TableClasses; 
import java.util.List; 
import Java.SymbolTable.Column;
import Java.SymbolTable.Type; 
import Java.Main; 
import java.util.ArrayList; 
import com.google.gson.Gson; 
import com.google.gson.JsonArray; 
import com.google.gson.JsonElement; 
import com.google.gson.JsonObject; 
import com.google.gson.stream.JsonReader; 
import java.io.FileNotFoundException; 
import java.io.FileReader; 
 public class s {
  	boolean    die ;
  	String    name ;
  	double    id ; 
 	 public void setdie(boolean value){
 	this.die  = value ; 
 	} 
 	 public void setname(String value){
 	this.name  = value ; 
 	} 
 	 public void setid(double value){
 	this.id  = value ; 
 	}  
 	 public boolean getdie(){
 	return die ;   
 	} 
 	 public String getname(){
 	return name ;   
 	} 
 	 public double getid(){
 	return id ;   
 	}    
 	String tablePath = "D:";
	String tableType = "json"; 
 	static List<s> entityObject  ;
  	public void load(){
	if(tableType == "json")
	{
	entityObject = readJsonFile();
	}
	else 
	{
	}
	}
	 public List<s> readJsonFile(){
	List<s> result = new ArrayList<>();
	FileReader fr = null;Gson json = new Gson();
	try {
	fr=new FileReader(tablePath);
	}catch(FileNotFoundException e) {
	e.printStackTrace();}
	JsonReader reader = new JsonReader(fr);
	JsonObject testing = json.fromJson(fr, JsonObject.class);
	JsonElement json_ele = testing.get("s");
	JsonArray j = json_ele.getAsJsonArray();
	for (int i = 0 ; i < j.size() ; i++ ) {
	s tableName = new s();
	if(j.get(i).getAsJsonObject().get("die") != null);
	{
	tableName.setdie(j.get(i).getAsJsonObject().get("die").getAsBoolean());
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
	 } }