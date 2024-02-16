import java.io.*;
import java.util.*;


public class Main {

    static int H,W;
    static int[][] arr;
    static int[] height;
    static boolean[][] visited;
    static int ans;
    static int[] dr = {-1,0,0}; // 위, 오른쪽, 왼쪽
    static int[] dc = {0,1,-1};

    static boolean check(int r,int c){
        // r=3, h=1
        // r=2, h=2
        // r=1, h=3
        // r=0, h=4
        int h = H-r;
        // c인덱스에 h높이라고 했을 때, 왼쪽 오른쪽 크거나 같은 높이가 있는지

        for(int i=0;i<c;i++){ // 왼쪽
            if(height[i] >= h){
                for(int j=c+1;j<W;j++){ // 오른쪽
                    if(height[j] >= h){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static void bfs(int r,int c){
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r,c});

        while(!q.isEmpty()){
            int[] cur = q.pollFirst();
            int cr = cur[0];
            int cc = cur[1];
            if(check(cr,cc)) {
                ans+=1;
                arr[cr][cc]=9; // 물
            }

            for(int d=0;d<3;d++){
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                if(nr<0) continue;
                if(nc<0 || nc>=W) continue;
                if(arr[nr][nc]!=0) continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc]=true;
                q.offer(new int[]{nr,nc});
            }
        }

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        arr = new int[H][W];
        height = new int[W];
        visited = new boolean[H][W];
        ans = 0;

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<W;i++){
            int n = Integer.parseInt(st.nextToken());
            height[i] = n;
            for(int j=H-1;j>H-1-n;j--){
                arr[j][i]=1;
            }
        }

        for(int r=H-1;r>=0;r--){
            for(int c=0;c<W;c++){
                if(!visited[r][c] && arr[r][c]==0){
                    visited[r][c]=true;
                    bfs(r,c);
                }
            }
        }

        System.out.println(ans);

    }

}
