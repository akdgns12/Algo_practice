package 백준.company.그래프탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16928 {
    // 뱀과 사다리 게임 / 실버 1 / 그래프 탐색
    /*
        방문했던 칸은 다시 방문하지 않게 boolean 배열
        주사위는 1~6 해당 칸에서 6번의 다른 위치로 이동할 기회
        이 때 해당 칸이 사다리나 뱀인지 판단해 사다리나 뱀이면 입력했던
        다른 칸으로 이동하고 아니라면 주사위의 숫자만큼 이동
     */
    static int N,M;
    static int[] count = new int[101];
    static int[] ladderAndShake = new int[101];
    static boolean[] visited = new boolean[101];
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 사다리 수
        M = Integer.parseInt(st.nextToken()); // 뱀의 수

        // 사다리 정보
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ladderAndShake[x] = y;
        }

        // 뱀 정보
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            ladderAndShake[u] = v;
        }

        bfs();
    }

    static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.offer(1); // 초기 시작위치 1번부터 큐에 넣어줌
        visited[1] = true;
        count[1] = 0; // 칸 인덱스에 따른 이동횟수 저장 배열

        while(!q.isEmpty()){
            int cur = q.poll();
            if(cur == 100){ // 종료조건 100번에 도착했을시 이동횟수 return
                System.out.println(count[cur]);
                return;
            }

            for(int i=1; i<7; i++){
                int x = cur + i; // x : 주사위 굴리고 난 후 이동 좌표
                if(x > 100) continue; // 범위 벗어나면 skip
                if(visited[x]) continue; // 방문했던 곳이면 skip
                visited[x] = true;

                if(ladderAndShake[x] != 0){ // 사다리 또는 뱀의 위치일 때
                    if(!visited[ladderAndShake[x]]){ // 방문했던 곳이 아니라면
                        q.offer(ladderAndShake[x]);
                        visited[ladderAndShake[x]] = true;
                        count[ladderAndShake[x]] = count[cur] + 1;
                    }
                }else{ // 아무것도 아닐 때(사다리 또는 뱀의 위치가 아닐 때)
                    q.offer(x);
                    count[x] = count[cur] + 1;
                }
            }
        }
    }
}
