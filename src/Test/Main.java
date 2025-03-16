package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1. 상,좌,우,하
조건에 맞게 총 몇분 후 모든 사람들이 편의점에 도착하는지
*/
public class Main {
    static int n, m;
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};
    static boolean[][] visited;
    static boolean[][][] peopleVisited;
    static int[][] map, store;
    static Queue<Node> q;
    static int time = 1;
    static int totalStore;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 크기
        m = Integer.parseInt(st.nextToken()); // 사람 수
        map = new int[n][n];
        visited = new boolean[n][n];
        peopleVisited = new boolean[n][n][m+2];
        q = new LinkedList<>();
        totalStore = m;

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        store = new int[m+1][2];
        for(int i=0; i<m; i++){ // 편의점 정보
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            store[i][0] = x;
            store[i][1] = y;
        }

        // 0: 빈 칸, 1: 베이스캠프, 2: 편의점
        boolean isIn = false;
        int cnt = 0;
        while(true){

            if(isIn){
                bfs();
                time++;
                if(time <= m && cnt < m){
                    putPerson(cnt, m);
                    cnt++;
                }
            }else{
                if(time <= m){
                    putPerson(cnt, m);
                    isIn = true;
                }
            }

            if(totalStore == 0) break;
        }

        System.out.println(time - 1);
    }

    // 가장 가까운 베이스 캠프에 위치시키기
    static void putPerson(int cnt, int m){
        System.out.println("여기 들어온 타임: " + time);
        int x = store[cnt][0];
        int y = store[cnt][1];

        int minDist = Integer.MAX_VALUE;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for(int i=n-1; i>=0; i--){
            for(int j=n-1; j>=0; j--){
                if(map[i][j] == 1 && !visited[i][j]){ // 베이스 캠프
                    int temp = Math.abs(i - x) + Math.abs(j - y);
                    if(temp <= minDist) {
                        minDist = temp;
                        minX = i;
                        minY = j;
                    }
                }
            }
        }

        visited[minX][minY] = true;
        System.out.println("나가는 타임 : " + time);
        if(cnt < m){
            q.offer(new Node(minX, minY, cnt, 0));
        }
    }

    static void bfs(){
        while(!q.isEmpty()){
            int size = q.size();
            for(int sz=0; sz<size; sz++){
                Node now = q.poll();

                int storeX = store[now.person][0];
                int storeY = store[now.person][1];
                if(storeX == now.x && storeY == now.y){ // 해당 사람이 편의점에 도착하면
                    visited[now.x][now.y] = true;
                    if(time >= m){
                        System.out.println("time : " + time + " " + "now.dist" + now.dist);
                        time += now.dist;
                    }
                    totalStore--;
                    return;
                }

                for(int i=0; i<4; i++){
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if(!isOutRange(nx, ny) || visited[nx][ny] || peopleVisited[nx][ny][now.person]) continue;

                    q.offer(new Node(nx, ny, now.person, now.dist + 1));
                    peopleVisited[nx][ny][now.person] = true;
                }
            }
        }
    }

    static boolean isOutRange(int x, int y){
        if(x < 0 || y < 0 || x >= n || y >= n) return false;
        else return true;
    }

    static class Node{
        int x, y;
        int person;
        int dist;

        public Node(int x, int y, int person, int dist){
            this.x = x;
            this.y = y;
            this.person = person;
            this.dist = dist;
        }
    }
}


/*
5 3
0 0 0 0 0
1 0 0 0 1
0 0 0 0 0
0 1 0 0 0
0 0 0 0 1
2 3
4 4
5 1

14 28
0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 1 0 0 0 0 0
0 0 0 0 0 0 0 0 0 1 0 0 0 0
0 1 0 0 1 0 0 1 1 1 0 0 0 0
0 1 0 1 0 0 1 0 0 1 0 0 0 0
0 0 0 0 0 0 0 0 0 1 0 0 0 0
0 0 1 0 0 0 0 0 1 1 0 0 0 0
1 0 1 1 0 0 0 0 0 0 0 0 1 0
1 1 1 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0 0 0 0 1 0 1
1 0 0 0 0 0 0 1 0 0 0 1 0 0
0 0 0 0 0 1 0 0 1 0 0 0 0 0
0 0 1 0 0 0 0 0 1 1 0 0 0 0
4 12
10 9
13 4
11 8 
10 7
8 7
7 3
3 1
4 2
14 8
2 14
12 6
9 9
4 3
10 4
5 1
10 10
3 2
14 11
5 13
4 11
1 3
6 14
5 4
10 13
11 4
5 14
7 5
 */