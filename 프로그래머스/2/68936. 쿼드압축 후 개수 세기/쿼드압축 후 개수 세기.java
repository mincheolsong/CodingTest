import java.util.*;

// 최종 0과 1의 개수
// 계속 재귀적으로 타고 들어가기?
// (가로 x 세로) 길이가 절반씩 줄어듦
// 정사각형은 좌측상단 좌표, 한 변의 길이로 정의할 수 있음

class Solution {
    int zero,one;
    int[][] arr;
    
    boolean check(int r,int c,int l){
        
        int val = arr[r][c];
        
        for(int i=r;i<r+l;i++){
            for(int j=c;j<c+l;j++){
                if(arr[i][j]!=val) return false;
            }
        }
        
        return true;
    }
    
    void divide(int r,int c,int l){ // (r,c) 에서 l인 정사각형 범위 숫자 일치여부 확인, 병합, 재귀
        
        if(l==1 || check(r,c,l)){ 
            if(arr[r][c]==0) zero += 1;
            if(arr[r][c]==1) one += 1;
            return;
        }
        
        divide(r,c,l/2);
        divide(r,c+l/2,l/2);
        divide(r+l/2,c,l/2);
        divide(r+l/2,c+l/2,l/2);
    }
    
    public int[] solution(int[][] arr) {
        int[] answer;
        zero  = 0;
        one = 0;
        this.arr = arr;
        
        divide(0,0,arr.length);
        
        answer = new int[]{zero, one};
        
        return answer;
    }
}