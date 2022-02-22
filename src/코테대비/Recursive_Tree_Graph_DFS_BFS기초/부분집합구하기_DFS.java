package 코테대비.Recursive_Tree_Graph_DFS_BFS기초;


// DFS(1) - 내가 만드려는 부분집합에 1을 사용한다, 안한다
public class 부분집합구하기_DFS {
    static int n;
    static boolean[] visited;

    static void DFS(int level) {
        if(level == n+1) {
            String tmp = "";
            for (int i = 1; i <= n; i++) {
                if(visited[i]) tmp += (i+" ");
            }
            // 공집합제외
            if(tmp.length() > 0) System.out.println(tmp);
        }
        else{
            visited[level] = true;
            DFS(level + 1); // 왼쪽으로 뻗고
            visited[level] = false;
            DFS(level + 1); // 오른쪽으로 뻗고
        }
    }

    public static void main(String[] args) {
        부분집합구하기_DFS T = new 부분집합구하기_DFS();
        n = 3;
        visited = new boolean[n+1]; // 1부터 n까지 인덱스니까
        T.DFS(1);
    }
}
