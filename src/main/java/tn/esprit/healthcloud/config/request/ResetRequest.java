package tn.esprit.healthcloud.config.request;

public class ResetRequest {
    private String email;
    private String newPassword;
    private String confirmPassword;

    public String getEmailUser() {
        return email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setEmailUser(String emailUser) {
        this.email = emailUser;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
