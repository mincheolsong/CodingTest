import java.util.*;

// N*N을 배열 말고, Stack으로

class Solution {
    
    int N;
    Deque<Integer> stack;
    int answer;
    
    void checkStack(){
        
        if(stack.size() < 2) return;
        
        int topNum = stack.pop();
        if(stack.peek()==topNum){
            answer += 2;

            stack.pop();
            
        }else{
            stack.push(topNum);
        }
    }
    
    void solve(int[][] board, int[] moves){
        
        for(int i=0;i<moves.length;i++){
            int n = moves[i]-1;
            for(int j=0;j<N;j++){
                if(board[j][n]!=0){
                    stack.push(board[j][n]);
                    board[j][n]=0;
                    checkStack();
                    break;
                }
            }
        }
        
    }

    public int solution(int[][] board, int[] moves) {
        answer = 0;
        N = board.length;
        stack = new ArrayDeque<>();
        
        solve(board,moves);
        
        return answer;
    }
}