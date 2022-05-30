package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ2792 {
    // 보석 상자 / 실버 2 / 이분탐색
    /*
        모든 보석을 N명의 학생에게 나눠주려함, 이때 보석을 못받는 학생이 있어도 됨.
        학생은 항상 같은 색상의 보석만 가짐
        질투심 = 가장 많은 보석을 가져간 학생이 가지고 있는 보석의 개수.
        이런 질투심을 최소가 되게 보석을 나눠주려 함

        로직
        - 주어지는 보석개수의 차이가 아니라 개수 자체에 관한 문제라는 것 -> 그냥 보석을 N명에게 모두 나누어 줄 수 있는 최소 개수를 가지면 된다.
        - 아예 보석을 못받는 학생이 있어도 괜찮은 것 -> 학생수보다 주는 보석 방법의 개수가 작거나 같으면 싹 다 답이 될 수 있음
        1. lt는 1부터 시작, 보석을 나누어 주는 것이니까
        2. rt는 최대 보석의 개수부터, 그 이상 줘도 줄 수 있는 개수는 바뀌지 않으므로
        3. lt,rt 값이 정해졌으므로 그 가운데 mid에 대해 해당 크기에 맞춰 보석을 하나하나 나눠준다.
        4. 현재 나눠준 보석이 학생수보다 많으면 -> 모두 준게 아니므로 이방법은 못쓴다. 더 많이 나눠줄 것
        5. 현재 나눠준 보석이 학생수보다 적거나 같으면 -> 못받는 학생이 있어도 괜찮다. 일단 저장하고 더 적게 나눠줄 수 있는 방법을 찾을 것
        - 모든 학생에게 나눠줘야 하는 것이 아니다!
        6. 일단 남는 보석이 없다면 저장하고, 한번에 그보다 적은 보석을 나눠주는 방법을 찾아가면 된다.
     */

    // 결론은 질투심의 최솟값! N명의 사람에게 보석을 나눠줄때 질투심 최소가 되게
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 사람 수
        int M = Integer.parseInt(st.nextToken()); // 색상 수

        int num[] = new int[M];

        int left = 1; // 나눠줄 수 있는 최소값
        int right = Integer.MIN_VALUE; // 나눠줄 수 있는 최대값

        for(int i=0; i<M; i++){
            num[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, num[i]);
        }

        int ans = 0; // 질투심의 최솟값
        while(left <= right){
            int mid = (left+right)/2;
            int sum = 0;

            for(int i=0; i<M; i++){
                if(num[i] % mid == 0){
                    sum += num[i]/mid;
                }else{
                    sum += num[i]/mid + 1;
                }
            }

            if(sum > N){
                left = mid+1;
            }else{
                right = mid-1;
                ans = mid;
            }
        }
        System.out.println(ans);
    }
}
