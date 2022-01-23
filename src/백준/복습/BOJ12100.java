package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12100 {
    static int N;
    static int[][] map;
    static int[] dx = {-1,1,0,0}; // 상하좌우
    static int[] dy = {0,0,-1,1};
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 5번 이하로 움직여서 얻을 수 있는 모든 경우의 수 구하는 재귀 메서드
        dfs(0, map);
        System.out.println(max);
    }

    static void dfs(int depth, int[][] map){
        if(depth == 5){

            int tempResult = 0;
            // 보드판의 모든 칸을 순회해서 가장 큰값을 저장
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    tempResult = Math.max(tempResult, map[i][j]);
                }
            }

            max = Math.max(tempResult, max);
            return;
        }

        // 반복문으로 상하좌우 이동
        for(int i=0; i<4; i++){
            // 합쳐진 여부 판단할 체크배열
            boolean[][] sumCheck = new boolean[N][N];

            // 각각의 경우의 수마다 보드판을 조작해야 하므로 임시보드판 생성 후 초기화
            int[][] tempMap = new int[N][N];
            for(int j=0; j<N; j++){
                for(int k=0; k<N; k++){
                    tempMap[j][k] = map[j][k];
                }
            }

            // 상, 좌로 움직일 때
            if(i == 0 || i == 2){
                // 보드판의 좌측 상단부터 시작
                for(int j=0; j<N; j++){
                    for(int k=0; k<N; k++){
                        // 정해진 방향으로 보드판의 값들을 움직이기
                        move(tempMap, sumCheck, i, j, k);
                    }
                }
            }

            // 하, 우로 움직일 때
            else if(i == 1 || i == 3){
                // 보드판의 우측 하단부터 시작
                for(int j=N-1; j>=0; j--){
                    for(int k=N-1; k>=0; k--){
                        // 정해진 방향을 보드판의 값들을 움직이기
                        move(tempMap, sumCheck, i, j, k);
                    }
                }
            }
            // 한 번의 이동이 끝나고 재귀호출
            dfs(depth+1, tempMap);
        }
   }

    // 보드판의 값들을 움직이기 위한 메서드
    // 파라미터 : 임시Map, 보드판의 값들이 합쳐졌는지 체크할 배열, 어느방향으로 움직이는지 구분할 변수, 시작 행, 시작 열
   static void move(int[][] tempMap, boolean[][] sumCheck, int i, int j, int k){
        // 보드판의 값 체크를 시작할 행과 열 받기
       int x = j;
       int y = k;
       // 이동할 위치의 행과 열 계산
       int nx = x + dx[i];
       int ny = y + dy[i];
       // 범위 벗어나면 종료
       if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
           return;
       }

       // 이동의 종료를 판단할 변수
       boolean end = false;

       // 이동 시작
       while(!end){
            // 이동할 위치에 값이 0이면
           if(tempMap[nx][ny] == 0){
               // 현재 위치의 값을 다음 위치의 값으로 저장
               tempMap[nx][ny] = tempMap[x][y];
               // 현재위치의 값을 0으로 변경
               tempMap[x][y] = 0;
               // 현재 위치의 행과열 값 갱신
               x = nx;
               y = ny;
               // 다음에 이동할 위치 계산
               nx = x + dx[i];
               ny = y + dy[i];
                // 계산된 위치가 보드판을 벗어나면 계산 종료
               if(nx < 0 || ny < 0 || nx >= N || ny >= N){
                   end = true;
               }
           }
           // 이동할 위치의 값이 현재 위치의 값과 같을 때 (합쳐질 때)
           else if(tempMap[nx][ny] == tempMap[x][y]){
                if(!sumCheck[nx][ny]){ // 합쳐진적이 없다면 합치고 종료
                    tempMap[nx][ny] = tempMap[nx][ny] * 2;
                    tempMap[x][y] = 0;
                    sumCheck[nx][ny] = true;
                }
                // 이동할 위치의 값이 합쳐진 적이있다면 바로 종료
               end = true;
           }
           // 이동할 위치의 값이 현재위치의 값과 다르다면 이동 종료
           else{
               end = true;
           }
       }
   }
}
