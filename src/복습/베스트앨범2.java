package 복습;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class 베스트앨범2 {
    public int[] solution(String[] genres, int[] plays){
        int[] answer = {};
        HashMap<String, Integer> map = new HashMap<>();

        for(int i=0; i<genres.length; i++){
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }
        // 더많이 재생된 장르가 내림차순으로 정렬된 list
        ArrayList<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (o1, o2) -> map.get(o2) - map.get(o1));
        // 결과를 담을 result
        ArrayList<Integer> result = new ArrayList<>();
        for(String genre : list){
            int first = 0, second = 0;
            int max = 0;

            // 첫번째 값 찾기
            for(int i=0; i<genres.length; i++){
                if(genre.equals(genres[i])){
                    if(plays[i] > max){
                        max = plays[i];
                        first = i;
                    }
                }
            }

            max = 0;
            // 두번째 값 찾기
            for(int i=0; i<genres.length; i++){
                if(genre.equals(genres[i])){
                    if(i != first && plays[i] > max){
                        max = plays[i];
                        second = i;
                    }
                }
            }

            result.add(first);
            if(max != 0){
                result.add(second);
            }
        }

        answer = new int[result.size()];
        for(int i=0; i<result.size(); i++){
            answer[i] = result.get(i);
        }
        return answer;
    }
}
