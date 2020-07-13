package Java.SqlGenerated.AggregationsFunction;

import java.util.List;
import java.util.OptionalDouble;

public final class CommonAggregations {
    public double Sum(List<Double> numbers){
        return numbers.
                stream().
                mapToDouble(Double::doubleValue)
                .sum();
    }

    public OptionalDouble Max(List<Double> numbers){
        return numbers.
                stream().
                mapToDouble(Double::doubleValue)
                .max();
    }

    public OptionalDouble Min(List<Double> numbers){
        return numbers.
                stream().
                mapToDouble(Double::doubleValue)
                .min();
    }

    public OptionalDouble Avg(List<Double> numbers){
        return numbers.
                stream().
                mapToDouble(Double::doubleValue)
                .average();
    }






    private static volatile CommonAggregations myCommonAggregator;
    private CommonAggregations(){
        if (myCommonAggregator != null) {
            throw new IllegalStateException("CommonAggregations object is already created!");
        }
    }

    public static CommonAggregations getCommonAggregations() {
        if(myCommonAggregator == null){
            synchronized(CommonAggregations.class) {
                if (myCommonAggregator == null) {
                    myCommonAggregator = new CommonAggregations();
                }
            }
        }
        return myCommonAggregator;
    }

}
