import java.util.*;

class Solution {
    
    public String[] solution(String[] players, String[] callings) {
        
        String[] answer = new String[players.length];
        Map<String,Integer> rank = new HashMap<>();
        
        for(int i=0;i<players.length;i++){
            answer[i] = players[i];
            rank.put(players[i],i);
        }
        
        
        for(int i=0;i<callings.length;i++){
            
            int cur_rank = rank.get(callings[i]);
            
            rank.put(answer[cur_rank-1],cur_rank);
            rank.put(callings[i],cur_rank-1);
            
            String tmp = answer[cur_rank-1];
            answer[cur_rank-1] = answer[cur_rank];
            answer[cur_rank] = tmp;
        }
        
        
        
        
        return answer;
    }
}