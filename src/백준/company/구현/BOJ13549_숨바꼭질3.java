package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13549_숨바꼭질3 {
    static int N, K;
    static int[] dx = {-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 수빈이가 있는 위치
        K = Integer.parseInt(st.nextToken()); // 동생이 있는 위치

        bfs();
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        q.offer(new Node(N, 0));
        visited[N] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.position == K){
                System.out.println(now.time);
                return;
            }

            int next = now.position * 2;
            // if(next > 100000 || visited[next]) continue;
            if(next < 100001 && !visited[next]){
                visited[next] = true;
                q.offer(new Node(next, now.time));
            }

            for(int d=0; d<2; d++){
                next = now.position + dx[d];

                if(next >= 0 && next < 100001 && !visited[next]){
                    visited[next] = true;
                    q.offer(new Node(next, now.time + 1));
                }
            }
        }
    }

    static class Node{
        int position;
        int time;
        public Node(int position, int time){
            this.position = position;
            this.time = time;
        }
    }
}
