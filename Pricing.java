import java.util.*;
import java.util.stream.DoubleStream;

public class Pricing {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            /* read the item data from console*/
            List<String[]> itemDemandList = new ArrayList<String[]>();
            int itemCount = in.nextInt();
            readInput(in, itemCount, itemDemandList);

            /* read the market survey details from console*/
            int marketCount = in.nextInt();
            List<String[]> marketList = new ArrayList<String[]>();
            readInput(in, marketCount, marketList);

            processRequest(itemCount, itemDemandList, marketList);
        } catch (Exception e) {
            System.out.println("Failed while calculating the price.");
        }
    }

    /**
     * this method will process the request for selecting the price based on demand and supply.
     *
     * @param itemCount
     * @param itemDemandList
     * @param marketList
     */
    private static void processRequest(int itemCount, List<String[]> itemDemandList, List<String[]> marketList) throws Exception {
        try {
            String prefix = "A ";
            for (int i = 0; i < itemCount; i++) {
                if (i == 1)
                    prefix = "B ";
                String[] item = itemDemandList.get(i);
                double sellValue = PricingUtil.getChosenPrice(marketList, item[0]);
                if (item[1].equals("H") && item[2].equals("H")) {
                    System.out.println(prefix + (sellValue));
                } else if (item[1].equals("L") && item[2].equals("L")) {
                    System.out.println(prefix + (sellValue + ((sellValue * 10 / 100))));
                } else if (item[1].equals("L") && item[2].equals("H")) {
                    System.out.println(prefix + (sellValue + (sellValue * 5 / 100)));
                } else if (item[1].equals("H") && item[2].equals("L")) {
                    System.out.println(prefix + (sellValue - (sellValue * 5 / 100)));
                }
            }
        } catch (Exception e) {
            System.out.println("Error while displaying the result.");
            throw new Exception("Error while displaying the result");
        }
    }

    /**
     * read the input from the console and store the arrays into ArrayList.
     *
     * @param in
     * @param itemCount
     * @param inputList
     * @return List<String[]>
     */
    private static List<String[]> readInput(Scanner in, int itemCount, List<String[]> inputList) {
        int b = 0;
        try {
            while (in.hasNextLine() && b < itemCount) {
                String line = in.nextLine();
                if (line != null && line.length() > 0) {
                    String[] arr = line.split(" ");
                    inputList.add(arr);
                    b++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error in readInput method while reading the input data by the user.");
        }
        return inputList;
    }
}