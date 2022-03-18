package 백준.company.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ3967 {
    // 매직스타 / 골드5 / 백트래킹
    static final int dr[][] = {
            {1,1,1,1},{3,3,3,3},{0,1,2,3},{0,1,2,3},{1,2,3,4},{1,2,3,4}
    };
    static final int dc[][] = {
            {1,3,5,7},{1,3,5,7},{4,3,2,1},{4,5,6,7},{1,2,3,4},{7,6,5,4}
    };
    static boolean[] visited = new boolean[13];
    static char[][] map;
    static ArrayList<Node> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[5][9];
        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'x') arr.add(new Node(i,j));
                else if(map[i][j] != '.')
                    visited[map[i][j] - 'A' + 1] = true;
            }
        }

        dfs(0, arr.size());
    }

    static void dfs(int depth, int goal){
        if(depth == goal){

        }
    }

    static class Node{
        int first, second;
        public Node(int first, int second){
            this.first = first;
            this.second = second;
        }
    }
}
