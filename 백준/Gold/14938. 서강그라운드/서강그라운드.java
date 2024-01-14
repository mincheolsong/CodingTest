import java.io.*;
import java.util.*;


public class Main {
    // 모든 노드 사이의 최단거리를 구함 (플로이드워셜)
    // 모든 정점들을 보며 최대 갯수를 갱신해보기

    static final int INF = (int)1e9;
    static int n,m,r;
    static int[][] graph;
    static int[] item;

    static void print(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    static void floyd(){

        for(int k=1;k<n+1;k++){
            for(int i=1;i<n+1;i++){
                for(int j=1;j<n+1;j++){
                    if(graph[i][j] > graph[i][k] + graph[k][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        graph = new int[n+1][n+1];
        item = new int[n+1];

        for(int i=1;i<n+1;i++){
            Arrays.fill(graph[i],INF);
            graph[i][i]=0;
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<n+1;i++){
            item[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<r;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[from][to]=w;
            graph[to][from]=w;
        }

        floyd();

        int ans = 0;

        for(int i=1;i<n+1;i++){
            int value = item[i];
            for(int j=1;j<n+1;j++){
                if(i==j) continue;
                if(graph[i][j] <= m){
                    value+=item[j];
                }
            }
            ans = value > ans ? value : ans;
        }
        System.out.println(ans);



    }



}

