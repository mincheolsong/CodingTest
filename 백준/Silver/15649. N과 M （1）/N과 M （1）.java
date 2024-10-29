

import java.util.*;
import java.io.*;

public class Main {

    static int N,M;
    static int[] result;
    static boolean[] visited;
    static StringBuilder sb;
    static void solve(int cnt){

        if(cnt==M){
            for(int i=0;i<M;i++){
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=1;i<=N;i++){
            if(visited[i]) continue;

            result[cnt] = i;
            visited[i] = true;
            solve(cnt+1);
            visited[i] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = new int[M];
        visited = new boolean[N+1];
        sb = new StringBuilder();

        solve(0);

        System.out.println(sb.toString());
        

    }
}

