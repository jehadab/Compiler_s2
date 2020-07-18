 package Java.SqlGenerated.TableClasses; 
import java.util.List; 
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
 public class colors_id_color_value {
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
 	public void load()  { 
	}
  
	 public List<colors_id_color_value> readJsonFile(){
	return null;
	
	 }public List<colors_id_color_value> readCsvFile() throws IOException{
	return null;
	}
 }