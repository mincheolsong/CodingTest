import java.io.*;
import java.util.*;

class Edge{
    int from, to, w;

    public Edge(int from, int to, int w){
        this.from = from;
        this.to = to;
        this.w = w;
    }

}
public class Main {

    static final int INF = (int)1e9;
    static int N,M;
    static List<Edge> edgeList;
    static long[] dist;

    static boolean solve(){
        dist[1] = 0;

        for(int n=0;n<N;n++){
            boolean chk = false;

            for(int m=0;m<M;m++){
                Edge edge = edgeList.get(m);
                int from = edge.from;
                int to = edge.to;
                int w = edge.w;

                if(dist[from]!=INF && dist[to] > dist[from] + w){
                    chk = true;
                    dist[to] = dist[from] + w;
                    if(n==N-1){
                        return false;
                    }
                }
            }
            if(chk==false){
                return true;
            }

        }

        return true;

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList<>();
        dist = new long[N+1];
        Arrays.fill(dist,INF);

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from,to,w;
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(from,to,w));
        }

        if(solve()){
            for(int i=2;i<N+1;i++){
                System.out.println(dist[i]==INF ? -1 : dist[i]);
            }
        }else{
            System.out.println(-1);
        }






    }

}


