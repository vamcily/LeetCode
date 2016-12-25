public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null) {
            return null;
        }
        
        Map<String, List<String>> strMap = new HashMap<String, List<String>>();
        
        for (String str : strs) {
            if (str == null) {
                if (! strMap.containsKey(str)) {
                    strMap.put(str, new LinkedList<String>());
                }
                strMap.get(str).add(str);
                continue;
            }
            
            char[] strArray = str.toCharArray();
            Arrays.sort(strArray);
            String sig = String.valueOf(strArray);
            if (! strMap.containsKey(sig)) {
                strMap.put(sig, new LinkedList<String>());
            }
            strMap.get(sig).add(str);
        }
        
        List<List<String>> result = new ArrayList<List<String>>(strMap.values());
        return result;
    }
}