package 백준.company.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9663_2 {
    // N-Queen 다른 풀이
    static int n;
    static boolean[] isused1 = new boolean[40]; // column을 차지하고 있는지
    static boolean[] isused2 = new boolean[40]; // / 방향 대각선을 차지하고 있는지, (x+y)가 같으면 같은 대각선
    static boolean[] isused3 = new boolean[40]; // \ 방향 대각선을 차지하고 있는지, (x-y)가 같으면 같은 대각선
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dfs(0);
        System.out.println(cnt);
    }

    static void dfs(int cur){ // cur번째 row에 말을 배치할 예정
        if(cur == n){ // n개를 놓는데 성공했다면
            cnt++;
            return;
        }

        for(int i=0; i<n; i++){ // (cur, i)에 퀸을 놓을 예정
            if(isused1[i] || isused2[i+cur] || isused3[cur-i+n-1]) continue;
            isused1[i] = true;
            isused2[i+cur] = true;
            isused3[cur-i+n-1] = true;
            dfs(cur+1);
            isused1[i] = false;
            isused2[i+cur] = false;
            isused3[cur-i+n-1] = false;
        }
    }
}
