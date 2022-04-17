package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1389 {
    // 케빈 베이컨의 6단계 법칙 / 실버 1 / BFS
    // 케빈 베이컨 - 임의의 두 사람이 최소 몇단계만에 이어질 수 있는지 -> 케빈베이컨의 수가 가장 작은 사람 출력 여러명일 경우 번호가 가장 작은 사람
    //
    static int n, m;
    static int[] dist;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        // 최소 카운트와 해당 정점 번호
        int minCnt = Integer.MAX_VALUE;
        int minIdx = 0;
        // n만큼 각각 BFS를 돌려 최댓값을 갱신
        for (int i = 1; i <= n; i++) {
            int cnt = bfs(i);
            if(minCnt > cnt){
                minCnt = cnt;
                minIdx = i;
            }
        }

        System.out.println(minIdx);
    }

    static int bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        dist = new int[n+1]; // 방문 여부 및 카운트 초기화
        int cnt = 0;
        q.offer(start);
        dist[start] = 0;

        while(!q.isEmpty()){
            int now = q.poll();

            for (int next : list[now]){
                if(dist[next] != 0) continue;
                dist[next] = dist[now] + 1;
                cnt += dist[next]; // 이동횟수 누적
                q.offer(next);
            }
        }
        return cnt;
    }
}
