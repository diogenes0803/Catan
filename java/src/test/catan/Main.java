package test.catan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import shared.models.Game;
import shared.models.jsonholder.JsonModelHolder;

import com.google.gson.Gson;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("This is test");
		try {
			String filePath = new File("").getAbsolutePath();
			BufferedReader br = new BufferedReader(new FileReader(filePath+"/java/src/TestJSON.json"));
			Gson gson = new Gson();
			JsonModelHolder modelHolder = gson.fromJson(br, JsonModelHolder.class);
			System.out.println("Deserialize Done");
			Game thisGame = modelHolder.buildCatanModel();
			thisGame.getVersion();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
