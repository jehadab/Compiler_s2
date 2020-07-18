package Java;
 import Java.SqlGenerated.TableClasses.students$sum; 
 import Java.SqlGenerated.TableClasses.students$sum_mark; 
 import Java.SqlGenerated.TableClasses.students_mark;   
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
 public class SqlMain { 
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException { Func(); }
  private static void Func()throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException{
  students$sum x = new students$sum();
  x.load();
   students$sum_mark y = new students$sum_mark();
  y.load();
   students_mark z = new students_mark();
  z.load();
        
 	}
 }