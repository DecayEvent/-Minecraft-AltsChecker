package com.decayevent.main;

import com.decayevent.utils.ApplicationUtils;
import com.decayevent.utils.CheckUtils;
import com.decayevent.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 * @author DecayEvent
 *
 *
 * */

public class MainForm extends JFrame {

    JFileChooser fileChooser = new JFileChooser();
    JTextArea AllAlts = new JTextArea();
    JTextArea loginFailed = new JTextArea();
    JTextArea  loginSuccess = new JTextArea();

    public MainForm()
    {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(800, 599));
        this.setResizable(false);

        /*创建菜单栏*/
        JMenuBar menuBar = new JMenuBar();
        /*创建文件菜单*/
        JMenu fileMenu = new JMenu("File");
        /*创建文件菜单子菜单*/
        JMenuItem importAlt = new JMenuItem("Import Alts");
        importAlt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
                fileChooser.showOpenDialog(null);

                AllAlts.setText(FileUtils.ReadFile(fileChooser.getSelectedFile().getAbsoluteFile().getPath()));

            }
        });
        JMenuItem exportAlt = new JMenuItem("Export Success Alts");
        exportAlt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
                fileChooser.showSaveDialog(null);

                FileUtils.WriteFile(fileChooser.getSelectedFile().getAbsoluteFile().getPath(), loginSuccess.getText());

            }
        });

        JMenu aboutMenu = new JMenu("About");
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Code by DecayEvent");
            }
        });

        JMenuItem joinus = new JMenuItem("JoinUs");
        joinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desktop dt = Desktop.getDesktop();
                if(dt.isSupported(Desktop.Action.BROWSE))
                {
                    try {
                        dt.browse(new URI("https://jq.qq.com/?_wv=1027&k=BdXrqMoI"));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (URISyntaxException uriSyntaxException) {
                        uriSyntaxException.printStackTrace();
                    }
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(null);

        fileMenu.add(importAlt);
        fileMenu.add(exportAlt);

        aboutMenu.add(about);
        aboutMenu.add(joinus);

        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);

        JLabel jl = new JLabel("Username:Password");
        jl.setBounds(10, 10, 200, 30);

        JScrollPane AllAltsPane = new JScrollPane();
        AllAlts.setLineWrap(true);
        AllAltsPane.setViewportView(AllAlts);
        AllAltsPane.setBounds(10, 40, 300, 400);


        JLabel jl1 = new JLabel("Failed to Login: ");
        jl1.setBounds(310, 10, 200, 30);

        JScrollPane FailLoginPane = new JScrollPane();
        loginFailed.setLineWrap(true);
        FailLoginPane.setViewportView(loginFailed);
        FailLoginPane.setBounds(310, 40, 300, 400);

        JLabel jl2 = new JLabel("Success to Login: ");
        jl2.setBounds(610, 10, 200, 30);

        JScrollPane SucessLoginPane = new JScrollPane();
        loginSuccess.setLineWrap(true);
        SucessLoginPane.setViewportView(loginSuccess);
        SucessLoginPane.setBounds(610, 40, 300, 400);

        JButton checkBtn = new JButton("Check");
        checkBtn.setBounds(300, 450, 300, 30);
        checkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RunMutilCheck(AllAlts.getText());
            }
        });

        panel.add(jl);
        panel.add(AllAltsPane);

        panel.add(jl1);
        panel.add(FailLoginPane);

        panel.add(jl2);
        panel.add(SucessLoginPane);

        panel.add(checkBtn);

        this.add(menuBar, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);

        this.setSize(new Dimension(940, 600)); //再次设置一次大小, 因为有几率会看不见控件, 改变大小就好了
    }

    public static void main(String[] args) {
        ApplicationUtils.Init();
        new MainForm().setVisible(true);
    }

    public  void RunMutilCheck(String alts) {
        if (alts.trim().length() < 1) {
            JOptionPane.showMessageDialog(null, "Please enter or import alts");
            return;
        }

        String account[] = alts.split("\r\n");

        for (int i = 0; i < account.length; i++) {
            System.out.println(account[i]);
            String args[] = account[i].split(":");
            if (!args[0].contains("@"))
                continue;
            if (CheckUtils.Check(args[0], args[1])) {
                StringBuilder sb = new StringBuilder(loginSuccess.getText());
                loginSuccess.setText(sb.append(account[i]).toString() + "\r\n");
            } else {
                StringBuilder sb = new StringBuilder(loginFailed.getText());
                loginFailed.setText(sb.append(account[i]).toString() + "\r\n");
            }

        }
    }

}
