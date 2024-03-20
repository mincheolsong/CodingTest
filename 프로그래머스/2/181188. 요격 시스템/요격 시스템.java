// 가장 많이 겹친 위치에서 요격을 해야 함
// 배열을 사용해서 겹친 갯수를 구현하면, 시간복잡도 초과 100,000,000*500,000
// 요격미사일이 스치고 간 폭격미사일들의 갯수가 n개이면 됨
import java.util.*;
import java.io.*;

class Solution {
    
    public void print(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets,(o1,o2)->(o1[0]-o2[0]));
        int pre_s,pre_e;
        pre_s = targets[0][0];
        pre_e = targets[0][1];
        answer = 1;
        for(int i=1;i<targets.length;i++){
            int[] cur = targets[i];
            
            if(cur[0]<pre_e){
                pre_s = Math.max(pre_s,cur[0]);
                pre_e = Math.min(pre_e,cur[1]);
            }else{
                pre_s = cur[0];
                pre_e = cur[1];
                answer++;
            }
            
        }
        
        
        
        return answer;
    }
}