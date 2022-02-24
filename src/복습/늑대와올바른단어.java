package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 늑대와올바른단어 {
    static char[] wolf = {'w', 'o', 'l', 'f'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        System.out.println(process(str));
    }

    static int process(String str){
        char[] arr = new char[4];
        int[] cnt = new int[26];

        //첫번쨰 원소 미리 넣어준다
        char prev = str.charAt(0);
        arr[0] = str.charAt(0);
        cnt[str.charAt(0) - 'a']++;
        int idx = 1;

        for (int i = 1; i < str.length(); i++) {
            //현재문자
            char now = str.charAt(i);
            if(now == prev){ //이전문자와 같을 경우 갯수만 세어주고 pass
                cnt[now - 'a']++;
                continue;
            }

            // 다르다면
            // idx == 4가 되는 경우는 'w'가 다시 나오는 경우
            if(idx == 4){
                //순서와 반복횟수 체크하고 틀리다면 0
                if(!check(arr, cnt)) return 0;
                //true라면 다시 검사를 위해 반복cnt배열과 index초기화
                cnt = new int[26];
                idx = 0;
            }

            //이전문자와 다르고 idx == 4가 아닌 경우 아직 'w'가 다시 나타나기 전
            arr[idx++] = now;
            cnt[now - 'a']++;
            prev = now;

        }

        //f까지 돌고 for문을 빠져나오는 경우도 있으므로 -> 빠져나오고 나서 idx 4가 된다
        //마지막으로 한번 더 검사해줘야 한다
        if(!check(arr,cnt)) return 0;
        return 1;
    }

    static boolean check(char[] arr, int[] cnt) {
        int flag = cnt[arr[0] - 'a'];

        for (int i = 0; i < 4; i++) {
            if(arr[i] != wolf[i]) return false;
            if(cnt[arr[i] - 'a'] != flag) return false;
        }
        return true;
    }
}
