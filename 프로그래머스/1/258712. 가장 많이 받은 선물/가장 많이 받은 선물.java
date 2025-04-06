import java.util.*;

class Solution {
    Map<String,Integer> friend_map;
    int[][] history;
    int[] value;
    int[] receive;
        
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        friend_map = new HashMap<>();
        history = new int[friends.length][friends.length];
        value = new int[friends.length];
        receive = new int[friends.length];
        
        for(int i=0;i<friends.length;i++){
            friend_map.put(friends[i],i);
        }
        
        for(int i=0;i<gifts.length;i++){
            String[] from_to = gifts[i].split(" ");
            int from = friend_map.get(from_to[0]);
            int to = friend_map.get(from_to[1]);
            history[from][to] += 1;
            value[from] += 1;
            value[to] -= 1;
        }
        
//         for(int i=0;i<history.length;i++){
//             System.out.println(Arrays.toString(history[i]));
//         }
//         System.out.println("========");
        
//         System.out.println(Arrays.toString(value));
        
        for(int i=0;i<friends.length;i++){
            for(int j=i+1;j<friends.length;j++){
                if(i==j) continue;
                
                int i_give = history[i][j];
                int j_give = history[j][i];
                if(i_give>j_give){ // i가 j로부터 선물 받는다.
                    receive[i] += 1;
                }else if(i_give<j_give){ // j가 i로부터 선물 받는다.
                    receive[j] += 1;
                }else{ // 선물지수 비교
                    int i_value = value[i];
                    int j_value = value[j];
                    if(i_value > j_value){
                        receive[i] += 1;
                    }else if(i_value < j_value){
                        receive[j] += 1;
                    }
                }
            }
        }
        
        answer = Arrays.stream(receive).max().getAsInt();
        
        
        return answer;
    }
}