import java.util.*;

class Solution {
    
    int answer;
    
    void solve(int[] priorities,int location){
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)->-(o1-o2));
        
        for(int i=0;i<priorities.length;i++){
            pq.offer(priorities[i]);
        }
        
        while(!pq.isEmpty()){
            
            for(int i=0;i<priorities.length;i++){
                
                if(priorities[i]==pq.peek()){
                    answer++;
                    pq.poll();
                    
                    if(i==location) return;
                }
            }
        }
        
    }
    
    
    public int solution(int[] priorities, int location) {
        answer = 0;
            
        solve(priorities,location);
        
        
        
        return answer;
    }
}