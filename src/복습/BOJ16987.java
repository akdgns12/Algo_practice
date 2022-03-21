package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16987 {
    static class Egg{
        int s, w;
        public Egg(int s, int w){
            this.s = s;
            this.w = w;
        }
    }
    static int n;
    static int max = Integer.MIN_VALUE;
    static Egg[] egg;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        egg = new Egg[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            egg[i] = new Egg(s, w);
        }

        dfs(0,0);
        System.out.println(max);
    }

    static void dfs(int depth, int cnt){
        if(depth == n){
            max = Math.max(max, cnt);
            return;
        }

        Egg e = egg[depth];
        if(e.s <= 0 || cnt == n-1){ // 손에 쥔 계란이 깨져있을 때 or 다른 모든 계란이 깨져을 때
            dfs(depth+1, cnt);
            return;
        }

        int nCnt = cnt;
        for (int i = 0; i < n; i++) {
            // 손에 쥔 계란이거나 다른 계란들이 모두 깨졌을때
            if(i == depth || egg[i].s <= 0) continue;

            e.s -= egg[i].w; // 손에 쥔 계란 내구도 감소
            egg[i].s -= e.w; // 상대 계란 내구도 감소
            if(e.s <= 0) cnt++;
            if(egg[i].s <= 0) cnt++;

            dfs(depth+1, cnt);

            // 원상복구
            e.s += egg[i].w;
            egg[i].s += e.w;
            cnt = nCnt;
        }
    }
}
