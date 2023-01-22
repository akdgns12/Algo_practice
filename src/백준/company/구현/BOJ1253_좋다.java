package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1253_좋다 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
        // N개의 수 중 어떤 수가 다른 수 두개의 합으로 나타낼 수 있ㄷㅏ면 GOODk
        // N개의 수가 주어지면 그 중에서 좋은 수의 개수는 몇 개
        Arrays.sort(arr);
        int ans = 0;

        for(int i=0; i<N; i++){
            int lt = 0;
            int rt = N-1;

            while(lt < rt) {
                // 다른 두 수 처리
                if (i == lt) lt++;
                else if (i == rt) rt--;
                else {
                    if (arr[lt] + arr[rt] > arr[i]) {
                        rt--;
                    } else if (arr[lt] + arr[rt] < arr[i]) {
                        lt++;
                    } else { // 같을때
                        ans++;
//                    System.out.println(arr[lt] + arr[rt]);
                        break;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
