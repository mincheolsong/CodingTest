import java.util.*;


class Solution {
    
    
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        
        int left = 0, right = people.length-1;
        
        while(left < right){
            int sum = people[left] + people[right];
            
            if(sum <= limit){
                left+=1;
                right-=1;
            }else{
                right-=1;
            }
            answer+=1;
        }
        if(left==right){
            answer+=1;
        }
        
        return answer;
    }
}