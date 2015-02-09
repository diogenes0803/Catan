package shared.communicator;

public class ResponseBodyContainer {

    String responseBody;
    boolean success;
    
    
    public ResponseBodyContainer(String _responseBody) {
        responseBody = _responseBody;
        success = false;
    }
    
    public ResponseBodyContainer() {
        responseBody = "";
        success = false;
    }
    
    
    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


}
