package 코테대비.Recursive_Tree_Graph_DFS_BFS기초;

public class 피보나치재귀_메모제이션_제일좋은방법 {
    static int[] fibo;

    public int DFS(int n) {
        if(fibo[n] > 0) return fibo[n]; // 메모제이션 - 이미 다른 가지에서 구한 값을 바로 사용
        if(n == 1) return fibo[n] = 1;
        else if(n == 2) return fibo[n] = 2;
        else return fibo[n] = DFS(n-2) + DFS(n-1);
    }

    public static void main(String[] args) {
        피보나치재귀_메모제이션_제일좋은방법 T = new 피보나치재귀_메모제이션_제일좋은방법();
        int n = 45;
        fibo = new int[n+1];
        T.DFS(n);
        for (int i=1; i<=n; i++) System.out.print(fibo[i] + " ");
    }
}
