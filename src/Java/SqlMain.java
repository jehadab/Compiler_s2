package Java;
 import Java.SqlGenerated.TableClasses._AGGMAX; import Java.SqlGenerated.TableClasses.songs; import Java.SqlGenerated.TableClasses.clubs; import Java.SqlGenerated.TableClasses.colors1; import Java.SqlGenerated.TableClasses.countries; import Java.SqlGenerated.TableClasses.colors;   
 
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
import java.util.*;
import Java.SymbolTable.Column;
import Java.SymbolTable.Type; 
import java.util.HashSet;
import java.util.Set;
 public class SqlMain { 
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException ,CloneNotSupportedException, IOException { 
 	 songs tablesongs = new songs(); 
	 tablesongs.load(); 
 	 clubs tableclubs = new clubs(); 
	 tableclubs.load(); 
 	 colors1 tablecolors1 = new colors1(); 
	 tablecolors1.load(); 
 	 countries tablecountries = new countries(); 
	 tablecountries.load(); 
 	 colors tablecolors = new colors(); 
	 tablecolors.load();  Func(); }
  private static void Func()throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException ,CloneNotSupportedException, IOException{
  _AGGMAX cl = new _AGGMAX();
  cl.load();
        
 	}
 }