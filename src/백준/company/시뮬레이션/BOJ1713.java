package 백준.company.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1713 {
    // 후보 추천하기 / 실버 2 / 시뮬
    static int N;
    static int M;
    static HashMap<Integer, Integer> map = new HashMap<>(); // (학생번호, 추천횟수)
    static HashMap<Integer, Integer> time = new HashMap<>(); // (학생번호, 등록된 시간)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 사진틀 개수
        M = Integer.parseInt(br.readLine()); // 전체 학생의 총 추천 횟수

        // m이 등록된 시간을 의미
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            int num = Integer.parseInt(st.nextToken());

            // 이미 사진틀에 게재된 학생이라면 추천 횟수만 증가
            if(map.containsKey(num)){
                map.put(num, map.get(num)+1);
            }

            // 사진틀에 처음 등록되는 학생이면서
            else{
                // 사진틀에 빈공간이 없는 경우
                if(map.size() >= N){
                    int minRecommend = Integer.MAX_VALUE; // 추천 횟수가 가장 적은 후보
                    int minNum = Integer.MAX_VALUE; // 삭제할 학생번호

                    // 추천수가 가장 적은 학생 찾기(map전체탐색)
                    for(Integer key : map.keySet()){
                        if(minRecommend > map.get(key)){
                            minRecommend = map.get(key);
                            minNum = key;
                        }
                    }

                    // 가장 적은 추천수를 가지면서 게재된 시간이 오래된 학생을 찾는다(map 전체탐색)
                    for(Integer key : map.keySet()){
                        if(minRecommend == map.get(key)){
                            // time의 value값이 작을수록 일찍 게재된 학생
                            if(time.get(minNum) > time.get(key)){
                                minNum = key;
                            }
                        }
                    }

                    // 해당 학생 삭제
                    map.remove(minNum);
                    time.remove(minNum);
                }

                // 새로운 학생 등록
                map.put(num, 1);
                time.put(num, i); // 사진들에 게재된 시간도 저장
            }
        }

        // 최종적으로 사진들에 게재된 학생 번호를 증가하는 순서대로 출력하기 위해 연결리스트 사용
        List<Integer> result = new ArrayList<>(map.keySet());
        Collections.sort(result);
        for(Integer key : result){
            System.out.println(key + " ");
        }
    }
}
