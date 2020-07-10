package Java.AST;

import Java.AST.function.function_body;
import Java.AST.function.function_header;

public class FunctionDeclaration extends Node {

    function_header header ;
    function_body  body ;
// coposotion betwen function body because visited will visited header then function body
    public void setHeader(function_header header) {
        this.header = header;
    }

    public void setBody(function_body body) {
        this.body = body;
    }
}
