import java.io.*;
import java.util.*;

// 컴퓨터는 10개
// 1 2 3 4 5 6
// 3^3 = 27
// 1 2 3 4 5 6 7 8 9 10 11 12 ... 27
// 2^8 = 216
// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
// 1 2 3 4 5 6 7 8 9 10 1  2  3  4  5  6  7  8  9  10  1  2  3  4  5  6  7  8  9  10
public class Main {

    static int T;
    static int a,b;
    static List<Integer> l;


    static long solve(int n){ // a의 n승, a^n
        if(n==1){
            return a;
        }

        long res = solve(n/2)%10;

        if(n%2==0){
            return (res%10)*(res%10);
        }
        return (res%10)*(res%10)*(a%10);
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            long ans = solve(b) % 10;

            System.out.println(ans==0?10:ans);
        }



    }

}
