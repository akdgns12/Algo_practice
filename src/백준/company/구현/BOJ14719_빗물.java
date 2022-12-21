package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14719_빗물 { // 아이디어!!!!
    static int H, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        int ans = 0;
        int[] arr = new int[W];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<W; i++){
            arr[i] = Integer.parseInt(st.nextToken()); // 블록정보
        }

        // i의 양옆 최대치 중 작은값이 고이는 물의양
        for(int i=1; i<W-1; i++){

            int rightMax = 0;
            for(int j=i+1; j<=W-1; j++){
                rightMax = Math.max(arr[j], rightMax);
            }

            int leftMax = 0;
            for(int j=0; j<i; j++){
                leftMax = Math.max(arr[j], leftMax);
            }

            int min = Math.min(rightMax, leftMax);
            // min만큼 물이 고일 수 있는데 i자리 블록이 min이상이면 물이 넘침
            if(min <= arr[i]) continue;

            ans += min - arr[i];
        }

        System.out.println(ans);
    }
}
