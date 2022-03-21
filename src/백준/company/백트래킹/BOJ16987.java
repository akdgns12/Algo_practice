package 백준.company.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16987 {
    // 계란으로 바위치기 / 실버 1 / 백트래킹
    static int n;
    static int[] s;
    static int[] w;
    static int cnt = 0;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        s = new int[n];
        w = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            s[i] = Integer.parseInt(st.nextToken()); // 내구도
            w[i] = Integer.parseInt(st.nextToken()); // 무게
        }

        dfs(0);
        System.out.println(max);
    }

    static void dfs(int depth){ // depth번째 계란으로 깰다른 걸 깰 차례
        if(depth == n){
            max = Math.max(max, cnt);
            return;
        }

        if(s[depth] <= 0 || cnt == n-1){
            dfs(depth+1);
            return;
        }

        for(int i=0; i<n; i++){
            if(i == depth || s[i] <= 0) continue; // 자기자신이거나, 내구도가 0 이하라면 skip
            // 계란 부딪치고 각 계란의 무게만큼 서로의 내구도 감소
            s[depth] -= w[i];
            s[i] -= w[depth];
            if(s[depth] <= 0) cnt++; // 깨지면 카운트 증가
            if(s[i] <= 0) cnt++;
            dfs(depth+1);
            if(s[depth] <= 0) cnt--;
            if(s[i] <= 0) cnt--;
            s[depth] += w[i];
            s[i] += w[depth];
        }
    }
}
