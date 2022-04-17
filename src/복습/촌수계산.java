package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 촌수계산 {
    static int result = 0;
    static int[] dist; // 촌수 저장할 배열
    static ArrayList<Integer>[] relation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        relation = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            relation[i] = new ArrayList<>();
        }
        dist = new int[n+1];
        Arrays.fill(dist, -1);

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            relation[x].add(y);
            relation[y].add(x);
        }

        bfs(start, end);
        System.out.println(result == 0 ? -1 : result);
    }

    static void bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        dist[start] = 0;

        while(!q.isEmpty()){
            int now = q.poll();

            if(now == end){
                result = dist[now];
                return;
            }

            for (int i = 0; i < relation[now].size(); i++) {
                int next = relation[now].get(i);
                if(dist[next] != -1) continue; // 이미 확인한 사람이면 pass
                dist[next] = dist[now] + 1;
                q.offer(next);
            }
        }
    }
}
