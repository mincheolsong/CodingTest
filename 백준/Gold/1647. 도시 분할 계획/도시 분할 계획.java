import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
    int from,to,w;

    public Edge(int from, int to, int w){
        this.from = from;
        this.to = to;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o){
        return (this.w - o.w);
    }
}

public class Main {

    static int N,M;
    static List<Edge> edgeList;
    static int[] parent;
    static int ans;
    static int maxWeight;


    static int findParent(int n){
        if(n==parent[n]){
            return n;
        }

        return parent[n] = findParent(parent[n]);
    }

    static void union(int a, int b){
        a = findParent(a);
        b = findParent(b);

        if(a<b) parent[b] = a;
        else parent[a] = b;

    }
    static void solve(){

        for(int i=0;i<edgeList.size();i++){
            Edge edge = edgeList.get(i);
            int from,to,w;
            from = edge.from;
            to = edge.to;
            w = edge.w;

            if(findParent(from)!=findParent(to)){
                union(from,to);
                ans += w;
                maxWeight = Math.max(maxWeight,w);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList<>();
        parent = new int[N+1];
        ans = 0;
        maxWeight = 0;

        for(int i=1;i<N+1;i++){
            parent[i] = i;
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a,b,c;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(a,b,c));
        }

        Collections.sort(edgeList);

        solve();

        System.out.println(ans - maxWeight);
    }

}
