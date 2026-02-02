import java.util.*;

// 1번, 2번, 3번, 4번 지표 별 점수를 측정해야 함
// 입력받은 선택지
class Solution {
    
    Map<String, Integer> mbti;
    String[] survey;
    int[] choices;
    
    private String solve(){
        
        for(int i=0;i<survey.length;i++){
            String not_agree = String.valueOf(survey[i].charAt(0));
            String agree = String.valueOf(survey[i].charAt(1));
            
            if(choices[i] < 4){
                mbti.put(not_agree, mbti.get(not_agree)+4-choices[i]);
            }else if(choices[i] > 4){
                mbti.put(agree, mbti.get(agree)+choices[i]-4);
            }
        }

        // for(String key : mbti.keySet()){
        //     System.out.println("key : " + key + ", value : " + mbti.get(key));
        // }
        
        StringBuilder sb = new StringBuilder();
        String[][] jipyo = {{"R","T"},{"C","F"},{"J","M"},{"A","N"}};
        
        for(int i=0;i<4;i++){
            Arrays.sort(jipyo[i]);
            int left = mbti.get(jipyo[i][0]);
            int right= mbti.get(jipyo[i][1]);
            if(left>=right) sb.append(jipyo[i][0]);
            else if(left < right) sb.append(jipyo[i][1]);
            
        }
        
        
        
        return sb.toString();
    }
    
    public String solution(String[] survey, int[] choices) {
        String ans;
        this.survey = survey;
        this.choices = choices;
        
        mbti = new HashMap(){{
            put("R",0);
            put("T",0);
            put("C",0);
            put("F",0);
            put("J",0);
            put("M",0);
            put("A",0);
            put("N",0);
        }};      
        
        ans = solve();
        
        return ans;
    }
}