package 코테대비.Recursive_Tree_Graph_DFS_BFS기초;

import 백준.company.Main;

public class 이진수출력_재귀 {
    public static void DFS(int n) {
        if(n == 0) return;
        else{
            DFS(n / 2);
            System.out.print(n%2);
        }
    }

    public static void main(String[] args) {
        이진수출력_재귀 T = new 이진수출력_재귀();
        T.DFS(11);
    }
}
