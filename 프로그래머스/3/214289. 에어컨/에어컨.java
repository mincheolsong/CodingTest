import java.util.*;

// dfs + dp?
// dp(i) = min{ a + dp(i가 쾌적 온도와 가까워 지는 방향), b + dp(i), dp(i가 외부온도와 가까워 지는 방향)

class Solution {
    final int INF = (int)1e9;
    int answer;
    boolean[][] visited; // 인덱스는 온도를 나타내야 함 0(-10) ~ 50(40)
    int[][] memo;
    int temperature,t1,t2,a,b;
    int[] onboard;
    int flag;
    
    int solve(int sec, int temp){ // sec초에서 현재 temp온도 일 때 최소 소비전력을 리턴
        
        if(onboard[sec]==1){
            if(temp < t1 || temp > t2){ // 쾌적한 실내가 안되는 경우임
                return INF;
            }
        }
        
        if(sec==onboard.length-1){ // 끝까지 다 도달한 경우
            return 0;
        }
        
        if(visited[sec][temp]){
            return memo[sec][temp];
        }
        
        visited[sec][temp] = true;
        int x = INF; // 실내온도를 조절하는 경우
        int y = INF; // 살내온도를 유지하는 경우
        int z = INF; // 에어컨을 끄는 경우
        
        if(temp+flag >= 0 && temp+flag<=50){
            x = a + solve(sec+1,temp+flag);
        }
        
        if(temp>=0 && temp<=50)
            y = b + solve(sec+1, temp);
        
        if( temp+1 <= 50 && temp < temperature){
            z = solve(sec+1, temp+1);
        }else if(temp-1 >= 0 && temp > temperature){
            z = solve(sec+1, temp-1);
        }else if(temp >=0 && temp<= 50 && temp == temperature){
            z = solve(sec+1, temp);
        }
        
        int min;
        min = Math.min(x,y);
        min = Math.min(min,z);
        
        memo[sec][temp] = min;
        
        return memo[sec][temp];
    }
    
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        answer = 0;
        this.temperature = temperature + 10;
        this.t1 = t1 + 10;
        this.t2 = t2 + 10;
        this.a = a;
        this.b = b;
        this.onboard = onboard;
        visited = new boolean[1000][50+1];
        memo = new int[1000][50+1];
        
        for(int i=0;i<1000;i++){
            Arrays.fill(memo[i],INF);
        }
        
        if(temperature > t2){
            flag = -1;
        }else{
            flag = 1;
        }
        answer = solve(0,this.temperature);
        
        return answer;
    }
}