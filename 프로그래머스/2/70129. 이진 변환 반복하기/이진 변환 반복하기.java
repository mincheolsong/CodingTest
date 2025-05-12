import java.util.*;


// 제거 할 0의 갯수를 어떻게 count해야 할까? 그냥 O(N)

class Solution {
    // 이진법으로 바꾸는 함수.
    String convert(int n,int a){
        StringBuilder sb = new StringBuilder();
        
        while(n!=0){
            sb.append(n%a);
            n /= a;
        }
        
        sb.reverse();
        return sb.toString();
    }
    
    public int[] solution(String s) {
        int[] answer;
        int conv_cnt = 0, zero_cnt = 0;
        
        while(!s.equals("1")){
            String conv_s = s.replace("0", "");
            
            conv_cnt += 1;
            zero_cnt += ( s.length() - conv_s.length());
            s = convert(conv_s.length(),2);
        }
        
        answer = new int[]{conv_cnt, zero_cnt};
        
        
        return answer;
    }
}