import java.util.*;
import java.io.*;

public class Main {

    static int N,M,T;
    static List<Integer>[] circle;
    static boolean[][] visited;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};

    static void rotate(int X, int D, int K){

        if(D==0){ // 시계 방향이면, 제일 뒤에 원소를 제일 앞으로
            int x = X;
            while(x < N+1){
                for(int k=0;k<K;k++){
                    int back_e = circle[x].remove(circle[x].size()-1);
                    circle[x].add(0,back_e);
                }
                x += X;
            }

        }else if(D==1){ // 반시계 방향이면, 제일 앞의 원소를 제일 뒤로
            int x = X;
            while(x < N+1){
                for(int k=0;k<K;k++){
                    int front_e = circle[x].remove(0);
                    circle[x].add(front_e);
                }
                x += X;
            }
        }
    }

    static boolean check(int r, int c, int val){

        int flag = 0;

        for(int d=0;d<4;d++){
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nc==-1) nc = M-1;
            if(nc==M) nc = 0;

            if(nr>=1 && nr<=N && circle[nr].get(nc)==val){
                circle[nr].set(nc,-1);
                check(nr,nc,val);
                flag = 1;
            }
        }

        if(flag==1) return true;
        return false;

    }

    static void update(){
        double avg = 0;
        int sum = 0;
        int cnt = 0;
        for(int i=1;i<N+1;i++){
            for(int j=0;j<M;j++){
                if(circle[i].get(j)==-1) continue;
                sum += circle[i].get(j);
                cnt += 1;
            }
        }
        avg = (double)sum / cnt;

        for(int i=1;i<N+1;i++){
            for(int j=0;j<M;j++){
                int val = circle[i].get(j);
                if(val==-1) continue;
                if(val < avg){
                    circle[i].set(j,val+1);
                }else if(val > avg){
                    circle[i].set(j,val-1);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        circle = new List[N+1];
        for(int i=1;i<N+1;i++){
            circle[i] = new LinkedList<>();
        }

        for(int n=1;n<=N;n++){
            st = new StringTokenizer(br.readLine());
            for(int m=0;m<M;m++){
                circle[n].add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int t=0;t<T;t++) {
            st = new StringTokenizer(br.readLine());
            int X, D, K;
            X = Integer.parseInt(st.nextToken()); // 원판 번호(x의 배수)
            D = Integer.parseInt(st.nextToken()); // 0 : 시계, 1 : 반시계
            K = Integer.parseInt(st.nextToken()); // k칸

            rotate(X,D,K);

            /*for(int i=1;i<N+1;i++){
                System.out.println(circle[i].toString());
            }
            System.out.println("---------------");*/
            int flag = 0;
            for(int i=1;i<N+1;i++){
                for(int j=0;j<M;j++){
                    if(circle[i].get(j)==-1) continue;
                    if(check(i,j,circle[i].get(j))){
                        circle[i].set(j,-1); // 현재 위치도 지우기
                        flag = 1;
                    }
                }
            }
            if(flag==0){
                update();
            }

            /*for(int i=1;i<N+1;i++){
                System.out.println(circle[i].toString());
            }*/

        }

        int ans = 0;

        for(int i=1;i<=N;i++){
            for(int j=0;j<M;j++){
                int val = circle[i].get(j);
                if(val==-1) continue;
                ans += val;
            }
        }

        System.out.println(ans);

    }
}





