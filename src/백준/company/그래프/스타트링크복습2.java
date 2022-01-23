package 백준.company.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 스타트링크복습2 {
    static int F,S,G;
    static int[] move;
    static int[] dir = new int[2];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken()); // 총 층수
        S = Integer.parseInt(st.nextToken()); // 현재 층수
        G = Integer.parseInt(st.nextToken()); // 목표 층수
        dir[0] = Integer.parseInt(st.nextToken());
        dir[1] = -Integer.parseInt(st.nextToken());

        move = new int[F+1];
        bfs(S);
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        visited = new boolean[F+1];
        q.offer(start);

        visited[start] = true;
        move[start] = 0;

        while(!q.isEmpty()){
            int cur = q.poll();

            if(cur == G){
                System.out.println(move[cur]);
                return;
            }

            for(int i=0; i<2; i++){
                int next = cur + dir[2];

                if(next < 1 || next > F) continue;

                if(!visited[next]){
                    visited[next] = true;
                    q.offer(next);
                    move[next] = move[cur] + 1;
                }
            }
        }
        System.out.println("use the stairs");
    }
}
