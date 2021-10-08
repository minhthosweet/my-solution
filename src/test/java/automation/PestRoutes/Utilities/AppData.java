package automation.PestRoutes.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AppData {
	static Properties properties;
	public String generalData = "Properties/application.properties";
	public String quarterlyPreferredDayData = "Properties/quarterlybyprefferedday.properties";
	public static String environment = "Properties/env.properties";

	public static void loadData(String needFile) {

		try {

			properties = new Properties();
			ClassLoader classLoader = new AppData().getClass().getClassLoader();
			 
	        File file = new File(classLoader.getResource(needFile).getFile());
			FileInputStream fileData = new FileInputStream(file);

			properties.load(fileData);

		} catch (Exception e) {
			System.out.println("Exception is == " + e.getMessage());
		}
	}


	public static String getData(String needData, String needFile) throws IOException {
		loadData(needFile);
		String data = properties.getProperty(needData);
		return data;
	}

	public static void addData(String needKey, String needValue, String needFile) throws Exception {
		File location = new File(System.getProperty("user.dir") + "/src/test/resources/"+needFile);
		FileInputStream in = new FileInputStream(location);
		Properties props = new Properties();
		props.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream(location);
		props.setProperty(needKey, needValue);
		props.store(out, null);
		out.close();
	}

}
