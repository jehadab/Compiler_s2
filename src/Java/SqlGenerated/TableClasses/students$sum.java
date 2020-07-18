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
 public class students$sum { 
 	 double $sum ;    
	 public void set$sum (double value){
	this.$sum = value; 
	 }  
	 public double get$sum(){
	return this.$sum; 
	  }  
 	public void load() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException { 
	}
  
 	 public static void sum() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException{
 	 	
 String JarPath = "src/AggregationFunctions.jar";
 	String JarName = "sum.jar";
 	String ClassName = "CommonAggregations";
 	String MethodName = "SUM";
 	ArrayList<Double> myNumbers = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 12.0));
 	URLClassLoader myClassLoader = new URLClassLoader(	new URL[]{new File(JarPath ).toURI().toURL()},Main.class.getClassLoader()); 
 	Class<?> myClass = Class.forName(ClassName, true, myClassLoader);
 	Method mySingeltonGetterMethod = myClass.getMethod("get" + ClassName,null);
 	Object myObject = mySingeltonGetterMethod.invoke(null);
 	Object myValue = myObject.getClass().getDeclaredMethod(MethodName, List.class).invoke(myObject, myNumbers);
 	System.out.println(myValue);
 }
 	   
	 public List<students$sum> readJsonFile(){
	return null;
	
	 }
 }