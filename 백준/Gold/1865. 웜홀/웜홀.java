import java.io.*;
import java.util.*;

public class Main {

    // 플로이드워셜
    // A -> X -> A 가 - 인 경우가 있으면 YES 없으면 NO
    static final int INF = (int)1e9;
    static int N,M,W;
    static int[][] graph;

    static void check(){

        for(int i=1;i<N+1;i++){
            if(graph[i][i]<0){
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }
    static void floyd(){

        for(int k=1;k<N+1;k++){
            for(int i=1;i<N+1;i++){
                for(int j=1;j<N+1;j++){
                    if(graph[i][k]==INF || graph[k][j]==INF) continue;
                    /*if(i==2 && j==3){
                        System.out.println("k = " + k);
                        System.out.println("graph[i][j] : " + graph[i][j] + ", " +
                                "graph[2][k] : " + graph[i][k] + ", " + "graph[k][3] : " + graph[k][j] +
                                ", graph[i][k] + graph[k][j] : " + (graph[i][k] + graph[k][j])
                        );

                    }*/
                    graph[i][j] = Math.min(graph[i][j],graph[i][k] + graph[k][j]);
                }
            }
        }

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
            graph = new int[N+1][N+1];
            for(int i=1;i<N+1;i++){
                Arrays.fill(graph[i],INF);
                graph[i][i]=0;
            }


            for(int m=0;m<M;m++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph[from][to] = weight < graph[from][to] ? weight : graph[from][to];
                graph[to][from] = weight < graph[to][from] ? weight : graph[to][from];
            }

            for(int w=0;w<W;w++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph[from][to] = -weight < graph[from][to] ? -weight : graph[from][to];
            }

          /*  for(int i=0;i<N+1;i++){
                System.out.println(Arrays.toString(graph[i]));
            }
            System.out.println("=========");*/
            // 입력 끝
            floyd();

            /*for(int i=0;i<N+1;i++){
                System.out.println(Arrays.toString(graph[i]));
            }*/

            check();

        }


    }


}

