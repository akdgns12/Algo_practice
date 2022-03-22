package 백준.company.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1941 {
    // 소문난 칠공주 / 골드 3 / 백트래킹
    /*
        1. 5x5 이차원 배열에서의 조합으로 7칸의 좌표를 선택
        2. 선택된 7칸의 좌표는 모두 인접해야 함
        3. 반드시 S들로만 구성될 필요 X
        4. S는 적어도 4명이상
     */
    static char[][] student;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[] visited;
    static int[] selected;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        student = new char[5][5];
        visited = new boolean[25];
        selected = new int[7];
        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < 5; j++) {
                student[i][j] = str.charAt(j);
            }
        }

        dfs(0,0);
        System.out.println(answer);
    }

    // 25명의 학생 중 조합으로 7명을 선택하는 함수
    static void dfs(int start, int depth){
        if(depth == 7){ // 7명의 학생을 선택했다면
            int num = 0; // 이다솜파 학생 수 count
            int temp = 0; // 7명
            int x = 0, y = 0; // bfs함수를 시작하는 좌표를 저장할 변수
            int[][] map = new int[5][5];

            for (int i = 0; i < 25; i++) {
                int row = i/5; // 1차원 visited배열을 2차원 인덱스로 매핑
                int col = i%5;

                if(visited[i]){
                    map[row][col] = 1; // 선택 표시

                    if(temp == 0){ // temp가 0이라면 탐색 시작 -> bfs 시작 좌표
                        x = row;
                        y = col;
                    }

                    if(student[row][col] == 'S') // 선택된 7명 중 이다솜파가 몇명인지 count
                        num++;

                    temp++; // 7명 모두 골랐다면 빠르게 for문 탈출
                }

                if(temp == 7)
                    break;
            }

            if(num >= 4) // 이다솜파가 4명 이상이라면 모두 붙어 앉았는지 확인
                bfs(x,y,map);

            return;
        }

        // 25명 중 중복 없이 7명 선택 -> 백트래킹
        for (int i = start; i < 25; i++) {
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

                if(nx >= 0 && ny >=0 && nx < 5 && ny < 5){
                    if(!visited[nx][ny] && map[nx][ny] == 1){
                        q.offer(new Node(nx,ny));
                        visited[nx][ny] = true;
                        count++;
                    }
                }
            }
        }
        if(count >= 7)
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
