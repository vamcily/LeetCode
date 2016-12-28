public class Solution {
    public String longestPalindrome(String s) {
        char[] cs = s.toCharArray();
        
        Map<Integer, List<Range>> palindromes = new HashMap<Integer, List<Range>>();
        
        List<Range> oneList = new ArrayList<Range>();
        palindromes.put(1, oneList);
        for (int idx = 0; idx < cs.length; idx++) {
            oneList.add(new Range(idx, idx));
        }
        
        List<Range> twoList = new ArrayList<Range>();
        for (int idx = 0; idx < cs.length - 1; idx++) {
            if (isPalindrome(cs, idx, idx + 1)) {
                twoList.add(new Range(idx, idx + 1));
            }
        }
        if (twoList.size() > 0) {
            palindromes.put(2, twoList);
        }
        
        for (int num = 3; num <= cs.length; num++) {
            List<Range> preList = palindromes.get(num - 2);
            if (preList == null) {
                break;
            }
            
            List<Range> curList = new ArrayList<Range>();
            for (Range r : preList) {
                if (isPalindrome(cs, r.start - 1, r.end + 1)) {
                    curList.add(new Range(r.start - 1, r.end + 1));
                }
            }
            
            if (curList.size() > 0) {
                palindromes.put(num, curList);
            }
        }
        
        for (int num = cs.length; num > 0; num--) {
            if (palindromes.containsKey(num)) {
                Range r = palindromes.get(num).get(0);
                return s.substring(r.start, r.end + 1);
            }
        }
        
        return s.substring(0, 1);
    }
    
    private static class Range {
        int start;
        int end;
        
        Range(int s, int e) {
            start = s;
            end = e;
        }
    }
    
    private boolean isPalindrome(char[] str, int start, int end) {
        if (start < 0 || end > str.length - 1) {
            return false;
        }
        
        int i = start; 
        int j = end;
        
        boolean ret = true;
        
        while (i < j) {
            if (str[i] != str[j]) {
                ret = false;
                break;
            }
            
            i++;
            j--;
        }
        
        return ret;
    }
}