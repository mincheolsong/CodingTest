import java.util.*;

class Solution {
    
    public int solution(String[][] clothes) {
        int answer;
        
        HashMap<String,Integer> map = new HashMap<>();
        
        for(String[] c : clothes){
            map.put(c[1],map.getOrDefault(c[1],0)+1);
        }
        answer = 1;
        
        for(Integer i : map.values()){
            answer *= (i+1);
        }
        answer-=1;
        
        
        return answer;
    }
}