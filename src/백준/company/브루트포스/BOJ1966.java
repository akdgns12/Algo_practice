package 백준.company.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1966 {
    // 프린터 큐 / 실버3 / 구현
    /*
        생각생각
        중요도가 높은 문서가 인쇄될 수 있도록 큐에 문서들을 넣어야함

     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); // 테스트 개수

        for (int tc = 0; tc < t; tc++) {
            Queue<Node> q = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 각 테스트의 문서 개수
            int m = Integer.parseInt(st.nextToken()); // 몇 번째로 인쇄되었는지 궁금한 문서가 현재 큐에서 몇 번째 놓여있는지를 나타내는 정수

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int prior = Integer.parseInt(st.nextToken());
                q.offer(new Node(i, prior)); // 문서의 idx, prior
            }

            int cnt = 1;
            while(!q.isEmpty()){
                Node node = q.poll(); // 맨 앞의 문서 꺼냄

                Iterator<Node> it = q.iterator();
                boolean isCheck = true;
                while (it.hasNext()) {
                    Node document = (Node) it.next();
                    // 남아있는 문서들 중에 꺼낸 문서(맨 앞의 문서)보다 중요도가 더 큰게 있다면
                    if (node.prior < document.prior) {
                        isCheck = false;
                        break;
                    }
                }

                if(isCheck == false) q.offer(node); // 맨 뒤에 넣어줌
                else{
                    if(node.idx == m) System.out.println(cnt);
                    else cnt++;
                }
            }
        }
    }

    static class Node{
        int idx;
        int prior;

        public Node(int idx, int prior) {
            this.idx = idx;
            this.prior = prior;
        }
    }
}
