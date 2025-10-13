import java.util.*;

// HashMap<String, String> map; (id, 닉네임)

// Enter
// UserId님이 들어왔습니다. 문자열 result에 넣기
// 1. map에 없으면 map에 (id, 닉네임) 넣기
// 2. map에 있으면 닉네임 바꾸기

// Change
// map에서 닉네임 바꾸기

// Leave
// UserId님이 나갔습니다. 문자열 result에 넣기

// 최종적으로
// result 배열 돌면서, String.replace 로 userId를 nickname으로 바꾸기

class Solution {
    
    
    
    public String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();
        List<String> answer = new ArrayList<>();
        List<String> answerUserId = new ArrayList<>();
        
        for(int i=0;i<record.length;i++){
            String[] input = record[i].split(" ");
            String cmd = input[0];
            String userId = input[1];
            
            
            if(cmd.equals("Enter")){
                String nickname = input[2];
                answer.add("님이 들어왔습니다.");
                answerUserId.add(userId);
                map.put(userId,nickname);
            }else if(cmd.equals("Leave")){
                answer.add("님이 나갔습니다.");
                answerUserId.add(userId);
            }else if(cmd.equals("Change")){
                String nickname = input[2];
                map.put(userId,nickname);
            }
        }
        
        for(int i=0;i<answer.size();i++){
            String history = answer.get(i);
            answer.set(i,map.get(answerUserId.get(i)) + history);
        }
    
        return answer.toArray(new String[0]);
    }
}