package 코테대비.Recursive_Tree_Graph_DFS_BFS기초;

public class 재귀함수_스택프레임 { //
    public static void DFS(int n) {
        if(n == 0) return;
        else{
            DFS(n-1);
            // 1 2 3 으로 출력되는 이유
            System.out.print(n + " "); // 스택프레임을 사용하기 때문에, DFS(3),DFS(2),DFS(1),DFS(0) : DFS(0)부터 수행하면서 pop()된다
        }
    }
    public static void main(String[] args) {
        재귀함수_스택프레임 T = new 재귀함수_스택프레임();
        T.DFS(3);
    }
}
