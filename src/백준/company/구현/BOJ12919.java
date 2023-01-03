package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12919 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        System.out.println(dfs(S, T));
    }

    static int dfs(String origin, String target){
        if(origin.length() == target.length()){
            if(origin.equals(target)) return 1;
            else return 0;
        }

        /*
            T를 S로 만드는 쪽으로 생각
            1. 맨 앞이 B면 B를빼고 뒤집는다
            2. 맨뒤가 A면 A를 뺀다
         */
        int ans = 0;
        if(target.charAt(0) == 'B'){ // 1.
            String temp = target.substring(1);
            String next = "";
            for(int i=temp.length() - 1; i>=0; i--){
                next += temp.charAt(i);
            }

            ans += dfs(origin, next);
        }

        if(target.charAt(target.length() - 1) == 'A'){ // 2.
            ans += dfs(origin, target.substring(0, target.length() - 1));
        }

        return ans > 0 ? 1 : 0;
    }
}
