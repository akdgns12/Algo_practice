package 백준.company.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5212 {
    // 지구온난화 / 실버 2 / 시뮬
    static char[][] map;
    static int[][] nummap; // 주변의 육지 개수를 저장하는 배열
    static int r, c;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    /*
        50 년 후의 지도를 출력
        조건
        50년이 지나면 인접한 세 칸, 또는 네 칸에 바다가 있는 땅은 모두 잠긴다
        -> ? 인접한 세칸 또는 인접한 네칸?
        사방 검사해서 count가 3이상이면 '.' 바다로 바뀜
        'X' : 땅 , '.' : 바다

     */
    /**
     * 접근부터가 틀림...
     * 핵심 로직
     * 1. 육지의 상하좌우 탐색을 할 때 바다가 몇개인지를 탐색하면
     * 배열 범위를 넘어가는 경우도 고려해야 하는데 이 떄 오류가 발생
     * 그래서 육지 주변의 바다를 탐색하는게 아니라
     * 육지 주변의 육지 개수가 몇 개인지를 탐색
     * -> 만약 육지가 1개 이하라면 바다로 변경
     *
     * 2. 육지를 포함하는 최소한의 직사각형 출력 로직
     * lx = Integer.MAX_VALUE
     * rx = Integer.MIN_VALUE
     * 이런식으로
     * map돌면서 최소, 최대의 각 양쪽 x,y좌표 구해서
     * 최소의, 최대의 x,y좌표 구해서 사각형 만들기
     */

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
                    }
                }
                nummap[i][j] = land;
            }
        }

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(nummap[i][j] < 2){
                    map[i][j] = '.';
                }
            }
        }
    }

    // 모든 육지를 포함하는 최소한의 지도를 출력하는 함수
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
