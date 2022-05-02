package 백준.company.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10819 {
    // 차이를 최대로 / 실버 2 / 완탐
    // 배열에 들어있는 수의 순서를 적절히 바꿔서 조건에 맞는 최댓값 리턴
    // N의 최대값이 8까지여서, 모든 조합을 구하자
    static int[] arr;
    static int[] selected; // 뽑은 조합 저장 배열
    static int n;
    static boolean[] visited;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        visited = new boolean[n];
        selected = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);
        System.out.println(answer);
    }

    // 백트래킹으로 모든 경우의 수 구하기
    static void dfs(int depth) {
        if(depth == n){
            answer = Math.max(getResult(), answer);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[depth] = arr[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }

    static int getResult() {
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += Math.abs(selected[i] - selected[i+1]);
        }
        return sum;
    }
}
