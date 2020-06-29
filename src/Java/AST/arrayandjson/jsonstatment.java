package Java.AST.arrayandjson;

import Java.AST.ParametesandArgs.args;

import java.util.ArrayList;
import java.util.List;

public class jsonstatment extends jsonform {
    private List<inside_json_statmnet> json_statmen = new ArrayList<inside_json_statmnet>();

   // public void setJson_statmen(List<inside_json_statmnet> json_statmen) {
     //   this.json_statmen = json_statmen;
    //}

    //public List<inside_json_statmnet> getJson_statmen() {
      //  return json_statmen;
    //}
    public void additemtojsonstatment (inside_json_statmnet j ){
        this.json_statmen.add(j);

    }

}
