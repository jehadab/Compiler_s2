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
 public class f {
  	String    name ; 
 	 public void setname(String value){
 	this.name  = value ; 
 	}  
 	 public String getname(){
 	return name ;   
 	}   
  	public void load(){
	}
	 public List<f> readJsonFile(){
	return null;
	
	 } }