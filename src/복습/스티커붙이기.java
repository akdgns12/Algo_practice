package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커붙이기 {
    static int[][] map;
    static int n, m, k, r, c;
    static int[][] sticker;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m]; // 노트북

        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            sticker = new int[r][c];
            for(int j=0; j<r; j++){
                st = new StringTokenizer(br.readLine());
                for (int k=0; k<c; k++){
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

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(i+r > n || j+c > m) break;

                    if (putOn(i, j, r, c, sticker)) {
                        return;
                    }
                }
            }
        }
    }

    static boolean putOn(int x, int y, int r, int c, int[][] sticker) {
        for(int i=x; i<x+r; i++){
            for(int j=y; j<y+c; j++){
                if(map[i][j] == 1 && sticker[i-x][j-y] == 1){
                    return false;
                }
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

    static int[][] rotate(int[][] sticker, int r, int c){
        int[][] newSticker = new int[c][r];

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                newSticker[j][r-i-1] = sticker[i][j];
            }
        }
        return newSticker;
    }
}
