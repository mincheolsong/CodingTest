import java.io.*;
import java.util.*;

public class Main {

    // 백준 2638 치즈
    // BFS
    // (0,0)에서 BFS를 시작
    // BFS를 한 턴씩 수행해야 함 (큐 사이즈 만큼)
    // 닿은지점은 -1, 치즈인 지점은 ++, 3이면 치즈 녹이기 (-1로 바꾸기)
    // 치즈 녹으면 전체 치즈갯수-=1
    // 전체 치즈갯수가 0이되면 함수 종료

    static final int[] dr = {-1,0,1,0};
    static final int[] dc = {0,1,0,-1};
    static int N,M;
    static int[][] arr;
    static boolean[][] visited;
    static int cheese; // 전체 치즈 갯수
    static int time;

    static void print(int[][] a){
        for(int[] t : a){
            for(int k : t){
                System.out.print(k + "\t");
            }
            System.out.println();
        }
        System.out.println("-----------");
    }
    static int[][] clone(int[][] a){
        int[][] result = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                result[i][j] = a[i][j];
            }
        }

        return result;
    }

    static Deque<Point> spread(){ // 테두리 반환

        visited = new boolean[N][M];
        visited[0][0]=true;

        Deque<Point> q = new ArrayDeque<>();
        Deque<Point> wrap = new ArrayDeque<>();

        q.offer(new Point(0,0));

        while(!q.isEmpty()){
            Point cur = q.pollFirst();
            int cr = cur.r;
            int cc = cur.c;

            for(int d=0;d<4;d++){
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                if(nr<0 || nr>=N) continue;
                if(nc<0 || nc>=M) continue;
                if(visited[nr][nc]) continue; // 방문한 곳이면 다시 방문하지 않음 (중복제거)

                if(arr[nr][nc]==0){ // 빈 칸
                    visited[nr][nc]=true; // 방문처리
                    q.offer(new Point(nr,nc));
                }else if(arr[nr][nc]==1){
                    wrap.offer(new Point(nr,nc));
                }
            }
        }

        // wrap 중복제거
        Set<Point> s = new HashSet<>(wrap);
        wrap.clear();

        for(Point a : s){
            wrap.offer(a);
        }

        return wrap;
    }
    static void bfs(){

        for(int i=0;i<(N-1)*(M-1);i++){
            time+=1;
            Deque<Point> wrap = spread();

            List<Point> willMelt = new ArrayList<>();

            while (!wrap.isEmpty()) {
                Point c = wrap.pollFirst();
                int cr = c.r;
                int cc = c.c;

                for(int d=0;d<4;d++){
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];
                    if(nr<0 || nr>=N) continue;
                    if(nc<0 || nc>=M) continue;

                    if(arr[nr][nc]==0 && visited[nr][nc]){
                        arr[cr][cc]+=1;
                    }
                }

                if(arr[cr][cc]>=3){
                    cheese-=1;
                    if(cheese==0){
                        return;
                    }
                    willMelt.add(new Point(cr,cc));
                }else if(arr[cr][cc]==2){
                    arr[cr][cc]=1;
                }
            }

            for(Point p : willMelt){
                arr[p.r][p.c]=0;
            }

        }

    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        cheese = 0;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]==1) cheese+=1;
            }
        }

        // 입력 끝
        time = 0;
        bfs();
        System.out.println(time);

    }

    static class Point{
        int r,c;

        public Point(int r,int c){
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o){
            if(this==o) return true;
            if(o==null || !(o instanceof Point)) return false;
            Point p = (Point)o;

            return (this.r==p.r && this.c==p.c);

        }

        @Override
        public int hashCode(){
            return Objects.hash(r)+Objects.hash(c);
        }

        @Override
        public String toString(){
            return "(" + r + ", " + c + ")";
        }
    }


}



