import java.io.*;
import java.util.*;

public class Main {

    // 스택
    // 하나하나 쌓다가 폭발 문자열의 마지막 문자 만나면 폭발 문자열인지 검사

    static String str;
    static String bomb;
    static Stack<Character> stack;

    static void solve(){

        for(int i=0;i<str.length();i++){
            stack.push(str.charAt(i));

            if(stack.size() >= bomb.length()){
                boolean isEqual = true;

                for(int j=0;j<bomb.length();j++){
                    if(stack.get(stack.size()- bomb.length()+j)!=bomb.charAt(j)){
                        isEqual = false;
                        break;
                    }
                }

                if(isEqual){
                    for(int j=0;j<bomb.length();j++){
                        stack.pop();
                    }
                }

            }

        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();
        bomb = br.readLine();
        stack = new Stack<>();

        solve();

        StringBuilder sb = new StringBuilder();
        for(Character c : stack){
            sb.append(c);
        }
        System.out.println(sb.length()==0 ? "FRULA" : sb.toString());


    }

}
