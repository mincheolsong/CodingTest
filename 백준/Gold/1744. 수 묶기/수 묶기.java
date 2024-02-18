import java.io.*;
import java.util.*;


public class Main {

    // 음수 * 음수 : 더 작은 수 끼리 곱해야 커짐
    // 양수 * 양수 :  더 큰 수 끼리 곱해야 커짐
    // 음수 부분 양수 부분 나눠서 묶기
    // 0은 음수를 곱하는데만 사용할 수 있음

    static int N;
    static PriorityQueue<Integer> plus;
    static PriorityQueue<Integer> minus;
    static int zero_cnt;
    static int ans;

    static void solve(){

        while(plus.size()>=2){
            int x = plus.poll();
            int y = plus.poll();
            if(y!=1){
                ans += (x*y);
            }
            else{
                ans += (x+y);
            }
        }

        if(plus.size()==1){
            ans += plus.poll();
        }

        while(minus.size()>=2){
            int x = minus.poll();
            int y = minus.poll();
            ans += (x*y);
        }

        if(minus.size()==1 && zero_cnt==0){ // 0이 하나도 없으면 그냥 더해줘야 함
            ans += minus.poll();
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        plus = new PriorityQueue<>((o1,o2)->-(o1-o2));
        minus = new PriorityQueue<>((o1,o2)->(o1-o2));
        zero_cnt = 0;

        N = Integer.parseInt(br.readLine());
        ans = 0;

        for(int i=0;i<N;i++){
            int n = Integer.parseInt(br.readLine());
            if(n==0) zero_cnt++;
            else if(n>0) plus.offer(n);
            else minus.offer(n);
        }

        solve();

        System.out.println(ans);

    }

}
