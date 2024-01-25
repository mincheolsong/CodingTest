import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static int[][] memo;

    static void dp(){
        for(int i=1;i<N+1;i++){
            memo[i][i]=1;
        }

        for(int c=1;c<N+1;c++){
            for(int r=1;r<c;r++){
                if(c-r==1 && arr[r]==arr[c]){
                    memo[r][c]=1;
                }
                else if(arr[r]==arr[c] && memo[r+1][c-1]==1){
                    memo[r][c]=1;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        memo = new int[N+1][N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<N+1;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp();

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from, to;
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            sb.append(memo[from][to]+"\n");
        }
        System.out.println(sb.toString());


    }

}
