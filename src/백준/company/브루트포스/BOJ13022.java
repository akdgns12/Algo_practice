package 백준.company.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ13022 {

    static char[] wolf = {'w', 'o', 'l', 'f'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        System.out.println(process(str));
    }

    static int process(String str) {
        char[] arr = new char[4];
        int[] cnt = new int[26];

        //첫 문자 넣어두기
        char prev = str.charAt(0);
        arr[0] = str.charAt(0);
        cnt[str.charAt(0) - 'a']++;
        int idx = 1;

        for (int i = 1; i < str.length(); i++) {
            char now = str.charAt(i);
            //이전 문자와 같다면 pass
            if (now == prev) {
                cnt[now - 'a']++;
                continue;
            }

            //다르다면
            //'w'로 다시 시작할 경우
            if (idx == 4) {
                //순서 및 반복횟수 확인
                if (!check(arr, cnt)) return 0;
                //cnt배열과 index초기화
                cnt = new int[26];
                idx = 0;
            }

            arr[idx++] = now;
            cnt[now - 'a']++;
            prev = now;
        }

        //길이가 4인경우도 확인 필요
        if (!check(arr, cnt)) return 0;
        return 1;
    }

    static boolean check(char[] arr, int[] cnt) {
        //반복횟수는 모두 같아야 하므로 기준으로 0번째 원소의 반복횟수를 flag에 저장
        int flag = cnt[arr[0] - 'a'];

        for (int i = 0; i < 4; i++) {
            //순서확인
            if (arr[i] != wolf[i]) return false;
            //반복횟수 확인(기준으로 정한 flag의 반복횟수와 다르다면 false)
            if (cnt[arr[i] - 'a'] != flag) return false;
        }
        return true;
    }
}
