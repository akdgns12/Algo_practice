package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10815 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] card = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            card[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(card);

        int m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            System.out.print(binarySearch(card, n, num) + " ");
        }
    }

    static int binarySearch(int[] card, int n, int num){
        int lt = 0;
        int rt = n-1;

        while(lt <= rt){
            int mid = (lt + rt) / 2;

            if(card[mid] == num){
                return 1;
            }

            if(card[mid] < num){ // 중간값이 찾으려는 수보다 작으면, 그 이하로는 볼필요 x
                lt = mid + 1;
            }else{ // 중간값이 찾으려는 수보다 크면, 그 이상으로는 볼 필요 x
                rt = mid - 1;
            }
        }
        return 0;
    }
}
