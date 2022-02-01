package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 지구온난화복습 {
    static int r,c;
    static char[][] map;
    static int[][] numLand;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        numLand = new int[r][c];
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

                        // 범위 벗어나면 skip
                        if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;

                        // 바다라면 skip
                        if(map[nx][ny] == '.') continue;

                        if(map[i][j] == 'X'){
                            land++;
                        }
                    }
                }
                numLand[i][j]= land;
            }
        }

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(numLand[i][j] < 2){
                    map[i][j] = '.';
                }
            }
        }
    }

    static void print(){
        int lx = Integer.MAX_VALUE;
        int ly = Integer.MAX_VALUE;
        int rx = Integer.MIN_VALUE;
        int ry = Integer.MIN_VALUE;

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j] == 'X'){
                    lx = Math.min(lx, i);
                    ly = Math.min(ly, j);
                    rx = Math.max(rx, i);
                    ry = Math.max(ry, j);

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
