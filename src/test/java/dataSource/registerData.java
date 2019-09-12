package dataSource;

import org.testng.annotations.DataProvider;

public class registerData {
    //测试数据抽离至对应的方法
    @DataProvider(name = "email")
    public static Object[][] emailDataProvider(){
        return new Object[][]{
                {""},
                {"dhqwoifhqw"},
                {"asdfgh.asd"},
                {""}
        };
    }

    @DataProvider(name = "password")
    public static Object[][] passwordDataProvider(){
        return new Object[][]{
                {""},
                {"12345"},
                {"012345678901234567890"},
                {"!@#$"},
                {"s1@"},
                {"123$s"}
        };
    }

    @DataProvider(name = "repassword")
    public static Object[][] repasswordDataProvider(){
        return new Object[][]{
                {""},
                {"51541"},
                {"54654654654646546546465464654654654684984231"},
                {"!@#$"},
                {"sad@#"},
                {"qw2#"}
        };
    }

    @DataProvider(name = "pwd_compare")
    public static Object[][] pwd_compareDataProvider(){

        return new Object[][]{
                {"1234567","1234568"},
                {"123@E#@!","asdbuwf@"},
                {"asdfghjklz0123456789","lkjhgfdsaz9876543210"},
                {"@!$!@@!#","@$$@!%@#"}
        };

    }







}
