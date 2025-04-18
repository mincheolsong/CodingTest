import java.util.*;

// 투 포인터
class Solution {
    Map<String,Integer> map;
    
    boolean check(String[] want, int[] number){
        for(int i=0;i<want.length;i++){
            if(number[i] > map.getOrDefault(want[i],-1)) return false;
        }        
        return true;
    }
    
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        map = new HashMap<>();
        
        for(int i=0;i<10;i++){
            map.put(discount[i],map.getOrDefault(discount[i],0)+1);
        }
        
        for(int i=10;i<discount.length;i++){
            if(check(want,number)) answer += 1;
            map.put(discount[i],map.getOrDefault(discount[i],0)+1);
            map.put(discount[i-10], map.get(discount[i-10])-1);
        }
        if(check(want,number)) answer += 1;
        return answer;
    }
}