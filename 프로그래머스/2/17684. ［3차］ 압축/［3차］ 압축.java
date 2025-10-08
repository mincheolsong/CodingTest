import java.util.*;

// 투포인터 + hashmap
// 투포인터로 사전에 일치하는 가장 긴 문자열 찾기
// 있으면 -> start는 end위치로
// 없으면 -> start는 그냥 ++

class Solution {
    Map<String,Integer> map;
    String[] word;
    List<Integer> ans;
    int idx;
    
    void solve(){
        
        for(int start=0;start<word.length;start++){
            StringBuilder sb = new StringBuilder();
            // System.out.printf("start : %d , ", start);
            
            sb.append(word[start]);
            
            int end = start + 1;
            
            while(end < word.length && map.containsKey(sb.toString())){
                sb.append(word[end]);
                end += 1;
            }
            
//             System.out.printf("end : %d \n",end);
            
//             System.out.printf("sb.toString : %s\n",sb.toString());
            
            // start = end - 2;
            
            if(map.containsKey(sb.toString())){
                ans.add(map.get(sb.toString()));
                break;
            }else{
                map.put(sb.toString(),idx++); // 새로운 단어 
                ans.add(map.get(sb.deleteCharAt(sb.length()-1).toString()));
                start = end - 2;
            }
          
        }
    }
    
    public int[] solution(String msg) {
        map = new HashMap<>();
        ans = new ArrayList<>();
        idx = 27;
        for(char ch='A'; ch <= 'Z'; ch++){
            map.put(String.valueOf(ch),ch-'A'+1);
        }
        
        char[] msgArray = msg.toCharArray();
        word = new String[msgArray.length];
        for(int i=0;i<msgArray.length;i++){
            word[i] = String.valueOf(msgArray[i]);
        }
        
        solve();
          
        // System.out.println(ans.toString());
        int[] result = new int[ans.size()];
        for(int i=0;i<ans.size();i++){
            result[i] = ans.get(i);
        }
        return result;
    }
}