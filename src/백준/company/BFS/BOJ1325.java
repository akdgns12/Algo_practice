package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1325 {
    // 효율적인 해킹 / 실버 1 / BFS
    // a -> b 이런식으로 연결되어있는 컴퓨터들 관계에서
    // 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 번호 출력
    /*
        단방향 그래프의 문제, 방문횟수를 체크하여 해킹이 가능한 수를 구하면 됨
        시작점으로부터 몇 개의 컴퓨터가 방문 가능한지 세면 된다.
        모든 노드를 시작점으로 잡고 각각 돌면서 방문 가능한 컴퓨터 수가 가장 많은 노드가 답
     */
    static int n, m;
    static int a, b;
    static boolean[] visited;
    static int[] answer;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        answer = new int[n+1];
        list = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        // a가 b를 신뢰한다
        // 문제에서 해킹은 위의 관계일 때 b를 해킹하면 a를 해킹가능한 것으로 나옴 즉, 반대로
        for (int i = 0 ; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            list[a].add(b);
        }

        for (int i = 1; i <= n; i++) {
            visited = new boolean[n+1];
            bfs(i);
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, answer[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if(max == answer[i])
                sb.append(i + " ");
        }

        System.out.println(sb.toString().trim());
    }

    static void bfs(int node){
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        visited[node] = true;

        while(!q.isEmpty()){
            int now = q.poll();

            for(int next : list[now]){
                if (!visited[next]) {
                    answer[next]++;
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
    }
}
