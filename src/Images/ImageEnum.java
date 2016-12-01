package Images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public enum ImageEnum {
	APPLE(new String[] { "apple.png" }), GRASS(new String[] { "grass.png" }), LEAF(
			new String[] { "foliage.png" }), WOOD(new String[] { "wood.png" }), DIRT(
			new String[] { "dirt1.png" }), LAVA(new String[] { "lava1.png",
			"lava2.png", "lava3.png", "lava4.png", "lava5.png" }), IRON(
			new String[] { "iron.png" }), GOLD(new String[] { "gold.png" }), STONE(
			new String[] { "stone.png" });

	private String[] filenames;
	private ArrayList<BufferedImage> images;
	private Random random;

	private ImageEnum(String[] filenames) {
		random = new Random();
		this.filenames = filenames;
	}

	public void createBufferedImages(int height, int width) {
		images = new ArrayList<>();
		BufferedImage img;
		try {
			for (String filename : filenames) {
				File file = new File("src/images/" + filename);
				img = ImageIO.read(file);
				BufferedImage resizedImg = imageUtil.resizeImage(img, width,
						height);
				BufferedImage transparentImg = imageUtil.makeTransparent(
						resizedImg, width, height);

				images.add(transparentImg);
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public BufferedImage getRandomBufferedImage() {
		return images.get(random.nextInt(images.size()));
	}
}
