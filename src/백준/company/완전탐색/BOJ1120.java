package 백준.company.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1120 {
    // 문자열 / 실버4 / 완탐
    // A와 B의 길이가 같으면서, A와 B의 차이를 최소가 되도록 했을 때, 그 차이를 출력하시오.
    /*
        로직
        최대길이가 50이라 완탐 가능
        A의 앞 뒤에는 아무 알파벳을 추가할 수 있다. 즉, 현재 A의 길이와 상관없이 앞뒤로 오는 문자열은 B와 같은 문자를 둘 수 있다.
        결국 추가되는 문자는 신경쓰지 않고 현재 A와 B의 상태만 비교하면 된다.
        B의 문자열 중 시작점을 정하여 시작점부터 A의 길이만큼만 A와 비교하여 다른 문자의 개수를 찾는다. 이 과정을 B의 문자열에서 가능한 모든
        시작점에서 수행한다. 그렇게 찾은 A와 B의 차이들 중 가장 최솟값을 리턴
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String A = st.nextToken();
        String B = st.nextToken();
        int answer = 50;

        for (int i = 0; i < B.length() - A.length() + 1; i++) {
            int temp = 0;

            for (int j = i; j < A.length()+i; j++) {
                if (B.charAt(j) != A.charAt(j - i)) {
                    temp++;
                }
            }
            answer = Math.min(answer, temp);
        }

        System.out.println(answer);
    }
}
