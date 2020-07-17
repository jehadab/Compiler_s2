//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Java.SymbolTable;

import java.util.ArrayList;

public class AggregationFunction extends Type {
    protected String AggregationFunctionName;
    protected  String jar_path;
    protected String ClassName;
    protected String MethodName;
    protected String returnType;
    private ArrayList<parametes> params = new ArrayList();

    public String getJar_path() {
        return jar_path;
    }

    public void setJar_path(String jar_path) {
        this.jar_path = jar_path;
    }

    public AggregationFunction() {
    }

    public String getAggregationFunctionName() {
        return this.AggregationFunctionName;
    }

    public void setAggregationFunctionName(String aggregationFunctionName) {
        this.AggregationFunctionName = aggregationFunctionName;
    }

    public String getClassName() {
        return this.ClassName;
    }

    public void setClassName(String className) {
        this.ClassName = className;
    }

    public String getMethodName() {
        return this.MethodName;
    }

    public void setMethodName(String methodName) {
        this.MethodName = methodName;
    }

    public String getReturnType() {
        return this.returnType;
    }



    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public ArrayList<parametes> getParams() {
        return this.params;
    }

    public void setParams(ArrayList<parametes> params) {
        this.params = params;
    }

    public void add_parama(parametes p) {
        this.params.add(p);
    }

    public void print_params() {
        for(int u = 0; u < this.getParams().size(); ++u) {
            System.out.println(" we have params " + ((parametes)this.getParams().get(u)).toString().toString());
        }

    }
}
