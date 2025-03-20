package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15686_치킨배달 {
    static int N, M;
    static int[][] arr;
    static boolean[] pick;
    static ArrayList<Node> person;
    static ArrayList<Node> chicken;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        person = new ArrayList<>();
        chicken = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 1) person.add(new Node(i, j));
                if (arr[i][j] == 2) chicken.add(new Node(i, j));
            }
        }

        pick = new boolean[chicken.size()];
        // M개를 골랐을 모든 경우 중 도시의 치킨거리 최솟값
        solve(0, 0);
        System.out.print(ans);
    }

    // 1-집, 2-치킨집
    static void solve(int start, int cnt) {
        if (cnt == M) { // M개 선택하면
            int result = 0;

            // 선택된 치킨집과 집과의 거리계산
            for (int i = 0; i < person.size(); i++) {
                int temp = Integer.MAX_VALUE;

                for (int j = 0; j < chicken.size(); j++) {
                    if (pick[j]) { // 선택된 치킨집이면
                        int distance = Math.abs(person.get(i).x - chicken.get(j).x) + Math.abs(person.get(i).y - chicken.get(j).y);
                        temp = Math.min(temp, distance);
                    }
                }

                result += temp;
            }

            ans = Math.min(ans, result);
        }

        for (int i = start; i < chicken.size(); i++) {
            pick[i] = true;
            solve(i + 1, cnt + 1);
            pick[i] = false;
        }
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
