package com.company;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.nio.file.Files.readAllLines;
import static java.nio.file.Files.setPosixFilePermissions;

public class Main {

    public static void main(String[] args) throws IOException {
        writeInFile("com.company", "file2.txt");
        copyFile("file2.txt", "com.company");
        message();
        Scanner input = new Scanner(System.in);
        System.out.println(input.hasNext());
        String caseYouChoose = input.next();
        while (!Objects.equals(caseYouChoose, "q")) {
            switch (caseYouChoose) {
                case "r" -> {
                    System.out.println("You chose to read the file: ");
                    readingFromAFile("com.company");
                    input.nextLine();
                    message();
                    caseYouChoose = input.next();
                }
                case "w" -> {


                }
                case "a" -> {
                    System.out.println("You chose to append a file to another file: ");
                    appendToAFile("mainFile", "appendedFile", "mergedFile");
                    message();
                    caseYouChoose = input.next();
                }
                default -> {
                    System.out.println("Please choose one action from: \"r\" or \"w\" or \"a\"");
                    caseYouChoose = input.next();
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
        finally {
            prFinal.close();
            br1.close();
            fr1.close();
            br2.close();
            fr2.close();
        }
    }

    public static void writeInFile(String pathOfFile, String newPath) throws IOException{
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bri = new BufferedReader(isr);
        FileReader fr = new FileReader(pathOfFile);
        BufferedReader brw = new BufferedReader(fr);
        PrintWriter pr = new PrintWriter(newPath);

        try(isr; bri; fr; brw; pr) {
            String line;
            while((line = brw.readLine()) != null){
                pr.println(line);
            }
            System.out.println("You are in try catch");
            line = bri.readLine();
            pr.println(line);

        }
        finally {
            try {
                pr.close();
                brw.close();
                fr.close();
                bri.close();
                isr.close();
            }catch (Exception e){

            }

        }
    }

    public static void copyFile(String pathOfFile, String newPath) throws IOException{
        FileReader fr = new FileReader(pathOfFile);
        BufferedReader br = new BufferedReader(fr);
        PrintWriter pr = new PrintWriter(newPath);

        try(fr; br; pr) {
            String line;
            while((line = br.readLine()) != null){
                pr.println(line);
            }
        }
        finally {
            pr.close();
            br.close();
            fr.close();

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
