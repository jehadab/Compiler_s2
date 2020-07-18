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
 public class students_mark {
  	double    mark ;   
 	 public void setmark(double value){
 	this.mark  = value ; 
 	}   
 	 public double getmark(){
 	return mark ;   
 	}   
 	public void load()  { 
	}
  
	 public List<students_mark> readJsonFile(){
	return null;
	
	 }
 }