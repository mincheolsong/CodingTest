import java.io.*;
import java.util.*;
// 1:(1,6) -1:(6,1) 2:(2,5) -2:(5,2) 3:(3,4) -3:(4,3) 은 서로 반대방향에 위치 (보이는 부분, 안보이는 부분)
// 주사위의 현재 상태를 저장하고 있어야 함
// 현재 (윗면, 앞면, 오른쪽 면)이 누구인지 저장
// 초기값은 (1, 2, 3)
// 북쪽으로 굴리면 : 윗면은 현재 앞면이 됨, 앞면은 현재 윗면의 반대쌍이 됨, 오른쪽 면은 그래로 : 2,-1,3
// 남쪽으로 굴리면 : 윗면은 현재 앞면의 반대쌍이 됨, 앞면은 현재 윗면이 됨, 오른쪽 면은 그대로 : -2,1,3
// 동쪽으로 굴리면 : 윗면은 오른쪽면의 반대쌍이 됨, 앞면은 그대로, 오른쪽 면은 현재 윗면이 됨 : -3,2,1
// 서쪽으로 굴리면 : 윗면은 오른쪽 면이 됨, 앞면은 그대로, 오른쪽 면은 현재 윗면의 윗면의 반대쌍 : 3,2,-1

public class Main {

    static Map<Integer,int[]> shape = new HashMap<>(){
        {
            put(1,new int[]{1,6});
            put(-1,new int[]{6,1});
            put(2, new int[]{2,5});
            put(-2,new int[]{5,2});
            put(3, new int[]{3,4});
            put(-3, new int[]{4,3});
        }
    };

    static int N,M;
    static int[][] map;
    static int[] current_position = new int[2];
    static int[] command;
    static int[] current_shape = {1,2,3};
    static int[] current_dice = {0,0,0,0,0,0,0};
    static List<Integer> ans;
    static int[][] d = {
            {0,0},
            {0,1},
            {0,-1},
            {-1,0},
            {1,0}
    };

    static void show_map(){
        System.out.println("*현재 맵*");

        for(int[] m : map){
            for(int c : m){
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    static void show_dice(){
        System.out.println("*현재 주사위 상태*");
        for(int i=1;i<=6;i++){
            System.out.println(i + "번 주사위 면의 수 : " + current_dice[i]);
        }

        System.out.println("=======");

    }

    static void show_shape(){
        System.out.println("*현재 주사위 모양*");
        System.out.println("윗면 : " + shape.get(current_shape[0])[0]);
        System.out.println("앞면 : " + shape.get(current_shape[1])[0]);
        System.out.println("오른쪽면 : " + shape.get(current_shape[2])[0]);

    }


    static void solve(){

        int cx,cy;
        int nx,ny;
        int cupper,cfront,cright;
        int nupper=0,nfront=0,nright=0;

        for(int c : command){
            cx = current_position[0];
            cy = current_position[1];

            nx = cx + d[c][0];
            ny = cy + d[c][1];

            if(nx<0 || nx>=N) continue;
            if(ny<0 || ny>=M) continue;

            current_position[0]=nx;
            current_position[1]=ny;

            cupper = current_shape[0];
            cfront = current_shape[1];
            cright = current_shape[2];

            if(c==1){ // 동쪽으로 굴리면 : 윗면은 오른쪽면의 반대쌍이 됨, 앞면은 그대로, 오른쪽 면은 현재 윗면이 됨 : -3,2,1
//                System.out.println("*동쪽으로 굴림*");
                nupper = cright*-1;
                nfront = cfront;
                nright = cupper;

            }else if(c==2){ // 서쪽으로 굴리면 : 윗면은 오른쪽 면이 됨, 앞면은 그대로, 오른쪽 면은 현재 윗면의 윗면의 반대쌍 : 3,2,-1
//                System.out.println("*서쪽으로 굴림*");

                nupper = cright;
                nfront = cfront;
                nright = cupper*-1;

            }else if(c==3){ // 북쪽으로 굴리면 : 윗면은 현재 앞면이 됨, 앞면은 현재 윗면의 반대쌍이 됨, 오른쪽 면은 그래로 : 2,-1,3
//                System.out.println("*북쪽으로 굴림*");

                nupper = cfront;
                nfront = cupper*-1;
                nright = cright;
            }else if(c==4){ // 남쪽으로 굴리면 : 윗면은 현재 앞면의 반대쌍이 됨, 앞면은 현재 윗면이 됨, 오른쪽 면은 그대로 : -2,1,3
//                System.out.println("*남쪽으로 굴림*");

                nupper = cfront*-1;
                nfront = cupper;
                nright = cright;
            }

            current_shape[0]=nupper;
            current_shape[1]=nfront;
            current_shape[2]=nright;

            if(map[nx][ny]==0){
                map[nx][ny] = current_dice[shape.get(nupper)[1]]; // 현재 주사위의 밑면
            }else{
                current_dice[shape.get(nupper)[1]] = map[nx][ny]; // 현재 주사위의 밑면
                map[nx][ny]=0;
            }

            ans.add(current_dice[shape.get(nupper)[0]]); // 현재 주사위의 윗면

//            System.out.println("현재 위치 (" + current_position[0] + ", " + current_position[1] + ")");
//            show_map();
//            show_shape();
//
//            show_dice();
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x,y,K;
        ans = new ArrayList<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        current_position[0] = x;
        current_position[1] = y;
        command = new int[K];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++){
            command[i] = Integer.parseInt(st.nextToken());
        }

        // 입력완료

        solve();


        for(Integer a : ans){
            System.out.println(a);
        }


    }
}