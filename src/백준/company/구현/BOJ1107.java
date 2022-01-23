package 백준.company.구현;

import java.util.Scanner;

public class BOJ1107 {
    // 리모컨 / 골드5 / 완전탐색
    /*
        단순히 우선 누를 수 있는 숫자 버튼을 사용하여 n에 가장 가깝게 숫자를 누르고
        그 뒤부터는 +나 -버튼을 사용하면 된다. -> 완전탐색
     */
    static boolean[] broken = new boolean[10];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 목표 채널
        int m = sc.nextInt(); // 고장난 버튼 개수

        for(int i=0; i<m; i++){
            int num = sc.nextInt();
            broken[num] = true;
        }

        int min = Math.abs(n - 100); // +,- 버튼으로만 누르는 경우, 시작채널은 100번
        for(int i=0; i<=1000000; i++){ // 0~백만까지 완탐
            // len : 숫자버튼 누르는 횟수
            int len = press(i); // i를 숫자버튼으로 누를 수 있는지, 누를 수 있다면 몇번 눌러야 하는지 구하기
            // len이 0보다 커야 숫자 버튼으로 i를 누를 수 있음을 의미
            if(len > 0){
                int count = Math.abs(n - i); // +,- 버튼을 누르는 횟수
                min = Math.min(min, len+count); // 기존의 min 값과 숫자버튼 + (+,-)버튼 누른 횟수 중 작은 값으로 갱신
            }
        }
        System.out.println(min);
    }

    // num 숫자를 숫자버튼으로 누를 수 있는지 확인하는 함수
    static int press(int num){
        if(num == 0){ // num이 0일 경우 예외처리
            if(broken[0])
                return 0;
            else
                return 1;
        }
        int count = 0;
        while(num > 0){
            if(broken[num%10]) // num중 고장난 숫자가 포함됐다면 누를 수 없으므로 return 0
                return 0;

            count += 1; // 누르는 숫자의 갯수 증가
            num /= 10; // 다음에 탐색할 숫자
        }

        return count; // 최종적으로는 num을 숫자버튼으로만 누를 수 있다면 num의 자릿수가 return 된다
    }
}
