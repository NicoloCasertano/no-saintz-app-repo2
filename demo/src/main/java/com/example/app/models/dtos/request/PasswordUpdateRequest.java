package com.example.app.models.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PasswordUpdateRequest {

    @NotEmpty(message = "Inserisci la tua vecchia password")
    @Size(min = 8, max = 32, message = "La vecchia password deve essere tra 8 e 32 caratteri")
    private String oldPasssword;

    @NotEmpty(message = "La password è obbligatoria")
    @Size(min = 8, max = 32, message = "La nuova password deve contenere tra gli 8 e i 32 caratteri")
    private String newPassword;

    @NotEmpty(message = "Conferma password")
    @Size(min = 8, max = 32, message = "La nuova password deve contenere tra gli 8 e i 32 caratteri")
    private String newPasswordRepeat;

    public PasswordUpdateRequest(String oldPasssword, String newPassword, String newPasswordRepeat) {
        this.oldPasssword = oldPasssword;
        this.newPassword = newPassword;
        this.newPasswordRepeat = newPasswordRepeat;
    }

    public String getOldPasssword() {
        return oldPasssword;
    }

    public void setOldPasssword(String oldPasssword) {
        this.oldPasssword = oldPasssword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordRepeat() {
        return newPasswordRepeat;
    }

    public void setNewPasswordRepeat(String newPasswordRepeat) {
        this.newPasswordRepeat = newPasswordRepeat;
    }
}
