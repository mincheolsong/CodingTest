import java.util.*;
import java.io.*;

class Solution {
    
    public char[] sorted_skill;
    
    public boolean isIn(char st){
        // 이분탐색
//         int start = 0, end = sorted_skill.length;
        
//         while(start < end){
//             int mid = (start+end)/2;
            
//             if(sorted_skill[mid]==st) return true;
            
//             if(sorted_skill[mid]<=st){
//                 end = mid;
//             }else{
//                 start=mid+1;
//             }
//         }
        int result = Arrays.binarySearch(sorted_skill,st);
        if(result<0) return false;
        return true;
        
    }
    
    public boolean check(char[] skill,char[] input){
        int skill_idx = 0;
        
        for(int i=0;i<input.length;i++){
            if(isIn(input[i])){
                if(skill[skill_idx]==input[i]){
                    skill_idx+=1;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
    
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        char[] skill_arr = skill.toCharArray();
        
        // skill_arr 복사 배열 생성 (오름차순 정렬, 이진탐색에 활용)
        sorted_skill = new char[skill_arr.length];
        for(int i=0;i<skill_arr.length;i++){
            sorted_skill[i] = skill_arr[i];
        }
        
        Arrays.sort(sorted_skill);
        
        for(String s : skill_trees){
            char[] skill_trees_arr = s.toCharArray();
            
            if(check(skill_arr,skill_trees_arr)) answer += 1;
        }
        
        return answer;
    }
}