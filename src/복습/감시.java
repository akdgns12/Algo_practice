package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 감시 {
    static class CCTV{
        int num;
        int x,y;
        public CCTV(int num, int x, int y){
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
    static int n, m;
    static int[][] map;
    static int[][] copyMap;
    static int[] dx = {-1,0,1,0}; // 상우하좌
    static int[] dy = {0,1,0,-1};
    static ArrayList<CCTV> cctvList = new ArrayList<>();
    static int[] direction;
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
                    cctvList.add(new CCTV(map[i][j], i, j));
                }
            }
        }

        direction = new int[cctvList.size()];
        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int depth){
        if(depth == cctvList.size()){
            copyMap = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j=0; j<m; j++){
                    copyMap[i][j] = map[i][j];
                }
            }

            for(int i=0; i<cctvList.size(); i++){
                operate_cctv(cctvList.get(i), direction[i]);
            }

            count_blind();

            return;
        }

        for(int i=0; i<4; i++){
            direction[depth] = i;
            dfs(depth+1);
        }
    }

    static void operate_cctv(CCTV cctv, int dir){
        int num = cctv.num;

        if(num == 1){
            if(dir == 0) bfs(cctv, 0);
            else if(dir == 1) bfs(cctv, 1);
            else if(dir == 2) bfs(cctv, 2);
            else if(dir == 3) bfs(cctv, 3);
        }else if(num == 2){
            if(dir == 0 || dir == 2){
                bfs(cctv, 0);
                bfs(cctv, 2);
            }else{
                bfs(cctv, 1);
                bfs(cctv, 3);
            }
        }else if(num == 3){
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
        }else if(num == 4){
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
                bfs(cctv, 0);
            }
        }else if(num == 5){
            bfs(cctv, 0);
            bfs(cctv, 1);
            bfs(cctv, 2);
            bfs(cctv, 3);
        }
    }

    static void bfs(CCTV cctv, int dir) {
        Queue<CCTV> q = new LinkedList<>();
        q.offer(cctv);

        while(!q.isEmpty()){
            CCTV cur = q.poll();

            int nx = cur.x + dx[dir];
            int ny = cur.y + dy[dir];

            if(nx < 0 || ny < 0 || nx >= n || ny >= m || copyMap[nx][ny] == 6){
                break;
            }

            if(copyMap[nx][ny] == 0){
                copyMap[nx][ny] = 7;
                q.offer(new CCTV(cur.num, nx, ny));
            }else{
                q.offer(new CCTV(cur.num, nx, ny));
            }
        }

    }

    static void count_blind(){
        int cnt =0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(copyMap[i][j] == 0)
                    cnt++;
            }
        }

        answer = Math.min(answer, cnt);
    }
}
