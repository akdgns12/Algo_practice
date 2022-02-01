package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 후보추천하기_구현{
    static int N, M;
    static HashMap<Integer, Integer> map = new HashMap<>(); // (학생번호, 추천횟수)
    static HashMap<Integer, Integer> time = new HashMap<>(); // (학생번호, 게재된 시간)

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 사진틀 개수
        M = Integer.parseInt(br.readLine()); // 추천 횟수

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            int num = Integer.parseInt(st.nextToken());

            // 이미 사진틀에 게제된 후보라면 추천 횟수만 증가
            if(map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }

            // 처음 사진틀에 게재되는 사진이라면
            else{
                // 사진틀에 빈 곳이 없다면
                if(map.size() >= N){
                    int minRecommend = Integer.MAX_VALUE;
                    int removeNum = Integer.MAX_VALUE;

                    // 추천 횟수가 가장 적은 후보를 찾는다
                    for(Integer key : map.keySet()){
                        if(minRecommend > map.get(key)){
                            minRecommend = map.get(key);
                            removeNum = key;
                        }
                    }

                    // 추천횟수가 가장 적으면서 게재된 시간이 가장 오래된 후보를 찾는다
                    for(Integer key : map.keySet()){
                        if(minRecommend == map.get(key)){
                            if(time.get(removeNum) > time.get(key)){
                                removeNum = key;
                            }
                        }
                    }
                    // 해당 학생 삭제
                    map.remove(removeNum);
                    time.remove(removeNum); //
                }

                // 새로운 후보 등록
                map.put(num, 1);
                time.put(num, i);
            }
        }

        ArrayList<Integer> result = new ArrayList<>(map.keySet());
        Collections.sort(result);
        for(Integer key : result){
            System.out.println(key + " ");
        }
    }
}
