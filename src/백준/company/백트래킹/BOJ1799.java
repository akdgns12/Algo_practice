package 백준.company.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1799 {
    // 비숍 / 골드 1 / 백트래킹
    // 1 : 비숍 놓을 수 있는 곳(white), 0 : 비숍 놓지 못하는 곳(black)
    /*
       체스판의 흰 영역과 검은 영역은 절대 한 대각선상에 위치할 수 없다는 점
       두 영역 각 백트래킹을 수행
       이렇게 하면 시간복잡독 줄어든다
     */
        static int n;
        static int[] ans;
        static int[][] map;
        static ArrayList<Node>[] blankDotList;

        static int[] dx = {-1,-1}; // 좌상, 우상
        static int[] dy = {-1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        ans = new int[2]; // 0:black, 1:white
        blankDotList = new ArrayList[2]; // 0:black, 1:white
        for (int i = 0; i < 2; i++) {
            blankDotList[i] = new ArrayList<>();
        }

        //체스판의 검/흰 각각 영역의 비숍을 놓을 수 있는 자리를 list로 구성함
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 1){ //비숍을 놓을 수 있는 곳이라면
                    if(isBlack(i,j)){ // 같은
                        blankDotList[0].add(new Node(i,j));
                    }else{
                        blankDotList[1].add(new Node(i,j));
                    }
                }
            }
        }

        dfs(0,0,0); // 체스판의 검은 영역
        dfs(0,0,1); // 체스판의 흰 영역
        System.out.println(ans[0] + ans[1]);
    }

    static void dfs(int index, int cnt, int color){
        for (int i = index; i < blankDotList[color].size(); i++) {
            Node cur = blankDotList[color].get(i);

            if (isPossible(cur.x, cur.y)) {
                map[cur.x][cur.y] = 2;
                dfs(i+1, cnt+1, color);
                map[cur.x][cur.y] = 1;
            }
        }

        ans[color] = Math.max(ans[color], cnt);
    }

    static boolean isBlack(int x, int y){
        if (x % 2 == 0 && y % 2 == 0) {
            return true;
        }

        if(x % 2 == 1 && y % 2 == 1){
            return true;
        }

        return false;
    }

    // 비숍을 놓을 수 있는지를 판단 (대각선상에 비숍이 존재하는지)
    // 위에서부터 비숍을 놓으므로, 현재 위치보다 아래 행에 있는 체스판은 검사할 필요 X
    static boolean isPossible(int x, int y){
        for(int j=1; j<=x; j++){
            for (int i = 0; i < 2; i++) {
                int nx = x + dx[i]*j;
                int ny = y + dy[i]*j;

                if(nx >= 0 && nx < n && ny >= 0 && ny < n){
                    if(map[nx][ny] == 2){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}

