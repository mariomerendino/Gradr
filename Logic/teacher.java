package Logic;
import java.util.*;
import java.io.*;

public class teacher{
    public static String password;
    public static String username;
    public static String firstName;
    public static String lastName;
    public teacher(){

    }
    public static void setuserName(String n){
        username = n;
    }
    public static void setfirstName(String n){
        firstName = n;
    }
    public static void setlastName(String n){
        lastName = n;
    }
    public static String getuserName(){
        return username;
    }
    public static String getfirstName(){
        return firstName;
    }
    public static String getlastName(){
        return lastName;
    }
    public void setpassword(String p){
        password = p;
    }
    public static String getpassword(){
        return password;
    }
}