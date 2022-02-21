package 코테대비.Recursive_Tree_Graph_DFS_BFS기초;

public class 피보나치재귀_메모제이션_더좋은방법 {
    static int[] fibo;

    public static int DFS(int n){
        if(n==1) return fibo[n] = 1;
        else if(n == 2) return fibo[n] = 1;
        else return fibo[n] = DFS(n-2) + DFS(n-1);
    }

    public static void main(String[] args) {
        피보나치재귀_메모제이션_더좋은방법 T = new 피보나치재귀_메모제이션_더좋은방법();
        int n = 10;
        fibo = new int[n+1];
        T.DFS(n);
        for (int i=1; i<=n; i++) System.out.print(fibo[i] + " ");
    }
}
