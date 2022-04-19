package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질2 {
    static int n, k;
    static int min, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        min = Integer.MAX_VALUE;
        cnt = 1;

        if(n > k){
            System.out.println(n - k);
            System.out.println(1);
        }else{
            bfs(n, k);
            System.out.println(min);
            System.out.println(cnt);
        }
    }

    static void bfs(int start, int end) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));

        boolean[] visited = new boolean[100001];
        visited[start] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();

            int now = cur.node;
            int time = cur.time;

            visited[now] = true;

            if(now == k){
                if(min > time)
                    min = time;
                else if(min == time)
                    cnt++;
                continue;
            }

            int nx1 = now - 1;
            int nx2 = now + 1;
            int nx3 = now * 2;

            if(nx1 >= 0 && !visited[nx1])
                q.offer(new Node(nx1, time + 1));
            if(nx2 <= 100000 && !visited[nx2])
                q.offer(new Node(nx2, time + 1));
            if(nx3 <= 100000 && !visited[nx3])
                q.offer(new Node(nx3, time + 1));
        }
    }

    static class Node{
        int node;
        int time;

        public Node(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }
}
