import java.util.*;

// A도둑의 흔적 최소
// dp, 메모리제이션
// dp(i)(j) = a흔적의 최솟값 ( i : 훔친 물건의 갯수, j : b흔적의 갯수 )
// i : 0 ~ info.length
// j : 0 ~ m-1
// dp(i)(j) = Math.min{dp(i+1,}
class Solution {
    final int INF = (int)1e9;
    int[][] memo;
    
    public int solution(int[][] info, int n, int m) {
        int answer = INF;
        memo = new int[info.length+1][m];
        
        for(int i=0;i<info.length+1;i++){
            Arrays.fill(memo[i],INF);
        }
        
        memo[0][0] = 0;
            
        for(int i=1;i<info.length+1;i++){
            int a = info[i-1][0];
            int b = info[i-1][1];
            
            for(int j=0;j<m;j++){
                // a선택
                memo[i][j] = Math.min(memo[i][j],memo[i-1][j]+a);
                //b선택
                if(j+b < m){
                    memo[i][j+b] = Math.min(memo[i][j+b],memo[i-1][j]);
                }
            }
        }
        
        for(int i=0;i<m;i++){
            if(memo[info.length][i] >= n) continue;
            answer = Math.min(answer,memo[info.length][i]);
        }
        
        return answer==INF?-1:answer;
    }
}