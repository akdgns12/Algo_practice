package 백준.company.해시;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Programmers_베스트앨범 {
        static class Music{
            String gern;
            int play;
            int idx;
            Music(String gern, int play, int idx){
                this.gern = gern;
                this.play = play;
                this.idx = idx;
            }
        }
        public int[] solution(String[] genres, int[] plays) {

            HashMap<String, Integer> map = new HashMap<>();

            for(int i=0; i<genres.length; i++){
                map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]); // 장르별 총 재생횟수
            }

            // 1. 장르순서 정하기(map의 value값인 총 재생횟수 높은순으로)
            ArrayList<String> genres_order = new ArrayList<>();
            while(map.size() != 0){
                int max = -1;
                String max_key = "";
                for(String key : map.keySet()){
                    int tmp_cnt = map.get(key);
                    if(tmp_cnt > max){
                        max = tmp_cnt;
                        max_key = key;
                    }
                }
                genres_order.add(max_key);
                map.remove(max_key);
            }

            // 2. 장르내 노래 선정
            ArrayList<Music> result = new ArrayList<>(); // 결과를 담을 list
            for(String gern : genres_order){
                ArrayList<Music> list = new ArrayList<>();
                for(int i=0; i<genres.length; i++){
                    if(genres[i].equals(gern)){
                        list.add(new Music(gern, plays[i], i));
                    }
                }
                Collections.sort(list, (o1, o2) -> o2.play - o1.play);
                result.add(list.get(0));
                if(list.size() != 1){
                    result.add(list.get(1));
                }
            }

            int[] answer = new int[result.size()];
            for(int i=0; i<result.size(); i++){
                answer[i] = result.get(i).idx;
            }


            return answer;
        }
    }
