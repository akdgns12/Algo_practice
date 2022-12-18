package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2138_전구와스위치 { // 넘 어렵다.. 아이디어 참고함..
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String cur = br.readLine(); // 현재 전구의 상태
        String target = br.readLine(); // 만들고자 하는 상태

        // 최소 몇번으로 만들고자 하는 상태로 만들 수 있는지
        // 첫번째 전구를 초기상태, 스위치 바꾼 상태(2가지 상태로 비교 시작해나가기)
        int[] turnOn = new int[N];
        int[] turnOff = new int[N];
        int[] goal = new int[N];
        for(int i=0; i<N; i++){
            turnOn[i] = cur.charAt(i) - '0';
            turnOff[i] = cur.charAt(i) - '0';
            goal[i] = target.charAt(i) - '0';
        }

        // 0번째 스위치 상태 바꾸기(0,1번째 전구만 영향받음)
        turnOff[0] = 1 - turnOff[0];
        turnOff[1] = 1 - turnOff[1];

        int cnt1 = 1, cnt2 = 0;
        for(int i=1; i<N; i++){
            if(turnOff[i-1] != goal[i-1]){
                turnOff[i-1] = 1 - turnOff[i-1];
                turnOff[i] = 1 - turnOff[i];
                cnt1++;
                if(i != N -1){
                    turnOff[i+1] = 1 - turnOff[i+1];
                }
            }

            if(turnOn[i-1] != goal[i-1]){
                turnOn[i-1] = 1 - turnOn[i-1];
                turnOn[i] = 1 - turnOn[i];
                cnt2++;
                if(i != N - 1){
                    turnOn[i+1] = 1 - turnOn[i+1];
                }
            }
        }

        if(!isCorrect(turnOff, goal)) cnt1 = Integer.MAX_VALUE;
        if(!isCorrect(turnOn, goal)) cnt2 = Integer.MAX_VALUE;

        if(cnt1 == Integer.MAX_VALUE && cnt2 == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(Math.min(cnt1, cnt2));
    }

    static boolean isCorrect(int[] arr, int[] target){
        for(int i=0; i<N; i++){
            if(arr[i] != target[i]) return false;
        }
        return true;
    }
}
