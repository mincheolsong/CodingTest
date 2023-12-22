import java.io.*;
import java.util.*;

public class Main {

    
    static final int INF = (int)1e9;
    static int N,M,X;
    static List<Node>[] adj;
    static List<Node>[] rev_adj;

    static int[] dijkstra(List<Node>[] adj_list, int start){
        int[] dist = new int[N+1];
        Arrays.fill(dist,INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));
        dist[start]=0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(dist[cur.idx] < cur.w){ // 이미 처리된 노드는 무시
                continue;
            }

            for(Node node : adj_list[cur.idx]){
                int cost = cur.w + node.w;
                if(cost < dist[node.idx]){
                    dist[node.idx]=cost;
                    pq.offer(new Node(node.idx,cost));
                }
            }

        }

        return dist;
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        rev_adj = new ArrayList[N+1];

        for(int i=1;i<N+1;i++){
            adj[i] = new ArrayList<>();
            rev_adj[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from,to,w;
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            adj[from].add(new Node(to,w));
            rev_adj[to].add(new Node(from,w));
        }

        int[] depart = dijkstra(rev_adj,X);
        int[] arrive = dijkstra(adj,X);
        int ans = 0;

        for(int i=1;i<N+1;i++){
            int c = depart[i] + arrive[i];
            ans = c > ans ? c : ans;
        }

        System.out.println(ans);



    }

}

class Node implements Comparable<Node>{
    public int idx, w;

    public Node(int idx,int w){
        this.idx = idx;
        this.w = w;
    }

    @Override
    public int compareTo(Node o){
        return (this.w - o.w);
    }
}
