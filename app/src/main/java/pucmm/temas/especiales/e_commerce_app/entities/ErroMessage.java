package pucmm.temas.especiales.e_commerce_app.entities;

public class ErroMessage {
    private boolean success;
    private boolean error;
    private String message;

    public ErroMessage() { }

    public ErroMessage(boolean success, boolean error, String message) {
        this.success = success;
        this.error = error;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
