package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 늑대와올바른단어2 {
    static char[] wolf = {'w', 'o', 'l', 'f'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        System.out.println(process(str));
    }

    static int process(String str) {
        char[] arr = new char[4]; //각 단어 순서 저장할 배열
        int[] cnt = new int[26]; //각 단어 횟수 저장할 배열
        int prev = str.charAt(0);
        arr[0] = str.charAt(0);
        cnt[str.charAt(0) - 'a']++;
        int idx = 1;

        for (int i = 1; i < str.length(); i++) {
            char now = str.charAt(i);
            //이전과 같다면
            if (now == prev) {
                cnt[now - 'a']++;
                continue;
            }

            //다르다면
            //'w'이 다시 시작할 때
            if (idx == 4) {
                //단어 순서, 반복횟수 다르다면 0 출력
                if (!check(arr, cnt)) return 0;
                    //같다면 다시 사이클을 돌기 위해 단어횟수,idx 초기화
                else
                    cnt = new int[26];
                idx = 0;
            }

            arr[idx++] = now;
            cnt[now - 'a']++;
            prev = now;
        }

        //모든 단어를 돌고 종료할 때, 마지막단어가 f라면 체크 한 번 더 해줘야 한다
        if (!check(arr, cnt)) return 0;

        return 1;
    }

    //단어 순서, 반복 횟수 체크할 함수
    static boolean check(char[] arr, int[] cnt) {
        int flag = cnt[arr[0] - 'a']; //반복횟수 기준으로 삼을 첫번째 원소의 반복횟수
        for (int i = 0; i < 4; i++) {
            if(arr[i] != wolf[i]) return false;
            if(cnt[arr[i] - 'a'] != flag) return false;
        }
        return true;
    }
}
