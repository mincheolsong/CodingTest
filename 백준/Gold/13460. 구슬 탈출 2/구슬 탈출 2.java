import java.io.*;
import java.util.*;


// 구슬(한꺼번에) 기울이기
// 이동할 때 횟수 카운트하기
// 같이 구멍에 빠지는 경우 처리
class Marble{
    private int redRow,redCol,blueRow,blueCol;

    public Marble(){}

    public Marble(int redRow, int redCol, int blueRow, int blueCol){
        this.redRow = redRow;
        this.redCol = redCol;
        this.blueRow = blueRow;
        this.blueCol = blueCol;
    }

    public int getRedRow(){
        return this.redRow;
    }
    public int getRedCol(){
        return this.redCol;
    }
    public int getBlueRow(){
        return this.blueRow;
    }
    public int getBlueCol(){
        return this.blueCol;
    }
    public void setRedRow(int row){
     this.redRow = row;
    }
    public void setRedCol(int col){
        this.redCol = col;
    }
    public void setBlueRow(int row){
        this.blueRow = row;
    }
    public void setBlueCol(int col){
        this.blueCol = col;
    }

}

public class Main {

    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int N,M;
    static String[][] arr;
    static Marble marble;
    static boolean[][][][] visited;

    static void solve(){
        Deque<Marble> q = new ArrayDeque();
        q.offer(marble);
        visited[marble.getRedRow()][marble.getRedCol()][marble.getBlueRow()][marble.getBlueCol()] = true;
        int count = 0;

        while(!q.isEmpty()){


            int size = q.size();

            for(int i=0;i<size;i++){
                Marble cur = q.pollFirst();

                if(arr[cur.getRedRow()][cur.getRedCol()].equals("O")){
                    System.out.println(count);
                    return;
                }

                int curRedRow = cur.getRedRow();
                int curRedCol = cur.getRedCol();
                int curBlueRow = cur.getBlueRow();
                int curBlueCol = cur.getBlueCol();

                for(int d=0;d<4;d++){
                    int nextRedRow = curRedRow + dr[d];
                    int nextRedCol = curRedCol + dc[d];
                    int nextBlueRow = curBlueRow + dr[d];
                    int nextBlueCol = curBlueCol + dc[d];

                    while(true){
                        if(arr[nextRedRow][nextRedCol].equals("#")){
                            nextRedRow -= dr[d];
                            nextRedCol -= dc[d];
                            break;
                        }

                        if(arr[nextRedRow][nextRedCol].equals("O")){
                            break;
                        }
                        nextRedRow += dr[d];
                        nextRedCol += dc[d];
                    }

                    while(true){
                        if(arr[nextBlueRow][nextBlueCol].equals("#")){
                            nextBlueRow -= dr[d];
                            nextBlueCol -= dc[d];
                            break;
                        }
                        if(arr[nextBlueRow][nextBlueCol].equals("O")){
                            break;
                        }
                        nextBlueRow += dr[d];
                        nextBlueCol += dc[d];
                    }

                    if(arr[nextBlueRow][nextBlueCol].equals("O")){
                        continue;
                    }

                    if(nextRedRow==nextBlueRow && nextRedCol==nextBlueCol){
                        int redMoved = Math.abs(nextRedRow-curRedRow) + Math.abs(nextRedCol-curRedCol);
                        int blueMoved = Math.abs(nextBlueRow-curBlueRow) + Math.abs(nextBlueCol-curBlueCol);

                        if(redMoved > blueMoved){
                            nextRedRow -= dr[d];
                            nextRedCol -= dc[d];
                        }else{
                            nextBlueRow -= dr[d];
                            nextBlueCol -= dc[d];
                        }
                    }
                    if(!visited[nextRedRow][nextRedCol][nextBlueRow][nextBlueCol]) {
                        q.offer(new Marble(nextRedRow, nextRedCol, nextBlueRow, nextBlueCol));
                        visited[nextRedRow][nextRedCol][nextBlueRow][nextBlueCol] = true;
                    }
                }
            }

            count+=1;

            if(count==11){
                System.out.println(-1);
                return;
            }
        }

        System.out.println(-1);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new String[N][M];

        marble = new Marble();
        visited = new boolean[N][M][N][M];

        for(int i=0;i<N;i++){
            String input = br.readLine();
            int j=0;
            for(char ch : input.toCharArray()){
                arr[i][j] = ch + "";
                if(arr[i][j].equals("R")){
                    marble.setRedRow(i);
                    marble.setRedCol(j);
                }else if(arr[i][j].equals("B")){
                    marble.setBlueRow(i);
                    marble.setBlueCol(j);
                }
                j+=1;
            }
        }

        solve();






    }

}

