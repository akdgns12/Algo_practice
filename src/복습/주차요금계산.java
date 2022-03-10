package 복습;

import java.util.*;

class 주차요금계산 {
    public int[] solution(int[] fees, String[] records) {
        TreeMap<String, Integer> result = new TreeMap<>();
        HashMap<String, Integer> map = new HashMap<>();

        for(String x : records){
            String[] str = x.split(" ");
            if(str[2].equals("IN")){
                int min = calcTime(str[0]);
                if(!result.containsKey(str[1])) result.put(str[1], 0);
                map.put(str[1], min);
            }
            else if(str[2].equals("OUT")){
                int min = calcTime(str[0]);
                result.put(str[1], result.get(str[1]) + min - map.get(str[1]));
                map.remove(str[1]);
            }
        }

        if(!map.isEmpty()){
            for(String key : map.keySet()){
                result.put(key, result.get(key) + (23*60 + 59) - map.get(key));
            }
        }

        ArrayList<Integer> priceList = new ArrayList<>();

        for(String key : result.keySet()){
            if(result.get(key) <= fees[0]){
                priceList.add(fees[1]);
            }else{
                int res = (int) Math.ceil((double)(result.get(key) - fees[0]) / fees[2]);
                priceList.add(fees[1] + res * fees[3]);
            }
        }

        int[] answer = new int[priceList.size()];

        for(int i=0; i<priceList.size(); i++){
            answer[i] = priceList.get(i);
        }

        return answer;
    }

    public static int calcTime(String time){
        String[] split = time.split(":");
        int min = Integer.parseInt(split[0]) * 60;
        min += Integer.parseInt(split[1]);
        return min;
    }
}