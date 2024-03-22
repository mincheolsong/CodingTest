import java.util.*;

// edge의 최대 갯수는 99개
// 어디를 자르든 2개로 나눠짐
// 나눠진 트리들의 갯수를 파악해야 함


class Solution {
    List<Integer>[] graph;
    boolean[] visited;
    final int INF = (int)1e9;
    
    int count(int start, int delete){
        Deque<Integer> q = new ArrayDeque<>();
        int result = 1;
        q.offer(start);
        visited[start]=true;
        
        while(!q.isEmpty()){
            int cur = q.pollFirst();
            
            for(int node : graph[cur]){
                if(node==delete) continue;
                if(visited[node]) continue;
                visited[node]=true;
                result+=1;
                q.offer(node);
            }
        }
        return result;
    } 
    
    public int solution(int n, int[][] wires) {
        int answer = INF;
        
        graph = new List[n+1];
        
        for(int i=1;i<n+1;i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0;i<n-1;i++){
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        
        for(int i=0;i<n-1;i++){
            int[] wire = wires[i];
            int v1 = wire[0];
            int v2 = wire[1];
            // v1 ~ v2 간선 자르기
            // v1쪽 그래프 갯수 카운트
            visited = new boolean[n+1];
            int v1_count = count(v1,v2);
            int v2_count = n-v1_count;
            
            answer = Math.min(answer,Math.abs(v1_count-v2_count));
            
        }
        return answer;
    }
}