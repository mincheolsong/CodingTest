import java.io.*;
import java.util.*;


// 9*9
// 백트래킹
// 행 방향 검사하는 함수
// 열 방향 검사하는 함수
// 3*3 정사각형 검사하는 함수

public class Main {

    static int[][] arr;
    static List<int[]> blank;



    static void print_answer(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean check(int mode,int row, int col, int number){

        if(mode==0){ // 행 방향
            for(int i=0;i<9;i++){
                if(arr[row][i]!=0 && arr[row][i]==number){
                    return false;
                }
            }
            return true;
        }

        if(mode==1){ // 열 방향
            for(int i=0;i<9;i++){
                if(arr[i][col]!=0 && arr[i][col]==number){
                    return false;
                }
            }
            return true;
        }

        if(mode==2){ // 정사각형
            int a = row / 3;
            int b = col / 3;

            // (a,b) 정사각형 영역에 있음
            for(int i=a*3;i<a*3+3;i++){
                for(int j=b*3;j<b*3+3;j++){
                    if(arr[i][j]!=0 && arr[i][j]==number){
                        return false;
                    }
                }
            }
            return true;
        }

        return false;
    }
    static boolean solve(int n){
        if(n==blank.size()){
            print_answer();
            return true;
        }

        // 행, 열, 정사각형 검사
        int[] p = blank.get(n);
        int r = p[0];
        int c = p[1];


        for(int j=1;j<10;j++){ // 1 ~ 9 중 넣을 수 있는 숫자 선택
            if(!check(0,r,c,j)) continue;
            if(!check(1,r,c,j)) continue;
            if(!check(2,r,c,j)) continue;
            arr[r][c] = j;
            if(solve(n+1)) return true;
            arr[r][c]=0;
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        arr = new int[9][9];
        blank = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) {
                    blank.add(new int[]{i, j});
                }
            }
        }

        solve(0);


    }

}
