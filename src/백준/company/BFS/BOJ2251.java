package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2251 {
    // 물통 / 실버 1 / BFS, DFS
    static int[] water;
    // from -> to, 모든 경우의 수
    static int[] from = {0, 0, 1, 1, 2, 2};
    static int[] to = {1, 2, 0, 2, 0, 1};
    static boolean[][] check;
    static boolean[] answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        water = new int[3];
        for(int i=0; i<3; i++) water[i] =Integer.parseInt(st.nextToken());

        check = new boolean[201][201];
        answer = new boolean[201];

        bfs();
        for (int i=0; i<=water[2]; i++){
            if(answer[i])
                System.out.print(i + " ");
        }
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0,0));
        check[0][0] = true;
        answer[water[2]] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int a = node.x;
            int b = node.y;
            int c = water[2] - a - b;

            for (int i = 0; i < 6; i++) {
                int[] next = {a, b, c,};
                next[to[i]] += next[from[i]];
                next[from[i]] = 0;

                if (next[to[i]] > water[to[i]]) { // 물통의 용량보다 물이 많을 때
                    next[from[i]] = next[to[i]] - water[to[i]]; // 초과하는 만큼 다시 넣어주고
                    next[to[i]] = water[to[i]]; // 용량에 가득 찬 물을 넣어준다.
                }
                if(!check[next[0]][next[1]]){
                    check[next[0]][next[1]] = true;
                    q.offer(new Node(next[0], next[1]));
                    if(next[0] == 0){
                        answer[next[2]] = true;
                    }
                }
            }
        }
    }

    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
