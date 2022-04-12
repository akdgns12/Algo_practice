package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1475 {
    // 방 번호 / 실버 5 / 구현
    // 생각
    /*
        6과 9는 서로 뒤집어서 사용할 수 있기 때문에 입력할 때부터 9를 6으로 대체. 따라서 9가 입력되도 6이고, 6은
        한 세트로 2번까지 사용할 수 있음
        0~9까지인데, 9는 6이랑 같으므로 크기가 9인 배열을 만들어 번호마다 카운트를 해준다.
        여기서 6번 카드만 2번까지 사용할 수 있으므로 6번 카드가 홀수번 등장하면 나누기 2한 값에 1을 추가해
        세트를 올바르게 추가할 수 있도록 한다.
        그리고 마지막에 정렬을 하는데, 그 이유는 가장 큰 값이 세트의 개수이기 때문이다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] n = br.readLine().replaceAll("9","6").split("");

        int[] set = new int[9];
        for (String s : n){
            set[Integer.parseInt(s)]++;
        }

        if(set[6] % 2 != 0) set[6] = set[6] / 2 + 1;
        else set[6] /= 2;

        Arrays.sort(set);
        System.out.println(set[8]);
    }
}
