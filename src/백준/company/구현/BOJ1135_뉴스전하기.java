package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1135_뉴스전하기 {
    static int N;
    static List<ArrayList<Integer>> list, list2;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 직원 수

        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) { // i번 직원의 상사 정보
            int num = Integer.parseInt(st.nextToken());
            if (num == -1) {
                continue;
            }
            list.get(i).add(num);
        }

        visited = new boolean[N];
        System.out.println(dfs(0, 0, 0));
    }

    static int dfs(int prev, int now, int ans) {
        if (check()) {
            return ans;
        }

        visited[now] = true;


        ans++;
        for (int next : list.get(now)) {
            if (visited[next]) continue;
            visited[next] = true;
            dfs(prev, next, ans);
        }

        return ans;
    }

    static boolean check() {
        for (int i = 0; i < N; i++) {
            if (!visited[i]) return false;
        }
        return true;
    }
}

/*
10
-1 0 0 0 2 2 3 3 3 6

 */
