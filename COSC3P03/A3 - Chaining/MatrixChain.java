public class MatrixChain{

    private int[][] m; //m matrix
    private int[][] s; //s matrix
    private int n;

    public MatrixChain(int[] p) //Computes optimal matrix chain product
    {
 n = p.length - 1; // how many matrices are in the chain
 m = new int[n+1][n+1]; // overallocate m, so that we don't use index 0
 s = new int[n+1][n+1]; // same for s
 matrixChainOrder(p); // run the dynamic-programming algorithm
    }

    private void matrixChainOrder(int[] p)
    {
 for (int i = 1; i <= n; i++)
     m[i][i] = 0;

 for (int l = 2; l <= n; l++) {  //increasing length 1
     for (int i = 1; i <= n-l+1; i++) {
  int j = i + l - 1;
  m[i][j] = Integer.MAX_VALUE;

  for (int k = i; k < j; k++) { //check each split
      int q = m[i][k] + m[k+1][j] + p[i-1] * p[k] * p[j];
      if (q < m[i][j]) {
   m[i][j] = q;
   s[i][j] = k;
      }
  }
     }
 }
    }
    private String printOptimal(int i, int j) //prints optimal solution
    {
 if (i == j)
     return "A[" + i + "]";
 else
     return "(" + printOptimal(i, s[i][j]) +
  printOptimal(s[i][j] + 1, j) + ")";
    }
    
    public String toString() //returns string describing matrix chain
    {
 return printOptimal(1, n);
    }
}