import java.util.*;

class Solution {
    int answer;
    List<Integer>[] graph;
    int[] distance;
    
    void bfs(int node){
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{node,0});
        distance[node]=0;
        
        while(!q.isEmpty()){
            int[] cur = q.pollFirst();
            int current_node = cur[0];
            int current_distance = cur[1];
            
            for(int next_node : graph[current_node]){
                if(distance[next_node]!=-1) continue;
                distance[next_node] = current_distance + 1;
                q.offer(new int[]{next_node, current_distance + 1});
            }
        }
    }
    
    public int solution(int n, int[][] edge) {
        answer = 0;
        graph = new List[n+1];
        distance = new int[n+1];
        Arrays.fill(distance,-1);
         
        for(int i=1;i<n+1;i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] g : edge){
            int a = g[0];
            int b = g[1];
            graph[a].add(b);
            graph[b].add(a);
        }
        
        bfs(1);
        
        int max = Arrays.stream(distance).max().getAsInt();
        
        for(int i=1;i<n+1;i++){
            if(distance[i]==max) answer+=1;
        }
        
        return answer;
    }
}