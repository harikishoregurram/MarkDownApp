package com.format;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Formatter {

    private static final String outputPath = System.getProperty("user.dir") + "/src/main/resources/output.html";
    private static final Scanner scanner = new Scanner(System.in);

    /* Get user input and invoke mark down function*/
    public static void main(String[] args) throws IOException {
        System.out.print("***************** Enter the absolute path of input file which has a empty line between input: ");
        System.out.flush();
        String fileName = scanner.nextLine();
        markDownHTML(fileName);
    }

    /* Parse the input file and invoke the mark-up function */
    public static String markDownHTML(String fileName) throws IOException {
        String output = "";
        if (fileName.length() > 0) {
            FileInputStream inputStream = null;
            Scanner sc = null;
            BufferedWriter writer = null;
            try {
                inputStream = new FileInputStream(fileName.trim());
                sc = new Scanner(inputStream);
                StringBuilder input = new StringBuilder();
                StringBuilder result = new StringBuilder();
                /* File Parser */
                while (sc.hasNextLine()) {
                    String line1 = sc.nextLine();
                    String line2 = "";
                    if (sc.hasNextLine())
                        line2 = sc.nextLine();
                    if (line1.length() > 0)
                        input.append(line1);
                    if (line1.length() > 0 && line2.length() > 0) {
                        input.append("\n");
                        input.append(line2);
                    } else if (line2.length() > 0) {
                        input.append(line2);
                    }
                    if (input.length() > 0)
                        result.append(markDown(input.toString()));
                    input.delete(0, input.length());
                }
                /* Write the output to html file */
                writer = new BufferedWriter(new FileWriter(outputPath));
                writer.write(result.toString());
                if (sc.ioException() != null) {
                    throw sc.ioException();
                }
                System.out.println("***************** Find the output html here : " + outputPath);
            } catch (IOException e) {
                output = "***************** Enter a valid file path *****************";
                System.out.println(output);
            }
            /* Close file streams */ finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (writer != null) {
                    writer.close();
                }
                if (sc != null) {
                    sc.close();
                }
            }
        } else {
            output = "***************** Enter a file path *****************";
            System.out.println(output);
        }
        return output;
    }

    public static StringBuilder markDown(String str) {
        StringBuilder res = new StringBuilder();
        if (str.length() > 0) {
            if (str.charAt(0) == '#') {
                markDownHeaderTag(str, res);
            } else if (str.charAt(0) == '[') {
                markDownLinkTag(str, res);
            } else {
                markDownParagraphTag(str, res);
            }
        }
        return res;
    }

    /*Function to format the link tag*/
    public static void markDownLinkTag(String str, StringBuilder res) {
        if (str.length() > 0) {
            res.append(" <a").append(" href=\"");
            String temp = str.substring(str.indexOf("(") + 1, str.indexOf(")")).trim();
            res.append(temp).append("\">");
            res.append(str.substring(str.indexOf("[") + 1, str.indexOf("]")).trim());
            res.append("</a>");
        }
    }

    /*Function to format the paragraph tag*/
    public static void markDownParagraphTag(String str, StringBuilder res) {
        if (str.length() > 0) {
            if (!str.contains("http")) {
                res.append("<p>");
                res.append(str.trim());
                res.append("</p>");
            } else {
                res.append("<p>");
                res.append(str.substring(0, str.indexOf("[")).trim());
                res.append(markDown(str.substring(str.indexOf("["), str.indexOf(")") + 1).trim()));
                res.append(str.substring(str.indexOf(")") + 1));
                res.append("</p>");
            }
        }
    }

    /*Function to format the header tags*/
    public static void markDownHeaderTag(String str, StringBuilder res) {
        if (str.length() > 0) {
            String[] temp = str.split(" ");
            str = str.replace("#", "");
            int len = temp[0].length();
            res.append("<h").append(len).append(">");
            if (!str.contains("http")) {
                res.append(str.trim());
            } else {
                res.append(str.substring(0, str.indexOf("[")).trim());
                res.append(markDown(str.substring(str.indexOf("["), str.indexOf(")") + 1).trim()));
                res.append(str.substring(str.indexOf(")") + 1));
            }
            res.append("</h").append(len).append(">");
        }

    }
}
