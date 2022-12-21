package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2179_비슷한단어 {
    static int N;
    static String S, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 영단어 수

        TreeMap<String, Integer> map = new TreeMap<>();

        for(int i=0; i<N; i++){
            map.put(br.readLine(), i); // str, index
        }

        int ans = Integer.MIN_VALUE;
        Iterator<String> keys = map.keySet().iterator();
        while(keys.hasNext()){
            String preStr = keys.next();
            int preIdx = map.get(preStr);

            if(preStr == map.lastKey()) break; // 만약 마지막 키값이라면 종료

            String curStr = keys.next();
            int curIdx = map.get(curStr);

            if(preStr.equals(curStr)) continue; // 같은 단어 skip

            int cnt = 0;
            for(int j=0; j<Math.min(preStr.length(), curStr.length()); j++){
                if(preStr.charAt(j) == curStr.charAt(j)) cnt++;
                else break;
            }

            if(ans <= cnt){ // 가장 길이가 긴 접두사 길이로 갱신
                ans = cnt;

                if(curIdx > preIdx){ // 두 단어중 먼저 입력된 단어가 S
                    S = preStr;
                    T = curStr;
                }else if(curIdx < preIdx){
                    S = curStr;
                    T = preStr;
                }
            }
        }

        System.out.println(S);
        System.out.println(T);
    }
}
