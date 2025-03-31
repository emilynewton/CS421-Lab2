/**
 * @author Emily Newton
 * ShuffleDP defines a class for a bottom-up dynamic programming
 * approach for the shuffle problem.
 */
public class ShuffleDP {


    static Boolean[][] memo; 

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Requires at least 3 arguments: java ShuffleDP <X> <Y> <Z> [<debug level>]"); 
            return; 
        }

        String X = args[0]; 
        String Y = args[1]; 
        String Z = args[2]; 

        if (args[3] != null) {
            int debug = Integer.parseInt(args[3]); 
        }
        
        if (X.length() + Y.length() != Z.length()) {
            System.out.println("Z must be the length of X and Y combined."); 
            return; 
        }




        boolean result = isShuffleDP(X, Y, Z);  
        System.out.println(result ? "yes" : "no"); 

    }



    public static boolean isShuffleDP(String X, String Y, String Z) {
        int i = X.length(); 
        int j = Y.length(); 

        boolean[][] matrix = new boolean[i + 1][j + 1]; 

        // base case - if i=0 && j=0, return true 
        matrix[0][0] = true; 

        // base case - first column when Y is empty
        for (int m = 1; m <= i; m++) {
            if (matrix[m - 1][0] && X.charAt(m - 1) == Z.charAt(m - 1)) {
                matrix[m][0] = true; 
            }
        }

        // base case - first row when X is empty
        for (int n = 1; n <= j; n++) {
            if (matrix[0][n - 1] && Y.charAt(n - 1) == Z.charAt(n - 1)) {
                matrix[0][n] = true; 
            }
        }

        // neither X or Y is empty 
        for (int m = 1; m <= i; m++) {
            for (int n = 1; n <= j; n++) {
                if (((X.charAt(m - 1) == Z.charAt(m + n - 1)) && matrix[m - 1][n]) || ((Y.charAt(n - 1) == Z.charAt(m + n - 1)) && matrix[m][n - 1])) {
                    matrix[m][n] = true; 
                }
            }
        }
        return false; 
    }
}


/*
 * for ()
 */