package 복습;

import java.io.*;
import java.util.StringTokenizer;

public class 세그먼트트리_구간합구하기 {
    static int N,M,K;
    static long[] tree;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 수의 개수
        M = Integer.parseInt(st.nextToken()); // 수의 변경 횟수
        K = Integer.parseInt(st.nextToken()); // 구간 합을 구하는 횟수

        // 배열 변수 선언 (배열의 0 인덱스는 안쓰려고 길이 1추가)
        arr = new long[N+1];

        // 배열 값 초기화
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 세그먼트 트리 생성
        // 2^k >= N인 최소의 k를 찾아야 함.
        // 양변에 log을 취하면,
        // k >= logN / log2
        // (logN / log2)의 값을 올림한 후 1을 더하면 k가 됨.
        // 위에서 구한 k를 제곱하면 세그먼트 트리의 size를 구할 수 있음.

//		int k = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
//		int size = (int) Math.pow(2, k);
//
//		tree = new long[size];

        // 사이즈를 구하는 위의 과정이 귀찮으면, 단순히 N에 4를 곱한 사이즈를 사용해도 무방함.
        tree = new long[N * 4];

        // 1. 구간 합 트리 생성
        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M+K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) { // a가 1인 경우 수 갱신
                // dif -> index번째 수를 val로 변경한다 하면 그 수가 얼만큼 변경했는지 알아야 한다
                // 변경한 차이 값 = dif
                long dif = c - arr[b];
                arr[b] = c;
                update(1, N, 1, b, dif);
            } else if (a == 2) { // a가 2인 경우 부분 합 구하기
                sb.append(sum(1, N, 1, b, (int) c) + "\n");
            }
        }

        System.out.println(sb.toString());
    }

    /*
        tree : 세그먼트 트리
        node : 세그먼트 트리 노드 번호
        start, end : 구하고자 하는 시작점과 끝
     */
    // 1. 구간 합 트리 생성하는 함수
    static long init(int start, int end, int node){
        // start == end인 경우는 -> node가 리프노드인 경우
        // 리프노드는 배열의 그 원소 자체를 가져야 하기 때문에 tree[node] = arr[start]
        if(start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        // 왼쪽 자식은 node*2, 오른쪽 자식은 node*2+1
        // 노드가 담당하는 구간이 [start, end]라면 왼쪽 자식은 [start, mid], 오른쪽 자식은 [mid+1, end]
        // 이에 따라, 재귀함수를 이용해서 왼쪽 자식과 오른쪽 자식 트리를 만들고, 그 합을 저장
        return tree[node] = init(start, mid, node*2) + init(mid+1, end, node*2+1);
    }

    /**
     * 구간 합을 구하는 함수 동작 logic
     * node가 담당하고 있는 구간이 [start,end] 이고, 합을 구해야하는 구간이 [left,right] 이라면 다음과 같이 4가지 경우로 나누어질 수 있습니다.
     *
     * 1. [left,right]와 [start,end]가 겹치지 않는 경우
     * 2. [left,right]가 [start,end]를 완전히 포함하는 경우
     * 3. [start,end]가 [left,right]를 완전히 포함하는 경우
     * 4. [left,right]와 [start,end]가 겹쳐져 있는 경우 (1, 2, 3 제외한 나머지 경우)
     * 1번 경우에는 if (left > end || right < start)로 나타낼 수 있습니다.
     * left > end는 [start,end] 뒤에 [left,right]가 있는 경우이고,
     * right < start는 [start,end] 앞에 [left,right]가 있는 경우입니다.
     * 이 경우에는 겹치지 않기 때문에, 더 이상 탐색을 이어나갈 필요가 없습니다. 따라서 0을 리턴해 탐색을 종료합니다.
     *
     * 2번 경우는 if (left <= start && end <= right)로 나타낼 수 있습니다. 이 경우도 더 이상 탐색을 이어나갈 필요가 없습니다.
     * 구해야하는 합의 범위는 [left,right]인데, [start,end]는 그 범위에 모두 포함되고,
     * 그 node의 자식도 모두 포함되기 때문에 더 이상 호출을 하는 것은 비효율적입니다. 따라서, tree[node]를 리턴해 탐색을 종료합니다.
     *
     * 3번과 4번의 경우에는 왼쪽 자식과 오른쪽 자식을 루트로 하는 트리에서 다시 탐색을 시작해야 합니다.
     */
    static long sum(int start, int end, int node, int left, int right){
        // 1. [left, right]와 [start,end]가 겹치지 않는 경우 -> 범위 밖에 있는 경우
        if(left > end || right < start) return 0;
        // 2. [left, right]가 [start, end]를 완전히 포함하는 경우 -> 범위 안에 있는 경우
        if(left <= start && end <= right){
            return tree[node];
        }

        // 그렇지 않다면 두 부분으로 나누어 합을 구하기(3,4번 케이스)
        int mid = (start + end) / 2;
        return tree[node] = sum(start, mid, node * 2, left, right)
                + sum(mid+1, end, node*2+1, left, right);

    }

    /**
     * 중간에 어떤 수를 변경해줘야 한다면 그 숫자가 포함된 구간을 담당하는 노드를 모두 변경해줘야 한다
     * index번째 수를 val로 변경한다면, 그 수가 얼마만큼 변했는지를 알아야 한다.
     * 이를 dif라 한다면, dif = val - arr[index]
     * 수 변경은 2가지 경우가 있다.
     * 1. [start, end]에 index가 포함되는 경우
     * 2. [start, end]에 index가 포함되지 않는 경우
     * node의 구간에 포함되는 경우에는 dif만큼 증가시켜 합을 변경해 줄 수 있다.
     * tree[node] = tree[node] + dif
     * 포함되지 않는 경우는 그 자식도 index가 포함되지 않기 때문에, 탐색을 중단해야 한다
     * 마지막으로, 리프노드가 아닌 경우에는 자식도 변경해줘야 하기 때문에
     * start != end로 리프노드인지 검사를 해야한다.
     *
     * @param start 노드의 시작 인덱스
     * @param end 노드의 종료 인덱스
     * @param node
     * @param idx 구간 합을 수정하고자 하는 노드(문제에서 변경하고자 주어진 인덱스)
     * @param dif
     */
    static void update(int start, int end, int node, int idx, long dif){
        // 2. [start, end]에 index가 포함되지 않는 경우
        // 범위 밖에 있는 경우
        if(idx < start || idx > end) return;

        // 1. [start, end]에 index가 포함되는 경우
        // 범위 안에 잇으면 내려가며 다른 원소도 갱신
        tree[node] = tree[node] + dif;
        if(start == end){
            // start == end -> 리프노드라는 의미
            // 리프노드면 자식은 변경해줄 필요가 없다.
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, dif);
        update(mid+1, end, node*2+1, idx, dif);
    }
}
