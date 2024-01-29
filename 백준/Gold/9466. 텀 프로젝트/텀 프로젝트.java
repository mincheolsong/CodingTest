import java.io.*;
import java.util.*;

// 백준 9466번

public class Main {

    static int T;
    static int n;
    static int[] students;
    static boolean[] visited;
    static boolean[] done;
    static int cnt;


    static void solve(int idx){
        if(done[idx]) return;
        if(visited[idx]){
            done[idx]=true;
            cnt+=1;
        }

        visited[idx]=true;
        solve(students[idx]);
        visited[idx]=false;

        done[idx]=true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            n = Integer.parseInt(br.readLine());
            students = new int[n+1];
            done = new boolean[n+1];
            visited = new boolean[n+1];
            cnt = 0;

            st = new StringTokenizer(br.readLine());
            for(int i=1;i<n+1;i++){
                students[i] = Integer.parseInt(st.nextToken());
            }


            for(int i=1;i<n+1;i++){
                if(done[i]) continue;
                solve(i);
            }

//            System.out.println(team.toString());
            System.out.println(n-cnt);


        }

    }

}
