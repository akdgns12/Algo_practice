package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ15683 {
    static class CCTV{
        int num;
        int x, y;
        public CCTV(int num, int x, int y){
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
    static int N, M;
    static int[][] map;
    static int[][] copyMap;
    static int[] direction;
    static ArrayList<CCTV> cctvList = new ArrayList<>();
    static int[] dx = {-1,0,1,0}; // 상 우 하 좌
    static int[] dy = {0,1,0,-1};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0 && map[i][j] != 6){ // cctvList에 카메라 넣어주기
                    cctvList.add(new CCTV(map[i][j], i, j));
                }
            }
        }

        direction = new int[cctvList.size()]; // 순열 담을배열
        permutation(0);

        System.out.println(answer);
    }

    //
    public static void permutation(int depth){
        if(depth == cctvList.size()){ // 모든 CCTV의 방향을 세팅했다면
            copyMap = new int[N][M];
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    copyMap[i][j] = map[i][j];
                }
            }

            // 해당 방향으로 CCTV 작동
            for(int i=0; i<cctvList.size(); i++){
                operate_CCTV(cctvList.get(i), direction[i]);
            }

            count_blind();

            return;
        }

        // 0:상, 1:우, 2:하, 3:좌 중 방향 세팅
        for(int i=0; i<4; i++){
            direction[depth] = i;
            permutation(depth+1);
        }
    }

    public static void operate_CCTV(CCTV cctv, int dir){
        int number = cctv.num;

        if(number == 1){ // 1번 CCTV
            if(dir == 0) watch_CCTV(cctv, 0); // 상
            else if(dir == 1) watch_CCTV(cctv, 1); // 우
            else if(dir == 2) watch_CCTV(cctv, 2); // 하
            else if(dir == 3) watch_CCTV(cctv, 3); // 좌
        }else if(number == 2){ // 2번 CCTV
            if(dir == 0 || dir == 2){
                watch_CCTV(cctv, 0); // 상
                watch_CCTV(cctv, 2); // 하
            }else{ // 좌우
               watch_CCTV(cctv, 1); // 좌
               watch_CCTV(cctv, 3); // 우
            }
        }else if(number == 3){ // 3번 CCTV
            if(dir == 0){
                watch_CCTV(cctv, 0); // 상
                watch_CCTV(cctv, 1); // 우
            }else if(dir == 1){ // 우하
                watch_CCTV(cctv, 1);
                watch_CCTV(cctv, 2);
            }else if(dir == 2) { // 하좌
                watch_CCTV(cctv, 2);
                watch_CCTV(cctv, 3);
            }else if(dir == 3){ // 좌상
                watch_CCTV(cctv, 3);
                watch_CCTV(cctv, 0);
            }
        }else if(number == 4){
            if(dir == 0){ // 좌상우
                watch_CCTV(cctv, 3);
                watch_CCTV(cctv, 0);
                watch_CCTV(cctv, 1);
            }else if(dir == 1){ // 상우하
                watch_CCTV(cctv, 0);
                watch_CCTV(cctv, 1);
                watch_CCTV(cctv, 2);
            }else if(dir == 2){ // 우하좌
                watch_CCTV(cctv, 1);
                watch_CCTV(cctv, 2);
                watch_CCTV(cctv, 3);
            }else if(dir == 3){ // 하좌상
                watch_CCTV(cctv, 2);
                watch_CCTV(cctv, 3);
                watch_CCTV(cctv, 0);
            }
        }else if(number == 5){ // 상하좌우
            watch_CCTV(cctv, 0);
            watch_CCTV(cctv, 1);
            watch_CCTV(cctv, 2);
            watch_CCTV(cctv, 3);
        }
    }

    // cctv있는 위치에서 bfs로 방향과 cctv특성에 맞게 퍼지게 탐색
    static void watch_CCTV(CCTV cctv, int dir){
        Queue<CCTV> q = new LinkedList<>();
        q.add(cctv);

        while(!q.isEmpty()){
            CCTV p = q.poll();

            int nx = p.x + dx[dir];
            int ny = p.y + dy[dir];

            // 범위 벗어나거나 벽을 만나면 종료
            if(nx < 0 || ny < 0 || nx >= N || ny >= M || copyMap[nx][ny] == 6){
                break;
            }

            // 감시 가능한 공간이라면 -1로 변경
            if(copyMap[nx][ny] == 0){ // 빈 칸이라면
                copyMap[nx][ny] = -1;
                q.add(new CCTV(cctv.num, nx, ny));
            }
            // CCTV가 있는 칸이라면 통과할 수 있으므로 큐에 삽입
            else{
                q.add(new CCTV(cctv.num, nx, ny));
            }
        }
    }

    static void count_blind(){
        int cnt = 0;

        for(int i=0; i<N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0)
                    cnt++;
            }
        }
        answer = Math.min(answer, cnt);
    }
}