import java.io.*;
import java.util.*;


public class Main {
    static int M,N;
    static int[][] arr;
    static int[][] memo;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static boolean[][] visited;

    static int solve(int r,int c){

        if(r==M-1 && c==N-1){
            return 1;
        }

        if(visited[r][c]){
            return memo[r][c];
        }

        visited[r][c]=true;
        
        for(int d=0;d<4;d++){

            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr<0 || nr>=M) continue;
            if(nc<0 || nc>=N) continue;

            if(arr[r][c] > arr[nr][nc]){
                memo[r][c] += solve(nr,nc);
            }
        }

        return memo[r][c];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[M][N];
        memo = new int[M][N];
        visited = new boolean[M][N];

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve(0,0));
    }

}
