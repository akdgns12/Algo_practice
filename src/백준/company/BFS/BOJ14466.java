package 백준.company.BFS;


import java.util.*;

public class BOJ14466 {
    // 소가 길을 건너간 이유 6 / 골드 4 / BFS
    /*
        어떤 두 소는 길을 건너지 않으면 만나지 못한다. 이런 소가 몇 쌍인지 세어보자
     */
    // 소의 위치를 시작점으로 bfs를 통해 목초지 탐색, 다리가 존재하는 경우는 탐색 x
    // 탐색이 끝나고 다른 소 위치에 대한 방문 여부를 체크하는데 이때 방문이 되지 않는 경우는 다리를 지나지 않으면 만날 수 없다는 것을 의미하기 때문에
    // 해당 경우를 모두 찾으면 된다.
    // 각각의 소에서 다리를 이용하지 않고 다른 소들을 방문할 수 있는지 물어보는 문제

        static ArrayList<Node> cowList = new ArrayList<>();
        static int[][] cowMap;
        static boolean[][] visited;
        static int N;
        static boolean[][] check;
        static ArrayList<Node>[][] roads;

        static int[] dx = {-1, 0, 1, 0};
        static int[] dy = {0, 1, 0, -1};

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            N = sc.nextInt();
            int K = sc.nextInt(); // k마리의 소
            int R = sc.nextInt();

            // 길 위치 저장
            roads = new ArrayList[N+1][N+1];
            for(int i = 1; i < N+1; i++) {
                for(int j = 1; j < N+1; j++) {
                    roads[i][j] = new ArrayList<>();
                }
            }

            for(int i = 0; i < R; i++) {
                int r1 = sc.nextInt();
                int c1 = sc.nextInt();
                int r2 = sc.nextInt();
                int c2 = sc.nextInt();

                roads[r1][c1].add(new Node(r2, c2));
                roads[r2][c2].add(new Node(r1, c1));
            }

            // 소 위치 저장
            cowMap = new int[N+1][N+1];

            for(int i = 0; i < K; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                cowList.add(new Node(x, y));
                cowMap[x][y] = i+1;
            }

            int ans = 0;
            for(int i = 0; i < cowList.size(); i++) {
                check = new boolean[N+1][N+1];

                bfs(cowList.get(i).x, cowList.get(i).y);

                for(int j = i+2; j < K+1; j++) {
                    if(!check[i+1][j]) {
                        ans++;
                    }
                }
            }

            System.out.println(ans);
        }

        public static void bfs(int x, int y) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(new Node(x, y));

            visited = new boolean[N+1][N+1];
            visited[x][y] = true;

            while(!queue.isEmpty()) {
                int cx = queue.peek().x;
                int cy = queue.poll().y;

                for(int i = 0; i < 4; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];
                    // 범위를 벗어나거나, 이미 방문한 곳이라면 pass
                    if(nx <= 0 || ny <= 0 || nx > N || ny > N || visited[nx][ny]) continue;

                    boolean isRoad = false;
                    // 현재 위치가 길인데 새로운 위치도 길이라서 길을 건너야 한다면 pass
                    for(int j = 0; j < roads[cx][cy].size(); j++) {
                        if(roads[cx][cy].get(j).x == nx && roads[cx][cy].get(j).y == ny) {
                            isRoad = true;
                            break;
                        }
                    }

                    if(isRoad) continue;

                    if(cowMap[nx][ny] >= 1) check[cowMap[x][y]][cowMap[nx][ny]] = true;

                    visited[nx][ny] = true;
                    queue.add(new Node(nx, ny));
                }
            }
        }

        static class Node{
        int x, y;

            public Node(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }