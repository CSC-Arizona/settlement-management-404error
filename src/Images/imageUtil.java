package Images;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import com.sun.javafx.util.Utils;

public class imageUtil {

	public static BufferedImage makeTransparent(BufferedImage image, int x, int y) {
	    ColorModel cm = image.getColorModel();
	    if (!(cm instanceof IndexColorModel))
	        return image; // sorry...
	    IndexColorModel icm = (IndexColorModel) cm;
	    WritableRaster raster = image.getRaster();
	    int pixel = raster.getSample(x, y, 0);
	    int size = icm.getMapSize();
	    byte[] reds = new byte[size];
	    byte[] greens = new byte[size];
	    byte[] blues = new byte[size];
	    icm.getReds(reds);
	    icm.getGreens(greens);
	    icm.getBlues(blues);
	    IndexColorModel icm2 = new IndexColorModel(8, size, reds, greens, blues, pixel);
	    return new BufferedImage(icm2, raster, image.isAlphaPremultiplied(), null);
	}
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	public static BufferedImage resizeImage(BufferedImage image, int width, int height) {
		return toBufferedImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}
	
}
