public class Fib_On {

    static int fibonacciNumber(int n) {
        int A[][] = { { 1, 1 }, { 1, 0 } };
        if (n == 0)
            return 0;
        exp(A, n - 1);
        return A[0][0];
    }


     //multplies 2 matrices X and Y of size 2x2 and puts result back in X

    static void mult(int X[][], int Y[][]) {
        int x = X[0][0] * Y[0][0] + X[0][1] * Y[1][0];
        int y = X[0][0] * Y[0][1] + X[0][1] * Y[1][1];
        int z = X[1][0] * Y[0][0] + X[1][1] * Y[1][0];
        int w = X[1][0] * Y[0][1] + X[1][1] * Y[1][1];
        X[0][0] = x;
        X[0][1] = y;
        X[1][0] = z;
        X[1][1] = w;
    }


     //function that calculates X to the exp n and puts theresult in x
    
    static void exp(int X[][], int n) {
        int i;
        int A[][] = { { 1, 1 }, { 1, 0 } };
        for (i = 2; i <= n; i++) {
            mult(X, A);
        }
    }
    public static void main(String[] args) {
        int n = 10; //fibonacci value
        System.out.println("Fibonacci " + n + " is "
                         + fibonacciNumber(n));
    }

}