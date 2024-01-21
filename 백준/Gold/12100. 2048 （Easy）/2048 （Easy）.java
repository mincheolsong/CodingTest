import java.io.*;
import java.util.*;



public class Main {

    static int N;
    static int[][] board;
    static int ans;

    static void print(int[][] arr){
        for(int i=0;i<N;i++){
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println("==============");
    }
    static int[][] copy(int[][] arr){
        int[][] tmp;
        tmp = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                tmp[i][j] = arr[i][j];
            }
        }
        return tmp;
    }

    static int findArrMax(int[][] arr){
        int max = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                max = Math.max(max,arr[i][j]);
            }
        }
        return max;
    }

    static int[] calc(Deque<Integer>q){
        int[] result = new int[N];
        int idx=0;
        int prev = 0;

        while(!q.isEmpty()){
            int num = q.pollFirst();
            if(num==prev){
                result[idx++]=num*2;
                prev = 0;
            }else{
                if(prev!=0) {
                    result[idx++] = prev;
                }
                if(q.size()==0){
                    result[idx]=num;
                }
                prev = num;
            }
        }

        return result;
    }

    static void move(int d,int[][] arr){

        Deque<Integer> q;

        if(d==0){ // 왼쪽
            for(int r=0;r<N;r++){
                q = new ArrayDeque<>();

                for(int c=0;c<N;c++){
                    if(arr[r][c]!=0) {
                        q.offer(arr[r][c]);
                    }
                }

                int[] result = calc(q);
                int resultIdx = 0;

                for(int c=0;c<N;c++){
                    arr[r][c] = result[resultIdx++];
                }
            }
        }else if(d==1){ // 오른쪽
            for(int r=0;r<N;r++){
                q = new ArrayDeque<>();

                for(int c=N-1;c>=0;c--){
                    if(arr[r][c]!=0){
                        q.offer(arr[r][c]);
                    }
                }

                int[] result = calc(q);
                int resultIdx = 0;
                for(int c=N-1;c>=0;c--){
                    arr[r][c] = result[resultIdx++];
                }

            }

        }else if(d==2){ // 위쪽
            for(int c=0;c<N;c++) {
                q = new ArrayDeque<>();
                for (int r = 0; r < N; r++) {
                    if (arr[r][c] != 0) {
                        q.offer(arr[r][c]);
                    }
                }

                int[] result = calc(q);
                int resultIdx = 0;
                for (int r = 0; r < N; r++) {
                    arr[r][c] = result[resultIdx++];
                }
            }
        }else if(d==3){ // 아래쪽

            for(int c=0;c<N;c++){
                q = new ArrayDeque<>();

                for(int r=N-1;r>=0;r--){
                    if(arr[r][c]!=0){
                        q.offer(arr[r][c]);
                    }
                }
                int[] result = calc(q);
                int resultIdx = 0;
                for(int r=N-1;r>=0;r--){
                    arr[r][c] = result[resultIdx++];
                }
            }
        }

    }

    static void solve(int n, int[][] arr){

        if(n==5){
            ans = Math.max(ans,findArrMax(arr));
            return;
        }

        int[][] copiedArr;

//        System.out.println("n = " + n);
        for(int d=0;d<4;d++){

            copiedArr = copy(arr);

//            System.out.println("d = " + d);
//            System.out.println("이동 전 copiedArr ");
//            print(copiedArr);
            move(d,copiedArr);
//            System.out.println("이동 후 copiedArr ");
//            print(copiedArr);
            solve(n+1,copiedArr);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;

        solve(0,board);

        System.out.println(ans);


    }

}
