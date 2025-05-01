package com.Automation.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
	
	public static String readDatafromConfigPropertiesFile(String filePath, String key)
	{
		File file = new File(filePath);
		FileInputStream fis = null;
		Properties configPropFile =  new Properties();
		String data = null;
		
		try 
		{
			fis = new FileInputStream(file);// TO access the file we need FileINpitStream
			configPropFile.load(fis);// to do read/ "load()" need this Properties class methods 
			data = configPropFile.getProperty(key);
			fis.close();
			
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
		
		return data;
	}
	
	public static void writetoConfigProperties(String filePath, String key, String value)
	{
		Properties configPropFile = new Properties();
		FileOutputStream fio = null;
		File file = new File(filePath);
		try
		{
			fio = new FileOutputStream(file);
			configPropFile.setProperty(key, value);
			configPropFile.store(fio, "Adding new property to the Confog PRoperties file");//To save the newly added key:value pair use store() method
			fio.close();
			
		}
		catch (FileNotFoundException e) {
//			System.out.println(".............error in file path....................");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			System.out.println("..............error while loading property file..........");
			e.printStackTrace();
		}
	}
}


