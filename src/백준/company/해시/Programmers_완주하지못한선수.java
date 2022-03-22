package 백준.company.해시;

import java.util.HashMap;

public class Programmers_완주하지못한선수 {
    class Solution{
        public String solution(String[] participant, String[] completion){
            String answer = "";
            HashMap<String, Integer> map = new HashMap<>();
            for (String player : participant){
                map.put(player, map.getOrDefault(player, 0) + 1);
            }

            for (String player : completion){
                map.put(player, map.get(player) - 1);
            }

            for (String key : map.keySet()){
                if(map.get(key) != 0){
                    answer = key;
                }
            }
            return answer;
        }
    }
}
