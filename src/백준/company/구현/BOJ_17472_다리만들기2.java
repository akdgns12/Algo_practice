package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17472_다리만들기2 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] parent;
    static int start = 0;
    static int ans = 0;
    static ArrayList<Island> list = new ArrayList<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 섬별로 마킹
        int mark = 1;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(!visited[i][j] && map[i][j] == 1){
                    bfs(i,j,mark);
                    mark++;
                }
            }
        }

//        print();
        parent = new int[mark];
        for(int i=1; i<mark; i++){
            parent[i] = i;
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] != 0){
                    // 다리 놓기
                    start = map[i][j];
                    for(int d=0; d<4; d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if(!rangeCheck(nx, ny)) continue;
                        if(map[nx][ny] == 0){ // 다음좌표가 바다면 다리놓기 시작
                            int cnt = 1;
                            makeBridge(nx, ny, cnt, d);
                        }
                    }
                }
            }
        }

        // 짧은 다리 먼저 놓으면서 모든 다리 연결하기 (union-find 연산 사용)
        // 다리 후보리스트를 다리길이가 짧은순으로 정렬
        Collections.sort(list);

        // list에서 하나씩 꺼내 다리를 연결(두 섬의 부모가 다르면 연결한다.)
        for(Island tmp : list){
            if(find(tmp.start) != find(tmp.end)){ // 시작섬과 도착섬이 같지 않다면
                union(tmp.start, tmp.end); // 다리를 놓고 합친다.
                ans += tmp.cnt;
            }
        }
        // 모든 다리가 연결되었는지 확인 - 하나라도 부모가 다른 섬이 있다면 모든 섬이 연결되지 않은 것이다.
        int P = find(1);
        for(int i=2; i<mark; i++){
            if(P != find(i)){
                System.out.println(-1);
                return;
            }
        }

        System.out.println(ans);
    }

    static int find(int x){
        if(x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x < y ){
            parent[y] = x;
        }else
            parent[x] = y;
    }

    /* 다리 후보 만들기  */
    static void makeBridge(int x, int y, int cnt, int dir){
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(!rangeCheck(nx, ny)) return;

        if(map[nx][ny] > 0){ // 다음 섬을 만나면 다리 놓기 끝
            if(cnt >= 2){
                // 다리 후보 리스트에 추가
                list.add(new Island(start, map[nx][ny], cnt)); // 시작점, 도착섬, 다리길이
            }
            return;
        }

        if(map[nx][ny] == 0){ // 바다면 계속 다리 놓기 진행
            makeBridge(nx, ny, cnt + 1, dir);
        }
    }

    static boolean rangeCheck(int x, int y){
        if(x < 0 || y < 0 || x >= N || y >= M) return false;
        else return true;
    }
    static void print(){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(map[i][j]+ " ");
            }
            System.out.println();
        }
    }

    static void bfs(int x, int y, int mark){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, mark));
        map[x][y] = mark;
        visited[x][y] = true;

        while (!q.isEmpty()){
            Node now = q.poll();

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;

                if(map[nx][ny] == 1){
                    q.offer(new Node(nx, ny, mark));
                    visited[nx][ny] = true;
                    map[nx][ny] = mark;
                }
            }
        }
    }

    static class Island implements  Comparable<Island>{
        int start;
        int end;
        int cnt;

        public Island(int start, int end, int cnt){
            this.start = start;
            this.end = end;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Island o){
            return o.cnt - this.cnt;
        }
    }

    static class Node{
        int x, y;
        int mark;

        public Node(int x, int y, int mark){
            this.x = x;
            this.y = y;
            this.mark = mark;
        }
    }
}
