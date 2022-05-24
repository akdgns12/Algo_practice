package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1244 {
    // 스위치 켜고 끄기 / 실버3 / 구현
    // 남학생은 스위치 번호가 자기가 받은 수의 배수이면, 그 스위치의 상태를 바꿈.
    // 여학생은 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아서, 그 구간에 속한 스위치의 상태를
    // 모두 바꾼다. 이때 구간에 속한 스위치 개수는 항상 홀수가 된다
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 스위치의 개수
        arr = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) { // 스위치 상태정보
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }

        // 학생이 받은 수에 맞게 조건에 따라 변화된 스위치 상태 출력
        // 단, 20개를 넘어가면 다음줄에 출력
        m = Integer.parseInt(br.readLine()); // 학생 수
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken()); // 성별
            int num = Integer.parseInt(st.nextToken()); // 학생이 받은 수

            if (gender == 1) {
                boy(num);
            } else {
                girl(num);
            }
        }

        for (int i=1; i<=n; i++){
            System.out.print(arr[i] + " ");
            if(i % 20 == 0) System.out.println();
        }
    }

    static void girl(int num) { // 받은수를 포함한 최대 길이의 좌우 대칭인 스위치 바꿈
        int left = num;
        int right = num;
        while(left >= 1 && right <= n){
            if(arr[left] != arr[right]) break; // 좌우가 다른값이면 종료
            left--;
            right++;
        }
        right -= 1;
        left += 1;

        if(left > right){
            left = num;
            right = num;
        }

        for (int i=left; i<=right; i++) arr[i] = (arr[i] == 1) ? 0 : 1;
    }

    static void boy(int num) { // 받은 수를 포함한 배수들의 스위치 상태 바꿈
        for (int i=num; i<=n; i+=num) arr[i] = (arr[i] == 1) ? 0 : 1;
    }
}
