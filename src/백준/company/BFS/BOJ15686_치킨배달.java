package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15686_치킨배달 {
    static int N, M;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int ans = Integer.MAX_VALUE;
    static ArrayList<Node> person;
    static ArrayList<Node> chicken;
    static boolean[] pick;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        person = new ArrayList<>();
        chicken = new ArrayList<>();

        // 1: 집, 2: 치킨집
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    person.add(new Node(i, j));
                }else if(map[i][j] == 2){
                    chicken.add(new Node(i, j));
                }
            }
        }

        pick = new boolean[chicken.size()];
        solve(0, 0);
        System.out.println(ans);
    }

    // 치킨집이면 골라서 계산하고, 가장 최솟값
    static void solve(int start, int cnt){
        if(cnt == M){ // M번 선택하면
            int result = 0;

            for(int i=0; i<person.size(); i++){
                int temp = Integer.MAX_VALUE;

                // 집과 치킨집의 모든 거리 비교
                for(int j=0; j<chicken.size(); j++){
                    if(pick[j]){ // 선택한 치킨집
                        int dist = Math.abs(person.get(i).x - chicken.get(j).x) + Math.abs(person.get(i).y - chicken.get(j).y);

                        temp = Math.min(dist, temp); // 선택한 치킨집에서부터의 거리
                    }
                }

                result += temp; // 선택한 치킨집들과의 기준 거리합
            }

            ans = Math.min(result, ans);
        }

        for(int i=start; i<chicken.size(); i++){
            pick[i] = true;
            solve(i + 1, cnt + 1);
            pick[i] = false;
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