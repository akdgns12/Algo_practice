package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110 {
    //공유기 설치 / 골드 4 / 이분탐색
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] house = new int[n];
        for (int i = 0; i < n; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);
        long min = 1;
        long max = house[n-1] - house[0];

        long answer = 1;
        while(min <= max){
            long mid = (min + max) / 2;
            long cnt = 1;
            int before = house[0];
            for (int i = 1; i < n; i++) {
                int dist = house[i] - before;
                if(dist >= mid){
                    before = house[i];
                    cnt++;
                }
            }

            if(cnt >= c){
                answer = Math.max(answer, mid);
                min = mid + 1;
            }else{
                max = mid - 1;
            }

        }

        System.out.println(answer);
    }
}
