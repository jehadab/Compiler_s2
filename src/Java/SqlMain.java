package Java;
 import Java.SqlGenerated.TableClasses.colors_id$colors1_id1_color1;
 import Java.SqlGenerated.TableClasses.colors1; import Java.SqlGenerated.TableClasses.colors;
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
import java.util.HashSet;
import java.util.Set;
 public class SqlMain { 
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException ,CloneNotSupportedException { 
 	 colors1 tablecolors1 = new colors1(); 
	 tablecolors1.load(); 
 	 colors tablecolors = new colors(); 
	 tablecolors.load();  Func(); }
  private static void Func()throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException ,CloneNotSupportedException{
  colors_id$colors1_id1_color1 y = new colors_id$colors1_id1_color1();
  y.load();
        
 	}
 }