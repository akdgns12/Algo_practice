package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2531_회전초밥 {
    static int N, d, k, c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 접시 수
        d = Integer.parseInt(st.nextToken()); // 초밥 종류 수
        k = Integer.parseInt(st.nextToken()); // window 크기
        c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] susi = new int[N];

        for(int i=0; i<N; i++){
            susi[i] = Integer.parseInt(br.readLine()) - 1;
        }

        /*
            보니까 이중 for문으로 슬라이딩 윈도우 진행하면 무조건
            시간초과 날듯
            윈도우 처음과 끝만 조절 ㄲ
         */

        int[] eatCnt = new int[d]; // 해당 종류의 초밥을 몇개 먹었는지 저장
        int cnt = 0;
        // 윈도우 크기인 k안에 카운팅 가능한 초기세팅
        for(int i=0; i<k; i++){
            if(eatCnt[susi[i]] == 0){ //
                cnt++;
            }
            eatCnt[susi[i]]++;
        }

        int max = 0;
        for(int i=0; i<N; i++){
            if(max <= cnt){
                if(eatCnt[c-1] == 0) max = cnt + 1;
                else max = cnt;
            }

            eatCnt[susi[i]]--; // i는 윈도우에서 사라지니까 하나 줄임
            if(eatCnt[susi[i]] == 0) cnt--; // 하나 줄여서 아예 없어지는거라면 cnt--

            int j = (i + k) % N;
            if(eatCnt[susi[j]] == 0) cnt++;
            eatCnt[susi[j]]++;
        }

        System.out.println(max);
    }
}
