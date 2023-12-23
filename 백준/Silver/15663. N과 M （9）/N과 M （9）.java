import java.io.*;
import java.util.*;

public class Main {

    static int N,M;
    static int[] arr;
    static boolean[] visited;
    static int[] tmp;
    static LinkedHashSet<String> ans;
    static void solve(int n){
        if(n==M){
            StringBuilder sb = new StringBuilder();
            for(int p : tmp){
                sb.append(p).append(' ');
            }
            ans.add(sb.toString());
            return;
        }

        for(int i=0;i<N;i++){
            if(visited[i]) continue;
            visited[i]=true;
            tmp[n]=arr[i];
            solve(n+1);
            visited[i]=false;
        }
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        visited = new boolean[N];
        tmp = new int[M];
        ans = new LinkedHashSet<>();


        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        solve(0);

        ans.forEach(System.out::println);


    }

}
