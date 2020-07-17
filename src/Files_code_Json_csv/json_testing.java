package Files_code_Json_csv;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class json_testing {
    public void get_data_from_json(employess emplo,String name ) {

        FileReader fr = null;
        Gson json = new Gson();
        try {
            fr=new FileReader("C://Users//Dell//IdeaProjects//LOLO//src//Files_code_Json_csv//json_fle.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonReader reader = new JsonReader(fr);
        JsonObject testing = json.fromJson(fr, JsonObject.class);
        JsonElement json_ele = testing.get(name );
       // System.out.println("yes we get it " + classe.getName());
        JsonArray j = json_ele.getAsJsonArray();
        System.out.println(j);
        for (int i = 0; i < j.size(); i++) {
            if(j.get(i).getAsJsonObject().get("city")!=null)
                if (j.get(i).getAsJsonObject().get("city").getAsJsonArray() != null) {
                    JsonArray nested_one = j.get(i).getAsJsonObject().get("city").getAsJsonArray();
                    emplo.setCc(get_table(nested_one));
                    //  System.out.println("let see what we will get here "+nested_one.deepCopy());
                /*for(int ii=0;ii<nested_one.size();ii++)
                {
                    //System.out.println(nested_one.get(ii).getAsJsonObject().get("Name"));
                    //System.out.println(nested_one.get(ii).getAsJsonObject().get("Birth"));
                    city c = new city();
                    c.setName(nested_one.get(ii).getAsJsonObject().get("Name").toString());
                    c.setBirth(nested_one.get(ii).getAsJsonObject().get("Birth").getAsInt());
                    if(nested_one.get(ii).getAsJsonObject().get("street")!=null)
                    {
                        c.setT(get_types(nested_one.get(ii).getAsJsonObject().get("street").deepCopy()));
                    }

                    if(nested_one.get(ii).getAsJsonObject().get("city").getAsJsonArray()!=null)
                    {

                    }
                    emplo.setCc(c);

                }*/
                }
            if(j.get(i).getAsJsonObject().get("twon")!=null)
                if (j.get(i).getAsJsonObject().get("twon").isJsonObject() == true) {
                    twon t = new twon();
                    t.setHome_number(j.get(i).getAsJsonObject().get("twon").getAsJsonObject().get("home_number").getAsInt());
                    t.setT(get_types(j.get(i).getAsJsonObject().get("twon").getAsJsonObject().get("street").deepCopy()));
                    System.out.println("we ahould refinde it here "+j.get(i).getAsJsonObject().get("twon").getAsJsonObject().get("street"));
                    emplo.setC(t);

                }
            if(j.get(i).getAsJsonObject().get("id")!=null)
                emplo.setId(j.get(i).getAsJsonObject().get("id").getAsInt());
            if(j.get(i).getAsJsonObject().get("age")!=null)
                emplo.setAge(j.get(i).getAsJsonObject().get("age").getAsInt());
            if(j.get(i).getAsJsonObject().get("name")!=null)
                emplo.setName(j.get(i).getAsJsonObject().get("name").toString());
//System.out.println("for testing it "+emplo.getC().getHome_number());
            System.out.println( "for the row his index is  "+i);
            System.out.println("id is " + emplo.getId());
            System.out.println("name  is " + emplo.getName());
            System.out.println("age" + emplo.getAge());
            System.out.println(" home_number" + emplo.getC().getHome_number());
            System.out.println("number" + emplo.getC().getT().getNumber());
            System.out.println("building_number" + emplo.getC().getT().getB().getBuilding_number());
            System.out.println(" person name " + emplo.getC().getT().getB().getP().getPerson_name());
            System.out.println("city Name" + emplo.getCc().getName());
            System.out.println("city  Birth" + emplo.getCc().getBirth());
            System.out.println("street number" + emplo.getCc().getT().getNumber());
            System.out.println(" nested city "+emplo.getCc().getCcc().getName());
            System.out.println(" another nested one "+emplo.getCc().getCcc().getCcc().getName());
        }


    }

    public <T> T get_types(JsonElement object) {
        person p = new person();
        building B = new building();
        street s = new street();
        if (object.getAsJsonObject().get("number") != null) {
            s.setNumber(object.getAsJsonObject().get("number").getAsInt());
            if (object.getAsJsonObject().get("building") != null)
                s.setB(get_types(object.getAsJsonObject().get("building").deepCopy()));
            return (T) s;
        }
        if (object.getAsJsonObject().get("building_number") != null) {
            B.setBuilding_number(object.getAsJsonObject().get("building_number").getAsInt());
            if (object.getAsJsonObject().get("person") != null) {

                B.setP(get_types(object.getAsJsonObject().get("person").deepCopy()));

            }

            return (T) B;

        }

        if (object.getAsJsonObject().get("person_name") != null) {
            p.setPerson_name(object.getAsJsonObject().get("person_name").getAsString());
            return (T) p;

        }
        return null;
    }


    public <T> T get_table(JsonArray array) {

        city c = new city();
        for (int ii = 0; ii < array.size(); ii++) {
            if(array.get(ii).getAsJsonObject().get("Name")!=null)
            {
                c.setName(array.get(ii).getAsJsonObject().get("Name").toString());
            }
            if(array.get(ii).getAsJsonObject().get("Birth")!=null) {
                c.setBirth(array.get(ii).getAsJsonObject().get("Birth").getAsInt());
            }
            if (array.get(ii).getAsJsonObject().get("street")!=null) {
                if(array.get(ii).getAsJsonObject().get("street").isJsonObject())
                    c.setT(get_types(array.get(ii).getAsJsonObject().get("street").deepCopy()));
            }
            if(array.get(ii).getAsJsonObject().get("city")!=null)
                if (array.get(ii).getAsJsonObject().get("city").getAsJsonArray()!= null) {
                    JsonArray  nested_one = array.get(ii).getAsJsonObject().get("city").getAsJsonArray();
                    c.setCcc(get_table(nested_one.deepCopy()));

                }

        }
        return (T) c;
    }
}



//for (int i= 0; i<columnList.size();i++){
//if(columnList.get(i).getColumn_type().getName()!=Type.NUMBER_CONST) {
//        setid(j.get(i).getAsJsonObject().get(columnList.get(i).getColumn_name()).getAsDouble());
//        }
//        else if(columnList.get(i).getColumn_type().getName()!=Type.BOOLEAN_CONST) {
////		setid(j.get(i).getAsJsonObject().get(columnList.get(i).getColumn_name()).getAsBoolean());
//        }
//        else if(columnList.get(i).getColumn_type().getName()!=Type.STRING_CONST) {
//        setname(j.get(i).getAsJsonObject().get(columnList.get(i).getColumn_name()).getAsString());
//        }
//        else {
//
//        }
//
//        }
