package Files_code_Json_csv;

import Files_code_Json_csv.street;

public class city  {
    String  Name;
    int Birth;
    street t ;
    city ccc;

    public String getName() {
        return Name;
    }

    public city getCcc() {
        return ccc;
    }

    public void setCcc(city ccc) {
        this.ccc = ccc;
    }

    public street getT() {
        return t;
    }

    public void setT(street t) {
        this.t = t;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getBirth() {
        return Birth;
    }

    public void setBirth(int birth) {
        Birth = birth;
    }
}
