package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17615_볼모으기 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[] ball = br.readLine().toCharArray();

        int redCnt = 0;
        int blueCnt = 0;
        for(int i=0; i<N; i++){
            if(ball[i] == 'R') redCnt++;
            else blueCnt++;
        }

        int min = -1;
        min = Math.min(blueCnt, redCnt); // 가장 적은 볼 개수만큼 모두 이동하는 것, 이것보다 작은 경우가 있다면 갱신 go

        int cnt = 1;
        for(int i=0; i<N-1; i++){ // 왼쪽부터 세보는 경우
            if(ball[i] == ball[i+1]) cnt++;
            else break;
        }

        // 한 색의 전체 개수 - 연속된 개수 = 그 색깔을 움직여 조건만족할 수 있는 횟수
        if(ball[0] == 'R') min = Math.min(min, redCnt - cnt);
        else min = Math.min(min, blueCnt - cnt);

        cnt = 1;
        for(int i=N-1; i>0; i--){
            if(ball[i] == ball[i-1]) cnt++;
            else break;
        }

        if(ball[N-1] == 'R') min = Math.min(min, redCnt - cnt);
        else min = Math.min(min, blueCnt - cnt);

        System.out.println(min);
    }
}
