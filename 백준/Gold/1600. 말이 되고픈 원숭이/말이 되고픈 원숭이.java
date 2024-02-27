import java.io.*;
import java.util.*;

public class Main {

    static final int INF = (int)1e9;
    static int K;
    static int H,W; // H * W 배열
    static int[][] map;
    static boolean[][][] visited;
    static int[] dr = {-1,0,1,0,-2,-2,-1,-1,1,1,2,2};
    static int[] dc = {0,1,0,-1,-1,1,-2,2,-2,2,-1,1};
    static int ans;



    static void solve(){
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0,0}); // (0,0), 0번 이동횟수
        visited[0][0][0] = true;

        int tmp = -1;
        while(!q.isEmpty()){
            int size = q.size();
            tmp+=1;
            for(int s=0;s<size;s++){
                int[] cur = q.pollFirst();
                int cr = cur[0];
                int cc = cur[1];
                int kMv = cur[2];


                if(cr==H-1 && cc==W-1){
                    ans = tmp;
                    return;
                }

                for(int d=0;d<4;d++){
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];
                    if(nr<0 || nr>=H) continue;
                    if(nc<0 || nc>=W) continue;
                    if(map[nr][nc]==1) continue;
                    if(visited[nr][nc][kMv]) continue;
                    visited[nr][nc][kMv]=true;
                    q.offer(new int[]{nr,nc,kMv});
                }

                if(kMv >= K) continue;
                for(int d=4;d<12;d++){
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];
                    if(nr<0 || nr>=H) continue;
                    if(nc<0 || nc>=W) continue;
                    if(map[nr][nc]==1) continue;
                    if(visited[nr][nc][kMv+1]) continue;
                    visited[nr][nc][kMv+1]=true;
                    q.offer(new int[]{nr,nc,kMv+1});
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visited = new boolean[H][W][K+1];
        ans = -1;

        for(int i=0;i<H;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<W;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        System.out.println(ans);


    }

}


