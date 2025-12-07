package Modul09;

import java.io.Serializable;

public class UserConfig  implements Serializable{
    private String username;
    private int fontsize;
    
    private String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public int getFontsize() {
        return fontsize;
    }
    
    public void setFontsize() {
        this.fontsize = fontsize;
    }
}
