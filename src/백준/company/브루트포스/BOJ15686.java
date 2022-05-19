package 백준.company.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15686 {
    // 치킨 배달 / 골드 5 / 구현
    static int n,m;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static boolean[] visited; // 중복되게 고르면 안되니까
    static ArrayList<Node> chicken = new ArrayList<>();
    static ArrayList<Node> house = new ArrayList<>();

    // 0 : 빈칸, 1 : 집, 2 : 치킨 집
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 도시에 있는 치킨집 중에서 M개를 고르는 경우의 수
        // 그렇게 M개를 고른 각 경우의 수마다 치킨거리를 구해서 그 중 가장 작은 치킨거리
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    house.add(new Node(i,j));
                }else if(map[i][j] == 2){
                    chicken.add(new Node(i,j));
                }
            }
        }

        visited = new boolean[chicken.size()];
        dfs(0,0);
        System.out.println(answer);
    }

    static void dfs(int depth, int start){
        if(depth == m){ // m개를 골랐으면
            // 각 집으로부터 치킨집까지의 최단거리 구하기
            int result = 0;

            for (int i = 0; i < house.size(); i++) {
                int temp = Integer.MAX_VALUE;

                for (int j = 0; j < chicken.size(); j++) {
                    if(visited[j]){
                        int dist = distance(chicken.get(j).x, chicken.get(j).y, house.get(i).x, house.get(i).y);

                        temp = Math.min(temp, dist); // 한 집의 치킨거리
                    }
                }

                result += temp;
            }

            answer = Math.min(result, answer);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            if(!visited[i]){
                visited[i] = true;
                dfs(depth+1, i+1);
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
