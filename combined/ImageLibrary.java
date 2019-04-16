import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
//import java.net.URL;
//import java.net.MalformedURLException;

public class ImageLibrary{
    private Image[] images = new Image[11];
    //private String path = "https://web.cs.sunyit.edu/~schneieh/images/";
    private String path = "images/";
    //private URL dummy;
    //private URL base;
	private int maxMineNum = 8;
    //Constructor
    public ImageLibrary(){

        for(int i = 0; i < 11; i++ ){

            System.out.println("image " + i + " loading");
            try
            {
                images[i] = ImageIO.read(new File(path+"Number"+i+".png"));
                //images[i]= ImageIO.read(dummy);
            }
            catch (IOException e)
            {
            }
            if(images[i]==null)
                System.out.println("Path Not Found");
        }
    }
    //Returns the Image from the ImageIcon array(Skips a step 4 u)
    public Image getImage(int numMines){
        //System.out.println(numMines + " gotten");
        return images[numMines].getScaledInstance(85, 85, Image.SCALE_FAST);
    }
	public int maxMineNum()
	{
		return maxMineNum;
	}

}
