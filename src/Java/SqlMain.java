package Java;
 import Java.SqlGenerated.TableClasses.Color_id; import Java.SqlGenerated.TableClasses.colors;

 import java.lang.reflect.Field;
 import java.util.*;

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
 import java.util.List;
import Java.SymbolTable.Column;
import Java.SymbolTable.Type; 
 public class SqlMain { 
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException {
        colors tablecolors = new colors();
        tablecolors.load();
        System.out.println(Arrays.toString(tablecolors.getClass().getFields()));
        Field field[] = tablecolors.getClass().getFields();
        System.out.println(field[0].getAnnotatedType().getType().getTypeName().equals(String.class.getTypeName()));
        Set<Field> fields = new HashSet<>(Arrays.asList(field));
//        fields.iterator().
        System.out.println("set "+((Field) fields.iterator().next()).getName());
        System.out.println(field[0].getName());



	 Func(); }
  private static void Func()throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException{
  Color_id x = new Color_id();
  x.load();
        
 	}
 }