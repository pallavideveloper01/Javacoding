import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.DoubleStream;

public class PricingUtil {

    public static double getChosenPrice(List<String[]> marketList, String item) {
        double[] price = new double[marketList.size()];
        int j = 0;
        for (int i = 0; i < marketList.size(); i++) {
            String[] marketItem = marketList.get(i);
            if (marketItem[0].equals(item)) {
                price[j] = Double.parseDouble(marketItem[2]);
                j++;
            }
        }
        double totalAmount = DoubleStream.of(price).sum();
        double average = totalAmount / j;
        return PricingUtil.getMaxRepeating(price, average);
    }


    public static double getMaxRepeating(double[] price, double average) {
        ArrayList<Double> priceList = new ArrayList<Double>();
        for (int i = 0; i < price.length; i++) {
            if (price[i] != 0 && (price[i] < (average + (average / 2))) && (price[i] > (average - (average / 2)))) {
                priceList.add(price[i]);
            }
        }
        Collections.sort(priceList);
        double repeatedValue = priceList.get(0);
        int prevCount = 1;
        int ccCount = 1;
        double prevVal = priceList.get(0);
        for (int j = 1; j < priceList.size(); j++) {
            double val = priceList.get(j);
            if (prevVal == val) {
                ccCount++;
                if (ccCount > prevCount) {
                    repeatedValue = val;
                }
            } else {
                if (ccCount > prevCount) {
                    prevCount = ccCount;

                }
                ccCount = 1;
                prevVal = val;
            }
        }
        if (ccCount == 1) {
            repeatedValue = priceList.get(0);
        }
        return repeatedValue;
    }

}
