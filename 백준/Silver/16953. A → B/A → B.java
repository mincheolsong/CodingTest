import java.io.*;
import java.util.*;

public class Main {

    static int A,B;
    static int ans = -1;

    static void solve(){
        int cnt = 0;

        while(A <= B){
            if(A==B){
                ans = cnt+1;
                return;
            }
            cnt+=1;
            if(B%2==0){
                B/=2;
            }else if(B%10==1){
                B = B / 10;
            }else{
                break;
            }

        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        solve();

        System.out.println(ans);


    }

}
