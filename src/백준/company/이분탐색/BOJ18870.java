package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ18870 {
    // 좌표압축 / 실버 2 / 이분 탐색
    /**
     * 결국, 문제를 잘 읽어보면
     * 주어진 입력 값들을 정렬했을 때 몇번째 순서인지 출력하는 문제
     * -> 입력 배열은 후에 다시 사용되어 새로운 배열에 복사한 다음정렬.
     * Map을 사용하여 0부터 좌표의 순서를 저장. 중복되는 좌표값들이 있기 때문에
     * Map을 사용.
     * 입력값들을 Map에서 찾으면 그 순서를 알 수 있다.
     */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] origin = new int[n]; // 원본 배열
        int[] sorted = new int[n]; // 정렬 할 배열
        HashMap<Integer, Integer> map = new HashMap<>(); // rank를 매길 HashMap

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            // 정렬 할 배열과 원본 배열에 값을 넣어준다.
            sorted[i] = origin[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬 할 배열에 대해 정렬을 수행한다.
        Arrays.sort(sorted);

        // 정렬 된 배열을 순회하면서 map에 넣어준다.
        int rank = 0;
        for (int v : sorted){
            /*
                이 때 만약 앞선 원소가 이미 순위가 매겨졌다면
                중복되면 안되므로, 원소가 중복되지 않을 때만
                map에 원소와 그에 대응되는 순위를 넣어준다.
             */
            if(!map.containsKey(v)){
                map.put(v, rank);
                rank++; // map에 넣고나면 다음 순위를 가리킬 수 있도록 1을 더해준다.
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int key : origin){
            int ranking = map.get(key); // 원본 배열 원소(key)에 대한 value(순위)를 갖고온다.
            sb.append(ranking).append(' ');
        }

        System.out.println(sb);
    }
}

