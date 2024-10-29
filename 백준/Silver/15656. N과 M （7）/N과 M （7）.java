

import java.util.*;
import java.io.*;

public class Main {

    static int N,M;
    static int[] result;
    static int[] input;
    static StringBuilder sb;
    static void solve(int start, int cnt){

        if(cnt==M){
            for(int i=0;i<M;i++){
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0;i<N;i++){

            result[cnt] = input[i];
            solve(i+1,cnt+1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N];
        result = new int[M];
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);

        solve(0,0);

        System.out.println(sb.toString());


    }
}

