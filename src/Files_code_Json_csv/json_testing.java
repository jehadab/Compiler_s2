package Files_code_Json_csv;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class json_testing {
    public void get_data_from_json(employess emplo, String name ){

        FileReader fr = null;
        Gson json = new Gson();
        try {
            fr=new FileReader("C://Users//Dell//Desktop//Sql_compiler//src//Files_code_Json_csv//json_fle.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonReader reader = new JsonReader(fr);
        JsonObject testing = json.fromJson(fr, JsonObject.class);
        JsonElement json_ele = testing.get(name);
        JsonArray j = json_ele.getAsJsonArray();
        System.out.println(j);
        for (int i=0;i<j.size();i++)
        {
            if(j.get(i).getAsJsonObject().get("city").getAsJsonArray()!=null)
            {

                JsonArray nested_one = j.get(i).getAsJsonObject().get("city").getAsJsonArray();
                for(int ii=0;ii<nested_one.size();ii++)
                {
                    //System.out.println(nested_one.get(ii).getAsJsonObject().get("Name"));
                    //System.out.println(nested_one.get(ii).getAsJsonObject().get("Birth"));
                    city c = new city();
                    c.setName(nested_one.get(ii).getAsJsonObject().get("Name").toString());
                    c.setBirth(nested_one.get(ii).getAsJsonObject().get("Birth").getAsInt());
                    emplo.setCc(c);
                }
            }
            if(j.get(i).getAsJsonObject().get("twon").isJsonObject()==true )
            {
                twon t =new twon();
                t.setHome_number(j.get(i).getAsJsonObject().get("twon").getAsJsonObject().get("home_number").getAsInt());
                get_types( j.get(i).getAsJsonObject().get("twon").getAsJsonObject().get("street").deepCopy());
                emplo.setC(t);

            }
            emplo.setId(j.get(i).getAsJsonObject().get("id").getAsInt());
            emplo.setAge(j.get(i).getAsJsonObject().get("age").getAsInt());
            emplo.setName(j.get(i).getAsJsonObject().get("name").toString());
//System.out.println("for testing it "+emplo.getC().getHome_number());
            //          System.out.println("for another testing "+emplo.getCc().getBirth());
            //        System.out.println("for more testing "+emplo.getCc().getName());

        }


    }
    public <T> T get_types(JsonElement object){
        System.out.println(object.getAsJsonObject().get("number").getAsInt());

        return null;
    }
}
