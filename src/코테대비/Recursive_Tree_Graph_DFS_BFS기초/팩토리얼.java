package 코테대비.Recursive_Tree_Graph_DFS_BFS기초;

public class 팩토리얼 {
    public static int DFS(int n){
        if(n == 1) return 1;
        else return n*DFS(n-1);
    }
    public static void main(String[] args) {
        팩토리얼 T = new 팩토리얼();
        System.out.println(T.DFS(5));
    }
}
