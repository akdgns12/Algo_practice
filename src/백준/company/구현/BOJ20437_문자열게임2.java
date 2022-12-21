package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ20437_문자열게임2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 문자열 게임 수

        for(int tc=0; tc<T; tc++){
            int[] alpha = new int[26];
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            if(K == 1) { // 이 처리 놓쳐서 틀린듯?
                sb.append(1 + " " + 1).append("\n");
                continue;
            }

            for(int i=0; i<W.length(); i++){
                alpha[W.charAt(i) - 'a']++;
            }

            // 어떤 문자를 정확히 K개 포함해야하는 가장 짧은 연속 문자열의 길이
            int minLen = Integer.MAX_VALUE; // 가장 짧은 연속 문자열의 길이
            int maxLen = Integer.MIN_VALUE;
            for(int i=0; i<W.length(); i++){
                if(alpha[W.charAt(i) - 'a'] < K) continue; // 해당 문자가 K개 미만이면 skip

                int cnt = 1; // 같은 문자 개수
                for(int j=i+1; j<W.length(); j++){
                    if(W.charAt(j) == W.charAt(i)) cnt++; // 같다면
                    if(cnt == K){
                        minLen = Math.min(minLen, j - i + 1);
                        maxLen = Math.max(maxLen, j - i + 1);
                        break;
                    }
                }
            }

            if(minLen == Integer.MAX_VALUE || maxLen == Integer.MIN_VALUE) sb.append(-1 + "\n");
            else sb.append(minLen + " " + maxLen).append("\n");
        }
        System.out.println(sb);
    }
}
