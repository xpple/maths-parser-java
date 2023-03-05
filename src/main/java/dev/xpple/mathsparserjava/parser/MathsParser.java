package dev.xpple.mathsparserjava.parser;

import dev.xpple.mathsparserjava.objects.MathsObject;
import dev.xpple.mathsparserjava.objects.MathsSet;
import dev.xpple.mathsparserjava.objects.NaturalNumber;
import dev.xpple.mathsparserjava.objects.OrderedPair;

import java.util.*;
import java.util.stream.Collectors;

public class MathsParser {
    public static MathsObject fromString(String mathsString) {
        return parseMathsObject(mathsString.trim());
    }

    private static MathsObject parseMathsObject(String string) {
        if (string.isEmpty()) {
            return null;
        }
        char chr = string.charAt(0);
        if (chr == '{') {
            int j = findClosing(string, '{');
            return parseMathsSet(string.substring(1, j));
        }
        if (chr == '}') {
            int j = findClosing(string, ')');
            return parseOrderedPair(string.substring(1, j));
        }
        return new NaturalNumber(Integer.parseInt(string));
    }

    private static MathsSet parseMathsSet(String string) {
        List<MathsObject> elements = new ArrayList<>();
        int start = 0;
        while (start < string.length()) {
            int end = findClosing(string.substring(start), ',') + start;
            MathsObject element = parseMathsObject(string.substring(start, end));
            if (element != null) {
                if (!elements.contains(element)) {
                    elements.add(element);
                }
            }
            start = end + 1;
        }
        return new MathsSet(elements);
    }

    private static OrderedPair parseOrderedPair(String string) {
        int j = findClosing(string, ',');
        MathsObject left = parseMathsObject(string.substring(0, j));
        MathsObject right = parseMathsObject(string.substring(j + 1));
        return new OrderedPair(left, right);
    }

    private static final Map<Character, Character> CLOSE_BRACKETS = Map.of('{', '}', '(', ')');
    private static final Map<Character, Character> OPEN_BRACKETS = CLOSE_BRACKETS.entrySet().stream().collect(Collectors.toUnmodifiableMap(Map.Entry::getValue, Map.Entry::getKey));

    private static int findClosing(String string, char next) {
        Character closing = CLOSE_BRACKETS.get(next);
        closing = closing == null ? next : closing;
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < string.length()) {
            char chr = string.charAt(i);
            if (CLOSE_BRACKETS.get(chr) != null) {
                stack.push(chr);
            }
            if (OPEN_BRACKETS.get(chr) != null) {
                stack.pop();
            }
            if (chr == closing && stack.empty()) {
                return i;
            }
            i += 1;
        }
        return i;
    }
}
