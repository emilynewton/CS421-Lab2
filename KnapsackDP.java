import java.util.*;
import java.io.*;

public class KnapsackDP {
    static int tableReferences = 0;
    static int totalWeight = 0;
    
    private int n, W;  // Declare n and W as instance variables
    private int[] weights, values;
    private int[][] dp, dt;

    public KnapsackDP(int n, int W, int[] weights, int[] values) {
        this.n = n;
        this.W = W;
        this.weights = weights;
        this.values = values;
        this.dp = new int[n + 1][W + 1];
        this.dt = new int[n + 1][W + 1];
    }

    public void solve() {
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    tableReferences += 2; 
                    dp[i][w] = Math.max(dp[i - 1][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                    dt[i][w] = (dp[i][w] > dp[i-1][w] ? 1 : 0);
                } else {
                    dp[i][w] = dp[i - 1][w];
                    tableReferences++;
                }
            }
        }
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
            System.out.println("Usage: java KnapsackDP <n> <W> <w.txt> <v.txt> [<debug level>]");
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
            wScanner.close();
            vScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }

        // Create a KnapsackDP instance
        KnapsackDP knapsack = new KnapsackDP(n, W, weights, values);
        knapsack.solve();

        if (debugLevel == 1) {
            System.out.println("KnapsackDP-VTable:");
            printTable(knapsack.dp);
            System.out.println("KnapsackDP-DTable:");
            printTable(knapsack.dt);
        }
        ArrayList<Integer> optimalItems = knapsack.getOptimalItems(); 
        System.out.println("Optimal solution: ");
        System.out.println(optimalItems);
        System.out.println("Total Weight: " + knapsack.getTotalWeight());
        System.out.println("Optimal value: " + knapsack.dp[n][W]);
        System.out.println("Number of table references: " + tableReferences);
    }

    /**
     * Prints either the optimal solution table or the decision table 
     * @param table
     */
    private static void printTable(int[][] table) {
        for (int[] row : table) {
            for (int value : row) {
                System.out.printf("%" + 4 + "d ", value);
            }
            System.out.println();
        }
    }
}
