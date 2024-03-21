import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        Set<Integer> set = new HashSet<>();
        
        for(int num : nums){
            set.add(num);    
        }
        if(set.size() <= nums.length/2){
            answer = set.size();
        }else{
            answer = nums.length/2;
        }
        
        
        return answer;
    }
}