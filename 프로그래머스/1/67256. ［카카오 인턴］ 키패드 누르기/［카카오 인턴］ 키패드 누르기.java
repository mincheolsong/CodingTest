import java.util.*;

// 현재 손 위치 기억하기(2차원 배열 좌표)
// 거리 계산 함수
// 
class Solution {
    
    int[] numbers;
    String hand;
    String answer;
    
    int[][] pos = {
                    {3,1}
                   ,{0,0}
                   ,{0,1}
                   ,{0,2}
                   ,{1,0}
                   ,{1,1}
                   ,{1,2}
                   ,{2,0}
                   ,{2,1}
                   ,{2,2}
                  };
    Point leftHand, rightHand;
    
    int calcDist(int sr,int sc, int tr, int tc){            
        return Math.abs(sr-tr) + Math.abs(sc-tc);
    }
    
    String findHand(int num){
        // 현재 leftHand, rightHand 비교해서 가까운 곳 찾기, 거리 같으면 주 손
        int numR, numC;
        numR = pos[num][0];
        numC = pos[num][1];
        
        int leftDist = Math.abs(leftHand.r - numR) + Math.abs(leftHand.c - numC);
        int rightDist = Math.abs(rightHand.r - numR) + Math.abs(rightHand.c - numC);
        
        if(leftDist < rightDist) return "LEFT";
        if(leftDist > rightDist) return "RIGHT";
        if(hand.equals("right")) return "RIGHT";
        return "LEFT";
        
    }
    
    void solve(){
        
        StringBuilder sb = new StringBuilder();
        
        for(int num : numbers){
            if(num==1 || num==4 || num==7){
                sb.append("L");
                leftHand.setR(pos[num][0]);
                leftHand.setC(pos[num][1]);
            }else if(num==3 || num==6 || num==9){
                sb.append("R");
                rightHand.setR(pos[num][0]);
                rightHand.setC(pos[num][1]);
            }else{
                // 거리 계산해서 가까운 손 찾기
                String hand = findHand(num);
                if(hand.equals("LEFT")){
                    sb.append("L");
                    leftHand.setR(pos[num][0]);
                    leftHand.setC(pos[num][1]);
                }else {
                    sb.append("R");
                    rightHand.setR(pos[num][0]);
                    rightHand.setC(pos[num][1]);
                }
                         
            }      
        }
        answer = sb.toString();
    }
                         
    
    public String solution(int[] numbers, String hand) {
        this.answer = "";
        this.numbers = numbers;
        this.hand = hand;
        leftHand = new Point(3,0);
        rightHand = new Point(3,2);
        
        solve();
        
        return answer;
    }
}
class Point{
    int r,c;
    
    public Point(int r,int c){
        this.r = r;
        this.c = c;
    }
    
    void setR(int r){
        this.r = r;
    }
    
    void setC(int c){
        this.c = c;   
    }
}