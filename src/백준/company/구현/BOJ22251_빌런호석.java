package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ22251_빌런호석 {
    static int N, K, P, X;
    static int[][] display = {
            {1,1,1,0,1,1,1}, // 0
            {0,0,1,0,0,1,0}, // 1
            {1,0,1,1,1,0,1}, // 2
            {1,0,1,1,0,1,1}, // 3
            {0,1,1,1,0,1,0}, // 4
            {1,1,0,1,0,1,1}, // 5
            {1,1,0,1,1,1,1}, // 6
            {1,0,1,0,0,1,0}, // 7
            {1,1,1,1,1,1,1}, // 8
            {1,1,1,1,0,1,1}, // 9
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 층 제한
        K = Integer.parseInt(st.nextToken()); // diplay 칸
        P = Integer.parseInt(st.nextToken()); // 최대 변경 수
        X = Integer.parseInt(st.nextToken()); // 현재 층

        int[] number = changeTodisplay(X); // x의 각 자리 숫자 저장할 배열

        System.out.println(solve(number));
    }

    static int solve(int[] number){
        int ans = 0; // 가능한 경우의 수
        for(int i=1; i<=N; i++){ // 1~N의 층 중에 가능한 층이 뭔지 검사
            if(check(i, number)) ans++;
        }
        return ans;
    }

    static boolean check(int checkNum, int[] number){
        int[] target = changeTodisplay(checkNum);
        int diff = 0;
        for(int i=0; i<K; i++){
            if(checkNum == X) return false;
            for(int k=0; k<7; k++){
                if(display[target[i]][k] != display[number[i]][k]) {
                    diff++;
                    if(diff > P){
                        return false;
                    }
                }
           }
        }

        return true;
    }

    static int[] changeTodisplay(int x){
        int[] temp = new int[K];
        for(int i=K-1; i>=0; i--){
            temp[i] = x % 10;
            x /= 10;
        }

        return temp;
    }
}
