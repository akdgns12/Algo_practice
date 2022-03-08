package 백준.company.자료구조_분리집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ21939 {
    static class Problem implements Comparable<Problem>{
        int num;
        int level;
        public Problem(int num, int level){
            this.num = num;
            this.level = level;
        }
        @Override
        public int compareTo(Problem o){
            if(this.level == o.level) return o.num - this.num;
            else return o.level - this.level;
        }
    }
    static int n, p, l;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        TreeSet<Problem> ts = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            ts.add(new Problem(p, l));
            map.put(p, l);
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if(command.equals("add")){
                int idx = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());
                ts.add(new Problem(idx, level));
                map.put(idx,level);
            }else if(command.equals("recommend")){
                int x = Integer.parseInt(st.nextToken());
                if(x == 1){
                    System.out.println(ts.first().num);
                }else{
                    System.out.println(ts.last().num);
                }
            }else{ // solved
                int idx = Integer.parseInt(st.nextToken());
                ts.remove(new Problem(idx, map.get(idx)));
                map.remove(idx);
            }
        }
    }
}
