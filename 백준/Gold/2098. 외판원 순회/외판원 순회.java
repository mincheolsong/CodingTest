import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static final int INF = 16_000_000;
    static int[][] W, dp;


    static int solve(int cur, int visited){

        if(visited == (1<<N)-1){
            if(W[cur][0]!=0){
                return W[cur][0];
            }
            return INF;
        }

        if(dp[cur][visited]!=0){
            return dp[cur][visited];
        }

        dp[cur][visited] = INF;

        for(int i=0;i<N;i++){
            if((visited & (1<<i)) == 0 && W[cur][i]!=0){
                dp[cur][visited] = Math.min(solve(i,visited | (1<<i)) + W[cur][i],dp[cur][visited]);
            }
        }

        return dp[cur][visited];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][(1<<N)-1];

        System.out.println(solve(0,1));

    }




}
