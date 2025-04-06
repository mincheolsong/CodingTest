import java.util.*;

class Solution {
    
    Map<String,Integer> id_map;
    int[] cnt;
    List<Integer>[] report_list;
    List<Integer> ban;
    
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        id_map = new HashMap<>();
        cnt = new int[id_list.length];
        report_list = new List[id_list.length];
        ban = new ArrayList<>();
        
        for(int i=0;i<id_list.length;i++){
            report_list[i] = new ArrayList<>();    
        }
        
        for(int i=0;i<id_list.length;i++){
            id_map.put(id_list[i],i);
        }
        
        for(int i=0;i<report.length;i++){
            String[] tmp = report[i].split(" ");
            int from = id_map.get(tmp[0]);
            int to = id_map.get(tmp[1]);
            // System.out.println(from + ", "+ to);
            if(!report_list[from].contains(to)){
                report_list[from].add(to);
                cnt[to] += 1;
            }
            
            
            if(cnt[to]>=k && !ban.contains(to)){
                ban.add(to);
            }
        }
        
        for(int i=0;i<report_list.length;i++){
            for(int j=0;j<report_list[i].size();j++){
                if(ban.contains(report_list[i].get(j))){
                    answer[i] += 1;
                }
            }
        }
        
        
        return answer;
    }
}