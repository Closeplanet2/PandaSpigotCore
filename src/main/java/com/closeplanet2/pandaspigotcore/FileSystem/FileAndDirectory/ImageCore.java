package com.closeplanet2.pandaspigotcore.FileSystem.FileAndDirectory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCore {
    public static BufferedImage OpenFile(String filePath){ return OpenFile(new File(filePath)); }
    public static BufferedImage OpenFile(File textureFile){
        try { return ImageIO.read(textureFile);
        } catch (IOException e) { return null; }
    }
}
