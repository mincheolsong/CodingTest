import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<char[]> list;

    static boolean check(char[] ch1, char[] ch2) {

        for(int i=0;i<ch1.length;i++){
            if(ch1[i]!=ch2[i]){
                return true;
            }
        }

        return false;
    }

    static boolean solve() {

        for (int i = 0; i < list.size() - 1; i++) {
            char[] iChar = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                char[] jChar = list.get(j);

                if (iChar.length == jChar.length) continue; // 길이가 같으면 비교할 필요가 없음

                if (!check(iChar, jChar)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        int N;

        for (int t = 0; t < T; t++) {
            list = new ArrayList<>();
            N = Integer.parseInt(br.readLine());
            for (int n = 0; n < N; n++) {
                String s = br.readLine().replace(" ","");
                char[] ch = s.toCharArray();
                list.add(ch);
            }
            list.sort(new Comparator<char[]>() {
                @Override
                public int compare(char[] o1, char[] o2) {
                    return o1.length - o2.length;
                }
            });
          
            if (!solve()) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }


        }

    }
}