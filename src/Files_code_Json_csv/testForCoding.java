//package Files_code_Json_csv;
//
//import Java.SqlGenerated.TableClasses.colors;
//import Java.SqlGenerated.TableClasses.colors_id_color_value;
//import java.util.List;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;
//import Java.Main;
//import java.io.BufferedReader;
//import java.io.*;
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.stream.JsonReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.File;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLClassLoader;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import Java.SymbolTable.Column;
//import Java.SymbolTable.Type;
//import java.util.HashSet;
//import java.util.Set;
//public class testForCoding {
//        public String color;
//        public double id;
//        public String value;
//        public Java.SqlGenerated.TableClasses.colors.Add add;
//
//        class Add {
//            Java.SqlGenerated.TableClasses.colors.Street s;
//            double postnumber;
//
//            public Java.SqlGenerated.TableClasses.colors.Street getS() {
//                return s;
//            }
//
//            public void setS(Java.SqlGenerated.TableClasses.colors.Street s) {
//                this.s = s;
//            }
//
//            public double getPostnumber() {
//                return postnumber;
//            }
//
//            public void setPostnumber(double postnumber) {
//                this.postnumber = postnumber;
//            }
//        }
//
//        class Street {
//            double streetId;
//            String streetname;
//
//            public double getStreetId() {
//                return streetId;
//            }
//
//            public void setStreetId(double streetId) {
//                this.streetId = streetId;
//            }
//
//            public String getStreetname() {
//                return streetname;
//            }
//
//            public void setStreetname(String streetname) {
//                this.streetname = streetname;
//            }
//        }
//
//        public void setadd(Java.SqlGenerated.TableClasses.colors.Add add) {
//            this.add = add;
//        }
//
//        public void setcolor(String value) {
//            this.color = value;
//        }
//
//        public void setid(double value) {
//            this.id = value;
//        }
//
//        public void setvalue(String value) {
//            this.value = value;
//        }
//
//        public Java.SqlGenerated.TableClasses.colors.Add getadd() {
//            return add;
//        }
//
//        public String getcolor() {
//            return color;
//        }
//
//        public double getid() {
//            return id;
//        }
//
//        public String getvalue() {
//            return value;
//        }
//
//        String tablePath = "C:/Users/Dell/Desktop/Final/Data/Colors.json";
//        String tableType = "json";
//        static List<Java.SqlGenerated.TableClasses.colors> entityObject = new ArrayList<>();
//
//        public void load() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException, CloneNotSupportedException, IOException {
//            if (tableType == "json") {
//                entityObject = readJsonFile();
//            } else {
//                entityObject = readCsvFile();
//            }
//        }
//
//        public List<Java.SqlGenerated.TableClasses.colors> readJsonFile() {
//            List<Java.SqlGenerated.TableClasses.colors> result = new ArrayList<>();
//            FileReader fr = null;
//            Gson json = new Gson();
//            try {
//                fr = new FileReader(tablePath);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            JsonReader reader = new JsonReader(fr);
//            JsonObject testing = json.fromJson(fr, JsonObject.class);
//            JsonElement json_ele = testing.get("colors");
//            JsonArray j = json_ele.getAsJsonArray();
//            for (int i = 0; i < j.size(); i++) {
//                Java.SqlGenerated.TableClasses.colors tableName = new Java.SqlGenerated.TableClasses.colors();
//                if (j.get(i).getAsJsonObject().get("color") != null) ;
//                {
//                    tableName.setcolor(j.get(i).getAsJsonObject().get("color").getAsString());
//                }
//                if (j.get(i).getAsJsonObject().get("id") != null) ;
//                {
//                    tableName.setid(j.get(i).getAsJsonObject().get("id").getAsDouble());
//                }
//                if (j.get(i).getAsJsonObject().get("value") != null) ;
//                {
//                    tableName.setvalue(j.get(i).getAsJsonObject().get("value").getAsString());
//                }
//                if (j.get(i).getAsJsonObject().get("add").getAsJsonArray() != null) {
//                    JsonArray nested_one = j.get(i).getAsJsonObject().get("add").getAsJsonArray();
//                    setadd(get_table(nested_one));
//                }
//                result.add(tableName);
//            }
//            return result;
//        }
//
//        public List<Java.SqlGenerated.TableClasses.colors> readCsvFile() throws IOException {
//            List<Java.SqlGenerated.TableClasses.colors> result = new ArrayList<>();
//            BufferedReader csvReader = null;
//            String[] data = new String[0];
//            File csvFile = new File("C:/Users/Dell/Desktop/Final/Data/Colors.json");
//            csvReader = new BufferedReader(new FileReader(csvFile));
//            if (csvFile.isFile()) {
//                String row;
//                Java.SqlGenerated.TableClasses.colors classname = new Java.SqlGenerated.TableClasses.colors();
//                CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT.withHeader("color", "id", "value").withIgnoreHeaderCase().withTrim());
//                for (CSVRecord csvRecord : csvParser) {
//                    if (csvRecord.get("color") != null) {
//                        classname.setcolor(csvRecord.get("color"));
//                    }
//                    if (csvRecord.get("id") != null) {
//                        classname.setid(Double.parseDouble(csvRecord.get("id")));
//                    }
//                    if (csvRecord.get("value") != null) {
//                        classname.setvalue(csvRecord.get("value"));
//                    }
//                }
//                result.add(classname);
//            }
//            return result;
//        }
//
//        public void printContentFunction() {
//            System.out.println("-----------------------------------------------------------------------------");
//            System.out.printf(" %10s    %20s   %20s  ", "color", "id", "value");
//            System.out.println();
//
//            System.out.println("-----------------------------------------------------------------------------");
//
//            for (Java.SqlGenerated.TableClasses.colors obj : entityObject) {
//                System.out.format("  %5s    %20s    %20s  ", obj.getcolor(), obj.getid(), obj.getvalue());
//                System.out.println();
//            }
//            System.out.println("-----------------------------------------------------------------------------");
//        }
//
//
//        public <T> T get_table(JsonArray array) {
//            Java.SqlGenerated.TableClasses.colors.Add add = new Java.SqlGenerated.TableClasses.colors.Add();
//            for (int ii = 0; ii < array.size(); ii++) {
//                if (array.get(ii).getAsJsonObject().get("postnumber") != null) {
//                    add.setPostnumber(array.get(ii).getAsJsonObject().get("postnumber").getAsDouble());
//                }
//                if (array.get(ii).getAsJsonObject().get("Street") != null) {
//                    if (array.get(ii).getAsJsonObject().get("street").isJsonObject())
//                        add.setS(get_types(array.get(ii).getAsJsonObject().get("street").deepCopy()));
//                }
//            }
//            return (T) add;
//        }
//
//        public <T> T get_types(JsonElement object) {
//            Java.SqlGenerated.TableClasses.colors.Street s = new Java.SqlGenerated.TableClasses.colors.Street();
//            if (object.getAsJsonObject().get("number") != null) {
//                s.setStreetId(object.getAsJsonObject().get("streetId").getAsDouble());
//            }
//            if (object.getAsJsonObject().get("number") != null) {
//                s.setStreetname(object.getAsJsonObject().get("StreetName").getAsString());
//            }
//            return (T) s;
//        }
//    }
