import java.io.*;
import java.util.*;


// 비 양을 최대에서 점차 감소시키기

public class Main {

    static int N;
    static int[][] arr;
    static int rain;
    static boolean[][] visited;
    static int ans;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};

    /*static void find(){
        for(int r=rain-1;r>0;r--) {
            for (int i=0;i<N;i++) {
                for(int j=0;j<N;j++){
                    if(arr[i][j]!=rain && arr[i][j] >= r){
                        rain = arr[i][j];
                        return;
                    }
                }
            }
        }
    }*/
    static void bfs(int r, int c, int v){
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
                if(nc<0 || nc>=N) continue;
                if(arr[nr][nc]<=v) continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc]=true;
                q.offer(new int[]{nr,nc});
            }
        }

    }

    static void solve(){

        for(int r=rain;r>=0;r--){

            visited = new boolean[N][N];
            int cnt = 0;

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(arr[i][j] <= r) continue;
                    if(visited[i][j]) continue;
                    visited[i][j]=true;
                    bfs(i,j,r);
                    cnt+=1;
                }
            }

            ans = Math.max(ans,cnt);

        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        rain = 0;
        ans = 1; // 안전한 영역의 최소 갯수는 1

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                rain = Math.max(rain,arr[i][j]);
            }
        }

        solve();

        System.out.println(ans);



    }

}


