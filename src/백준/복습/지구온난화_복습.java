package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 지구온난화_복습 {
    static char[][] map;
    static int[][] nummap; // 해당 육지의 주변 육지 개수를 저장하는 배열
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    // 해당 육지좌표의 주변 바다의 개수를 세는게 아닌, 해당 육지 좌표의 주변 육지 개수를 세서 2미만이라면 바다로 변경해주기
    static int r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        nummap = new int[r][c];

        for(int i=0; i<r; i++){
            String str = br.readLine();
            for(int j=0; j<c; j++){
                map[i][j] = str.charAt(j);
            }
        }

        warming();
        print();
    }

    static void warming(){
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                int land = 0;
                if(map[i][j] == 'X'){


                    for(int k=0; k<4; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;

                        if(map[nx][ny] == '.') continue;

                        if(map[nx][ny] == 'X'){
                            land++;
                        }
                    } // end for
                } // end if

                // 카운팅 한 주변 육지개수 nummap에 저장해주기
                nummap[i][j] = land;
            }
        }

        // 해당 좌표의 육지 주변 육지 개수가 2미만이라면 바다로 변경해주기
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(nummap[i][j] < 2){
                    map[i][j] = '.';
                }
            }
        }
    }

    // 조건을 적용한 육지를 포함하는 최소한의 크기의 직사각형 출력하기
    static void print(){
        int lx = Integer.MAX_VALUE;
        int ly = Integer.MAX_VALUE;
        int rx = Integer.MIN_VALUE;
        int ry = Integer.MIN_VALUE;

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j] == 'X'){
                    lx = Math.min(i, lx);
                    ly = Math.min(j, ly);
                    rx = Math.max(i, rx);
                    ry = Math.max(j, ry);
                }
            }
        }

        for(int i=lx; i<=rx; i++){
            for(int j=ly; j<=ry; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
