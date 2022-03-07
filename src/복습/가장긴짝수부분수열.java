package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장긴짝수부분수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int lt = 0, rt = 0;
        int cnt = 0; // 홀수 개수 카운팅
        int answer = 0;

        for (rt = 0; rt < n; rt++) {
            if(arr[rt] % 2 == 1) cnt++;
            while(cnt > k){
                if(arr[lt] % 2 == 1) cnt--;
                lt++;
            }
            answer = Math.max(answer, (rt - lt + 1) - cnt);
        }

        System.out.println(answer);
    }
}
