package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2467_용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int lt = 0;
        int rt = N-1;
        int min = Integer.MAX_VALUE;
        int ans1 = 0;
        int ans2 = 0;

        while(lt < rt){
            int sum = Math.abs(arr[lt] + arr[rt]);

            if(sum < min){
                min = sum;
                ans1 = lt;
                ans2 = rt;
            }
            if(sum == 0) break;

            if(arr[lt] + arr[rt] > 0){ // ....위에서 절댓값 처리한걸로 비교하니까 틀리지 영훈아 와..
                rt--;
            }else{
                lt++;
            }
        }

        System.out.println(arr[ans1] + " " + arr[ans2]);
    }
}
/*
8
-1000000 -99 99 100 101 102 103 104 105
 */