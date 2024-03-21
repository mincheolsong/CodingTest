import java.util.*;

class Solution {
    
    public void print(char[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public boolean solve(char[][] ch){
        Set<String> set = new HashSet<>();
        
        for(int i=ch[0].length;i<=ch[ch.length-1].length;i++){ // 20
            for(int j=0;j<ch.length;j++){ // 1_000_000
                if(ch[j].length<i) continue;
                
                StringBuilder sb = new StringBuilder();
                for(int k=0;k<i;k++){ // 20
                    sb.append(ch[j][k]);
                }
                if(set.contains(sb.toString())){
                    return false; 
                }
                if(sb.toString().length()==ch[j].length){
                    set.add(sb.toString());
                }
            }
        }
        return true;
    }
    
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Arrays.sort(phone_book,(o1,o2)->(o1.length()-o2.length()));
        
        char[][] ch = new char[phone_book.length][];
        
        for(int i=0;i<phone_book.length;i++){
            ch[i] = phone_book[i].toCharArray();
        }
        
        answer = solve(ch);
        
        return answer;
    }
}