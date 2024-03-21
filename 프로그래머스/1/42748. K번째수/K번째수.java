import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int idx=0;idx<commands.length;idx++){
            int i = commands[idx][0]; // i-1부터 j-1까지
            int j = commands[idx][1];
            int k = commands[idx][2];
            
            int[] tmp = Arrays.copyOfRange(array,i-1,j);
            Arrays.sort(tmp);
            answer[idx] = tmp[k-1];
        }
        
        
        return answer;
    }
}