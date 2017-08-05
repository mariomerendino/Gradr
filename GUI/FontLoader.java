package GUI;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import GUI.*;
import Logic.*;
import java.io.*;

public class FontLoader {

    public static Font SFDisplay;
    public static Font SFText;
    public static Font SFDisplayBold;
    public static Font headerFont;
    public static Font subheaderFont;
    public static Font navBarFont;
    public static Font navBarIconFont;
    public static Font loginFont;
    public static Font tableFont;
    public static Font boldHeaderFont;
    public static Font subheaderBold;
    
    // constructor
    public FontLoader(){
        try {
            SFDisplay = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/SF-UI-Display/SF-UI-Display-Regular.ttf")).deriveFont(12f);
            SFText = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/SF-UI-Text/SF-UI-Text-Regular.ttf")).deriveFont(12f);
            SFDisplayBold = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/SF-UI-Display/SF-UI-Display-Bold.ttf")).deriveFont(40f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(SFDisplay);
            ge.registerFont(SFText);
        } catch (FontFormatException e) {
            System.out.println("FONT ERROR");
        } catch (IOException ioe) {
            System.out.println("IO ERROR");
        }
        
        headerFont = SFDisplay.deriveFont(48f);
        subheaderFont = SFDisplay.deriveFont(22f);
        navBarFont = SFDisplay.deriveFont(18f);
        navBarIconFont = SFDisplay.deriveFont(22f);
        loginFont = SFDisplay.deriveFont(14f);
        tableFont = SFDisplay.deriveFont(16f);
    }
}
