package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 위상정렬및구현 {
    static int n, m;
    static String[] person;
    static HashMap<String, Integer> map = new HashMap<>();
    static int[] indegree;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        // 사람 정보
        person = new String[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            person[i] = st.nextToken();
        }
        Arrays.sort(person); // 문제에서 이름의 사전순대로 리턴해야하니 사전순 정렬

        // 사람들의 이름에 인덱스 부여
        for (int i = 0; i < n; i++) {
            map.put(person[i], i);
        }

        list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        indegree = new int[n];

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = map.get(st.nextToken()); // 자식
            int y = map.get(st.nextToken()); // 조상

            list[y].add(x); // 조상은 진입차수(indegree)가 0인게 보장되니까 이런식으로 가자
            indegree[x]++; // 진입차수 증가
        }

        topological();
    }

    static void topological(){
        Queue<Integer> q = new LinkedList<>(); // indegre 0인 노드들 넣을 큐
        ArrayList<Integer> root = new ArrayList<>(); // 각 가문의 조상 넣을 list

        for (int i = 0; i < n; i++) {
            if(indegree[i] == 0){
                q.offer(i);
                root.add(i);
            }
        }

        // 자식관계 나타낼 인접리스트
        ArrayList<ArrayList<Integer>> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }

        while(!q.isEmpty()){
            int now = q.poll();

            for (int next : list[now]) {
                indegree[next]--;
                if(indegree[next] == 0){ // now의 자식은 next
                    children.get(now).add(next);
                    q.offer(next);
                }
            }
        }
        // 각 가문의 수 출력
        System.out.println(root.size());
        // 각 가문의 조상 출력
        for (int idx : root) {
            System.out.print(person[idx] + " ");
        }
        System.out.println();
        // 사전순으로 사람의 이름, 자식의 수, 자식들의 이름
        for (int i = 0; i < n; i++) {
            System.out.print(person[i] + " " + children.get(i).size() + " ");
            // 자식들의 이름 사전순 출력
            Collections.sort(children.get(i));
            for (int child : children.get(i)){
                System.out.print(person[child] + " ");
            }
            System.out.println();
        }
    }
}

