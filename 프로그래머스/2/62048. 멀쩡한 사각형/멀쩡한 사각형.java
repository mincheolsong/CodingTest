import java.util.*;


class Solution {
    
    int gcd(int a,int b){ // a > b
        
        if(b==0) return a;
        
        if(a < b){
            int tmp = a;
            a = b;
            b = tmp;
        }
        
        return gcd(b, a%b);
        
    }
    
    public long solution(int w, int h) {
        long answer = 1;
        
        int moreLonger = h;
        if(w > h){ // 항상 h가 더 길도록 설정
            int tmp = w;
            w = h;
            h = tmp;
        }
        
        int gcd = gcd(w,h);
        
        int ww = w / gcd;
        int hh = h / gcd;
        
        answer = (long)w * h - ((long)(ww + hh - 1) * (long)gcd);
        
      
        
        return answer;
    }
}