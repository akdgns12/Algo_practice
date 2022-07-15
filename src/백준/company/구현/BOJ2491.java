package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2491 {
    // 수열 / 실버1 / 구현
    /*
        0~9까지의 숫자로 이루어진 N개의 숫자중
        연속해서 커지거나, 연속해서 작아지는 수열 중 가장 길이가 긴 것을 찾아내어 그 길이를 출력
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 1;
        int upCnt = 1;
        int downCnt = 1;

        int before = Integer.parseInt(st.nextToken());
        n--;

        while (n-- > 0) {
            int cur = Integer.parseInt(st.nextToken());

            if (before == cur) {
                upCnt++;
                downCnt++;
                continue;
            }

            // upCheck

            if (before > cur) {
                max = Math.max(max, upCnt);
                upCnt = 1;
            } else {
                upCnt++;
            }

            //downCheck
            if (before < cur) {
                max = Math.max(max, downCnt);
                downCnt = 1;
            } else {
                downCnt++;
            }

            before = cur;
        }

        max = Math.max(max, Math.max(upCnt, downCnt));

        System.out.println(max);
    }
}
