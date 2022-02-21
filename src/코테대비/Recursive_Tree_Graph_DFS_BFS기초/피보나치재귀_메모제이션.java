package 코테대비.Recursive_Tree_Graph_DFS_BFS기초;

public class 피보나치재귀_메모제이션 {
    public int DFS(int n) { // n번째 항
        if(n == 1) return 1;
        else if(n == 2) return 1;
        else return DFS(n-2) + DFS(n-1);
    }

    public static void main(String[] args) {
        피보나치재귀_메모제이션 T = new 피보나치재귀_메모제이션();
        int n = 5;
        for (int i=1; i<=n; i++) System.out.print(T.DFS(i) + " ");
    }
}
