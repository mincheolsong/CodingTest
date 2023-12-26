import java.io.*;
import java.util.*;

public class Main {


    static int N,K;
    static int[] memo;
    static Deque<int[]> q = new ArrayDeque<>();

    static void bfs(){
        while(!q.isEmpty()){
            int[] cur = q.pollFirst();
            int idx = cur[0];
            int w = cur[1];

            if(idx-1>=0 && (memo[idx-1]==-1 || w+1 < memo[idx-1])){
                q.offer(new int[]{idx-1,w+1});
                memo[idx-1]=w+1;
            }
            if(idx+1<=100000 && (memo[idx+1]==-1 || w+1 < memo[idx+1])){
                q.offer(new int[]{idx+1,w+1});
                memo[idx+1]=w+1;
            }

            if(idx*2 <= 100000 &&  (memo[idx*2]==-1 || w < memo[idx*2])){
                q.offer(new int[]{idx*2,w});
                memo[idx*2] = w;
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if (N >= K) {
            System.out.println(N - K);
        } else {
            memo = new int[100001];
            Arrays.fill(memo, -1);
            memo[N] = 0;
            q.offer(new int[]{N, 0});
            bfs();
            System.out.println(memo[K]);
        }

    }

}
