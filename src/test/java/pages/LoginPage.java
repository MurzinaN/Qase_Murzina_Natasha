package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    public void login(String userName, String password) {
        open("/login");
        $("#inputEmail").setValue(userName);
        $("#inputPassword").setValue(password);
        $("#btnLogin").click();

    }

    public String getMessageErrorText() {
        return $(".form-control-feedback").getText();
    }
}
