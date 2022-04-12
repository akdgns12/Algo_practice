package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 치킨배달복 {
    static int n, m;
    static int[][] map;
    static boolean[] visited;
    static ArrayList<Node> house = new ArrayList<>();
    static ArrayList<Node> chicken = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    house.add(new Node(i, j));
                } else if (map[i][j] == 2) {
                    chicken.add(new Node(i,j));
                }
            }
        }

        visited = new boolean[chicken.size()];
        dfs(0, 0);
        System.out.println(answer);
    }

    static void dfs(int depth, int start) {
        if(depth == m){
            int result = 0;

            for (int i = 0; i < house.size(); i++) {
                int temp = Integer.MAX_VALUE;

                for (int j = 0; j < chicken.size(); j++) {
                    if(visited[j]){
                        int dist = distance(house.get(i).x, house.get(i).y, chicken.get(j).x, chicken.get(j).y);

                        temp = Math.min(temp, dist);
                    }
                }

                result += temp;
            }

            answer = Math.min(answer, result);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            if(!visited[i]){
                visited[i] = true;
                dfs(depth +1 , i+1);
                visited[i] = false;
            }
        }
    }

    static int distance(int x, int y, int x2, int y2) {
        return Math.abs(x - x2) + Math.abs(y - y2);
    }

    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
