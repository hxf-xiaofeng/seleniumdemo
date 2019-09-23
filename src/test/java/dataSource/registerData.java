package dataSource;

import org.testng.annotations.DataProvider;

public class registerData {
    //测试数据抽离至对应的方法
    //非法邮箱
    @DataProvider(name = "email")
    public static Object[][] emailDataProvider(){
        return new Object[][]{
                {""},
                {"dhqwoifhqw"},
                {"asdfgh.asd"},
                {"xiaofeng@asd.wpof"},
                {"xiaofeng@asd.w"},
                {"@123.com"},
                {"xiaofeng@a.w"},
                {"xiaofeng$qq.com"}
        };
    }
    //非法密码
    @DataProvider(name = "password")
    public static Object[][] passwordDataProvider(){
        return new Object[][]{
                {""},
                {"12345"},
                {"012345678901234567890"},
                {"!@#$%"},
                {"!@#$%^&*()!@#$%^&*()!"},
                {"abcde"},
                {"abcdefghijklnmopqrstu"},
                {"1a!2b"},
                {"!sd.sabcde12345!@#$%!"},
                {"1234a"},
                {"1234567890abcdefghijk"},
                {"a!a!a"},
        };
    }
    //非法确认密码
    @DataProvider(name = "repassword")
    public static Object[][] repasswordDataProvider(){
        return new Object[][]{
                {""},
                {"51541"},
                {"54654654654646546546465464654654654684984231"},
                {"!@#$1"},
                {"sad@#"},
                {"qw2#"}
        };
    }
    //不同密码
    @DataProvider(name = "pwd_compare")
    public static Object[][] pwd_compareDataProvider(){

        return new Object[][]{
                {"1234567","1234568"},
                {"123@E#@!","asdbuwf@"},
                {"asdfghjklz0123456789","lkjhgfdsaz9876543210"},
                {"@!$!@@!#","@$$@!%@#"}
        };
    }

    @DataProvider(name = "register")
    public static Object[][] reigsterDataProvider(){
        return new Object[][]{
                {"xiao@qq.com","123456"},
                {"xiao@163.com","abcdef"},
                {"xiao@126.com","1234567890123456789"},
                {"xiao@sina.com","abcdefghijklnmopqrs"},
                {"xiao@sina.cn","123abc"},
                {"xiao@yeah.net","1234567890abcdefghi"},
                {"xiao@aliyun.com","123456!"}
        };
    }







}
