package 쩜튜브;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 치킨배달 {
    // 도시의 치킨거리가 가장 작도록 구하라
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int n, m;
    static int[][] map;
    static ArrayList<Node> house = new ArrayList<>(); // 집 위치
    static ArrayList<Node> chicken = new ArrayList<>(); // 치킨 위치
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()); // 치킨집을 고르는 수
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) house.add(new Node(i,j));
                else if(map[i][j] == 2) chicken.add(new Node(i,j));
            }
        }

        visited = new boolean[chicken.size()];

        dfs(0,0);
        System.out.println(min);
    }

    static void dfs(int start, int depth){
        // 입력받은 m개의 치킨집이 선택되었다면
        if(depth == m){
            int result = 0;

            // 각 집으로부터 선택된 치킨집까지의 최단거리 구하기
            for (int i = 0; i < house.size(); i++) {
                int temp = Integer.MAX_VALUE;

                for (int j = 0; j < chicken.size(); j++) {
                    if (visited[j]) {
                        int dist = distance(house.get(i).x, house.get(i).y, chicken.get(j).x, chicken.get(j).y);

                        temp = Math.min(temp, dist);
                    }
                }

                result += temp;
            }

            // 가장 작은 도시의 치킨거리
            min = Math.min(min, result);
            return;
        }

        /*
            백트래킹
            총 chicken.size()의 치킨집 중에서 m개를 선택하는 과정
         */
        for (int i = start; i < chicken.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i + 1, depth + 1);
                visited[i] = false;
            }
        }
    }

    static int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
