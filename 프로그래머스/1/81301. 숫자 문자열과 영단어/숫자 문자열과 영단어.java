import java.util.*;

class Solution {
    public int solution(String s) {
        String[] strArr = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        for(int i = 0; i < strArr.length; i++) {
            s = s.replace(strArr[i], String.valueOf(i));
        }
        return Integer.parseInt(s);
    }
}
