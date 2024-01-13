import java.io.*;
import java.util.*;

class Node{
    int from, to, w;

    public Node(int from, int to, int w){
        this.from = from;
        this.to = to;
        this.w = w;
    }

}

public class Main {

    // 벨만포드
    static final int INF = (int)1e9;
    static int N,M,W;
    static List<Node> graph;
    static int[] dist;

    static boolean solve(){
        Arrays.fill(dist,INF);
        dist[1]=0; // 시작정점은 1로 (아무거나) 초기화

        for(int i=1;i<=N;i++){

            boolean chk = false;

            for(int j=0;j<graph.size();j++){ // 모든 간선
                Node node = graph.get(j);

                if(dist[node.to] > dist[node.from] + node.w){
                    dist[node.to] = dist[node.from] + node.w;
                    chk = true;

                    if(i==N){ // N번째에도 갱신이 일어나면 음수 순환이 있음
                        return true;
                    }
                }
            }
            if(!chk){ // chk가 false이면 더 이상 간선이 업데이트될 게 없기 때문에, 더 반복문을 돌릴 필요가 없다.
                break;
            }

        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int TC = Integer.parseInt(br.readLine());

        for(int t=0;t<TC;t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            dist = new int[N+1];


            for(int m=0;m<M;m++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.add(new Node(from,to,weight));
                graph.add(new Node(to,from,weight));
            }

            for(int w=0;w<W;w++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.add(new Node(from,to,-weight));
            }

            boolean ans = solve();
            if(ans){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }


        }


    }


}

