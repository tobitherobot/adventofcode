import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class d14 {

    public static String initial = null;
    public static Map<String, Node> rules = new HashMap<>();
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader(new File("r14.txt")))) {
            initial = br.readLine().strip();
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String split[] = line.strip().split(" -> ");
                String str = split[0];
                char ch = split[1].charAt(0);
                rules.put(str, new Node(str, ch));
            }

            for (String key : rules.keySet()) {
                String str1 = key.charAt(0) + "" + rules.get(key).ch;
                String str2 = rules.get(key).ch + "" + key.charAt(1);
                if (rules.containsKey(str1)) rules.get(key).next1 = rules.get(str1);
                if (rules.containsKey(str2)) rules.get(key).next2 = rules.get(str2);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        star1(initial, 10);

        // star 2
        star2();
    }

    public static void star1(String initial, int times) {

        Map<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            counts.put((char) ((int)'A' + i), 0);
        }
        for (int i = 0; i < initial.length(); i++) {
            counts.put(initial.charAt(i), counts.get(initial.charAt(i)) + 1);
        }

        List<Node> roots = new ArrayList<>();
        for (int i = 0; i < initial.length() - 1; i++) {
            String str = initial.substring(i, i+2);
            if (rules.containsKey(str)) roots.add(rules.get(str));
        }
        
        for (Node root : roots) root.count(counts, times);

        int mn = Integer.MAX_VALUE;
        int mx = Integer.MIN_VALUE;
        for (char c : counts.keySet()) {
            mx = Math.max(mx, counts.get(c));
            if (counts.get(c) > 0) mn = Math.min(mn, counts.get(c));
        }
        System.out.println(mx - mn);
    }

    public static void star2() {

        Map<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            counts.put((char) ((int)'A' + i), 0);
        }
        for (int i = 0; i < initial.length(); i++) {
            counts.put(initial.charAt(i), counts.get(initial.charAt(i)) + 1);
        }

        List<Node> roots = new ArrayList<>();
        for (int i = 0; i < initial.length() - 1; i++) {
            String str = initial.substring(i, i+2);
            if (rules.containsKey(str)) roots.add(rules.get(str));
        }
        
        //for (Node root : roots) root.count(counts, times);
        for (String key : rules.keySet()) System.out.println(rules.get(key).text + " + " + rules.get(key).ch + ": " + rules.get(key).getCycleDepth(rules.get(key).text, 0));

        int mn = Integer.MAX_VALUE;
        int mx = Integer.MIN_VALUE;
        for (char c : counts.keySet()) {
            mx = Math.max(mx, counts.get(c));
            if (counts.get(c) > 0) mn = Math.min(mn, counts.get(c));
        }
        System.out.println(mx - mn);
    }
}

class Node {

    String text;
    char ch;
    Node next1, next2;

    public Node(String t, char c) { 
        text = t;
        ch = c;
    }

    public Node() { }

    public void count(Map<Character, Integer> counts, int times) {

        if (times > 0) {
            counts.put(ch, counts.get(ch) + 1);
            next1.count(counts, times-1);
            next2.count(counts, times-1);
        }
    }

    public int getCycleDepth(String text, int start) {

        if (next1.text.equals(text) || next2.text.equals(text) || start==10) return start;
        return Math.min(next1.getCycleDepth(text, start+1), next2.getCycleDepth(text, start+1));
    }

    public String toString() {
        return text + " mit " + ch + " -> " + next1.text + " & " + next2.text;
    }
}