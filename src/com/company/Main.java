package com.company;

import java.io.*;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        String pass = null;
        try {
            pass = getPass();
            writePassToFile(pass);
            comparePass();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void comparePass() throws IOException {
        String pass1 = readPassFromFile(getFile());
        String pass2 = readPassFromFile(new File(Main.class.getResource("/password.txt").getPath()));
        if (pass1.equals(pass2)) {
            System.out.println("Пароли совпали" + pass1);
        } else {
            System.out.println("Пароли не совпали. В файле было " + pass1 + ". В системе" + pass2);
        }

    }

    private static String readPassFromFile(File file) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return br.readLine();
        }

    }

    private static void writePassToFile (String pass) throws IOException {

        try (FileWriter fw = new FileWriter(getFile())) {
            fw.write(pass);
        }
    }

    private static String getPass() throws IOException {

        String pass = null;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (pass == null || pass.isEmpty()) {
                System.out.println("Введит пароль:");
                pass = br.readLine();
                if (pass.isEmpty()) {
                    System.out.println("Введенный пароль пуст");
                }
            }
        }
        return pass;
    }


    private static File getFile()  throws IOException {
        File folder = new File(Paths.get("").toAbsolutePath().toString() + File.separator + "files");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File(folder, "password.txt");
        if (!file.exists())
        {file.createNewFile();}
        return file;
    }
}
