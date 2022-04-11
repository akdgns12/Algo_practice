package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 프린터 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            Queue<Node> q = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int prior = Integer.parseInt(st.nextToken());
                q.offer(new Node(i, prior));
            }

            int cnt = 1;
            while(!q.isEmpty()){
                Node node = q.poll(); // 맨 앞의 문서 꺼냄

                Iterator<Node> it = q.iterator();
                boolean isCheck = true;
                while (it.hasNext()) {
                    Node document = (Node) it.next();

                    if (node.prior < document.prior) {
                        isCheck = false;
                        break;
                    }
                }

                if(isCheck == false) q.offer(node);
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
