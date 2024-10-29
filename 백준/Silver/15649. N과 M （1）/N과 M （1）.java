
import java.util.*;
import java.io.*;

public class Main {

    static int N,M;
    static List<Integer> ans;
    static boolean[] visited;

    static void solve(int start, int cnt, int target){

        if(cnt==target){
            for(int i=0;i<ans.size();i++){
                System.out.print(ans.get(i) + " ");
            }
            System.out.println();
            return;
        }

        for(int i=1;i<=N;i++){
            if(visited[i]) continue;

            ans.add(i);
            visited[i] = true;
            solve(i+1,cnt+1,target);
            ans.remove(ans.size()-1);
            visited[i] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = new ArrayList<>();
        visited = new boolean[N+1];

        solve(1,0,M);
    }
}

