package 백준.company.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/*
    최대의 합을 만들어내기 위해 양수, 음수, 0을 모두 따로 생각해야 한다.
    1. 양수일 때
    내림차순 정렬하여 절댓값이 큰 것끼리 곱해준다.
    홀수개라서 짝이 안맞을 경우 마지막 원소(=절댓값이 가장 작은 원소)는 그냥 혼자
    더해준다.
        단, 입력이 1인 경우엔 곱하는 것이 오히려 손해. 따라서 1의 개수는 따로 세서
        더해준다.

    2. 음수일 때
    오름차순 정렬하여 절댓값이 큰 것끼리 곱해준다.
    음수가 짝수개일 경우 모두 짝을 지어 음의 값을 상쇄시킬 수 있음
    만약 홀수개라면 마지막 남은 음수는 0과 묶어 그 값을 상쇄키셔야 한다.
    묶을 0이 없다면 어쩔 수 없이 음의 값을 더해줘야 함.

    3. 0일 때
    어차피 0은 묶어서 더하든 그냥 더하든 0. 총합에 영향을 미치지 않는다.
    마지막 하나 남은 음수의 값을 상쇄시켜줄 0이 있는지 없는지만 확인하면 되기 때문에
    boolean으로 존재 유무만 체크해주면 된다.
 */
public class BOJ1744 {
    // 수 묶기 / 골드 4 / 정렬
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();
        boolean hasZero = false;
        int countOne = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num > 0) {//양수일 때
                if (num == 1) { // 1은 곱하면 오히려 손해이므로 따로 개수를 세서 더함
                    countOne += 1;
                } else {
                    positive.add(num);
                }
            } else if (num < 0) { //음수일 때
                negative.add(num);
            } else { //0일 때
                hasZero = true;
            }
        }

        //양수, 음수 각자 절댓값의 크기로 내림차순
        Collections.sort(positive, Collections.reverseOrder());
        Collections.sort(negative);

        int ans = countOne;
        int size = positive.size();
        for (int i = 0; i < size; i += 2) { // positive
            if (i == size - 1) { // positive가 홀수개이면 절댓값이 가장 작은 것은 그냥 더해줌
                ans += positive.get(i);
            } else { // 양수가 짝수개일 때
                ans += positive.get(i) * positive.get(i + 1);
            }
        }

        size = negative.size();
        for (int i = 0; i < size; i+=2) { //negative
            if (i == size - 1) { //negative가 홀수개이면 절댓값이 가장 작은 음수를 0으로 상쇄시킬 수 있는지 확인
                if (!hasZero) { // 음수를 상쇄시킬 0이 없으면 그냥 더하기
                    ans += negative.get(i);
                }
            }else { //negative가 짝수개이면
                ans += negative.get(i) * negative.get(i+1);
            }
        }

        System.out.println(ans);
    }
}
