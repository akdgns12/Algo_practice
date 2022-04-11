package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1316 {
    // 그룹단어체커 / 실버5 / 구현
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 그룹단어 체크용 배열 선언
        boolean[] alpha = new boolean[26];

        int cnt = n;

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            Arrays.fill(alpha, false);
            alpha[str.charAt(0) - 'a'] = true;
            for (int j = 1; j < str.length(); j++) {
                if(str.charAt(j) != str.charAt(j-1)){
                    if(alpha[str.charAt(j) - 'a']){
                        cnt--;
                        break;
                    }else{
                        alpha[str.charAt(j) - 'a'] = true;
                    }
                }
            }
        }

        System.out.println(cnt);
    }
}
