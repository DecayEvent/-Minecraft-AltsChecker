package com.decayevent.utils;

import javax.swing.*;
import java.io.*;

public class FileUtils {

    public static String ReadFile(String path) {
        File f = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            f = new File(path);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder("");
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s);
                sb.append("\r\n");
            }
            return sb.toString();
        } catch (NullPointerException | IOException e1) {
            JOptionPane.showMessageDialog(null, "No File Import", "Warning", 0);
        } finally {
            try {
                fr.close();
                br.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return null;
    }


    public static void WriteFile(String path, String Text)
    {
        File f = null;
        FileWriter fw = null;
        BufferedWriter bw = null;

        try{
            f = new File(path);
            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);
            bw.write(Text);
        }catch (NullPointerException | IOException e2)
        {
            JOptionPane.showMessageDialog(null, "No File Export", "Warning", 0);
        }finally {
            try {
                bw.flush();
                bw.close();
                fw.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
