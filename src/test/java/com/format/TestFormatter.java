package com.format;


import org.apache.tools.ant.util.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TestFormatter {


    @Test(description = "Verify that the function returns null string for empty string")
    public void testMarkDownHeaderTagWithEmptyStr() {
        String input = "";
        StringBuilder output = new StringBuilder();
        Formatter.markDownHeaderTag(input, output);
        Assert.assertEquals(output.toString(), "");
    }

    @Test(description = "Verify that the function returns null string for empty string")
    public void testMarkDownParagraphTagWithEmptyStr() {
        String input = "";
        StringBuilder output = new StringBuilder();
        Formatter.markDownParagraphTag(input, output);
        Assert.assertEquals(output.toString(), "");
    }

    @Test(description = "Verify that the function returns null string for empty string")
    public void testMarkDownLinkTagWithEmptyStr() {
        String input = "";
        StringBuilder output = new StringBuilder();
        Formatter.markDownLinkTag(input, output);
        Assert.assertEquals(output.toString(), "");
    }

    @Test(description = "Verify that the function returns null string for empty string")
    public void testMarkDownWithEmptyStr() {
        String input = "";
        Assert.assertEquals(Formatter.markDown(input).length(), 0);
    }

    @Test(description = "Verify that the function returns valid string for a valid input")
    public void testMarkDownHeaderTagWithValidStr() {
        String input = "# Header one";
        StringBuilder output = new StringBuilder();
        Formatter.markDownHeaderTag(input, output);
        Assert.assertEquals(output.toString(), "<h1>Header one</h1>");
    }

    @Test(description = "Verify that the function returns valid string for a valid input")
    public void testMarkDownParagraphTagWithValidStr() {
        String input = "Hello there";
        StringBuilder output = new StringBuilder();
        Formatter.markDownParagraphTag(input, output);
        Assert.assertEquals(output.toString(), "<p>Hello there</p>");
    }

    @Test(description = "Verify that the function returns valid string for a valid input")
    public void testMarkDownLinkTagWithValidStr() {
        String input = "[Link text](https://www.example.com)";
        StringBuilder output = new StringBuilder();
        Formatter.markDownLinkTag(input, output);
        Assert.assertEquals(output.toString(), " <a href=\"https://www.example.com\">Link text</a>");
    }

    @Test(description = "Verify that the function returns valid string for a valid input")
    public void testMarkDownWithValidStr() {
        String input = "";
        Assert.assertEquals(Formatter.markDown(input).length(), 0);
    }


    @Test(description = "Verify that the function returns valid string for a valid input with LinkText")
    public void testMarkDownHeaderTagWithLinkText() {
        String input = "## This is a header [with a link](http://yahoo.com)";
        StringBuilder output = new StringBuilder();
        Formatter.markDownHeaderTag(input, output);
        Assert.assertEquals(output.toString(), "<h2>This is a header <a href=\"http://yahoo.com\">with a link</a></h2>");
    }

    @Test(description = "Verify that the function returns valid string for a valid input with LinkText")
    public void testMarkDownParagraphTagWithLinkText() {
        String input = "This is sample markdown for the [Mailchimp](https://www.mailchimp.com) homework assignment.";
        StringBuilder output = new StringBuilder();
        Formatter.markDownParagraphTag(input, output);
        Assert.assertEquals(output.toString(), "<p>This is sample markdown for the <a href=\"https://www.mailchimp.com\">Mailchimp</a> homework assignment.</p>");
    }

    @Test(description = "Verify that the function returns valid string for a valid input file")
    public void testMarkDownHTML() throws IOException {
        String filePath = "src/main/resources/data.txt";
        Formatter.markDownHTML(filePath);
        File file1 = new File("src/main/resources/output.html");
        File file2 = new File("src/main/resources/output_data.html");
        FileUtils fileUtils = FileUtils.getFileUtils();
        Assert.assertTrue(fileUtils.contentEquals(file1, file2));
    }

    @Test(description = "Verify that the function returns valid string for a valid input file")
    public void testMarkDownHTML1() throws IOException {
        String filePath = "src/main/resources/data2.txt";
        Formatter.markDownHTML(filePath);
        File file1 = new File("src/main/resources/output.html");
        File file2 = new File("src/main/resources/output_data2.html");
        FileUtils fileUtils = FileUtils.getFileUtils();
        Assert.assertTrue(fileUtils.contentEquals(file1, file2));
    }

    @Test(description = "Verify that the function returns exception for invalid file path")
    public void testMarkDownHTMLWithInvalidFilePath() throws IOException {
        String filePath = "main/resources/data2.txt";
        Assert.assertEquals(Formatter.markDownHTML(filePath),"***************** Enter a valid file path *****************");
    }

    @Test(description = "Verify that the function returns exception for invalid file path")
    public void testMarkDownHTMLWithInvalidFilePath1() throws IOException {
        String filePath = "";
        Assert.assertEquals(Formatter.markDownHTML(filePath),"***************** Enter a file path *****************");
    }


}
