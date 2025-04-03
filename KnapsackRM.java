import java.util.*;
import java.io.*;

public class KnapsackRM {
    private int[] weights;
    private int[] values;
    private int n, W; 
    static int[][] memo;
    static int[][] dt; 
    static int references = 0;
    static int totalWeight = 0; 

    public KnapsackRM(int n, int W, int[] weights, int[] values) {
        this.n = n; 
        this.W = W; 
        this.weights = weights; 
        this.values = values; 
    }

    public ArrayList<Integer> getOptimalItems() {
        ArrayList<Integer> optimalItems = new ArrayList<>();
        int j = W;
        for (int i = n; i > 0; i--) {
            if (dt[i][j] == 1) {
                optimalItems.add(i);
                j -= weights[i - 1];
            }
        }
        Collections.reverse(optimalItems);
        return optimalItems;
    }

    public int getTotalWeight() {
        totalWeight = 0;
        ArrayList<Integer> optimalItems = getOptimalItems();
        for (Integer i : optimalItems) {
            totalWeight += weights[i - 1];
        }
        return totalWeight;
    }

    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: java KnapsackRM <n> <W> <w.txt> <v.txt> [<debug level>]");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int W = Integer.parseInt(args[1]);
        String weightFile = args[2];
        String valueFile = args[3];
        int debugLevel = (args.length == 5) ? Integer.parseInt(args[4]) : 0;

        int[] weights = new int[n];
        int[] values = new int[n];

        try {
            Scanner wScanner = new Scanner(new File(weightFile));
            Scanner vScanner = new Scanner(new File(valueFile));
            for (int i = 0; i < n; i++) {
                weights[i] = wScanner.nextInt();
                values[i] = vScanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }

        memo = new int[n + 1][W + 1];
        dt = new int[n + 1][W + 1]; 
        for (int[] row : memo) Arrays.fill(row, -1);
        KnapsackRM knapsack = new KnapsackRM(n, W, weights, values);
        knapsack.solve(n, W);
        if (debugLevel == 1) {
            try {
                File VTable = new File("KnapsackRM-VTable.txt"); 
                FileWriter myWriter = new FileWriter(VTable);  
                myWriter.write(printTable(knapsack.memo));
                myWriter.close(); 
            } catch (IOException e) {
                System.out.println("An error occured."); 
                e.printStackTrace(); 
            }
            try {
                File DTable = new File("KnapsackRM-DTable.txt"); 
                FileWriter myWriter2 = new FileWriter(DTable); 
                myWriter2.write(printTable(knapsack.dt)); 
                myWriter2.close();  
            } catch (IOException e) {
                System.out.println("An error occured."); 
                e.printStackTrace();
            }
        }
        ArrayList<Integer> optimalItems = knapsack.getOptimalItems();
        System.out.println("Optimal solution: "); 
        System.out.println(optimalItems);  
        System.out.println("Total Weight: " + knapsack.getTotalWeight()); 
        System.out.println("Optimal value: " + knapsack.memo[n][W]);
        System.out.println("Number of table references: " + references);
    }

    public int solve(int i, int w) {
        references++; // Increment table reference count
        if (i < 0 || w < 0) {
            return 0; 
        }
        if (i == 0 || w == 0) {
            memo[i][w] = 0; 
            dt[i][w] = 0; 
            return 0;
        } 
        if (memo[i][w] != -1) {
            return memo[i][w];
        }
        int result;
        int p = solve(i - 1, w); 
        if (weights[i - 1] <= w) {
            int var = solve(i - 1, w - weights[i - 1]);
            result = Math.max(values[i - 1] + var, p);
            dt[i][w] = (p < result) ? 1 : 0; // Mark item as included if choosing it results in a better value
        } else {
            result = p;
            dt[i][w] = 0; // Item cannot be included
        }

        memo[i][w] = result;
        return result;
    }

    /**
     * Prints either the optimal solution table or the decision table 
     * @param table
     */
    private static String printTable(int[][] table) {
        StringBuilder sb = new StringBuilder(); 
        for (int i = 1; i < table.length; i++) {
            for (int j = 1; j < table[i].length; j++) {
                sb.append(table[i][j]).append(" "); 
            }
            sb.append("\n"); 
        }
        return sb.toString();
    }

}
