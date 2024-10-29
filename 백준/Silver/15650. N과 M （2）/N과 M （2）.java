

import java.util.*;
import java.io.*;

public class Main {

    static int N,M;
    static int[] result;
    static StringBuilder sb;
    static void solve(int start,int cnt){

        if(cnt==M){
            for(int i=0;i<M;i++){
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=start;i<=N;i++){
            result[cnt] = i;
            solve(i+1,cnt+1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = new int[M];
        sb = new StringBuilder();

        solve(1,0);

        System.out.println(sb.toString());


    }
}

