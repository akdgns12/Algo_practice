package 백준.복습;

import java.util.Scanner;

/**
 *  복습 필요
 */
public class 텀프로젝트복습 {
    static int T;
    static int n;
    static boolean[] visited;
    static int[] arr;
    static boolean[] finished;
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        while(T --> 0){
            n = sc.nextInt();
            visited = new boolean[n+1]; // 단순히 방문 여부를 체크하는 배열
            /*
                해당 노드를 시작으로 하는 싸이클을 검출했는가 체크하는 배열 -> 중복을 방지하기 위해
                예를들어 1->3과 2->1->3->3 두개의 싸이클인 경우 앞의 1번 노드 탐색만으로
                싸이클을 검출할 수 있다. 2번 노드를 끝까지 해도 같은 결과 검출
             */
            finished = new boolean[n+1];
            arr = new int[n+1];
            for(int i=1; i<=n; i++){
                arr[i] = sc.nextInt();
            }

            count = 0;
            for(int i=1; i<=n; i++){
                dfs(i);
            }

            System.out.println(n - count);
        }
    }

    static void dfs(int now){
        if(visited[now]) // 탐색 시작 노드가 이미 방문했던 노드라면 종료
            return;

        visited[now] = true;
        int next = arr[now];

        if(!visited[next]){
            dfs(next);
        }

        // 노드가 끝나려면 반드시 싸이클을 가져야함
            else{
            if(!finished[next]){ // 아직 싸이클을 탐색하지 않은 노드라면
                // 싸이클에 포함되는 노드의 개수 count
                count++;
                for(int i=next; i!=now; i=arr[i]){
                    count++;
                }
            }
        }
        // 싸이클을 탐색했다면 더이상 해당 노드를 사용하지 않는다.
        finished[now] = true;
    }
}
