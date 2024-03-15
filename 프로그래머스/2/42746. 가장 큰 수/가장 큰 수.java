import java.util.*;
import java.io.*;

// 제일 앞자리 가 큰 순서대로 리턴
// 앞자리가 같으면 두 번째 자리 비교 (두 번째 자리가 없으면 첫 번째 자리)
class Solution {
    
    public String solution(int[] numbers) {
        String[] arr = new String[numbers.length];
        
        for(int i=0;i<numbers.length;i++){
            arr[i] = numbers[i]+"";
        }
        
        Arrays.sort(arr,(o1,o2)->{
            String a = o1+o2;
            String b = o2+o1;
            
            return -(Integer.parseInt(a)-Integer.parseInt(b));
            
        });
           
        if(arr[0].equals("0")){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<arr.length;i++){
            sb.append(arr[i]);
        }
            
        String answer = sb.toString();
        
        return answer;
    }
}