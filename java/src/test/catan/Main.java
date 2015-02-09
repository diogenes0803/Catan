package test.catan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
			JsonModelHolder jsonModel = thisGame.toJsonModel();
			String jsonString = gson.toJson(jsonModel);
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(filePath+"/java/src/TestJSONout.json"));
				bw.write(jsonString);
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
