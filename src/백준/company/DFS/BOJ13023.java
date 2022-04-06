package 백준.company.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    static boolean isLine = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 사람의 수
        m = Integer.parseInt(st.nextToken()); // 친구 관계의 수

        ArrayList<ArrayList<Integer>> arrList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arrList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arrList.get(a).add(b);
            arrList.get(b).add(a);
        }

        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited, false);
            dfs(i, arrList, visited, 1);
            if(isLine){
                break;
        }
    }


        System.out.println(isLine ? 1 : 0);
    }

    static void dfs(int start, ArrayList<ArrayList<Integer>> arrList, boolean[] visited, int cnt) {
        if (cnt == 5) {
            isLine = true;
            return;
        }

        visited[start] = true;
        for (int i : arrList.get(start)) {
            if (!visited[i]) {
                dfs(i, arrList, visited, cnt + 1);
            }

            if (isLine) {
                return;
            }
        }
        visited[start] = false; // 일직선이 아닐 경우, 방문한 지점은 모두 false 처리
    }
}
