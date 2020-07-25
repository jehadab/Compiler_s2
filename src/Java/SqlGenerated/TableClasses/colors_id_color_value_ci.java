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
 public class colors_id_color_value_ci implements Cloneable {
  	public double    $colors_id ;
  	public city    $colors_ci ;
  	public country    country ;
  	public String    name ;
  	public double    id ;
  	public String    $colors_value ;
  	public String    $colors_color ;   
 	 public void set$colors_id(double value){
 	this.$colors_id  = value ; 
 	} 
 	 public void set$colors_ci(city value){
 	this.$colors_ci  = value ; 
 	} 
 	 public void setcountry(country value){
 	this.country  = value ; 
 	} 
 	 public void setname(String value){
 	this.name  = value ; 
 	} 
 	 public void setid(double value){
 	this.id  = value ; 
 	} 
 	 public void set$colors_value(String value){
 	this.$colors_value  = value ; 
 	} 
 	 public void set$colors_color(String value){
 	this.$colors_color  = value ; 
 	}   
 	 public double get$colors_id(){
 	return $colors_id ;   
 	} 
 	 public city get$colors_ci(){
 	return $colors_ci ;   
 	} 
 	 public country getcountry(){
 	return country ;   
 	} 
 	 public String getname(){
 	return name ;   
 	} 
 	 public double getid(){
 	return id ;   
 	} 
 	 public String get$colors_value(){
 	return $colors_value ;   
 	} 
 	 public String get$colors_color(){
 	return $colors_color ;   
 	}   
	static List<colors_id_color_value_ci> entityObject  = new ArrayList<>();
 	public void load() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException ,CloneNotSupportedException, IOException{ 
	
	 colors_id_color_value_ci objcolors_id_color_value_ci = new colors_id_color_value_ci();
	for(int colorscounter = 0 ; colorscounter < colors.entityObject.size(); colorscounter++){
		 objcolors_id_color_value_ci.$colors_id = colors.entityObject.get(colorscounter).id; 
		 objcolors_id_color_value_ci.$colors_ci = colors.entityObject.get(colorscounter).ci; 
		 objcolors_id_color_value_ci.$colors_value = colors.entityObject.get(colorscounter).value; 
		 objcolors_id_color_value_ci.$colors_color = colors.entityObject.get(colorscounter).color; 
		
	 try{
		entityObject.add((colors_id_color_value_ci)objcolors_id_color_value_ci.clone()); 
 } 
catch (CloneNotSupportedException c){
			 c.printStackTrace();
	 }
 }  }
  
	 public List<colors_id_color_value_ci> readJsonFile(){
	return null;
	
	 }public List<colors_id_color_value_ci> readCsvFile() throws IOException{
	return null;
	}
	 public void printContentFunction()
	{
	System.out.println("-----------------------------------------------------------------------------");
	System.out.printf(" %10s    %20s   %10s    %20s   %20s   %20s   %20s  " ,  "$colors_id" ,  "$colors_ci" ,  "country" ,  "name" ,  "id"  "$colors_value" ,  "$colors_color" ) ;	System.out.println();

	System.out.println("-----------------------------------------------------------------------------");
	
	for(colors_id_color_value_ci obj:entityObject)
	{
	System.out.format("  %5s    %20s    %5s    %20s    %20s    %20s    %20s  " ,  obj.get$colors_id(), obj.get$colors_ci(), obj.getcountry(), obj.getname(),obj.getid()  obj.get$colors_value(),obj.get$colors_color() );
	System.out.println();
	}
	System.out.println("-----------------------------------------------------------------------------");
	}public <T> T get_table(JsonArray array)
	{
city t_$colors_ci = new city();
for (int ii = 0; ii < array.size(); ii++)
	{
	if (array.get(ii).getAsJsonObject().get("country") != null)
{
	}
	if (array.get(ii).getAsJsonObject().get("name") != null)
{
	t_$colors_ci.setname(array.get(ii).getAsJsonObject().get("name").getAsString());
	}
	if (array.get(ii).getAsJsonObject().get("id") != null)
{
	t_$colors_ci.setid(array.get(ii).getAsJsonObject().get("id").getAsDouble());
	}
	}
	return (T)t_$colors_ci;
}
 }