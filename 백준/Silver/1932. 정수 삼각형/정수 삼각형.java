import java.util.*;
import java.io.*;


public class Main {


    static int n;
    static List<Integer>[] graph;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n];
        memo = new int[n][n];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            graph[i]= new ArrayList<>();
            while(st.hasMoreTokens()){
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        memo[0][0]=graph[0].get(0);

        // 초기값
        for(int i=1;i<n;i++){
            memo[i][0] = memo[i-1][0] + graph[i].get(0);
            memo[i][i] = memo[i-1][i-1] + graph[i].get(graph[i].size()-1);
        }

        for(int i=2;i<n;i++){
            for(int j=1;j<i;j++){
                memo[i][j] = Math.max(memo[i-1][j-1],memo[i-1][j]) + graph[i].get(j);
            }
        }


        System.out.println(Arrays.stream(memo[n-1]).max().getAsInt());




    }

}