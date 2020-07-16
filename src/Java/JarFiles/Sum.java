package Java.JarFiles;
import java.util.List;

/**
 * Created by Jehad on 7/16/2020.
 */

public final class Sum {
    public double sumNumber(List<Double> numbers){

        return numbers.
                stream().
                mapToDouble(Double::doubleValue)
                .sum();
    }

    private static volatile Sum myCommonAggregator;

    private Sum(){
        if (myCommonAggregator != null) {
            throw new IllegalStateException("CommonAggregations object is already created!");
        }
        System.out.println("Sum Crated");
    }

    public static Sum getCommonAggregations() {
        if(myCommonAggregator == null){
            synchronized(Sum.class) {
                if (myCommonAggregator == null) {
                    myCommonAggregator = new Sum();
                }
            }
        }
        return myCommonAggregator;
    }
}
