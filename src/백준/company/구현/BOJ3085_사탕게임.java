package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3085_사탕게임 {
    static int N;
    static char[][] map;
    static int[] dx = {0, 1}; // 우 하
    static int[] dy = {1, 0};
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<N; j++){
                map[i][j] = str.charAt(j);
            }
        }

        change();
        System.out.println(ans);
    }

    // 우 하, 둘만 보면될듯
    static void change(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                int result = Integer.MIN_VALUE;

                for(int d=0; d<2; d++){
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(!isRange(nx, ny)) continue;

                    char temp = map[i][j];
                    map[i][j] = map[nx][ny];
                    map[nx][ny] = temp;

                    // 가장 긴 부분 찾기
                    result = Math.max(result, find());

                    // 바꾼 부분 되돌려놓기
                    map[nx][ny] = map[i][j];
                    map[i][j] = temp;
                }
                ans = Math.max(result, ans);
            }
        }
    }

    static int find(){
        int max = 0;

        // 가로로 체크
        for(int i=0; i<N; i++){
            int cnt = 1;
            for(int j=0; j<N-1; j++){
                if(map[i][j] == map[i][j+1]){ // 동일하면 증가
                    cnt++;
                }else{ // 다른 사탕이면 갱신
                    cnt = 1;
                }

                max = Math.max(cnt, max);
            }
        }

        // 세로 체크
        for(int i=0; i<N; i++){
            int cnt = 1;
            for(int j=0; j<N-1; j++){
                if(map[j][i] == map[j+1][i]){
                    cnt++;
                }else{
                    cnt = 1;
                }

                max = Math.max(max, cnt);
            }
        }

        return max;
    }

    static boolean isRange(int x, int y){
        if(x < 0 || y < 0 || x >= N || y >= N) return false;
        else return true;
    }
}
