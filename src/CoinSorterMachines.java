import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Coin machine project
 * @author riley kim
 * @version 04/06/2023
 */
public class CoinSorterMachines {
    private ArrayList<Coin> coins;
    private ArrayList<Coin> coinMap;
    private int[] coinCounts = {0,0,0,0,0,0}; // P, N, D, Q, HD, D

    /**
     * initializes coins ArrayList
     */
    public CoinSorterMachines(){

        coins = new ArrayList<>();

        coinMap = new ArrayList<>();

        coinMap.add(new Penny());
        coinMap.add(new Nickel());
        coinMap.add(new Dime());
        coinMap.add(new Quarter());
        coinMap.add(new HalfDollar());
        coinMap.add(new Dollar());
    }

    /**
     * use one or two Scanner objects, prompting user for the appropriate file
     * name and importing the data from filename – MUST handle diabolic values!
     */
    public void depositCoins(){
        Scanner userIn = new Scanner(System.in);
        System.out.print("Enter the name of the data file for coin deposit: ");
        String filename = userIn.nextLine();
        userIn.close();
        System.out.println("Depositing coins...");

        try{
            Scanner in = new Scanner(new File(filename));
            int temp;
            while(in.hasNext()) {
                temp = in.nextInt();
                Coin c = makeCoin(temp/100.0);
                if(c == null)
                    System.out.println("Coin value " + temp + " not recognized");
                else{
                    coins.add(c);
                    coinCounts[coinMap.indexOf(c)]++;
                }
            }
        }
        catch(Exception e)  {
            e.printStackTrace();
        }
    }

    /**
     * Prints deposit summary using a DecimalFormat object (see output section)
     */
    public void printDepositSummary(){
        DecimalFormat df = new DecimalFormat("$0.00");
        System.out.println("Summary of deposit: ");

        for(int i = 0; i < coinCounts.length; i++){
            System.out.print("\t" + coinCounts[i] + " ");
            System.out.print((coinCounts[i] > 1? coinMap.get(i).getPluralName() : coinMap.get(i).getName()) + " ");
            System.out.println(df.format(coinCounts[i]*coinMap.get(i).getValue()));
        }

        System.out.println("TOTAL DEPOSIT: " + df.format(getTotalValue()));

    }

    private Coin makeCoin(double value){
        for(Coin c : coinMap) {
            if(c.getValue() == value)
                return c;
        }
        return null;
    }
    /**
     *  return the total value of all Coin objects stored in coins as a double
     */
    public double getTotalValue(){
        double total = 0;
        for(int i = 0; i < coinCounts.length; i++){
            total += coinCounts[i]*coinMap.get(i).getValue();
        }
        return total;
    }

    /**
     *  main method for the class should use these exact three lines of code
     */
    public static void main(String[] args){

        CoinSorterMachines app = new CoinSorterMachines();
        app.depositCoins();
        app.printDepositSummary();
    }
}