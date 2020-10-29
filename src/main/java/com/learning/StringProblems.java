package com.learning;

import com.sun.tools.javac.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class StringProblems {
    public static void main(String[] args) {
        String input1 = "RLLLLLRRRRRLRL";
        String input2 = "aabcccccdddefff";
        List<String> input3 = Arrays.asList("g1 act car","ab1 zoo key dog","a8 off zoo");
        String input4 = "QWER";

        System.out.println(frequencySort("abaccadeeefaafcc"));
//        System.out.println(mostCommonWord("Bob. hIt, baLl" , new String[]{"bob", "hit"}));
//        System.out.println(checkInclusion("adc", "dcda"));
//        System.out.println(calculate("0-2147483647"));
//        groupAnagrams();
//        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
//        System.out.println(isPalindrome("0P"));
//        System.out.println(validAnagram("ta%20","rat%"));
//        System.out.println(isNumberRecurringCandidate(temp));
//        System.out.println(balancedString(input4));
//        System.out.println(reorderLogFiles(input3));
//        lengthOfLongestSubString(input2);
//        firstNonRepeatingCharacter(input2);
//        System.out.println(splitBalancedString(input1));
    }

    private static boolean isNumberRecurringCandidate(int temp) {
        while(temp%5==0) temp/=5;
        while(temp%2==0) temp/=2;

        return temp==1;
    }

    private static int splitBalancedString(String input) {
        int foundCount=0, lCount=0, rCount=0;

        for(char i : input.toCharArray()) {
            if(i == 'R') rCount++;
            else if(i == 'L') lCount++;

            if(lCount == rCount) {
                foundCount++;
                lCount=0;
                rCount=0;
            }
        }

        return foundCount;
    }

    private static void firstNonRepeatingCharacter(String input) {
        for(int i=0; i<input.length(); i++) {
            if(input.indexOf(input.charAt(i)) == input.lastIndexOf(input.charAt(i))) {
                System.out.println(input.toCharArray()[i]);
                break;
            }
        }
    }

    private static void lengthOfLongestSubString(String s) {
        int i=0, j=0, max = 0;
        HashSet<Character> set = new HashSet<>();

        while(j<s.length()){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j));
                j++;
                max = Math.max(max, set.size());
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }

        System.out.println(max);
    }

    public static List<String> reorderLogFiles(List<String> logInput) {
        String[] logs = logInput.stream().toArray(String[]::new);

        Arrays.sort(logs, (log1, log2) -> {
            System.out.println("a");
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0)
                    return cmp;
                return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return Arrays.asList(logs);
    }

    public static int balancedString(String s) {

//        1. as long as s is not balanced, keep moving r to the right;
//        2. as soon as s is balanced, keep moving l to the right until s is not balanced anymore.
//        3. update res as Math.min(res, r - l + 1) then repeat these 3 steps until r is out of bound.

        Map<Character,Integer> idMap = new HashMap<>();
        idMap.put('Q',0);
        idMap.put('W',1);
        idMap.put('E',2);
        idMap.put('R',3);

        int[] charCount = new int[4];
        int len = s.length();
        Arrays.fill(charCount, -len/4);

        for(char ch: s.toCharArray()) {
            charCount[idMap.get(ch)]++;
        }

        if(charCount[0]==0 && charCount[1]==0 && charCount[2]==0 && charCount[3]==0) {
            return 0;
        }

        int left=0, right=0, result=len;

        while(right < len) {
            while(right<len && (charCount[0]>0 || charCount[1]>0 || charCount[2]>0 || charCount[3]>0)) {
                charCount[idMap.get(s.charAt(right))]--;
                right++;
            }

            while(left<=right && charCount[0]<=0 && charCount[1]<=0 && charCount[2]<=0 && charCount[3]<=0) {
                charCount[idMap.get(s.charAt(left))]++;
                left++;
            }

            result = Math.min(result, right-left+1);
        }

        return result;
    }

    public static boolean validAnagram(String s, String t){
        if(s.length() != t.length())
            return false;

        Map<Integer, Integer> charMap = new HashMap<>();

        for(int i=0; i<s.length(); i++){
            charMap.put((int) s.charAt(i), charMap.getOrDefault((int) s.charAt(i), 0)+1);
            charMap.put((int) t.charAt(i), charMap.getOrDefault((int) t.charAt(i), 0)-1);
        }

        for(int i: charMap.keySet()){
            if(charMap.get(i)!=0)
                return false;
        }

        return true;
    }

    public static boolean isPalindrome(String s) {
        s = s.replaceAll("[^A-Za-z0-9]","").toLowerCase();
        int start = 0;
        int end = s.length()-1;

        while(start<end){
            if(s.charAt(start++)!=s.charAt(end--))
                return false;
        }
        return true;
    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";

        String prefix = strs[0];

        for(int i=1; i<strs.length; i++){
            while(strs[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length()-1);
                if(prefix.isEmpty())
                    return "";
            }
        }

        return prefix;
    }

    public static List<List<String>> groupAnagrams(){
        String[] strs = new String[]{"anagram","nagaram"};
        for(int i=0; i<strs.length; i++){
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            strs[i] = new String(temp);
        }

        System.out.println(strs);
        return null;
    }

    public static int calculate(String s) {
        s = s.replace(" ","");
        while(s.contains("/")) s = calculate(s.indexOf("/"), '/', s);
        while(s.contains("*")) s = calculate(s.indexOf("*"), '*', s);
        while(s.contains("+")) s = calculate(s.indexOf("+"), '+', s);
        while(s.contains("-") && s.indexOf("-")!=0) s = calculate(s.indexOf("-"), '-', s);

        return Integer.parseInt(s);
    }

    private static String calculate(int i, char action, String s){
        char a = s.charAt(i - 1);
        char b = s.charAt(i + 1);
        int result;
        switch (action) {
            case '/':
                result = Integer.parseInt(String.valueOf(a)) / Integer.parseInt(String.valueOf(b));
                return s.replace(a + "/" + b, result + "");
            case '*':
                result = Integer.parseInt(String.valueOf(a)) * Integer.parseInt(String.valueOf(b));
                return s.replace(a + "*" + b, result + "");
            case '+':
                result = Integer.parseInt(String.valueOf(a)) + Integer.parseInt(String.valueOf(b));
                return s.replace(a + "+" + b, result + "");
            case '-':
                result = Integer.parseInt(String.valueOf(a)) - Integer.parseInt(String.valueOf(b));
                return s.replace(a + "-" + b, result + "");
        }
        return s;
    }

    public static boolean checkInclusion(String s1, String s2) {
        int s1Length = s1.length();
        int s2Length = s2.length();

        if(s1Length > s2Length) return false;

        int[] s1Count = new int[26];
        int[] window = new int[26];

        for(int i=0; i<s1Length; i++) {
            s1Count[s1.charAt(i)-'a']++;
            window[s2.charAt(i)-'a']++;
        }

        for(int i=s1Length; i<s2Length; i++) {
            if(isEqual(s1Count, window))
                return true;
            else{
                window[s2.charAt(i-s1Length)-'a']--;
                window[s2.charAt(i)-'a']++;
            }
        }

        return false;
    }

    public static boolean isEqual(int[] a, int[] b){
        for(int i=0; i<a.length; i++){
            if(a[i]!=b[i])
                return false;
        }

        return true;
    }

    public static String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> count = new HashMap<>();
        List<String> bannedList = Arrays.asList(banned);
        String result = "";

        paragraph = paragraph.replaceAll("\\p{Punct}"," ").replaceAll("\\s+"," ").trim();

        for (String str: paragraph.split(" ")){
            str = str.toLowerCase();
            if(!bannedList.contains(str))
                count.put(str, count.getOrDefault(str, 0)+1);
        }

        for(String key: count.keySet()){
            if(count.get(key) > count.getOrDefault(result, 0))
                result = key;
        }

        return result;
    }

    public static String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        PriorityQueue<Character> pq = new PriorityQueue<>((p1, p2) -> map.get(p2) - map.get(p1));

        for(char c: s.toCharArray()) map.put(c, map.getOrDefault(c, 0)+1);
        pq.addAll(map.keySet());

        StringBuilder result = new StringBuilder();
        while(!pq.isEmpty()) {
            char ch = pq.poll();
            result.append(String.join("", Collections.nCopies(map.get(ch), String.valueOf(ch))));
        }

        return result.toString();
    }
}
