package com.company;

import javax.swing.*;
import java.awt.*;

public class ImageLibrary {
    private ImageIcon[] images = new ImageIcon[11];
    private String path = "C:\\Users\\richa\\Documents\\Classes\\MineSweeperProject\\images\\Number";
 //Constructor
    public ImageLibrary(){
       path = path.replace("\\","/");
        for(int i = 0; i<11;i++){
            images[i] = new ImageIcon(path+i+".png");
            if(images[i]==null)
                System.out.println("Path Not Found");
        }
    }
    //Returns the Image from the ImageIcon array(Skips a step 4 u)
    public Image getImage(int numMines){
        if(numMines ==0)
            return images[10].getImage().getScaledInstance(86,86,Image.SCALE_FAST);
        return images[numMines].getImage().getScaledInstance(86,86,Image.SCALE_FAST);
    }

}
