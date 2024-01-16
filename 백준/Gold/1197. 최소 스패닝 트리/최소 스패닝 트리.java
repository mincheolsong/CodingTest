import java.io.*;
import java.util.*;


class Edge implements Comparable<Edge>{
    int from,to,weight;

    public Edge(int from, int to,int weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o){
        return (this.weight - o.weight);
    }

}


public class Main {

    // 최소신장트리 (MST)
    // 크루스칼 알고리즘 (간선중심, 그리디)

    static int V,E;
    static Edge[] edge_arr;
    static int[] parents;

    static void makeSet(){
        parents = new int[V+1];
        for(int i=1;i<V+1;i++){
            parents[i] = i;
        }
    }

    static int findParent(int a){
        if(a==parents[a]){
            return a;
        }

        return parents[a] = findParent(parents[a]);
    }

    static boolean union(int a, int b){
        int ap = findParent(a);
        int bp = findParent(b);

        if(ap==bp){
            return false;
        }

        if(ap>bp){ // ap < bp 유지
            int tmp = ap;
            ap = bp;
            bp = tmp;
        }
        parents[bp]=ap;
        return true;
    }
    static void solve(){

        makeSet();
        int cnt = 0, ans = 0;

        for(Edge e : edge_arr){
            if(union(e.from,e.to)){
                ans += e.weight;
                if(++cnt==V-1){
                    System.out.println(ans);
                    return;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edge_arr = new Edge[E];



        for(int e=0;e<E;e++){
            st = new StringTokenizer(br.readLine());
            edge_arr[e] = new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(edge_arr);

        solve();


    }

}

