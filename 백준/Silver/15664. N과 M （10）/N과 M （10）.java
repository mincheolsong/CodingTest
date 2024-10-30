
import java.util.*;
import java.io.*;

public class Main {

    static int N,M;
    static int[] arr;
    static Set<String> set;
    static StringBuilder sb;
    static int[] comb;

    static void solve(int start,int cnt){
        if(cnt == M){
            sb = new StringBuilder();

            for(int i=0;i<M;i++){
                sb.append(comb[i]).append(" ");
            }
            String result = sb.toString();
            if(!set.contains(result)){
                set.add(result);
                System.out.println(result);
            }

            return;
        }


        for(int i=start;i<N;i++){
            comb[cnt] = arr[i];
            solve(i+1,cnt+1);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        comb = new int[M];
        set = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        solve(0,0);



    }
}

