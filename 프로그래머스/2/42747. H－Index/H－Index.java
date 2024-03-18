import java.util.*;
import java.io.*;

// [0,5,6,7,8] -> [4]
// 8 7 6 5 0


class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Integer[] c = new Integer[citations.length];
        for(int i=0;i<c.length;i++){
            c[i] = citations[i];
        }
            
        Arrays.sort(c,(o1,o2)-> -(o1-o2));
        
        int h = c[0];
        int idx = 0;
        int flag = 0;
        // 11 10
        while(h>=0){
            
            if(idx<c.length && c[idx]==h){
                if(idx+1>=h)  break;
            }else{
                if(idx>=h) break;
            } 
            
            while(flag==0 && c[idx]==h){
                idx+=1;
                if(idx==c.length){
                    flag = 1;
                }
            }
            
            h-=1;
        }
        
        answer = h;
        
        return answer;
    }
}