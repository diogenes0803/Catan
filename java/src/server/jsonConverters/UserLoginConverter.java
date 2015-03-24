package server.jsonConverters;

import java.io.InputStream;

import com.google.gson.Gson;

import shared.communicator.UserLoginParams;

public class UserLoginConverter {
	
	public UserLoginParams convert(InputStream is)
	{
		UserLoginParams params = new UserLoginParams();
		
		Gson gson = new Gson();
		params = gson.fromJson(is.toString(), UserLoginParams.class);
		
		return params;
	}

}
