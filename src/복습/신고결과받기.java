package 복습;

import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int n = id_list.length;
        int[] answer = new int[n];
        HashSet<String> rep = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();

        int idx = 0;
        for (String id : id_list){
            map.put(id, idx++);
        }

        int[] cnt = new int[n];
        for (String r : report){
            if(rep.contains(r)) continue;
            StringTokenizer st = new StringTokenizer(r);
            String reporter = st.nextToken();
            String singo = st.nextToken();
            cnt[map.get(singo)]++;
            rep.add(r);
        }

        for(String r: report){
            StringTokenizer st = new StringTokenizer(r);
            String reporter = st.nextToken();
            String singo = st.nextToken();
            if(cnt[map.get(singo)] >= k){
                answer[map.get(reporter)]++;
            }
        }

        return answer;
    }
}