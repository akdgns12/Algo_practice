package 백준.company.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ13023 {
    // ABCDE / 골드 5 / 양방향 인접리스트와 DFS
    /*
        A와 B가 친구고 B와 C가 친구고, C와 D가 친구고 D와 E가 친구인 관계는 0 ~ N -1 까지의
        노드 중에 일직선으로 연결된 노드가 5개가 있는지 확인하는 것과 동치. -> 일반화하여 모든 노드가 일직선 상에 있는지 확인해야겠다고 오해하면 안됨.
        dfs 구현시 주의 사항
        - 만약, 0번 노드에서 시작하여 1번, 2번 노드까지 방문이 완료되었다고 하면. visited[0] = visited[1] = visited[2] = true이 상태인데
        더이상 갈 곳이 없다면 이대로 방문 정보를 냅두면 안되고 모두 방문이 안 된 false 상태로 만들어야 한다.
     */
    static int n, m;
    static ArrayList<Integer>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 양방향
            list[a].add(b);
            list[b].add(a);
        }

        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            dfs(i,0);
        }
        System.out.println(0);
    }

    // dfs 탐색 문제, A->B->C->D->E 인 관계가 있다면 1을 출력, 아니면 0을 출력하는 문제
    // 위와 같은 연결 관계가 있다면 DFS 탐색 깊이가 4가 되는 것을 이용하여 문제를 푼다
    // 반복문을 사용하여 시작 순서를 돌아가면서 DFS 탐색을 하였고, 탐색 깊이가 4가 되는 순간 1을 출력하고 프로그램을 종료했다.  1이
    // 출력되면 그 이후에는 더이상 탐색하지 않아도 되기 때문.
    // 모든 DFS탐색 동안 1이 출력이 되지 않는다면 위와 같은 관계를 찾지 못했다는 의미이므로 0을 출력해준다.
    static void dfs(int start, int depth) {
        if (depth == 4) {
            System.out.println(1);
            System.exit(0);
        }

        visited[start] = true;
        for (int i = 0; i < list[start].size(); i++) {
            int temp = list[start].get(i);
            if(!visited[temp]){
                visited[temp] = true;
                dfs(temp, depth+1);
                visited[temp] = false;
            }
        }
    }
}
