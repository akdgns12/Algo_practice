package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 치킨배달_볷 {
    // 1. 도시에 있는 치킨집 중에서 M개를 고르는 경우의 수
    // 2. 각 경우의 수에 따라 치킨거리를 구하고
    // 3. 그렇게 구한 치킨 거리의 합 중 최소값을 출력
    static int n, m;
    static int[][] map;
    static boolean[] visited;
    static ArrayList<Node> chicken = new ArrayList<>();
    static ArrayList<Node> house = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    // 0 : 빈칸, 1 : 집, 2 : 치킨집
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
                else if(map[i][j] == 2) chicken.add(new Node(i,j));
            }
        }

        visited = new boolean[chicken.size()];
        dfs(0, 0);
        System.out.println(answer);
    }

    static void dfs(int depth, int start){
        if(depth == m){ // m개의 치킨집을 모두 골랐다면
            // 각 집으로부터 치킨집까지의 최단 거리 구하기
            int result = 0;

            for (int i = 0; i < house.size(); i++) {
                int temp = Integer.MAX_VALUE; // i번째의 집에서 가장 가까운 치킨집 구해야하니까

                for (int j = 0; j < chicken.size(); j++) {
                    if(visited[j]){
                        int dist = distance(chicken.get(j).x, chicken.get(j).y, house.get(i).x, house.get(i).y);

                        temp = Math.min(temp, dist);
                    }
                }

                result += temp;
            }

            answer = Math.min(answer, result);
            return;
        }

        for (int i=start; i<chicken.size(); i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    static int distance(int x, int y, int x2, int y2) {
        return Math.abs(x - x2) + Math.abs(y - y2);
    }

    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
