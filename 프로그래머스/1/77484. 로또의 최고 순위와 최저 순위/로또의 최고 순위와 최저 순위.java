import java.util.*;

// 일치하는 수의 갯수 파악 = x
// 0의 갯수 = y
// x가 6이면 return new int[]{1,1};
// 최고 갯수 : x+y개
// 최저 갯수 : x
class Solution {
    
    int[] lottos;
    int[] win_nums;
    int[] grade = {6,6,5,4,3,2,1};
        
    int[] solve(){
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<win_nums.length;i++){
            set.add(win_nums[i]);
        }
        
        int equal_cnt = 0, zero_cnt = 0;
        
        for(int i=0;i<lottos.length;i++){
            if(lottos[i]==0) zero_cnt += 1;
            else if(set.contains(lottos[i])) equal_cnt += 1;
        }
        
        if(equal_cnt == 6) return new int[]{1,1};
        
        int high = equal_cnt + zero_cnt;
        int low = equal_cnt;
        
        return new int[]{grade[high],grade[low]};
    }
    
    public int[] solution(int[] lottos, int[] win_nums) {
        this.lottos = lottos;
        this.win_nums = win_nums;
        
        return solve();
        
        
    }
}