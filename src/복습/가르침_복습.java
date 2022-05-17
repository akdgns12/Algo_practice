package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가르침_복습 {
    static int n, k;
    static String[] words;
    static int answer = Integer.MIN_VALUE;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        words = new String[n];
        visited = new boolean[26];

        if(k < 5){
            System.out.println(0);
            return;
        }else if(k == 26){
            System.out.println(n);
            return;
        }

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            words[i] = str.substring(4, str.length() - 4);
        }


        visited['a' - 'a'] = true;
        visited['c' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['n' - 'a'] = true;

        dfs(0, 0);

        System.out.println(answer);
    }

    static void dfs(int idx, int cnt) {
        if (cnt == k - 5) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                boolean canRead = true;
                for (int j = 0; j < words[i].length(); j++) {
                    if(!visited[words[i].charAt(j) - 'a']){
                        canRead = false;
                        break;
                    }
                }
                if(canRead) count++;
            }
            answer = Math.max(answer, count);
        }

        for (int i = idx; i < 26; i++) {
            if(!visited[i]){
                visited[i] = true;
                dfs(i+1, cnt+1);
                visited[i] = false;
            }
        }
    }
}
