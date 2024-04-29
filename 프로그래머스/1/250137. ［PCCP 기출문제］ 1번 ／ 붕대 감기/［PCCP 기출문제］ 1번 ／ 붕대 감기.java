import java.util.*;

class Solution {
    
    int answer, cur_health;
    
    void solve(int[] bandage, int health, int[][] attacks){
        int time = 1;
        int success_cnt = 0;
        int attacks_idx = 0;
        
        while(true){
            
            if(time==attacks[attacks_idx][0]){ // 공격받는 시각이면 체력 감소시키고, 연속 성공횟수 초기화
                cur_health -= attacks[attacks_idx][1];
                success_cnt = 0;
                
                attacks_idx+=1;
                
                if(cur_health <= 0){ // 현재 체력이 0이하가 되면 함수 종료
                    answer = -1;
                    return;
                }
                
                if(attacks_idx==attacks.length){ // 마지막 공격이었으면 함수 종료
                    answer = cur_health;
                    return;
                }
                
            }else{ // 공격 받는 시각이 아니면 체력 회복시키고, 연속 성공횟수 증가시키기.
                success_cnt += 1;
                cur_health += bandage[1];
                if(success_cnt == bandage[0]){
                    cur_health += bandage[2];
                    success_cnt = 0;
                }
                
                if(cur_health>health){
                    cur_health = health;
                }
            }
            
            time+=1;
        }
        
        
    }
    
    public int solution(int[] bandage, int health, int[][] attacks) {
        answer = 0;
                
        cur_health = health;
        
        solve(bandage,health,attacks);
        
        return answer;
    }
}