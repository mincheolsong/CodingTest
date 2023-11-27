import java.util.*;
import java.io.*;


public class Main {

    static int N;
    static List<Integer>[] graph;
    static int[] parent;
    static boolean[] visited;

    static void dfs(int n){

        for(int i : graph[n]){
            if(!visited[i]){
                visited[i]=true;
                parent[i]=n;
                dfs(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        graph = new List[N+1];
        parent = new int[N+1];
        parent[1] = 1; // 1번 노드는 루트노드
        visited = new boolean[N+1];

        for(int i=1;i<N+1;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        visited[1]=true;
        dfs(1);

        for(int i=2;i<N+1;i++){
            System.out.println(parent[i]);
        }


    }

}