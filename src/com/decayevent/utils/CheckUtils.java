package com.decayevent.utils;

import java.io.Console;

import javax.swing.JOptionPane;

import com.google.gson.JsonObject;

public class CheckUtils {
	
	public static boolean Check(String username, String password)
	{
		
		JsonObject json = new JsonObject();
		JsonObject json2 = new JsonObject();
		
		json2.addProperty("name", "Minecraft");
		json2.addProperty("version", 1);
		
		json.add("agent", json2);
		json.addProperty("password", password);
		json.addProperty("requestUser", true);
		json.addProperty("username", username);
		
		String retCode = HttpUtils.getJsonData(json, "https://authserver.mojang.com/authenticate");

		if(retCode.contains("accessToken"))
		{
			return true;
		}
		return false;
	}
	
}
