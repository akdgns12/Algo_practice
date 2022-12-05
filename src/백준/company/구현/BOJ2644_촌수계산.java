package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2644_촌수계산 {
    static int n, start, end, m;
    static List<Integer>[] list;
    static int ans = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 사람 수

        list = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            list[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine()); // 촌수를 계산해야하는 서로 다른 두 사람 번호
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 부모
            int y = Integer.parseInt(st.nextToken()); // 자식

            list[x].add(y);
            list[y].add(x);
        }

        // 촌수 : 거쳐가는 간선 수
        bfs(start, end);
        System.out.println(ans);
    }

    static void bfs(int start, int end){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        boolean[] visited = new boolean[n+1];
        int sum = 0;

        while(!q.isEmpty()){
            int size = q.size();
            for(int sz=0; sz<size; sz++){
                int now = q.poll();

                if(now == end) {
                    ans = sum;
                    return;
                }

                for(int next : list[now]){
                    if(visited[next]) continue;
                    visited[next] = true;
                    q.offer(next);
                }
            }

            sum++;
        }
    }
}
