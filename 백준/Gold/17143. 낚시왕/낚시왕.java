import java.io.*;
import java.util.*;

public class Main {



    static int R,C,M;
    static Shark[] sharks;
    static int[][] arr;
    static int[] dr = {0,-1,1,0,0}; // 1 : 위, 2 : 아래, 3 : 오른쪽, 4 : 왼쪽
    static int[] dc = {0,0,0,1,-1};
    static int ans;


    static void solve(){

        for(int c=1;c<C+1;c++){
            // i열의 상어 중 땅에 가장 가까운 상어 잡기
            for(int r=1;r<R+1;r++){
                if(arr[r][c]!=0){
                    ans += sharks[arr[r][c]].size;
                    sharks[arr[r][c]] = null;
                    break;
                }
            }
            // 상어 이동시키기
            for(int m=1;m<M+1;m++){
                if(sharks[m]!=null){
                    sharks[m].move();
                }
            }

            // 이동시킨 상어 arr 배열에 배치하기
            arr = new int[R+1][C+1];
            for(int m=1;m<M+1;m++){
                if(sharks[m]==null) continue;

                int shark_row = sharks[m].r;
                int shark_col = sharks[m].c;
                if(arr[shark_row][shark_col]==0){
                    arr[shark_row][shark_col] = m;
                    continue;
                }

                // 이미 있으면 대소 비교후 배치
                if(sharks[arr[shark_row][shark_col]].size < sharks[m].size){
                    sharks[arr[shark_row][shark_col]] = null;
                    arr[shark_row][shark_col] = m;
                }else{
                    sharks[m] = null;
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[R+1][C+1];
        sharks = new Shark[M+1];
        ans = 0;

        for(int i=1;i<M+1;i++){
            st = new StringTokenizer(br.readLine());
            int r,c,s,d,z;
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            sharks[i] = new Shark(r,c,s,d,z);
            arr[r][c]=i;
        }


        solve();

        System.out.println(ans);

    }

    static class Shark{

        int r,c;
        int speed,d,size; // 1 : 위, 2 : 아래, 3 : 오른쪽, 4 : 왼쪽

        public Shark(int r,int c,int speed, int d, int size){
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.d = d;
            this.size = size;
        }

        public void move() {

            if(d==1 || d==2){
                int s = speed % (R*2-2);
                for(int i=0;i<s;i++){
                    r += dr[d];
                    if(r==0){
                        r = 2;
                        d = 2;
                    }else if(r==R+1){
                        r = R-1;
                        d = 1;
                    }
                }
            }else if(d==3 || d==4){
                int s = speed % (C*2-2);
                for(int i=0;i<s;i++){
                    c += dc[d];
                    if(c==0){
                        c = 2;
                        d = 3;
                    }else if(c==C+1){
                        c = C-1;
                        d = 4;
                    }
                }
            }

        }


        @Override
        public String toString(){
            return "(r : " + r + ", c : " + c + ", speed : " + speed + ", d : " + d + ", size : " + size+")";
        }

    }

}
