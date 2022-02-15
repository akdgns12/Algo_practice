package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 스타트링크 {
    static int F,S,G;
    static int[] dir = new int[2];
    static boolean[] visited;
    static int[] move;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken()); // 총 건물 층수
        S = Integer.parseInt(st.nextToken()); // 시작 층수
        G = Integer.parseInt(st.nextToken()); // 목표 층수
        dir[0] = Integer.parseInt(st.nextToken()); // U
        dir[1] = -Integer.parseInt(st.nextToken()); // D

        bfs(S);
        System.out.println();
    }

    static void bfs(int start){
        Queue<Integer> q=  new LinkedList<>();
        visited = new boolean[F+1];
        move = new int[F+1];

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
                int next = cur + dir[i];

                if(next < 1 || next > F) continue;

                if(!visited[next]){
                    visited[next] = true;
                    q.offer(next);
                    move[next] = move[cur] + 1;
                }
            }
        }
        System.out.println("use ther stairs");
    }
}