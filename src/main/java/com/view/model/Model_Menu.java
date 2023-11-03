package com.view.model;

import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Model_Menu {

    private String dir = null;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public Model_Menu(String icon, String name, MenuType type) {
        String path = "src\\main\\java\\com\\view\\icon";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        dir = absolutePath;
        this.icon = icon;
        this.name = name;
        this.type = type;
    }

    public Model_Menu() {

    }

    private String icon;
    private String name;
    private MenuType type;

    public Icon toIcon() {
        return new ImageIcon(dir+"\\"+icon+".png");
    }

    public static enum MenuType {
        TITLE, MENU, EMPTY
    }
}
