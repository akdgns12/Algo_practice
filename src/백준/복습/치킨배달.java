package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 치킨배달 {
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int n,m;
    static int[][] map;
    static ArrayList<Node> house = new ArrayList<>();
    static ArrayList<Node> chicken = new ArrayList<>();
    static int min = Integer.MAX_VALUE;
    static boolean[] visited;

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
                if(map[i][j] == 1) house.add(new Node(i,j));
                else if(map[i][j] == 2) chicken.add(new Node(i, j));
            }
        }

        visited = new boolean[chicken.size()];

        dfs(0, 0);
        System.out.println(min);
    }

    static void dfs(int start, int depth) {
        if (depth == m) {
            int result = 0;

            for (int i = 0; i < house.size(); i++) {
                int temp = Integer.MAX_VALUE;

                for (int j = 0; j < chicken.size(); j++) {
                    if (visited[j]) {
                        int dist = distance(house.get(i).x, house.get(i).y, chicken.get(j).x, chicken.get(j).y);
                        // 해당 집에서 가장 가까운 치킨거리
                        temp = Math.min(temp, dist);
                    }
                }

                // 각 집에서 가장 가까운 치킨거리 찾아 result에 누적해서 더해줌
                result += temp;
            }

            min = Math.min(min, result);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(start + 1, depth + 1);
                visited[i] = false;
            }
        }
    }

    static int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
