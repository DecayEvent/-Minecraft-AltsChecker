package com.decayevent.utils;

import com.decayevent.main.MainForm;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.io.File;

public class ApplicationUtils {

    public static void Init()
    {
        JsonObject obj = new JsonObject();
        try {
            String path = getApplicationPath();
            File f = new File(path + "\\cfg.json");
            if(!f.exists())
            {
                int result = JOptionPane.showConfirmDialog(null, "Do you want to try new UI?", "DecayEvent", JOptionPane.YES_NO_OPTION);
                if(result == 0)
                {

                    obj.addProperty("isBeautyEye", true);
                    FileUtils.WriteFile(path + "\\cfg.json", obj.toString());
                    BeautyEyeLNFHelper.launchBeautyEyeLNF();
                }else{
                    obj.addProperty("isBeautyEye", false);
                    FileUtils.WriteFile(path + "\\cfg.json", obj.toString());
                }
            }else{
                String json = FileUtils.ReadFile(path + "\\cfg.json");
                obj = (JsonObject)new JsonParser().parse(json).getAsJsonObject();
                if(obj.get("isBeautyEye").getAsBoolean())
                {
                    BeautyEyeLNFHelper.launchBeautyEyeLNF();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Init Failed");
            e.printStackTrace();
        }
    }

    public static String getApplicationPath()
    {
        String path = System.getProperty("user.dir");
        return path;
    }

}
