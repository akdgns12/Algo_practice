package 백준.company.위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ21276 {
    // 계보 복원가 호석 / 골드 3 / 그래프, 위상정렬(방향성이 있으며 사이클이 없는 그래프), 자료구조
    static int n, m;
    static String[] human;
    static HashMap<String, Integer> map;
    static ArrayList<Integer>[] list; // 그래프의 관계를 표현하기 위한 2차원 인접리스트
    static int[] indegree; // 해당 노드를 가리키는 간선 갯수를 담기 위한 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 총 사람 수
        indegree = new int[n];
        human = new String[n];

        // 사람들의 이름을 입력받아 정렬
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            human[i] = st.nextToken();
        }
        Arrays.sort(human);

        // 사람들의 이름에 인덱스 부여
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(human[i], i);
        }

        // 계보 정보를 저장할 list 초기화
        list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        // 가문 정보 입력받기
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int X = map.get(st.nextToken()); // 자손
            int Y = map.get(st.nextToken()); // 조상

            list[Y].add(X); // 조상이 자손을 가르킴, 이렇게 넣어야 가문의 개수 파악가능 root는 무조건 indegree 0인 차수인게 보장되니까
            indegree[X]++; // 자손의 차수 세기
        }

        // 위상정렬
        topological();
    }

    static void topological(){
        Queue<Integer> q = new LinkedList<>();    // indegree 값이 0이 된 노드들을 담기 위한 큐
        ArrayList<Integer> root = new ArrayList<>(); // 시조들의 이름을 저장할 리스트

        // 큐에 indegree가 0인 노드 담기(root, 즉 조상노드)
        for (int i = 0; i < n; i++) {
            if(indegree[i] == 0){ // 시조이면 큐와 리스트에 인덱스 저장(시조일 경우 indegree가 0이니까, 시조에서 출발했으므로)
                q.offer(i);
                root.add(i);
            }
        }

        ArrayList<ArrayList<Integer>> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }

        // 위상정렬
        while(!q.isEmpty()){
            int now = q.poll(); // now 인덱스의 자식 조사

            // now인덱스의 자식 조사
            for (int next : list[now]) {
                // 자식의 차수 낮추기
                indegree[next]--;
                if(indegree[next] == 0){ // next인덱스의 부모가 now인덱스이면
                    children.get(now).add(next); // now인덱스의 자식으로 next 추가
                    q.offer(next);
                }
            }
        }
        // 가문의 개수 출력
        System.out.println(root.size());
        // 각 가문의 시조들의 이름 출력
        for (int idx : root) {
            System.out.print(human[idx] + " ");
        }
        System.out.println();
        // 이름의 사전순대로 사람의 이름, 자식의 수, 사전순으로 자식들의 이름 출력
        for (int i = 0; i < n; i++) {
            System.out.print(human[i] + " " + children.get(i).size() + " ");
            children.get(i).sort(null); // i인덱스의 자식을 오름차순으로 정렬
            for (int idx : children.get(i)) { // 사전순으로 자식들의 이름 출력
                System.out.print(human[idx] + " ");
            }
            System.out.println();
        }
    }
}
