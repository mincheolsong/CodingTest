import java.util.*;

class Solution {
    
    long left = 1, right;
    long answer;
    
    void solve(int n, int[] times){
        
        while(left<right){
            long mid = left + (right-left)/2;
            
            // mid분 일때 모든 사람 심사할 수 있는지 확인
            long cnt = 0;
            for(int t : times){
                cnt += (mid/t);
            }
            
            if(n<=cnt){
                right = mid;                
            }else{
                left = mid+1;
            }
        }
        
        answer = left;
    }
    
    public long solution(int n, int[] times) {
        
        Arrays.sort(times);
        right = (long)times[0]*n+1;
        
        solve(n,times);
        
        
        return answer;
    }
}