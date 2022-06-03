package 백준.company.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ6118 {
    // 숨바꼭질 / 실버 1 / 다익스트라
    static int n, m;
    static ArrayList<ArrayList<Integer>> list;
    static int[] dist;
    static boolean[] visited;
    static final int INF = (int)1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        dist = new int[n+1];
        Arrays.fill(dist, INF);

        visited = new boolean[n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        dijkstra(1);
    }

    static void dijkstra(int start) {
        dist[start] = 0;
        visited[start] = true;

        // 시작점과 연결된 노드의 거리 갱신
        for (int i = 0; i < list.get(start).size(); i++) {
            int next = list.get(start).get(i);
            if(!visited[next]) dist[next] = 1;
        }

        // 모든 점에 대해서
        for (int d = 0; d < n - 1; d++) {
            int min = Integer.MAX_VALUE;
            int minPos = -1;

            // 방문하지 않은 노드 중 dist 최소 값 찾기
            for (int i = 1; i <= n; i++) {
                if (!visited[i] && dist[i] != Integer.MAX_VALUE) {
                    if (dist[i] < min) {
                        min = dist[i];
                        minPos = i;
                    }
                }
            }

            // 최소 거리 가지는 점 방문처리
            visited[minPos] = true;

            // minPos와 연결되었으면서 방문하지 않은 점 check
            for (int i = 0; i < list.get(minPos).size(); i++) {
                int x = list.get(minPos).get(i);
                if (!visited[x]) {
                    if (dist[x] > dist[minPos] + 1) {
                        dist[x] = dist[minPos] + 1;
                    }
                }
            }
        }

        int max = -1;
        int maxPos = -1;
        int maxNum = 1;
        // 최댓값 찾기
        for (int i = 1; i <= n; i++) {
            if (max < dist[i]) {
                max = dist[i];
                maxPos = i;
                maxNum = 1;
            } else if (max == dist[i]) {
                maxNum++;
            }
        }

        System.out.println(maxPos + " " + max + " " + maxNum);
    }
}
