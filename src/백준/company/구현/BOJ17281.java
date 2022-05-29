package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17281 {
    // 야구공 / 골드 4 / 구현
    // 1번타자는 4번타자로 이미 정해진 상태
    // 각 선수가 각 이닝에서 어떤 결과를 얻는지 미리 알고 있다. 가장 많은 득점을 하는 타순을 찾고, 그 때의 득점을 구해보자.
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int result = Integer.parseInt(st.nextToken());
            }
        }

        // 나머지 8명의 타자는 순열을 통해 모든 경우를 따져야함.(어떤 두 명의 선수가 각각 2루타를 치고 홈런을 치는 것과,
        // 홈런을 치고 2루타를 치는 것은 점수 차이가 나기때문에 조합이 아닌 순열을 사용해야 함/
        // 하나의 타순을 완성했다면, 이제 각 타자의 행동에 따라 야구를 진행하면 된다. 아웃 카운트가 3개일 때 종료된다는 점을
        // 기억하면서 N번의 이닝만큼 반복문을 수행.

    }
}



