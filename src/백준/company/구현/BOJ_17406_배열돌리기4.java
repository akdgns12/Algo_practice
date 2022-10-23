package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17406_배열돌리기4 {
    static int N, M, K;
    static int[][] map;
    // 하 우 상 좌
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Node[] list;
    static int[] pick;
    static boolean[] visited;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 회전 연산의 수

        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        list = new Node[K];
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            list[i] = new Node(r,c,s);
        }

        ans = Integer.MAX_VALUE;
        visited = new boolean[K];
        pick = new int[K];
        dfs(0);

        sb.append(ans);
        System.out.println(sb.toString());
        br.close();
    }

    static class Node{
        int x, y;
        int size;
        public Node(int x, int y, int size){
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

    static void dfs(int cnt){
        if(cnt == K){ // K만큼 수행했다면 최솟값 구하고 최종 답과 비교해서
            /*
                pick에 저장한 순서대로 r,c,s 꺼내서 연산 수행하고
                최솟값 갱신
             */
            int[][] temp = new int[N][M];
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    temp[i][j] = map[i][j];
                }
            }

            for(int i=0; i<K; i++){
                int r = list[pick[i]].x;
                int c = list[pick[i]].y;
                int s = list[pick[i]].size;

                rotate(r, c, s, temp);
            }

            int result = calc(temp);
            ans = Math.min(ans, result);
            return;
        }

        for(int i=0; i<K; i++){
            if(visited[i]) continue;
            visited[i] = true;
            pick[cnt] = i;
            dfs(cnt + 1);
            visited[i] = false;
            pick[cnt] = 0;
        }
    }

    static int calc(int[][] temp){
        int min = Integer.MAX_VALUE;
        for(int i=0; i<temp.length; i++){
            int tmp = 0;
            for(int j=0; j<temp[0].length; j++){
                tmp += temp[i][j];
            }
            if(min > tmp) min = tmp;
        }

        return min;
    }

    // (x-s,y-s) ~ (x+s,y+s) 구간 시계방향 한칸 회전
    static void rotate(int r, int c, int size, int[][] temp){
        // 결국 두 변 중 더 작은 변 / 2 만큼 회전 진행
        // -> 결국 s만큼 회전
        for(int i=0; i<size; i++){
            int x = r - size - 1 + i;
            int y = c - size - 1 + i;
            int origin = temp[x][y];
            int dir = 0;

            while (dir < 4){
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(nx >= r - size - 1 + i && ny >= c-size-1+i &&
                nx < r + size - i && ny < c + size - i){
                    temp[x][y] = temp[nx][ny];
                    x = nx;
                    y = ny;
                }else{
                    dir++;
                }
            }
            temp[r-size-1+i][c-size-1+i] = origin;
        }
    }
}
