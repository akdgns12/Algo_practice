package 백준.company.이분탐색;

import 복습.텀프로젝트_dfs심화;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2343 {
    // 기타레슨 / 실버1 / 이진탐색
    /*
        M개의 블루레이에 N개의 강의를 녹화
        이때, 블루레이의 크기를 최소로..
        단 M개의 블루레이는 모두 같은 크기
     */
    static int n, m;
    static int[] lecture;
    static int lt, rt;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 강의 수
        m = Integer.parseInt(st.nextToken()); // 주어진 블루레이 수

        st = new StringTokenizer(br.readLine());
        lecture = new int[n];

        for (int i = 0; i < n; i++) {
            lecture[i] = Integer.parseInt(st.nextToken());
            rt += lecture[i];
            lt = Math.max(lt, lecture[i]);
        }

        while (lt <= rt) {
            int mid = (lt + rt) / 2;

            int sum = 0;
            int cnt = 0;

            for (int i = 0; i < n; i++) {
                if (sum + lecture[i] > mid) {
                    sum = 0;
                    cnt++;
                }
                sum += lecture[i];
            }

            if(sum != 0) cnt++;
            if(cnt <= m) rt = mid - 1;
            else lt = mid + 1;
        }

        System.out.println(lt);
    }
}

