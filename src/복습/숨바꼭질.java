package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질 {
    static int n, k;
    static int[] dist; // 인덱스가 위치, 값이 시간
    static int[] move;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dist = new int[100001];
        Arrays.fill(dist, -1);

        System.out.println(bfs());
    }

    static int bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        dist[n] = 0; //수빈이 초기위치에서의 시간은 0초

        while(!q.isEmpty()){
            int cur = q.poll();

            if(cur == k){
                return dist[cur];
            }

            move = new int[3];
            move[0] = cur + 1;
            move[1] = cur - 1;
            move[2] = cur * 2;

            for (int i = 0; i < 3; i++) {
                // 범위 체크
                if(move[i] >= 0 && move[i] <= 100000){
                    // 방문했던 곳 체크
                    if(dist[move[i]] == -1){
                        q.offer(move[i]);
                        dist[move[i]] = dist[cur] + 1;
                    }
                }
            }
        }
        return -1;
    }
}
