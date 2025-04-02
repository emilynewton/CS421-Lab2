import java.util.*;
import java.io.*;

public class KnapsackRM {
    static int[] weights;
    static int[] values;
    static int[][] memo;
    static int tableReferences = 0;

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

        weights = new int[n];
        values = new int[n];

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
        for (int[] row : memo) Arrays.fill(row, -1);

        int result = solve(n, W);


        if (debugLevel == 1) {
            System.out.println("KnapsackRM-VTable:");
            printTable();
        }
        System.out.println("Optimal value: " + result);
        System.out.println("Number of table references: " + tableReferences);
    }

    public static int solve(int i, int w) {
        tableReferences++; // Increment table reference count

        if (i == 0 || w == 0) return 0;
        if (memo[i][w] != -1) return memo[i][w];

        int result;
        if (weights[i - 1] > w) {
            result = solve(i - 1, w);
        } else {
            result = Math.max(solve(i - 1, w), values[i - 1] + solve(i - 1, w - weights[i - 1]));
        }

        memo[i][w] = result;
        return result;
    }

    /**
     * Prints the optimal value table 
     */
    private static void printTable() {
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[i].length; j++) {
                System.out.print(memo[i][j] + " ");
            }
            System.out.println();
        }
    }
}
