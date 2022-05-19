package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16197 {
    // 두 동전 / 골드 4 / BFS
    // 두 동전 중 하나만 떨어뜨리기 위해 눌러야 하는 버튼의 최소 횟수 출력
    /*
        맵을 입력 받을 때 동전의 위치는 Coin객체를 생성하여 따로 저장.
        방문체크는 boolean 4차원 배열로 선언. 두 개의 동전이 한 번에 움직이므로 동시에 체크해줄 필요가 있음
        bfs
        1. 현재 코인들의 위치정보와 버튼을 누른 횟수가 필요하므로 두 동전의 현재 위치와 현재 버튼을 누른 횟수를
        저장하는 Node 객체 생성해 사용
        2. 현재 버튼을 누른 횟수가 10회 이상이라면 조건에 부합하지 않는 결과이므로 반복문을 빠져나와 -1 출력
        3. 현재 동전들의 위치에서 4방향 탐색 시작. 두 동전 모두 한 번에 진행
        4. 만약 두 개의 동전이 이동할 좌표가 벽이라면, 이동할 수 없으므로 제자리로 돌아옴.
        5. 떨어지지 않는 동전의 개수를 세어 주기 위해 동전 하나씩 이동할 위치가 유효한 범위에 있는지 체크
        6. 떨어지지 않는 동전의 개수가 하나라면, 찾는 정답이므로 cnt+1을 리턴
        7. 떨어지지 않는 동전의 개수가 두개면서 방문한적 없는 곳이라면 해당 좌표로 이동하고 탐색을 계속 진행
     */
    static int n, m;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][][][] visited;
    static Coin[] coin;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m][n][m]; // [첫째코인x좌표][둘째코인y좌표][둘째코인x좌표][둘째코인y좌표]

        // o : 동전, . : 빈칸, # : 벽
        coin = new Coin[2];
        int coinIdx = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'o'){ // 코인의 위치 저장
                    coin[coinIdx++] = new Coin(i, j);
                }
            }
        }

        bfs();
   }

   static void bfs(){
       Queue<Node> q = new LinkedList<>();
       q.offer(new Node(coin[0].x, coin[0].y, coin[1].x, coin[1].y, 0));
       visited[coin[0].x][coin[0].y][coin[1].x][coin[1].y] = true;

       while(!q.isEmpty()){
           Node cur = q.poll();

           if(cur.cnt >= 10){
               System.out.println(-1);
               return;
           }


           for (int i = 0; i < 4; i++) {
               int nx1 = cur.x1 + dx[i];
               int ny1 = cur.y1 + dy[i];
               int nx2 = cur.x2 + dx[i];
               int ny2 = cur.y2 + dy[i];

               // 동전이 둘 중 하나만 범위를 벗어나서 떨어지면
               if (!isRange(nx1, ny1) && isRange(nx2, ny2) || !isRange(nx2, ny2) && isRange(nx1, ny1)) {
                   System.out.println(cur.cnt + 1);
                   return;
               }

               // 조건에 맞는 경우 큐에 추가 - 1. 방문 X 2. 둘다 범위밖X 3. 벽인 경우 그대로
               if(!isRange(nx1, ny1) && !isRange(nx2, ny2)){
                   if(map[nx1][ny1] == '#'){
                       nx1 = cur.x1;
                       ny1 = cur.y1;
                   }
                   if(map[nx2][ny2] == '#'){
                       nx2 = cur.x2;
                       ny2 = cur.y2;
                   }

                   if(!visited[nx1][ny1][nx2][ny2]){
                       visited[nx1][ny1][nx2][ny2] = true;
                       q.offer(new Node(nx1, ny1, nx2, ny2, cur.cnt+1));
                   }
               }
           }
       }

       System.out.println(-1);
   }

    static boolean isRange(int x, int y) {
        return (x < 0 || y < 0 || x >= n || y >= m);
    }

   static class Node{ // 두 동전의 위치와 현재 버튼을 누른 횟수를 기록하는 구조체
        int x1,y1;
        int x2,y2;
        int cnt;

       public Node(int x1, int y1, int x2, int y2, int cnt) {
           this.x1 = x1;
           this.x2 = x2;
           this.y1 = y1;
           this.y2 = y2;
           this.cnt = cnt;
       }
   }

   static class Coin{ // 동전의 좌표를 기억하는 구조체
        int x,y;

       public Coin(int x, int y) {
           this.x = x;
           this.y = y;
       }
   }
}
