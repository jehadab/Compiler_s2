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
    int id ;
    String name ;
    int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void to_read_json_File(){
        FileReader fr = null;
        Gson json = new Gson();
        try {
            fr=new FileReader("C://Users//Dell//IdeaProjects//LOLO//src//Files_code_Json_csv//json_fle.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonReader reader = new JsonReader(fr);
        JsonObject testing = json.fromJson(fr, JsonObject.class);
        JsonElement json_ele = testing.get("employess");
        json_testing [] jsons = json.fromJson(json_ele,json_testing[].class);
        ArrayList list = new ArrayList<>(Arrays.asList(jsons));
        for(int ii=0;ii<list.size();ii++)
        {
            json_testing T = new json_testing();
            json_testing t = new json_testing();
            t= (json_testing) list.get(ii);
            System.out.println(" the id is "+t.id);
            System.out.println(" the name  is "+t.name);
            System.out.println("the age is "+t.age);
            JsonArray jsonElements = new JsonArray();
            T.setAge(t.age);
            T.setId(t.id);
            T.setName(t.name);

        }
//put the loading result in array_list_of data ...

    }
}
