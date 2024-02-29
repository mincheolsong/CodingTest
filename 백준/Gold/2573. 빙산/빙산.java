import java.io.*;
import java.util.*;

public class Main {

    static int N,M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static Deque<int[]> q;
    static int ans;

    static void check(int r,int c){
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r,c});

        while(!q.isEmpty()){
            int[] cur = q.pollFirst();
            int cr = cur[0];
            int cc = cur[1];

            for(int d=0;d<4;d++){
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                if(nr<0 || nr>=N) continue;
                if(nc<0 || nc>=M) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc]==0) continue;

                visited[nr][nc]=true;
                q.offer(new int[]{nr,nc});
            }
        }
    }

    static void melt(){
        Deque<int[]> q = new ArrayDeque<>();
        visited = new boolean[N][M];

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]!=0){
                    q.offer(new int[]{i,j});
                    visited[i][j]=true;
                }
            }
        }

        while(!q.isEmpty()){
            int[] cur = q.pollFirst();
            int cr = cur[0];
            int cc = cur[1];

            for(int d=0;d<4;d++){
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                if(nr<0 || nr>=N) continue;
                if(nc<0 || nc>=M) continue;
                if(visited[nr][nc]) continue;

                if(map[nr][nc]==0) {
                    map[cr][cc]-=1;
                    if(map[cr][cc]==0){
                        break;
                    }
                }

            }
        }
    }
    static void solve(){

        while(true){

            melt();

            // 1년 지남 (한 번 녹임)
            ans+=1;

            // 두 덩어리로 분리됐는지 검사
            visited = new boolean[N][M];
            int cnt = 0;
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(map[i][j]!=0 && !visited[i][j]){
                        cnt+=1;
                        visited[i][j] = true;
                        check(i,j);
                    }
                    if(cnt==2){ // 두 덩어리이면
                        return;
                    }
                }
            }
            if(cnt==0){ // 덩어리가 하나도 안 남았으면
                ans = 0;
                return;
            }

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = 0;

        map = new int[N][M];
        q = new ArrayDeque<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]!=0) q.offer(new int[]{i,j});
            }
        }

        solve();

        System.out.println(ans);


    }

}


