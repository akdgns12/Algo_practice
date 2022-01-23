package 백준.company.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12100_골2구현너무빡센데 {
    // 2048 게임 / 골드 2 / 그래프 탐색
    /*
        1. 한 번에 한 방향으로 이동이 가능하며 이동할 때 모든 블록이 같이 움직임
        2. 이동하는 과정에서 같은 값을 가진 블록을 만나면 이동방향에 있는 블록의 위치로 값이 합쳐짐
        3. 이동하는 과정에서 한번 합쳐진 적이 있던 블록은 다시 같은 값을 만나도 합쳐질 수 없음
     */
    static int[][] map;
    static int N;
    static int[] dx = {-1,1,0,0}; // 상 하 좌 우
    static int[] dy = {0,0,-1,1};
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[N][N];

        // 0: 빈칸, 이외의 모든 값은 블록
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값을 구하라
        // 5번 이하로 움직이는 모든 경우를 판단할 재귀 메서드
        dfs(0,map);
        System.out.println(max);
    }

    /*
        이동하는 모든 경우의 수를 체크할 재귀메서드
        (재귀메서드 진행 횟수, 보드가 움직여야하는 횟수, 보드판)
     */
    static void dfs(int depth, int[][] map){
        // 보드가 5번 움직였을 경우
        if(depth == 5){
            // 각각의 경우의 수마다 결과값을 저장하기 위한 변수
            int tempResult = 0;

            // 보드판의 모든 값을 순회해서 가장 높은 숫자를 결과 임시변수에 저장
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    tempResult = Math.max(tempResult, map[i][j]);
                }
            }
            // 임시결과변수와 결과변수를 비교해서 큰 값을 결과변수에 저장 후 종료
            max = Math.max(tempResult, max);
            return;
        }

        // 반복문으로 상하좌우 이동
        for(int i=0; i<4; i++){

            // 보드 내에서 숫자가 합쳐졌는지 여부를 판단할 배열
            boolean[][] sumCheck = new boolean[N][N];

            // 보드 내에서 경우의 수마다 보드판을 조작해야 하므로 임시보드판 생성 후 초기화
            int[][] tempMap = new int[N][N];
            for(int j=0; j<N; j++){
               for(int k=0; k<N; k++){
                   tempMap[j][k] = map[j][k];
               }
           }
            // 보드판의 값들을 위로, 왼쪽으로 움직이는 경우
           if(i == 0 || i == 2){
               // 보드판의 좌측 상단부터 시작
               for(int j=0; j<N; j++){
                    for(int k=0; k<N; k++){
                        // 정해진 방향으로 보드판의 값들을 움직이기
                        move(tempMap, sumCheck, i, j, k);
                    }
               }
           }
           // 보드판의 값들을 아래로, 오른쪽으로 움직이는 경우
           else if(i == 1 || i == 3){
               // 보드판의 우측 하단부터 시작
               for(int j=N-1; j>=0; j--){
                   for(int k=N; k>=0; k--){
                       // 정해진 방향으로 보드판의 값들을 움직이기
                       move(tempMap, sumCheck, i, j, k);
                   }
               }
           }

            // 한번의 이동이 완료된 후 재귀 호출
            dfs(depth+1, map);
        }
    }

    /*
        보드판의 값들을 움직이기 위해 사용하는 메서드
        tempMap : 보드판을 표현할 배열
        sumCheck : 보드판의 값들이 합쳐졌는지 여부를 체크하기 위한 배열
        i : 어느 방향으로 움직이는지를 구분하기 위한 변수(0:상, 1:하, 2:좌, 3:우)
        j : 시작 행
        k : 시작 열
     */
    static void move(int[][] tempMap, boolean[][] sumCheck, int i, int j, int k){
        // 보드판의 값 체크를 시작할 행과 열 받기
        int x = j;
        int y = k;
        // 이동할 위치의 행과 열 계산
        int nx = x + dx[i];
        int ny = y + dy[i];

        // 계산된 위치가 보드판을 벗어나면 종료
        if(nx < 0 || ny < 0 || nx >= N || ny >= N) return;

        // 이동의 종료 여부를 판단할 변수
        boolean end = false;

        // 이동 시작
        while(!end){
            // 이동할 위치에 값이 0이면
            if(tempMap[nx][ny] == 0){
                // 현재 위치의 값을 이동할 위치의 값으로 저장
                tempMap[nx][ny] = tempMap[x][y];
                // 현재 위치의 값을 0으로 변경
                tempMap[x][y] = 0;
                // 현재 위치의 행과열 값 갱신
                x = nx;
                y = ny;
                // 다음에 이동할 위치 계산
                nx = x + dx[i];
                ny = y + dy[i];
                // 계산된 위치가 보드판을 벗어나면 이동 종료
                if(nx < 0 || ny < 0 || nx >= N || ny >= N ){
                    end = true;
                }
            }
            // 이동할 위치에 값이 현재의 값과 동일하면
            else if(tempMap[nx][ny] == tempMap[x][y]){
                // 이동할 위치의 값이 합쳐진 이력이 없을 경우 블록을 합치고 종료
                if(!sumCheck[nx][ny]){
                    // 이동할 위치의 값 갱신(블록 2개가 합쳐지는 것)
                    tempMap[nx][ny] = tempMap[nx][ny] * 2;
                    // 현재 위치의 값 0으로 변경
                    tempMap[x][y] = 0;
                    // 이동할 위치의 값이 합쳐진 직이 있다는 이력 남기기
                    sumCheck[nx][ny] = true;
                }
                // 이동할 위치의 값이 합쳐진 이력이 존재하면 아무것도 하지않고 이동 종료
                end = true;
            }
            // 이동할 위치의 값이 현재 위치의 값과 다르다면 이동 종료
            else{
                end = true;
            }
        }
    }
}
