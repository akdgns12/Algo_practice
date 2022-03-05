package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 가운데에서만나기 {
    /*
        1. 모든 정점 쌍 사아의 최단거리를 구한다.
        2. 친구들 도시에서 각 정점들에 대한 왕복거리 중 최대값을 배열에 담아놓는다
        3. 배열 담아 놓은 값 중 최소값을 찾는다
        4. 최소값이 여러개 나올 수 있으므로 최소값에 해당하는 정점들을 list에 담는다
        5. 담아놓은 각 정점 오름차순 출력
     */
    static int n, m;
    static int[][] dist;
    static int INF = (int)1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // a - b로 가는 정점이 여러개일 수 있으니 최솟값 저장
            dist[a][b] = Math.min(dist[a][b], cost);
        }

        int friend = Integer.parseInt(br.readLine());
        ArrayList<Integer> city = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < friend; i++) {
            city.add(Integer.parseInt(st.nextToken()));
        }


        //플로이드-와샬로 최단거리로 갱신
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int[] max = new int[n+1];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < city.size(); j++) {
                max[i] = Math.max(max[i], dist[city.get(j)][i] + dist[i][city.get(j)]);
            }
            min = Math.min(min, max[i]);
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if(min <= max[i]) result.add(i);
        }

        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }

    }
}
