package 복습;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class 주차요금계산기 {
    public static int[] solution(int[] fees, String[] records){
        // 차량번호, 총 주차 시간
        TreeMap<String, Integer> result = new TreeMap<>();
        // 차량번호, 출차 시간
        HashMap<String, Integer> map = new HashMap<>();

        for (String x : records){
            String[] tmp = x.split(" ");
            if(tmp[2].equals("IN")){
                int min = calcTime(tmp[0]);
                if(!result.containsKey(tmp[1])) result.put(tmp[1], 0);
                map.put(tmp[1], min);
            }
            else if(tmp[2].equals("OUT")){
                int min = calcTime(tmp[0]);
                result.put(tmp[1], result.get(tmp[1]) + min - map.get(tmp[1]));
                map.remove(tmp[1]);
            }
        }

        // 아직 남아있다면 출차 기록이 없는 것 -> 23:59분에 나간걸로 처리
        if(!map.isEmpty()){
            for (String key : map.keySet()){
                result.put(key, result.get(key) + (23 * 60 + 59) - map.get(key));
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();

        // 시간에 따른 요금 계산
        for (String key : result.keySet()) {
            if (result.get(key) <= fees[0]) { // 기본시간 이하이면 기본요금 부과
                answer.add(fees[1]);
            } else {
                // 나머지 초과시간 구하고 요금계산
               int res = (int)Math.ceil((double)(result.get(key) - fees[0])/fees[2]);
               answer.add(fees[1] + res * fees[3]);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

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
