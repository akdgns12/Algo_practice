package 백준.company.해시;

import java.util.HashMap;

public class Programmers_위장 {
    class Solution {
        // 조합
        // 애초에 경우의 수 구하는 방식을 잘못 생각함
        // 옷의 개수 * 옷의 종류 - 1  이런식으로 생각했는데 이렇게 생각하면 안됨
        // (A종류 옷의 개수 + 1) * (B종류 옷의 개수 + 1) - 1(아무것도 안입는 케이스)
        // 이렇게 생각해야됨
        public int solution(String[][] clothes) {
            int answer = 1;
            HashMap<String, Integer> map = new HashMap<>();

            for(int i=0; i<clothes.length; i++){
                map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);
            }


            for(String key : map.keySet()){
                answer *= (map.get(key) + 1);
            }


            return answer-1;
        }
    }
}
