package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 치킨배달_복습 {
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int[][] map;
    static int n, m;
    static ArrayList<Node> house = new ArrayList<>();
    static ArrayList<Node> chicken = new ArrayList<>();
    static boolean[] visited; // 중복된 치킨집을 뽑으면 안되니까
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for(int i=0; i<n; i++){
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

    static void dfs(int start, int depth) {
        if(depth == m){ // m개의 치킨집을 뽑았다면
            // 각 집에서 치킨집 까지의 거리 구하기
            int result = 0;

            for (int i = 0; i < house.size(); i++) {
                int temp = Integer.MAX_VALUE;

                for (int j = 0; j < chicken.size(); j++) {
                    if(visited[j]){
                        int dist = distance(house.get(i).x , house.get(i).y, chicken.get(j).x, chicken.get(j).y);

                        temp = Math.min(dist, temp);
                    }
                }
                result += temp;
            }

            answer = Math.min(result, answer);
            return;
        }

        for(int i=start; i<chicken.size(); i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(i+1, depth+1);
                visited[i] = false;
            }
        }
    }

    static int distance(int x, int y, int x2, int y2){
        return Math.abs(x-x2) + Math.abs(y-y2);
    }
}
