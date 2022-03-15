package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697 {
    // 숨바꼭질 / 실버 1 / BFS
    static int n, k;
    static int[] dist; // 인덱스가 위치, 값이 시간
    static int[] move = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 수빈이 위치
        k = Integer.parseInt(st.nextToken()); // 동생 위치
        dist = new int[100001];
        Arrays.fill(dist, -1);

        System.out.println(bfs());
    }

    static int bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        dist[n] = 0;

        while(!q.isEmpty()){
            int cur = q.poll();

            move[0] = cur + 1;
            move[1] = cur - 1;
            move[2] = cur * 2;

            for(int i=0; i<3; i++){
                // 범위 체크
                if(move[i] >= 0 && move[i] <= 100000){
                    // 이전에 방문했는지 체크
                    if(dist[move[i]] == -1){
                        // 처음 간곳이라면 큐에 넣고 상태를 전 위치값 +1 해준다.
                        q.offer(move[i]);
                        dist[move[i]] = dist[cur] + 1; // 해당 위치로 이동하고 걸린시간 1만큼 더해준다.
                    }
                }
            }
        }
        return -1;
    }
}
