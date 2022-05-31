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
            rt += lecture[i]; // 디스크 최대크기는 모든 강의들을 합친 수
            lt = Math.max(lt, lecture[i]); // 최소 크기는 강의들 중 최대 크기
        }

        while (lt <= rt) {
            int mid = (lt + rt) / 2;

            int sum = 0;
            int cnt = 0;

            for (int i = 0; i < n; i++) {
                if (sum + lecture[i] > mid){ // 강의들을 더한 값이 mid를 넘어가면
                    sum = 0; // sum 초기화해주고
                    cnt++; // 디스크 수 증가
                }
                sum += lecture[i]; // 넘어간 값은 다시 초기화된 sum에 더해줌
            }

            if(sum != 0) cnt++;
            if(cnt <= m) rt = mid - 1; // cnt가 m개의 블루레이 수와 작거나 같다면, 강의를 담기 위해 디스크를 나눈 횟수가 늘어나도록 rt = mid - 1 (디스크의 크기가 커서 강의를 너무 큰 간격으로 담은 상태이기 때문에)
            else lt = mid + 1;
        }

        System.out.println(lt);
    }
}

