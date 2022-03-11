package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1654 {
    public static void main(String[] args) throws Exception{
        // 랜선자르기 / 실버 3 / 이분탐색
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] wire = new int[k];

        for (int i = 0; i < k; i++) {
            wire[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(wire);

        System.out.println(binarySearch(wire, n));
    }

    static long binarySearch(int[] wire, int n){
       long lt = 1;
       long rt = wire[wire.length - 1];
       long mid = 0;
       long cnt = 0;
       long answer = 0;

        while(lt <= rt){
            mid = (lt + rt) / 2;
            cnt = cutWire(mid, wire);

            if(cnt >= n){
                answer = Math.max(answer, mid);
                lt = mid + 1;
            }else {
                rt = mid - 1;
            }
        }
        return answer;
    }

    static int cutWire(long legnth, int[] wire){
        int cnt = 0;

        for (int w : wire){
            cnt += w / legnth;
        }

        return cnt;
    }
}
