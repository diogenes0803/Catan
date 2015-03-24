package server.jsonConverters;

import java.io.InputStream;
import com.google.gson.Gson;
import shared.communicator.RegisterUserParams;

public class RegisterUserConverter {
	
	public RegisterUserParams convert(InputStream is)
	{
		RegisterUserParams params = new RegisterUserParams();
		
		Gson gson = new Gson();
		params = gson.fromJson(is.toString(), RegisterUserParams.class);
		
		return params;
	}


}
