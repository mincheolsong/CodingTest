import java.io.*;
import java.util.*;


public class Main {

    static int T;
    static int V,E;
    static List<Integer>[] graph;
    static int[] chk;

    static boolean bfs(int node){

        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{node,0});
        chk[node]=0;

        while(!q.isEmpty()){
            int[] c = q.pollFirst();
            int n = c[0];
            int color = c[1];

            for(int i : graph[n]){
                if(chk[i]==color){
                    return false;
                }
                if(chk[i]==-1){
                    chk[i] = (color + 1)%2;
                    q.offer(new int[]{i,chk[i]});
                }
            }
        }


        return true;
    }
    static boolean solve(){

        for(int i=1;i<V+1;i++){
            if(chk[i]==-1){
                if(!bfs(i)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            graph = new List[V+1];
            chk = new int[V+1];
            Arrays.fill(chk,-1);

            for(int i=1;i<V+1;i++){
                graph[i] = new ArrayList<>();
            }
            for(int i=0;i<E;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }

            if(solve()){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }



    }

}
