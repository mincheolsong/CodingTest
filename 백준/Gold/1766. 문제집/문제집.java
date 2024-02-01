import java.io.*;
import java.util.*;


// 위상정렬

public class Main {

    static int N,M;
    static List<Integer>[] adj;
    static int[] indegree;
    static Deque<Integer> answer;
    static StringBuilder sb;

    static void solve(){
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)->(o1-o2));

        for(int i=1;i<N+1;i++){
            if(indegree[i]==0){
                pq.offer(i);
            }
        }

        while(!pq.isEmpty()){
            int cur = pq.poll();
            sb.append(cur).append(" ");

            for(Integer i : adj[cur]){
                if(--indegree[i]==0){
                    pq.offer(i);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adj = new List[N+1];
        indegree = new int[N+1];
        answer = new ArrayDeque();
        sb = new StringBuilder();

        for(int i=1;i<N+1;i++){
            adj[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adj[A].add(B);
            indegree[B]+=1;
        }

        solve();

        System.out.println(sb.toString());



    }

}
