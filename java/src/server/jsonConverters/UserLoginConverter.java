package server.jsonConverters;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.gson.Gson;

import shared.communicator.UserLoginParams;

public class UserLoginConverter {
	
	public UserLoginParams convert(InputStream is)
	{
		UserLoginParams params = new UserLoginParams();
		Gson gson = new Gson();
	
		
		String qry= "";
		String encoding = "ISO-8859-1";
		try {
		    ByteArrayOutputStream out = new ByteArrayOutputStream();
		    byte buf[] = new byte[4096];
		    for (int n = is.read(buf); n > 0; n = is.read(buf)) {
		        out.write(buf, 0, n);
		    }
		    qry = new String(out.toByteArray(), encoding);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		params = gson.fromJson(qry.toString(), UserLoginParams.class);

		return params;
	}

}
