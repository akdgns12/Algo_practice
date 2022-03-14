package 쩜튜브;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연산자끼워넣기 {
    static int n;
    static int[] number;
    static int[] operator;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        number = new int[n];
        operator = new int[4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        dfs(number[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int num, int idx){
        if (idx == n) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }else{
            for (int i = 0; i < 4; i++) {
                // 연산자 개수가 1개 이상인 경우
                if(operator[i] > 0){
                    // 해당 연산자 개수를 1감소
                    operator[i]--;

                    switch (i) {
                        case 0:
                            dfs(num + number[idx], idx+1);
                            break;
                        case 1:
                            dfs(num - number[idx], idx + 1);
                            break;
                        case 2:
                            dfs(num * number[idx], idx + 1);
                            break;
                        case 3:
                            dfs(num / number[idx], idx + 1);
                            break;
                    }
                }
                // 재귀호출이 종료되면 다시 연산자 개수를 복구한다.
                operator[i]++;
            }
        }
    }

}
