public class Solution {
    public String longestPalindrome(String s) {
      char[] cs = s.toCharArray();
        
        Map<Integer, int[]> palindromeStart = new HashMap<Integer, int[]>();
        Map<Integer, int[]> palindromeEnd = new HashMap<Integer, int[]>();
        
        int[] oneStartList = new int[cs.length];
        int[] oneEndList = new int[cs.length];
        palindromeStart.put(1, oneStartList);
        palindromeEnd.put(1, oneEndList);
        for (int idx = 0; idx < cs.length; idx++) {
            oneStartList[idx] = idx;
            oneEndList[idx] = idx;
        }
        
        int[] twoStartList = new int[cs.length - 1];
        int[] twoEndList =  new int[cs.length - 1];
        int validLength = 0;
        for (int idx = 0; idx < cs.length - 1; idx++) {
            if (cs[idx] == cs[idx + 1]) {
                twoStartList[validLength] = idx;
                twoEndList[validLength] = idx + 1;
                validLength++;
            }
        }
        if (validLength > 0) {
            palindromeStart.put(2, twoStartList);
            palindromeEnd.put(2, twoEndList);
            if (validLength < twoStartList.length) {
                twoStartList[validLength] = -1;
                twoEndList[validLength] = -1;
            }
        }
        
        for (int num = 3; num <= cs.length; num++) {
            int[] preStartList = palindromeStart.get(num - 2);
            int[] preEndList = palindromeEnd.get(num - 2);
            if (preStartList == null) {
                continue;
            }
            
            int[] curStartList = new int[cs.length - num + 1];
            int[] curEndList = new int[cs.length - num + 1];
            validLength = 0;
            for (int idx = 0; idx < preStartList.length; idx++) {
                if (preStartList[idx] == -1) {
                    break;
                }
                int start = preStartList[idx] - 1;
                int end = preEndList[idx] + 1;
                if (start < 0 || end > cs.length - 1) {
                    continue;
                }
                if (cs[start] == cs[end]) {
                    curStartList[validLength] = start;
                    curEndList[validLength] = end;
                    validLength++;
                }
            }
            
            if (validLength > 0) {
                palindromeStart.put(num, curStartList);
                palindromeEnd.put(num, curEndList);
                if (validLength < curStartList.length) {
                    curStartList[validLength] = -1;
                    curStartList[validLength] = -1;
                }
            }
        }
        
        for (int num = cs.length; num > 0; num--) {
            if (palindromeStart.containsKey(num)) {
                return s.substring(palindromeStart.get(num)[0], palindromeEnd.get(num)[0] + 1);
            }
        }
        
        return s.substring(0, 1);
    }
}