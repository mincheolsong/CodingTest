import java.util.*;
import java.io.*;


public class Main {

    static int N;
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {




        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            N = Integer.parseInt(br.readLine());
            arr = new int[2][N];
            dp = new int[2][N];
            for(int r=0;r<2;r++){
                st = new StringTokenizer(br.readLine());
                for(int c=0;c<N;c++){
                    arr[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][0] = arr[0][0];
            dp[1][0] = arr[1][0];

            for(int c=1;c<N;c++){
                for(int r=0;r<2;r++){
                    if(r==0){
                        dp[r][c] = Math.max(c-1>=0?dp[1][c-1]:0,c-2>=0?dp[1][c-2]:0) + arr[r][c];
                    }else if(r==1){
                        dp[r][c] = Math.max(c-1>=0?dp[0][c-1]:0,c-2>=0?dp[0][c-2]:0) + arr[r][c];
                    }
                }
            }
            System.out.println(Math.max(dp[0][N-1],dp[1][N-1]));
        }




    }

}