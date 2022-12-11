package 복습;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 소문난칠공주 {
    static char[][] map;
    static boolean[] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int answer =0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new char[5][5];
        visited = new boolean[25];
        for (int i=0; i<5; i++){
            String str = br.readLine();
            for (int j=0; j<5; j++){
                map[i][j] = str.charAt(j);
            }
        }

        dfs(0,0);
        System.out.println(answer);
    }

    static void dfs(int start, int depth){
        if(depth == 7){
            int num = 0; // 이다솜파 학생수 count
            int temp = 0; // 7명
            int[][] tempMap = new int[5][5];
            int x = 0 , y = 0; // bfs함수를 시작하는 좌표를 저장할 변수

            for(int i=0; i<25; i++){
                int row = i / 5; // 1차원 visited 배열을 2차원 인덱스로 매핑
                int col = i % 5;

                if(visited[i]){
                    tempMap[row][col] = 1; // 선택 표시

                    if(temp == 0){
                        x = row;
                        y = col;
                    }

                    if(map[row][col] == 'S') num++;

                    temp++; // 7명 모두 골랐다면 빠르게 for문 탈출
                }

                if(temp == 7) break;
            }
            // 7명 중 이다솜파가 4명 이상이라면 인접해있는지 검사
            if(num >= 4)
                bfs(x,y,tempMap);

            return;
        }

        for(int i=start; i<25; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(i+1, depth+1);
                visited[i] = false;
            }
        }
    }

    static void bfs(int x, int y, int[][] map){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y));

        boolean[][] visited = new boolean[5][5];
        visited[x][y] = true;

        int count = 1;
        while(!q.isEmpty()){
            Node node = q.poll();

            for(int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < 5 && ny < 5){
                    if(!visited[nx][ny] && map[nx][ny] == 1){
                        visited[nx][ny] = true;
                        q.offer(new Node(nx,ny));
                        count++;
                    }
                }
            }
        }

        if(count == 7)
            answer++;
    }

    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
