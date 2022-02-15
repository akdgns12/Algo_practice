package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 새로운게임2 {
    static class Node{
        int x, y;
        int dir;
        public Node(int x, int y, int dir){
            this.x = x ;
            this.y = y;
            this.dir = dir;
        }
    }
    static int N,K;
    static int[][] map;
    static StringBuilder[][] sb;
    static int[] dx = {0,-1,0,1}; // 우 상 좌 하
    static int[] dy = {1,0,-1,0};
    static ArrayList<Node> list = new ArrayList<>(); // 색상 정보

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 체스판의 크기
        K = Integer.parseInt(st.nextToken()); // 말의 개수

        map = new int[N][N];
        sb = new StringBuilder[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                sb[i][j] = new StringBuilder();
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;

            // 이게 뭔소리지? 이거 왜하는거임?
            // -> 이 해결방법 제시한 사람이 방향 설정을 문제에서 주어진대로 하지않고
            // 자기가 편한방식으로 설정하느라 변경해주는 작업이였음..
            // 이작업을 해주는 이유가 나중에 방향 바꿔줄 때 편리하게 하려고..
            if(dir == 1) dir = 2;
            else if(dir == 2) dir = 1;
            list.add(new Node(x, y, dir));
        }

        System.out.println(solve());
    }

    static int solve(){
        int size = list.size();
        for(int i=0; i<size; i++){
            Node node = list.get(i);
            sb[node.x][node.y].append(i); // 이거 살짝 이해안가는데
        }

        for(int k=0; k<1001; k++){ // 1000초가 지나기전에
            for(int i=0; i<size; i++){ // 말의 개수만큼
                Node node = list.get(i);

                int nx = node.x + dx[node.dir];
                int ny = node.y + dy[node.dir];
                //  (파랑, 벽)일 경우
                //  이동 방향 반대로 바꾸고 한칸이동 근데 거기가 파란색이면 가만히 있어야함
                if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2){
                    node.dir = (node.dir + 2 ) % 4;
                    nx = node.x + dx[node.dir];
                    ny = node.y + dy[node.dir];
                }
                if(!colorCheck(i, node, nx, ny, k)) return k+1; // ?
            }
        }
        return -1;
    }

    static boolean colorCheck(int i, Node node, int nx , int ny , int k){
        if(nx < 0 || ny < 0|| nx >= N || ny >= N || map[nx][ny] == 2){
            return true; // 파란색이면 끝냄
        }

        int idx = sb[node.x][node.y].indexOf(Integer.toString(i));
        StringBuilder moveHorse = new StringBuilder(sb[node.x][node.y].substring(idx));
        sb[node.x][node.y].delete(idx, sb[node.x][node.y].length());

        if(map[nx][ny] == 0){ // 흰색일떄

        }
        return true;
    }
}
