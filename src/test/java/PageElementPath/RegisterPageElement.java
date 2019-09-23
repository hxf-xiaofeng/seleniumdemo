package PageElementPath;

public class RegisterPageElement {
    public String Email_id ="email";
    public String Password_id="pwd";
    public String Repassword_id="repwd";
    public String ReferralIDs_name="pid";
    public String Register_button_id="stepclick";
    public String login_label_XPATH="//*[@id=\"step1\"]/div/p[9]/span/a/span";

    public String Email_error_msg_XPATH ="//*[@id=\"emailmsg\"]/font";
    public String Email_empty_XPATH="//*[@id=\"emailmsg\"]";

    public String Password_Error_msg_XPATH="//*[@id=\"pwdmsg\"]/font";
    public String Password_Empty_XPATH="//*[@id=\"pwdmsg\"]";

    public String RePassword_error_msg_XPATH="//*[@id=\"repwdmsg\"]/font";
    public String RePassword_Empty_XPATH="//*[@id=\"repwdmsg\"]";

    public String reffalIDs_error_msg_XPATH="//*[@id=\"pidmsg\"]";
}
