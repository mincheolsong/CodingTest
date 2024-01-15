import java.io.*;
import java.util.*;

class Node{
    int n, w;

    public Node(int n, int w){
        this.n = n;
        this.w = w;
    }
}

public class Main {

    static int n;
    static List<Node>[] tree;
    static int ans;
    static Set<int[]> set;
    static boolean[] visited;
    static boolean[] isLeaf;

    static void dfs(int n,int s){

        for(Node node : tree[n]){
            if(visited[node.n]) continue;
            visited[node.n]=true;
            dfs(node.n,s+node.w);
        }

        ans = s > ans ? s : ans;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n+1];
        ans = 0;
        set = new HashSet<>();
        isLeaf = new boolean[n+1];
        Arrays.fill(isLeaf,true);

        for(int i=1;i<n+1;i++){
            tree[i] = new ArrayList<>();
        }

        for(int i=0;i<n-1;i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            tree[p].add(new Node(c,w));
            tree[c].add(new Node(p,w));
            isLeaf[p]=false;
        }

        for(int i=1;i<n+1;i++){
            if(isLeaf[i]){
                visited = new boolean[n+1];
                visited[i]=true;
                dfs(i,0);
            }
        }

        System.out.println(ans);



    }



}

