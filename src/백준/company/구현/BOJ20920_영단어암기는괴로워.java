package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class BOJ20920_영단어암기는괴로워 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<N; i++){
            String str = br.readLine();

            if(str.length() < M) continue; // M보다 작다면 skip
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        List<String> words = new ArrayList<>(map.keySet());
        // 자주나온 단어 순 정렬
        words.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(map.get(o1) == map.get(o2)){ // 단어 횟수 같다면
                    if(o1.length() == o2.length()) { // 길이도 같다면
                        return o1.compareTo(o2); // 사전순 정렬
                    }
                        return o2.length() - o1.length(); // 길이가 긴 순서로
                }
                return map.get(o2) - map.get(o1); // 단어 많이나온 순
            }
        });

        // 단어 길이 길수록 정렬
        for(String key : words) sb.append(key + "\n");
        System.out.println(sb);
    }
}
