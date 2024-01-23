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

    static int N,K,W;
    static int[] build;
    static List<Node>[] graph;
    static int[] ans;
    static int[] cnt; // 해당 노드를 도달하기 위해 필요한 선행노드 갯수를 저장하는 배열
    static Deque<Node> q;

    static void bfs(){
        if(cnt[W]==0){
            ans[W] = build[W];
            return;
        }

        while(!q.isEmpty()){
            Node cur = q.pollFirst();

            if(ans[cur.n] > cur.w) continue;

            for(Node l : graph[cur.n]){
                if(ans[l.n] < cur.w + l.w){
                    ans[l.n] = cur.w + l.w;
                    q.offer(new Node(l.n,ans[l.n]));
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
            build = new int[N+1];
            graph = new ArrayList[N+1];
            ans = new int[N+1];
            cnt = new int[N+1];

            for(int i=1;i<N+1;i++){
                graph[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for(int i=1;i<N+1;i++){
                build[i] = Integer.parseInt(st.nextToken());
            }
            for(int i=0;i<K;i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(new Node(to,build[to]));
                cnt[to]+=1;
            }
            W = Integer.parseInt(br.readLine());
            q = new ArrayDeque<>();

            for(int i=1;i<N+1;i++){
                if(cnt[i]==0){
                    ans[i] = build[i];
                    q.offer(new Node(i,ans[i]));
                }
            }
            bfs();

            System.out.println(ans[W]);

        }
    }

}
