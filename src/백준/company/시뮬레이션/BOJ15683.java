package 백준.company.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ15683 {
    // 감시 / 골드 4 / 시뮬레이션
    /*
        CCTV 방향 안주어짐 -> 각 CCTV 방향의 경우의수 구해서 -> 구한 케이스마다 bfs로 감시지역 퍼트리고 사각지대 구하기 -> 구한 사각지대 값들 중 최소가 answer
     */
    static class CCTV{
        int num;
        int x, y;
        public CCTV(int num, int x, int y){
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
    static int n, m;
    static int[][] map;
    static int[][] copyMap;
    static int[] direction;
    static int[] dx = {-1,0,1,0};// 상우하좌
    static int[] dy = {0,1,0,-1};
    static ArrayList<CCTV> cctvList = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0 && map[i][j] != 6){
                    cctvList.add(new CCTV(map[i][j], i,j));
                }
            }
        }

        direction = new int[cctvList.size()];
        dfs(0);
        System.out.println(answer);
    }

    // CCTV 방향 경우의 수
    static void dfs(int depth){
        if(depth == cctvList.size()){ // 모든 CCTV의 방향을 세팅했다면
            copyMap = new int[n][m];
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    copyMap[i][j] = map[i][j];
                }
            }

            //해당 방향으로 CCTV 작동
            for(int i=0; i<cctvList.size(); i++){
                operate_CCTV(cctvList.get(i), direction[i]);
            }

            count_blind();
            return;
        }

        for(int i=0; i<4; i++){
            direction[depth] = i;
            dfs(depth+1);
        }
    }

    static void operate_CCTV(CCTV cctv, int dir){
        int num = cctv.num;

        if(num == 1){ // 1번 CCTV
            if(dir == 0) bfs(cctv, 0); // 상
            else if(dir == 1) bfs(cctv, 1); // 우
            else if(dir == 2) bfs(cctv, 2); // 하
            else if(dir == 3) bfs(cctv, 3); // 좌
        }else if(num == 2){ // 2번 CCTV
            if(dir == 0 || dir == 2){
                bfs(cctv, 0);
                bfs(cctv, 2);
            }else{
                bfs(cctv, 1);
                bfs(cctv, 3);
            }
        }else if(num == 3){ // 3번 CCTV
            if(dir == 0){
                bfs(cctv, 0);
                bfs(cctv, 1);
            }else if(dir == 1){
                bfs(cctv, 1);
                bfs(cctv, 2);
            }else if(dir == 2){
                bfs(cctv, 2);
                bfs(cctv, 3);
            }else if(dir == 3){
                bfs(cctv, 3);
                bfs(cctv, 0);
            }
        }else if(num == 4){ // 4번 CCTV
            if(dir == 0){
                bfs(cctv, 3);
                bfs(cctv, 0);
                bfs(cctv, 1);
            }else if(dir == 1){
                bfs(cctv, 0);
                bfs(cctv, 1);
                bfs(cctv, 2);
            }else if(dir == 2){
                bfs(cctv, 1);
                bfs(cctv, 2);
                bfs(cctv, 3);
            }else if(dir == 3){
                bfs(cctv, 2);
                bfs(cctv, 3);
                bfs(cctv, 4);
            }
        }else if(num == 5){
            bfs(cctv, 0);
            bfs(cctv, 1);
            bfs(cctv, 2);
            bfs(cctv, 3);
        }
    }

    static void bfs(CCTV cctv, int dir){
        Queue<CCTV> q = new LinkedList<>();
        q.offer(cctv);

        while(!q.isEmpty()){
            CCTV cur = q.poll();

            int nx = cur.x + dx[dir];
            int ny = cur.y + dy[dir];
            // 범위 벗어나거나 벽을 만나면 종료
            if(nx < 0 || ny < 0 || nx >= n || ny >= m || copyMap[nx][ny] == 6) {
                break;
            }

            // 감시 가능한 공간이라면 -1로 변경
            if(copyMap[nx][ny] == 0){ // 빈 칸이라면
                copyMap[nx][ny] = -1;
                q.offer(new CCTV(cctv.num, nx, ny));
            }
            else{ // CCTV가 있는 칸이라면 통과할 수 있으므로 큐에 삽입
                q.offer(new CCTV(cctv.num, nx, ny));
            }
        }
    }

    static void count_blind(){
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(copyMap[i][j] == 0)
                    cnt++;
            }
        }

        answer = Math.min(answer, cnt);
    }
}
