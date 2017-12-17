package com.company;

import java.io.*;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        String pass=getPass();
        writePassToFile(pass);
        ComparePass();
    }


    private static void ComparePass() {
        String pass1=readPassFromFile(getFile());
        String pass2=readPassFromFile(new File( Main.class.getResource("/password.txt").getPath()));
        if (pass1.equals(pass2))
        {  System.out.println("Пароли совпали" +pass1);}
        else
            System.out.println("Пароли не совпали. В файле было "+ pass1 + ". В системе" +pass2);

    }

    private static String readPassFromFile(File file) {

        try (BufferedReader br=new BufferedReader(new FileReader(file)) ){
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private static void writePassToFile(String pass) {

        try (FileWriter fw = new FileWriter(getFile())) {
            fw.write(pass);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getPass() {
        System.out.println("Введит пароль:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
    }


    private static File getFile()
    {
        File folder = new File(Paths.get("").toAbsolutePath().toString() + File.separator + "files");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File(folder, "password.txt");
        if (!file.exists()) try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
