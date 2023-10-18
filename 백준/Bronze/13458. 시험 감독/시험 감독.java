import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] students;
    static int B,C;
    static Long ans;

    static void solve(){

        for(int i=0;i<N;i++){
            int cnt = 1;
            int student = students[i];
            student -= B;
            if(student<=0){
                ans += cnt;
                continue;
            }
            cnt+=(student/C);
            if(student%C!=0){
                cnt+=1;
            }
            ans+=cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        students = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            students[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // 입력 끝

        ans=0L;
        solve();
        System.out.println(ans);



    }
}