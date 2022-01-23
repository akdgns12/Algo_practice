package 백준.company.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ18513 {
    // 샘터 / 골드5 / 그래프
    static class Node{
        int x,dist;

        public Node(int x, int dist){
            this.x = x;
            this.dist = dist;
        }
    }
    static int N, K;
    static int[] dx = {-1,1};
    static int MAX = 100000000;
    static Set<Integer> visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new HashSet<>();

        Queue<Node> q = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            int temp = Integer.parseInt(st.nextToken());
            q.offer(new Node(temp,0));
            visited.add(temp);
        }

        System.out.println(bfs(q));
    }

    static long bfs(Queue<Node> q){
        long res = 0;
        int homeCnt = 0;

        while(!q.isEmpty()){
            Node node = q.poll();

            if(homeCnt == K){
                return res;
            }

            for(int i=0; i<2; i++){
                int nx = node.x + dx[i];
                // 범위 벗어나면 skip
                if(nx < -MAX || nx > MAX) continue;
                // 이미 집을 지은 곳이라면 skip
                if(visited.contains(nx)) continue;

                visited.add(nx);
                q.offer(new Node(nx, node.dist+1));

                homeCnt++;
                res += node.dist+1;
            }
        }

        return res;
    }
}
