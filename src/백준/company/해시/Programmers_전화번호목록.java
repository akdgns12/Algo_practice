package 백준.company.해시;

import java.util.HashMap;

public class Programmers_전화번호목록 {
    class Solution {
        public boolean solution(String[] phone_book) {
            boolean answer = true;
            HashMap<String, String> map = new HashMap<>();

            for(int i=0; i<phone_book.length; i++){
                map.put(phone_book[i], "prefix");
            }

            for(String phone : phone_book){
                for(int i=0; i<phone.length(); i++){
                    if(map.containsKey(phone.substring(0, i))){
                        return false;
                    }
                }
            }
            return answer;
        }
    }
}
