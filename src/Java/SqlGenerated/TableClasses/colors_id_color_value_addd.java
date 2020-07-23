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
 public class colors_id_color_value_addd implements Cloneable {
  	public double    $colors_id ;
  	public String    $colors_value ;
  	public String    $colors_color ;
  	public Address    $colors_addd ;   
 	 public void set$colors_id(double value){
 	this.$colors_id  = value ; 
 	} 
 	 public void set$colors_value(String value){
 	this.$colors_value  = value ; 
 	} 
 	 public void set$colors_color(String value){
 	this.$colors_color  = value ; 
 	} 
 	 public void set$colors_addd(Address value){
 	this.$colors_addd  = value ; 
 	}   
 	 public double get$colors_id(){
 	return $colors_id ;   
 	} 
 	 public String get$colors_value(){
 	return $colors_value ;   
 	} 
 	 public String get$colors_color(){
 	return $colors_color ;   
 	} 
 	 public Address get$colors_addd(){
 	return $colors_addd ;   
 	}   
	static List<colors_id_color_value_addd> entityObject  = new ArrayList<>();
 	public void load() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException ,CloneNotSupportedException, IOException{ 
	
	 colors_id_color_value_addd objcolors_id_color_value_addd = new colors_id_color_value_addd();
	for(int colorscounter = 0 ; colorscounter < colors.entityObject.size(); colorscounter++){
		 objcolors_id_color_value_addd.$colors_id = colors.entityObject.get(colorscounter).id; 
		 objcolors_id_color_value_addd.$colors_value = colors.entityObject.get(colorscounter).value; 
		 objcolors_id_color_value_addd.$colors_color = colors.entityObject.get(colorscounter).color; 

	 try{
		entityObject.add((colors_id_color_value_addd)objcolors_id_color_value_addd.clone()); 
 } 
catch (CloneNotSupportedException c){
			 c.printStackTrace();
	 }
 }  }
  
	 public List<colors_id_color_value_addd> readJsonFile(){
	return null;
	
	 }public List<colors_id_color_value_addd> readCsvFile() throws IOException{
	return null;
	}
	 public void printContentFunction()
	{
	System.out.println("-----------------------------------------------------------------------------");
	System.out.printf(" %10s    %20s   %20s   %20s  " ,  "$colors_id" ,  "$colors_value" ,  "$colors_color" ,  "$colors_addd" ) ;	System.out.println();

	System.out.println("-----------------------------------------------------------------------------");
	
	for(colors_id_color_value_addd obj:entityObject)
	{
	System.out.format("  %5s    %20s    %20s    %20s  " ,  obj.get$colors_id(), obj.get$colors_value(), obj.get$colors_color(),obj.get$colors_addd() );
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