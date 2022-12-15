package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1062_가르침 {
    static int N, K;
    static int ans = Integer.MIN_VALUE;
    static boolean[] alpha;
    static String[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new String[N];
        for(int i=0; i<N; i++){
            String str = br.readLine().replace("anta", "");
            str = str.replace("tica", "");
            arr[i] = str;
//            System.out.println(str);
        }

        // "anta" "tica" a,n,t,i,c 5개 default
        alpha = new boolean[26];
        alpha['a' - 'a'] = true;
        alpha['n' - 'a'] = true;
        alpha['t' - 'a'] = true;
        alpha['i' - 'a'] = true;
        alpha['c' - 'a'] = true;

        if(K < 5){ // 다 못읽는 경우
            System.out.println(0);
            System.exit(0);
        }else if(K == 26){ // 다 읽을 수 있는 경우
            System.out.println(N);
            System.exit(0);
        }

        // K - 5글자로 읽을 수 있는 단어 개수 최댓값
        dfs(0, 0);
        System.out.println(ans);
    }

    // 26개중 K-5개 선택해서 조건에 맞는 최댓값 찾기(중복 x, 순서 x) 조합
    static void dfs(int cnt, int start){
        if(cnt == K - 5){
            int matchWordCnt = 0;
            for(int i=0; i<N; i++){
                boolean isPossible = true;
                for(int j=0; j<arr[i].length(); j++){
                    if(!alpha[arr[i].charAt(j) - 'a']){ // 읽을 수 없는 단어면 다음단어로
                        isPossible = false;
                        break;
                    }
                }

                if(isPossible) matchWordCnt++;
            }

            ans = Math.max(ans, matchWordCnt);
            return;
        }

        for(int i=start; i<26; i++){
            if(alpha[i]) continue;
            alpha[i] = true;
            dfs(cnt + 1, i);
            alpha[i] = false;
        }
    }
}
