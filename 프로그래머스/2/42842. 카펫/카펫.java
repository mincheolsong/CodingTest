import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        
        int total = brown + yellow;
        
        for(int i=1;i<=yellow;i++){
            if(yellow%i==0){
                int a = yellow / i;
                int b = i;
                System.out.printf("a : %d, b : %d\n",a,b);
                if((a+2)*(b+2)==total) return new int[]{a+2,b+2};
            }    
        }
        return null;
    }
}