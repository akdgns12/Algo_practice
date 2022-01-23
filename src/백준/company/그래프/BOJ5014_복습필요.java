package 백준.company.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5014_복습필요 {
    // 스타트링크 / 골드 5 / 버튼을 적어도 몇 번 눌러야 G층에 도착하는지 갈 수 없다면 "user ther stairs"
    static int F,S,G;
    static int[] move; // 버튼을 누른 수 저장
    static int[] dir = new int[2];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken()); // 건물 총 층수
        S = Integer.parseInt(st.nextToken()); // 시작 층수
        G = Integer.parseInt(st.nextToken()); // 가고자 하는 층수
        dir[0] = Integer.parseInt(st.nextToken()); // U
        dir[1] = -Integer.parseInt(st.nextToken()); // D

        move = new int[F+1];
        bfs(S); // 시작점부터
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
                int next = cur + dir[i];

                if(next > F || next < 1) continue;

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
