 package Java.SqlGenerated.TableClasses; 
import java.util.List; 
import java.util.ArrayList; 
import com.google.gson.Gson; 
import com.google.gson.JsonArray; 
import com.google.gson.JsonElement; 
import com.google.gson.JsonObject; 
import com.google.gson.stream.JsonReader; 
import java.io.FileNotFoundException; 
import java.io.FileReader; 
 public class d {
  	String    name ;
  	double    id ;   
 	String tablePath = "D:";
	String tableType = "json"; 
 	static List<d> entityObject  ;
 	public void load(){
	System.out.println("hiiiii");
	}
	public List<d> readJsonFile(){
	List<d> result = new ArrayList<>();
	FileReader fr = null;Gson json = new Gson();
	try {
	fr=new FileReader(tablePath);
	}catch(FileNotFoundException e) {
	e.printStackTrace();}
	JsonReader reader = new JsonReader(fr);
	JsonObject testing = json.fromJson(fr, JsonObject.class);
	JsonElement json_ele = testing.get("d");
	JsonArray j = json_ele.getAsJsonArray();
	return result;
	}
 }