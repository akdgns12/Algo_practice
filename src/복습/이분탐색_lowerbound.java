package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 이분탐색_lowerbound {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> result = new ArrayList<>();
        result.add(arr[0]);

        for(int i=1; i<n; i++){
            int lower = lowerBound(result, 0, result.size(), arr[i]);

            if(result.size() <= lower) result.add(arr[i]);
            else result.set(lower, arr[i]);
        }

        System.out.println(result.size());
    }

    static int lowerBound(ArrayList<Integer> result, int lt, int rt, int key){
        int mid = 0;
        while(lt < rt){
            mid = (lt + rt) / 2;
            if(result.get(mid) < key){
                lt = mid + 1;
            }else{
                rt = mid;
            }
        }
        return mid;
    }
}
