import java.util.*;

class Solution {
    
    String convert(int n, int num){
        StringBuilder sb = new StringBuilder();
        
        while(num!=0){
            int a = num % n;
            if(a==10){
                sb.append("A");
            }else if(a==11){
                sb.append("B");
            }else if(a==12){
                sb.append("C");
            }else if(a==13){
                sb.append("D");
            }else if(a==14){
                sb.append("E");
            }else if(a==15){
                sb.append("F");
            }else{
                sb.append(num%n);
            }
            num=num/n;
        }
        
        return sb.reverse().toString();
    }
    
    public String solution(int n, int t, int m, int p) {
        
        StringBuilder result = new StringBuilder();
        result.append("0");
        
        for(int i=1;i<=100000;i++){
            result.append(convert(n,i));
        }
        
        char[] arr = result.toString().toCharArray();
        StringBuilder ans = new StringBuilder();
        // System.out.println(Arrays.toString(arr));
        for(int i=0;i<t;i++){
            ans.append(arr[m*i+p-1]);
        }    
        
            
        return ans.toString();
    }
}