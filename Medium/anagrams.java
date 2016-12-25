public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null) {
            return null;
        }
        
        Map<String, List<String>> strMap = new HashMap<String, List<String>>(strs.length);
        for (String str : strs) {
            if (str == null || str.isEmpty()) {
                if (! strMap.containsKey(str)) {
                    strMap.put(str, new LinkedList<String>());
                }
                strMap.get(str).add(str);
                continue;
            }
            
            String sig = sortByAlpha(str);
            if (! strMap.containsKey(sig)) {
                strMap.put(sig, new LinkedList<String>());
            }
            strMap.get(sig).add(str);
        }
        
        List<List<String>> result = new ArrayList<List<String>>(strMap.values());
        return result;
    }
    
    private String sortByAlpha(String str) {
        char[] strArray = str.toCharArray();
        Arrays.sort(strArray);
        return String.valueOf(strArray);
    }
}