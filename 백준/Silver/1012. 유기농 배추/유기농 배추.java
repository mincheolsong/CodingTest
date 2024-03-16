import java.io.*;
import java.util.*;


public class Main {

    static int T;
    static int M,N,K;
    static int[][] map;
    static boolean[][] visited;
    static int ans;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};

    static void bfs(int r,int c){
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r,c});

        while(!q.isEmpty()){
            int[] cur = q.pollFirst();
            int cr = cur[0];
            int cc = cur[1];

            for(int d=0;d<4;d++){
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                if(nr<0 || nr>=M) continue;
                if(nc<0 || nc>=N) continue;
                if(map[nr][nc]==0) continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc] = true;
                q.offer(new int[]{nr,nc});
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[M][N];
            visited = new boolean[M][N];
            ans = 0;

            for(int k=0;k<K;k++){
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                map[r][c]=1;
            }

            for(int i=0;i<M;i++){
                for(int j=0;j<N;j++){
                    if(map[i][j]==1 && !visited[i][j]){
                        visited[i][j]=true;
                        bfs(i,j);
                        ans+=1;
                    }
                }
            }
            System.out.println(ans);
        }





    }

}





