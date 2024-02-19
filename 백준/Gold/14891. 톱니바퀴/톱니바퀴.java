import java.io.*;
import java.util.*;


public class Main {

    // 1번 톱니바퀴 : 2번 인덱스
    // 2번 톱니바퀴 : 2번, 6번 인덱스
    // 3번 톱니바퀴 : 2번, 6번 인덱스
    // 4번 톱니바퀴 : 6번 인덱스

    // 1. 회전 시키기 전에 접한 부분 체크
    // 2. 톱니바퀴 별로 회전시킬 방향 설정
    // 3. 회전시키기
    // 4. 시계 반대방향 회전 : 12시 가리키는 idx+=1
    // 5. 시계 방향 회전 : 12시 가리키는 idx-=1

    static int[][] gear;
    static int[][] pole;
    static int K;
    static int[] d;
    static boolean[] visited;

    static void left_update(int num,int dir){
        if(!visited[num-1] && gear[num-1][pole[num-1][1]]!=gear[num][pole[num][0]]){
            d[num-1] = dir*-1;
            visited[num-1]=true;
            update(num-1,dir*-1);
        }
    }

    static void right_update(int num,int dir){
        if(!visited[num+1] && gear[num+1][pole[num+1][0]]!=gear[num][pole[num][1]]){
            d[num+1] = dir*-1;
            visited[num+1]=true;
            update(num+1,dir*-1);
        }
    }

    static void update(int num, int dir){

        if(num==0){
            right_update(num,dir);
            return;
        }

        if(num==3){
            left_update(num,dir);
            return;
        }

        left_update(num,dir);
        right_update(num,dir);
    }

    static void rotate(){
        // d배열을 기준으로 pole 회전시키기
        for(int i=0;i<4;i++){
            if(d[i]==1){ // 시계방향 : pole[i][0] pole[i][1] 왼쪽으로 밀기
                pole[i][0] -= 1;
                pole[i][1] -= 1;
                pole[i][2] -= 1;
                if(pole[i][0]==-1) pole[i][0] = 7;
                if(pole[i][1]==-1) pole[i][1] = 7;
                if(pole[i][2]==-1) pole[i][2] = 7;
            }else if(d[i]==-1){ // 반시계방향 : pole[i][0] pole[i][1] 오른쪽으로 밀기
                pole[i][0] = (pole[i][0]+1)%8;
                pole[i][1] = (pole[i][1]+1)%8;
                pole[i][2] = (pole[i][2]+1)%8;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        gear = new int[4][8];
        pole = new int[4][3]; // 0열 : 왼쪽 극, 1열 : 오른쪽 극, 2열 : 12시 방향

        for(int i=0;i<4;i++){
            char[] ch = br.readLine().toCharArray();
            for(int j=0;j<8;j++){
                gear[i][j] = ch[j]-'0';
            }
            if(i==0){
                pole[i][1] = 2; // 0번 톱니바퀴는 오른쪽 극(인덱스)만 저장
            }else if(i==3){
                pole[i][0] = 6; // 3번 톱니바퀴는 왼쪽 극(인덱스)만 저장
            }else{
                pole[i][1] = 2;
                pole[i][0] = 6;
            }
            pole[i][2] = 0;
        }

        K = Integer.parseInt(br.readLine());

        for(int i=0;i<K;i++){
            int num,dir;
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            dir = Integer.parseInt(st.nextToken());
            visited = new boolean[4];
            d = new int[4]; // 톱니바퀴 별로 회전시킬 방향 저장 0 : 움직이지 않음, 1 : 시계방향, -1 : 반시계 방향
            d[num-1] = dir;
            visited[num-1] = true;
            update(num-1,dir);
            rotate();
        }

        int ans = 0;
        int val = 1;
        for(int i=0;i<4;i++){
            if(gear[i][pole[i][2]]==1){
                ans += val;
            }
            val *= 2;
        }

        System.out.println(ans);
    }

}
