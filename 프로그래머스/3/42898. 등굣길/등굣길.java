import java.util.*;

// dp(r,c) = dp(r-1,c) + dp(r,c-1)
class Solution {
    
    int dp(int m,int n,int[][] puddles){
        int[][] memo = new int[n][m];    
        
        for(int[] puddle : puddles){
            int pr = puddle[1]-1;
            int pc = puddle[0]-1;
            memo[pr][pc]=-1; // 물 웅덩이는 -1로 표시
        }
        if(m>=2 && memo[0][1]!=-1) memo[0][1]=1;
        if(n>=2 && memo[1][0]!=-1) memo[1][0]=1;
        
        for(int r=0;r<n;r++){
            for(int c=0;c<m;c++){
                if(r==0 && c==0) continue;
                if(r==0 && c==1) continue;
                if(r==1 && c==0) continue;
                if(memo[r][c]==-1) continue; // 물 웅덩이에 대해서는 dp를 해주지 않음
                
                if(r-1>=0 && memo[r-1][c]!=-1){ // 물 웅덩이가 아닌 영역에 대해서 dp (위쪽 방향)
                    memo[r][c] += (memo[r-1][c]%1_000_000_007);
                }
                if(c-1>=0 && memo[r][c-1]!=-1){ // 물 웅덩이가 아닌 영역에 대해서 dp (왼쪽 방향)
                    memo[r][c] += (memo[r][c-1]%1_000_000_007);
                }
            }
        }
        // for(int i=0;i<n;i++){
        //     System.out.println(Arrays.toString(memo[i]));
        // }
        return (memo[n-1][m-1]%1_000_000_007);
    }
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        answer = dp(m,n,puddles);
        
        return answer;
    }
}