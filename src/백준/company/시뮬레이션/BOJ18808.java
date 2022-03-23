package 백준.company.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ18808 {
    // 스티커 붙이기 / 골드 3 / 시뮬레이션
    // 조건에 맞게 노트북(격자판)에 스티커 최대한 붙이고 그 붙인 칸이 총 몇개인지
    static int n, m, k;
    static int r,c;
    static int[][] map; // 노트북
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 노트북 세로
        m = Integer.parseInt(st.nextToken()); // 노트북 가로
        k = Integer.parseInt(st.nextToken()); // 스티커 개수
        map = new int[n][m];

        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()); // 스티커 행
            c = Integer.parseInt(st.nextToken()); // 스티커 열
            int[][] sticker = new int[r][c]; // 스티커 담을 임시배열

            for(int j=0; j<r; j++){ // 스티커 정보 넣기
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<c; k++){
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            findLocation(sticker, r, c);
        }
        System.out.println(answer);
    }

    static void findLocation(int[][] sticker, int r, int c){
        for(int d=0; d<4; d++){
            if(d != 0)
                sticker = rotate(sticker, r, c);
            r = sticker.length;
            c = sticker[0].length;

            for(int i=0; i<n; i++) {
                for (int j = 0; j < m; j++) {
                    // 해당 노트북의 좌표 + 스티커 범위 가 노트북 범위를 벗어나면 break
                    if(i + r > n || j + c > m) break;

                    // 범위 안벗어나면 노트북의 좌표, 스티커의 범위, 스티커 붙일 수 있는지 검사
                    if(putOn(i, j, r, c, sticker)){
                        return;
                    }
                }
            }
        }
    }

    static boolean putOn(int x, int y, int r, int c, int[][] sticker){
        for(int i=x; i<x+r; i++){
            for(int j=y; j<y+c; j++){
                // 해당 좌표에 이미 스티커가 있고 스티커의 해당 좌표가 색칠된 부분이라면 붙일 수 없음
                if(map[i][j] == 1 && sticker[i-x][j-y] == 1)
                    return false;
            }
        }

        for(int i=x; i<x+r; i++){
            for(int j=y; j<y+c; j++){
                if(sticker[i-x][j-y] == 1){
                    map[i][j] = 1;
                    answer++;
                }
            }
        }

        return true;
    }

    // 배열 돌리기
    static int[][] rotate(int[][] sticker, int r, int c){
        int[][] newSticker = new int[c][r];

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                newSticker[j][r - i - 1] = sticker[i][j];
            }
        }

        return newSticker;
    }
}
