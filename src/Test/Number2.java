package Test;

import java.util.LinkedList;
import java.util.Queue;

public class Number2 {
        public static void main(String[] args) {
            Number2 T = new Test.Number2();
            String[] grid ={"??b", "abc", "cc?"};
            System.out.println(T.solution(grid));
        }

        static char[][] map;
        static int n, m;
        static int totalQuestion;
        static char[] arr = {'a', 'b', 'c'};
        boolean[][] visited;
        static int[] dx = {-1,1,0,0};
        static int[] dy = {0,0,-1,1};
        static Queue<Node> q = new LinkedList<>();

        // 1. 물음표를 a,b,c중 하나로 바꾸는 모든 경우를 택할 수 있게 한다
        // 2. 물음표 개수만큼 선택을 마쳤다면 그게 조건에 맞는지 검사
        // 3. 맞다면 카운팅
        public int solution(String[] grid) {
            int answer = 0;

            n = grid.length;
            m = grid[0].length();
            map = new char[n][m];

            for (int i = 0; i < n; i++) {
                String str = grid[i];
                for (int j = 0; j < m; j++) {
                    map[i][j] = str.charAt(j);
                    if(map[i][j] == '?') totalQuestion++; q.offer(new Node(i,j));
                }
            }

            dfs(0, arr);

            return answer;
        }

        static void dfs(int depth, char[] arr){
            if (depth == totalQuestion) {

                check(arr);
                return;
            }

            for (int i = 0; i < 3; i++) {
                dfs(depth+1, arr);
            }
        }

        static void check(char[] arr){
            Queue<Node> q = new LinkedList<>();

            while(!q.isEmpty()){
                Node node = q.poll();

                for (int a = 0; a < 3; a++) {
                    map[node.x][node.y] = arr[a];

                    for (int i = 0; i < 4; i++) {
                        int nx = node.x + dx[i];
                        int ny = node.y + dy[i];

                        if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                    }
                }


            }
        }

        static class Node{
            int x, y;
            public Node(int x, int y){
                this.x = x;
                this.y = y;
            }
        }
    }

//    from collections import defaultdict
//        def solution(grid):
//        answer = 0
//        grid = [list(row) for row in grid]
//        N = len(grid)
//        M = len(grid[0])
//        def check():
//        nonlocal grid
//        visited = [[True for _ in range(M)] for _ in range(N)]
//        cnt = defaultdict(int)
//        dx = [-1,1,0,0]
//        dy = [0,0,-1,1]
//        for x in range(N):
//        for y in range(M):
//        if visited[x][y]:
//        visited[x][y] = False
//        word = grid[x][y]
//        stack = [(x,y)]
//        while stack:
//        cx,cy = stack.pop()
//        for i in range(4):
//        nx = cx + dx[i]
//        ny = cy + dy[i]
//        if 0<=nx<N and 0<=ny<M:
//        if visited[nx][ny] and grid[nx][ny] == word:
//        stack.append((nx,ny))
//        visited[nx][ny] = False
//        cnt[word] += 1
//
//        for key in cnt:
//        if cnt[key] > 1:
//        return False
//        return True
//        def dfs(point):
//        nonlocal grid,answer
//        if point == N*M:
//        if check():
//        answer += 1
//        return
//        else:
//        x = point//M
//        y = point%M
//        if grid[x][y] == '?':
//
//        for other in ['a','b','c']:
//        grid[x][y] = other
//        dfs(point+1)
//        grid[x][y] = '?'
//        else:
//        dfs(point+1)
//        dfs(0)
//        return answer
//        solution(["??b", "abc", "cc?"])
