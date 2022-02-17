package _2022카카오블라인드;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class 주차요금계산기_3번 {
    public static ArrayList<Integer> solution(int[] fees, String[] records){
        // 차량번호,
        TreeMap<String, Integer> result = new TreeMap<>();
        // 차량번호, 시간
        HashMap<String, Integer> map = new HashMap<>();

        for (String x : records){
            String[] temp = x.split(" ");
            if(temp[2].equals("IN")){
                int min = calcTime(temp[0]);
                if(!result.containsKey(temp[1])) result.put(temp[1], 0);
                map.put(temp[1], min); // IN일땐 hashMap에 시간을 넣어준다
            }else if(temp[2].equals("OUT")){
                int min = calcTime(temp[0]);
                result.put(temp[1], result.get(temp[1]) + min - map.get(temp[1]));
                map.remove(temp[1]);
            }
        }

        if(!map.isEmpty()){ // 아직도 남아있다면 출차기록이 없는 것 = 23:59에 출차힌걸로 처리해준다
            for (String key : map.keySet()){
                result.put(key, result.get(key) + (23 * 60 + 59) - map.get(key));
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        // fees
        // 기본시간, 기본 요금, 단위 시간, 단위 요금
        for (String key : result.keySet()) { // result.get(key) = 해당 차량에 대한 주차시간
            if(result.get(key) <= fees[0]){ // 기본시간보다 적다면
                answer.add(fees[1]); // 기본 요금 부과
            }else{
                // 초과시간 res 구하고 올림처리
                int res = (int)Math.ceil(((double)(result.get(key) - fees[0]) / fees[2]);
                answer.add(fees[1] + res * fees[3]);
            }
        }

        return answer;
    }

    // 문자 시간 분으로 계산해주는 함수
    static int calcTime(String time){
        String[] split = time.split(":");
        int min = Integer.parseInt(split[0]) * 60;
        min += Integer.parseInt(split[1]);
        return min;
    }

    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        for(int x : solution(fees, records)) System.out.print(x + " ");
    }
}
