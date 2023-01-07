package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2531_회전초밥 {
    static int N, d, k, c;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
        k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
        c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] susi = new int[N];
        for(int i=0; i<N; i++){
            susi[i] = Integer.parseInt(br.readLine());
        }

        int[] select = new int[d+1];
        select[c]++; // 쿠폰 먹어놓고
        int cnt = 1;
        for(int i=0; i<k; i++){ // window만큼 먹을거라 초기세팅
            if(select[susi[i]] == 0) cnt++;
            select[susi[i]]++;
        }

        // start : i, end : (i+k)%N 만 따지며 탐색
        for(int i=0; i<N; i++){
            if(select[susi[(i+k)%N]] == 0) { // end에서 처음 보는 종류의 스시면 cnt++
                cnt++;
            }
            select[susi[(i+k)%N]]++; // 해당 종류 +1

            select[susi[i]]--; // window에서 벗어날 start부분 스시 줄여주고
            if(select[susi[i]] == 0) cnt--; // 줄여준 부분이 더이상 없는 스시면 cnt--
            ans = Math.max(cnt, ans);
        }

        System.out.println(ans);
    }
}
