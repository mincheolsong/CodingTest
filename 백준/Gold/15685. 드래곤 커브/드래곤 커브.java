import java.io.*;
import java.util.*;

// 드래곤 커브 그리기
// 이전 모양에 따라서 계속 새로운 모양이 만들어 짐
// List를 스택으로 활용해서 현재 모양을 기반으로 계속 업데이트 하기
// 정사각형의 개수 구하기

public class Main {

    static int N;
    static int[] dr = {1,0,-1,0}; // 0, 1, 2, 3
    static int[] dc = {0,-1,0,1};
    static int[] ddr = {-1,-1,1,1};
    static int[] ddc = {-1,1,1,-1};
    static List<Integer> history;
    static int x,y,d,g;
    static int[][] arr;

    // 이때 90도 회전하면 방향
    // 0 : 1
    // 1 : 2
    // 2 : 3
    // 3 : 0

    static void update_history(){ // g번 이동시키기

        for(int i=0;i<g;i++){
            int size = history.size();
            for(int j=size-1;j>=0;j--){
                history.add((history.get(j)+1)%4);
            }
        }
    }
    static void draw(){
        x*=2;
        y*=2;
        arr[y][x] = 1;
        for(int i=0;i<history.size();i++){
            int d = history.get(i);
            for(int c=0;c<2;c++){ // 2칸씩 이동시키기
                x += dr[d];
                y += dc[d];
                arr[y][x] = 1;
            }
        }
    }

    static int count(){
        int result = 0;

        for(int r=1;r<201;r+=2){
            for(int c=1;c<201;c+=2){
                int cnt = 0;
                if(arr[r][c]!=0) continue;
                for(int d=0;d<4;d++){
                    int nr = r + ddr[d];
                    int nc = c + ddc[d];
                    if(nr<0 || nr>=201) continue;
                    if(nc<0 || nc>=201) continue;
                    if(arr[nr][nc]==0) break; // 하나라도 0이면 될 수 없음
                    if(arr[nr][nc]==1) cnt+=1;
                }
                if(cnt==4){
                    result+=1;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[201][201];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            history = new ArrayList<>(); // 이동방향 기록
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            history.add(d); // 시작방향 넣어줌
            update_history(); // g세대 만큼 이동했을 때 history를 업데이트
            draw(); // history로 그림 그려주기, 2칸 씩 이동시키기 (정사각형 꼭짓점 맛닿은 부분 계산하기 위해서)\

        }

        // 정사각형의 네 꼭짓점이 다 맞닿은 부분 갯수 카운트
        System.out.println(count());


    }
}





