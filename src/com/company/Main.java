package com.company;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void rewrite(FileWriter fileWriter, String str, Matcher matcher) throws IOException{
        fileWriter.write(str.replace(matcher.group(), "") + "\n");
    }

    public static void main(String[] args) throws IOException {
        File newCodeTXT = new File("","newCode.txt");
        if (!newCodeTXT.exists()){
            newCodeTXT.createNewFile();
        }

        FileReader reader = new FileReader("code.txt");
        Scanner scanner = new Scanner(reader);

        Pattern pattern = Pattern.compile("/\\*.*");
        Pattern pattern1 = Pattern.compile("//.*");
        Pattern pattern2 = Pattern.compile("^.*$");
        Pattern pattern3 = Pattern.compile(".*\\*/");

        Matcher matcher;

        FileWriter writer = new FileWriter("newCode.txt", false);

        boolean is_a_multiLine_comment = false;

        do {
            String str = scanner.nextLine();

            if (is_a_multiLine_comment){
                matcher = pattern3.matcher(str);
                if(matcher.find()){
                    rewrite(writer,str,matcher);
                    is_a_multiLine_comment = false;
                    continue;
                }
                matcher = pattern2.matcher(str);
                matcher.find();
                rewrite(writer,str,matcher);
                continue;
            }

            matcher = pattern.matcher(str);
            if (matcher.find()) {
                rewrite(writer,str,matcher);
                continue;
            }

            matcher = pattern1.matcher(str);
            if(is_a_multiLine_comment = matcher.find()){
                rewrite(writer,str,matcher);
                continue;
            }
            else{
                writer.write(str);
            }


        }while (scanner.hasNextLine());
        writer.flush();
        writer.close();
    }
}
