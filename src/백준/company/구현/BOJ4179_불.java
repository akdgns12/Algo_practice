package 백준.company.구현;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4179_불 {
    static int R,C;
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<Node> fire = new LinkedList<>();
    static Queue<Node> person = new LinkedList<>();
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i=0; i<R; i++){
            String str = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'J'){
                    person.add(new Node(i, j, 0));
                }else if(map[i][j] == 'F'){
                    fire.add(new Node(i, j, 0));
                }
            }
        }

        bfs();
        if(ans == Integer.MAX_VALUE)System.out.println("IMPOSSIBLE");
        else System.out.println(ans);
    }

    static void bfs(){
        while(!person.isEmpty()){
            int size = fire.size();

            for(int sz=0; sz<size; sz++){
                Node now = fire.poll();

                for(int i=0; i<4; i++){
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if(!isRange(nx, ny) || map[nx][ny] == '#' || map[nx][ny] == 'F') continue;

                    map[nx][ny] = 'F';
                    fire.offer(new Node(nx, ny, now.time + 1));
                }
            }

            size = person.size();
            for(int sz=0; sz<size; sz++){
                Node now = person.poll();

                for(int i=0; i<4; i++){
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if(!isRange(nx, ny)){
                        ans = Math.min(ans, now.time + 1);
                        return;
                    }

                    if(map[nx][ny] == '#' || map[nx][ny] == 'F' || map[nx][ny] == 'J') continue;
                    // 실수한 점 : 좌표와 함께 시간을 관리한게 아니라 시간을 따로 구해도 된다 생각함, 이렇게 하면
                    // 경로는 결국 최소경로로 이동하겠지만 time은 잘못된 경로로 간것도 누적됨
                    // time을 좌표와 함께 가지고 다녀야 올바른 최소시간을 구할 수 있음
                    map[nx][ny] = 'J';
                    person.offer(new Node(nx, ny, now.time + 1));
                    System.out.println(nx + " " + ny + " time : " + now.time);
                }
            }
        }
    }

    // 범위벗어나면 탈출
    static boolean isRange(int x, int y){
        if(x < 0 || y < 0 || x >= R || y >= C) return false;
        else return true;
    }

    static class Node{
        int x, y;
        int time;
        public Node(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}


/*
3 3
###
#J.
#.F

4 6
######
......
#.J###
#F####

5 4
####
#...
#.##
#.J#
####

7 7
#######
#J#####
#.....#
#.#.#.#
#.#.#.#
F.#.#.#
##F.#.#
 */