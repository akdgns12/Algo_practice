package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1920_수찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        // 이분탐색인가? 범위 엄청크네
        Arrays.sort(arr);
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            int num = Integer.parseInt(st.nextToken()); // 이 수들이 arr안에 존재하는지
            boolean isIn = false;

            int lt = 0;
            int rt = N-1;

            while(lt <= rt){
                int mid = (lt + rt) / 2;

                if(arr[mid] > num){
                    rt = mid - 1;
                }else if(arr[mid] < num){
                    lt = mid + 1;
                }else{ // 같다면 존재
                    isIn = true;
                    break;
                }
            }

            if(isIn) System.out.println(1);
            else System.out.println(0);
        }
    }
}
