import java.io.*;
import java.util.*;




public class Main {

    static int N,K,W;

    static List<Integer>[] graph;
    static int[] d;
    static int[] indegree;
    static int[] result;

    static void solve(){
        Deque<Integer> q = new ArrayDeque<>();

        for(int i=1;i<N+1;i++){
            result[i] = d[i];
            if(indegree[i]==0){
                q.offer(i);
            }
        }

        while(!q.isEmpty()){
            int node = q.pollFirst();

            for(Integer i : graph[node]){
                if(result[i] < result[node] + d[i]){
                    result[i] = result[node] + d[i];
                }
                indegree[i]--;

                if(indegree[i]==0){
                    q.offer(i);
                }
            }
        }


    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            graph = new ArrayList[N+1];
            d = new int[N+1];
            indegree = new int[N+1];
            result = new int[N+1];


            for(int i=1;i<N+1;i++){
                graph[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for(int i=1;i<N+1;i++){
                d[i] = Integer.parseInt(st.nextToken());
            }


            for(int i=0;i<K;i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
                indegree[to]++;
            }

            W = Integer.parseInt(br.readLine());

            solve();

            System.out.println(result[W]);


        }
    }

}
