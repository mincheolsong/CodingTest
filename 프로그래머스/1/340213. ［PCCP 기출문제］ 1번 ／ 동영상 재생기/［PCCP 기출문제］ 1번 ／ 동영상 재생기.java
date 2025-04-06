import java.util.*;


class Solution {
    
    int current;
    int max_len;
    int opening_start, opening_end;
        
    int convert(String st){
        
        String[] tmp = st.split(":");
        int result = 0;
 
        result = Integer.parseInt(tmp[0])*100;
        result += Integer.parseInt(tmp[1]);
        
        return result;
    }
    
    void check_opening(){
        if(current >= opening_start && current <= opening_end){
            current = opening_end;
        }
    }
    
    void command(String command){
        
        int m = current / 100;
        int s = current % 100;
        
        if(command.equals("prev")){
            s -= 10;
        }else if(command.equals("next")){
            s += 10;
        }
        
        if(s < 0){
            m -= 1;
            s += 60;
        }else if(s >= 60){
            m += 1;
            s -= 60;
        }
        
        current = m*100+s;
        
        // 음수인지, video_len보다 커졌는지 확인
        check_border();
    }
    
    void check_border(){
        if(current < 0){
            current = 0;
        }else if(current > max_len){
            current = max_len;
        }
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands){
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        current = convert(pos);
        max_len = convert(video_len);
        opening_start = convert(op_start);
        opening_end = convert(op_end);
        
            
        for(int i=0;i<commands.length;i++){
            // 오프닝 체크
            check_opening();
            // 명령 수행
            command(commands[i]);
        }
        
        check_opening();
        
        if(current / 1000 == 0){
            sb.append("0");
        }
        sb.append(current/100).append(":");
        
        current = current % 100;
        
        if(current / 10 == 0){
            sb.append("0");
        }
        sb.append(current);
        
        answer = sb.toString();
        
        return answer;
    }
}