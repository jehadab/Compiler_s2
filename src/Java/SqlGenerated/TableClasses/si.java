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
 public class si {
  	double    col2 ;
  	String    col1 ;   
 	 public void setcol2(double value){
 	this.col2  = value ; 
 	} 
 	 public void setcol1(String value){
 	this.col1  = value ; 
 	}   
 	 public double getcol2(){
 	return col2 ;   
 	} 
 	 public String getcol1(){
 	return col1 ;   
 	}   
 	public void load()  { 
	}
  
	 public List<si> readJsonFile(){
	return null;
	 }
 }