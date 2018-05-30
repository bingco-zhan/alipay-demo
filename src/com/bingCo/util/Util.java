package com.bingCo.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Util {

    public static void write(String context, String toFile) {
        File file = new File(toFile);
        if (context != null && context.length() > 0 && file.isFile() && file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.newLine();
                writer.append(context);
                writer.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Object[] read(String fromFile) {
        File file = new File(fromFile);
        Object[] result = {0};
        if (file.isFile() && file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                result = reader.lines().toArray();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void println(Map<String, String> body, PrintStream print) {
        if (print == null) print = System.out;

        final PrintStream _print = print;

        int[] max = getMaxLength(body);
        String $1 = "", $2 = "";
        for (int x = 0; x < max[0]; x++) $1 += "-";
        for (int x = 0; x < max[1]; x++) $2 += "-";

        _print.println("+-" + $1 + "-+-" + $2 + "-+");
        body.entrySet().forEach(e -> {
            int $k = getLength(e.getKey()), $v = getLength(e.getValue());
            String ks = "", vs = "";
            for (int i = 0; i < max[0] - $k; i++)  ks += " ";
            for (int i = 0; i < max[1] - $v; i++)  vs += " ";
            _print.println("| " + e.getKey() + ks + " | " + e.getValue() + vs + " |");
        });
        _print.println("+-" + $1 + "-+-" + $2 + "-+");
    }

    private static int[] getMaxLength(Map<String, String> body) {
        int max1 = 0, max2 = 0;

        for (Map.Entry<String, String> entry : body.entrySet()) {
            int kL = getLength(entry.getKey()), vL = getLength(entry.getValue());
            max1 = kL > max1 ? kL : max1;
            max2 = vL > max2 ? vL : max2;
        }
        return new int[] {max1, max2};
    }

    private static int getLength(String value) {
        int count = 0;
        for (int x = 0, length = value.length(); x < length; x++) {
            int ascii = Character.codePointAt(value, x);
            if (ascii >= 0 && ascii <= 255) {
                count ++;
            }

            else {
                count += 2;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(getLength("Z"));
        System.out.println(getLength("阿"));
    }
}
