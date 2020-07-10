package Java.AST.arrayandjson;

import Java.AST.tokens.toktype;
import org.antlr.v4.runtime.Token;

public class value_json_statmnet extends inside_json_statmnet {
   public Token types;
    variablefromobject object ;
    jsonstatment statmnet ;

    public void setTypes(Token types) {
        this.types = types;
    }

    public Token getTypes() {
        return types;
    }

    public void setObject(variablefromobject object) {
        this.object = object;
    }

    public variablefromobject getObject() {
        return object;
    }

    public void setStatmnet(jsonstatment statmnet) {
        this.statmnet = statmnet;
    }

    public jsonstatment getStatmnet() {
        return statmnet;
    }
}
