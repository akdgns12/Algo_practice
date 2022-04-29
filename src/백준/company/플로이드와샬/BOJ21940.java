package 백준.company.플로이드와샬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ21940 {
    // 가운데에서 만나기 / 골드 4 / 그래프, 플로이드 와샬
    /*
        1. 모든 정점 쌍 사이의 최단거리를 플로이드 와샬로 구한다
        2. 구한 최단거리를 사용하여 도시별로 왕복시간 중 최대를 찾는다
        3. 각 도시별 왕복시간 최대값 중에서 최소를 찾는다
        4. 최소값으로 갈 수 있는 도시들을 찾는다
        5. 찾은 도시들 오름차순 출력
     */
    static int n, m, t;
    static int INF = (int)1e9;
    static int[][] dist; // 최단거리 테이블

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // 자기자신으로 가는 비용은 0
                if(i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 문제의 그래프는 단일 방향 그래프
            // 가는 경로가 하나가 아닐 수 있다. 따라서 그 중 최소비용을 저장한
            dist[a][b] = Math.min(dist[a][b], cost);
        }

        int friend = Integer.parseInt(br.readLine());
        ArrayList<Integer> friendCity = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < friend; i++) {
            friendCity.add(Integer.parseInt(st.nextToken()));
        }

        //1~n까지 플로이드 와
        //k번 정점을 거쳐가는 좌표
        //모든 정점 쌍 사이의 최단거리 구해서 갱신
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        // 입력받은 도시와 각 정점들의 왕복시간 중 최대값들 배열에 저장
        int[] max = new int[n+1];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            // 각 정점에 대해 친구들 size()만큼 구한 왕복시간 중 최대값 구하기
            for (int j = 0; j < friendCity.size(); j++) {
                max[i] = Math.max(max[i], dist[friendCity.get(j)][i] + dist[i][friendCity.get(j)]);
            }
            // 그렇게 구한 최대값들 중에서 최소 찾기
            min = Math.min(max[i], min);
        }

        // 최소값으로 갈 수 있는 도시 찾음
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if(min >= max[i]) result.add(i);
        }

        for (int i=0; i<result.size(); i++){
            System.out.print(result.get(i) + " ");
        }
    }
}
