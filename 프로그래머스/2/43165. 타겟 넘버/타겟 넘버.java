import java.util.*;

class Solution {
    
    int answer;
    
    void solve(int[] numbers,int n,int sum,int target){
        if(n==numbers.length){
            if(sum==target){
                answer+=1;
            }
            
            return;
        }
        
        solve(numbers,n+1,sum+numbers[n],target);
        solve(numbers,n+1,sum-numbers[n],target);
        
    }
    
    
    public int solution(int[] numbers, int target) {
        
        answer = 0;
        
        solve(numbers,0,0,target);
        
        return answer;
    }
}