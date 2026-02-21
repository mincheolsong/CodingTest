import java.util.*;

// 중위표기법 연산방법
// 후위표기법으로 바꾸고 나서 연산을 진행
// 100 200 300 500 20
//  -   *   -   +
class Solution {
    
    char[] pr; // 인덱스 > 우선순위
    char[] operators;
    boolean[] chk;
    String expression;
    List<String> exprList;
    long answer;
    
    void calc(){
        
        List<String> exprListCopy = new ArrayList<>(exprList);
        
        for(int i=0;i<pr.length;i++){
            String operator = String.valueOf(pr[i]);
            
            for(int j=1;j<exprListCopy.size()-1;j++){
                if(exprListCopy.get(j).equals(operator)){
                    long numLeft = Long.parseLong(exprListCopy.get(j-1));
                    long numRight = Long.parseLong(exprListCopy.get(j+1));
                    
                    long result = 0l;
                    
                    if(operator.equals("+")){
                        result = numLeft + numRight;
                    }else if(operator.equals("-")){
                        result = numLeft - numRight;
                    }else if(operator.equals("*")){
                        result = numLeft * numRight;
                    }
                    
                    exprListCopy.remove(j-1);
                    exprListCopy.remove(j-1);
                    exprListCopy.remove(j-1);
                    
                    exprListCopy.add(j-1,String.valueOf(result));
                    j-=1;
                }
            }
        }
        answer = Math.max(answer,Math.abs(Long.parseLong(exprListCopy.get(0))));
        
        
        
    }
    
    void solve(int n){
    
        if(n==3){ // 연산자들 우선순위 다 만들어지면, 계산하기
            calc();
            return;
        }
        
        for(int i=0;i<3;i++){
            if(chk[i]) continue;
            chk[i] = true;
            pr[n] = operators[i];
            solve(n+1);
            chk[i] = false;
        }
        
    }
    
    public long solution(String expression) {
        answer = 0l;
        pr = new char[3];
        operators = new char[]{'+','-','*'};
        chk = new boolean[3];
        this.expression = expression;
        
        char[] exArr = expression.toCharArray();
        StringBuilder sb = new StringBuilder();
        exprList = new ArrayList<>();
        
        // expression 을 리스트로 만들기
        for(int i=0;i<exArr.length;i++){
            char ch = exArr[i];
            if(ch == '+' || ch == '-' || ch == '*'){
                if(sb.length() > 0){
                    exprList.add(sb.toString());
                    sb.setLength(0);
                }
                exprList.add(String.valueOf(ch));
            }else{
                sb.append(String.valueOf(ch));
            }
        }
        if(sb.length()>0) exprList.add(sb.toString());
        
        solve(0);
        
        return answer;
    }
}