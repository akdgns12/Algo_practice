package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_탈출 {
    static class Node{
        int x, y;
        int time;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    static int r,c;
    static char[][] map;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static Queue<Node> biber = new LinkedList<>();
    static Queue<Node> water = new LinkedList<>();
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];

        for(int i=0; i<r; i++){
            String str = br.readLine();
            for(int j=0; j<c; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == '*'){
                    water.add(new Node(i,j));
                }else if(map[i][j] == 'S'){
                    biber.add(new Node(i,j,0));
                }
            }
        }

        bfs();
        if(min == Integer.MAX_VALUE) System.out.println("KAKTUS");
        else System.out.println(min);
    }

    // 물 먼저 이동하고 그 다음 비버가 이동
    static void bfs(){
        while(!biber.isEmpty()){
            int size = water.size(); // 원래 있던 물만큼 이동해야 하므로

            for(int i=0; i<size; i++){
                Node node = water.poll();

                for(int j=0; j<4; j++){
                    int nx = node.x + dx[j];
                    int ny = node.y + dy[j];

                    if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;

                    if(map[nx][ny] == 'D' || map[nx][ny] == 'X' || map[nx][ny] == '*') continue;
                    else if(map[nx][ny] == '.'){
                        water.add(new Node(nx,ny));
                    }
                }
            }

            size = biber.size();
            for(int i=0; i<size; i++){
                Node node = biber.poll();

                if(map[node.x][node.y] == 'D'){
                    System.out.println(node.time);
                    return;
                }

                for(int j=0; j<4; j++){
                    int nx = node.x + dx[j];
                    int ny = node.y + dy[j];

                    if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;

                    if(map[nx][ny] == 'D'){
                        min = Math.min(min, node.time+1);
                        return;
                    }
                    if(map[nx][ny] == '.'){
                        map[nx][ny] = 'S';
                        biber.offer(new Node(nx,ny,node.time+1));
                    }
                }
            }
        }
    }
}
