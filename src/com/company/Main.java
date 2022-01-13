package com.company;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import static java.nio.file.Files.readAllLines;

public class Main {

    public static void main(String[] args) throws IOException {
        message();
        Scanner input = new Scanner(System.in);
        String caseYouChoose = input.nextLine();
        while(caseYouChoose != "q") {
            switch (caseYouChoose) {
                case "r" -> {
                    System.out.println("You chose to read the file: ");
                    message();
                    readingFromAFile("com.company");
                    caseYouChoose = input.nextLine();
                }
                case "w" -> {
                    message();
                    writeInFile("com.company", "file2.txt");
                    copyFile("file2.txt", "com.company");
                    caseYouChoose = input.nextLine();

                }
                case "a" -> {
                    message();
                    System.out.println("You chose to append a file to another file: ");
                    appendToAFile("mainFile", "appendedFile", "mergedFile");
                    caseYouChoose = input.nextLine();
                }
                default -> {
                    System.out.println("Please choose one action from: \"r\" or \"w\" or \"a\"");
                    caseYouChoose = input.nextLine();
                }
            }
        }
    }

    public static void message(){
        System.out.println("Please choose from: ");
        System.out.println("1) \"r\" for reading a file");
        System.out.println("2) \"w\" for writing in a file:");
        System.out.println("3) \"a\" for appending a file to another file");
    }

    public static void appendToAFile(String mainFilePath, String pathOfTheAppendedFile, String MergedFilePath) throws IOException {
        FileReader fr1 = new FileReader(mainFilePath);
        BufferedReader br1 = new BufferedReader(fr1);
        FileReader fr2 = new FileReader(pathOfTheAppendedFile);
        BufferedReader br2 = new BufferedReader(fr2);
        PrintWriter prFinal = new PrintWriter(MergedFilePath);

        try(fr1; br1; fr2; br2; prFinal) {
            String line;
            while((line = br1.readLine()) != null){
                prFinal.println(line);
            }
            while((line = br2.readLine()) != null){
                prFinal.println(line);
            }
        }
    }

    public static void writeInFile(String pathOfFile, String newPath) throws IOException{
        System.out.println("entered in writing file");

        BufferedReader bri = new BufferedReader(new InputStreamReader(System.in));
        FileReader fr = new FileReader(pathOfFile);
        BufferedReader brw = new BufferedReader(fr);
        PrintWriter pr = new PrintWriter(newPath);

        try(bri; fr; brw; pr) {
            String line;
            while((line = brw.readLine()) != null){
                pr.println(line);
            }
            System.out.println("You are in try catch");
            line = bri.readLine();
            pr.println(line);

        }
    }

    public static void copyFile(String pathOfFile, String newPath) throws IOException{
        FileReader fr = new FileReader(pathOfFile);
        BufferedReader br = new BufferedReader(fr);
        PrintWriter pr = new PrintWriter(newPath);

        try(fr; br; pr) {
            String line;
            while ((line = br.readLine()) != null) {
                pr.println(line);
            }
        }
    }

    public static void readingFromAFile(String pathToTheFile) throws IOException {
        Path p = Paths.get(pathToTheFile);
        List<String> lines = readAllLines(p);

        for(String line: lines){
            System.out.println(line);
        }
    }
}
