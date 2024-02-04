import java.io.*;
import java.util.*;


public class Main {


    static int N,M;
    static int[][] arr;
    static int ans;

    static void solve(int length){

        for(int i=0;i<=N-length;i++){
            for(int j=0;j<=M-length;j++){
                if(arr[i][j]==arr[i][j+length-1] && arr[i][j]==arr[i+length-1][j] && arr[i][j]==arr[i+length-1][j+length-1]){
                    ans = Math.max(ans,length*length);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        ans = 1;

        for(int i=0;i<N;i++){
            char[] ch = br.readLine().toCharArray();
            for(int j=0;j<M;j++){
                arr[i][j] = ch[j]-'0';
            }
        }

        for(int i=2;i<=Math.min(N,M);i++){
            solve(i);
        }

        System.out.println(ans);

    }

}
