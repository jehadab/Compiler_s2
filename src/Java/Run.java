package Java;

import Java.JarFiles.Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jehad on 7/16/2020.
 */
public class Run {
    public static void main(String[] args){
        List list = new ArrayList<Double>(Arrays.asList(1.2,2.3,3.0));
        Sum sum = Sum.getCommonAggregations();
        double d = sum.sumNumber(list);
        System.out.println("sum = "+d);
}
}
