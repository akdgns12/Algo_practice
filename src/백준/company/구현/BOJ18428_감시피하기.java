package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18428_감시피하기 {
    static int N;
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;
    static String ans = "NO";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        // 3개의 장애물을 설치해 감시 피할 수 있으면 YES 아니면 NO
        /*
            장애물을 설치할 수 있는 칸들 중, 3개를 선택해서 설치하고
            조건에 맞다면 끝
         */
        visited = new boolean[N][N];
        dfs(0, 0);
        System.out.println(ans);
    }

    // 순서 x, 중복 x
    static void dfs(int cnt, int start){
        if(cnt == 3){ // 3개다 뽑았으면
            if(check()){
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        // x : i / N, y : i % 5
        for(int i=start; i<N*N; i++){
            int x = i / N;
            int y = i % N;
            if(!visited[x][y] && map[x][y] == 'X'){
                visited[x][y] = true;
                dfs(cnt + 1, i + 1);
                visited[x][y] = false;
            }
        }
    }

    // 감시 모두 피할 수 있는지 체크
    static boolean check(){
        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'T') {
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        while (isRange(nx,ny)) {
                            if (visited[nx][ny]) { // 선생님이 바라보는 곳중 하나가 범위 벗어나거나 벽이거나 장애물 설치한 곳이면
                                break; // 다른 방향 탐색하러 go
                            }

                            if(map[nx][ny] == 'S'){ // 하나라도 있으면 불가한 case
                                return false;
                            }

                            nx += dx[d];
                            ny += dy[d];
                        }
                    }
                }
            }
        }
        return true;
    }

    static boolean isRange(int x, int y){
        if(x < 0 || y < 0 || x >= N || y >= N) return false;
        else return true;
    }
}
