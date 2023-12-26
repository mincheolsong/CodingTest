import java.io.*;
import java.util.*;

public class Main {

    // 트리에서 임의의 두 점 사이의 거리 중 가장 긴 길이를 구하라.

    static int V;
    static List<Node>[] adj;
    static boolean[] visited;
    static int longest_v, longest_e;
    static void solve(int v,int w){

        if(w > longest_e){
            longest_v = v;
            longest_e = w;
        }

        for(Node node : adj[v]){
            if(visited[node.idx]) continue;
            visited[node.idx]=true;
            solve(node.idx,w+node.w);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        V = Integer.parseInt(br.readLine());
        adj = new ArrayList[V+1];
        for(int i=1;i<=V;i++){
            adj[i] = new ArrayList<>();
        }

        for(int i=0;i<V;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());

            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if(to==-1) break;
                int w = Integer.parseInt(st.nextToken());
                adj[from].add(new Node(to,w));
            }
        }

        // 임의의 하나의 정점 (1) 에서 가장 먼 정점 찾기
        visited = new boolean[V+1];
        longest_e = 0;
        longest_v = 0; // 가장 먼 정점 찾아짐
        visited[1]=true;
        solve(1,0);

        visited = new boolean[V+1]; // 방문배열 초기화
        visited[longest_v]=true; // 가장 먼 정점 방문처리
        longest_e = 0; // 가장 먼 간선 길이 초기화
        solve(longest_v,0);

        System.out.println(longest_e);



    }

}

class Node{
    int idx, w;

    public Node(int idx, int w){
        this.idx = idx;
        this.w = w;
    }

}

