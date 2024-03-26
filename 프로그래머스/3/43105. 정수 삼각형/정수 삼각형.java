import java.util.*;

class Solution {
    int[][] memo;
    
    void dp(int[][] triangle){
        for(int i=2;i<memo.length;i++){
            for(int j=1;j<i;j++){
                memo[i][j] = Math.max(memo[i-1][j-1],memo[i-1][j]) + triangle[i][j];
            }
        }
    }
    
    public int solution(int[][] triangle) {
        int answer = 0;
        memo = new int[triangle.length][];
        
        for(int i=0;i<triangle.length;i++){
            memo[i] = new int[triangle[i].length];
        }
        
        memo[0][0] = triangle[0][0];
        
        for(int i=1;i<memo.length;i++){
            memo[i][0] = memo[i-1][0] + triangle[i][0];
            memo[i][i] = memo[i-1][i-1] + triangle[i][i];
        }
        
        
        
        dp(triangle);
        // 마지막 행
        
        for(int j=0;j<memo.length;j++){
            answer = Math.max(answer,memo[memo.length-1][j]);
        }
        
        return answer;
    }
}