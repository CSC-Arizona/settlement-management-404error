package images;

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
			new String[] { "stone.png" }), LADDER(new String[] { "ladder.png" }), BED(
			new String[] { "bed.png" }), CRATE(new String[] { "crate.png" }), ANT_LEFT(
			new String[] { "ant_left.png" }), ANT_RIGHT(
			new String[] { "ant_right.png" }), DRAGON_LEFT(
			new String[] { "dragon_left.png" }), DRAGON_RIGHT(
			new String[] { "dragon_right.png" }), GRASSBLOCK(
			new String[] { "grassblock.png" }), ANTTUNNEL(
			new String[] { "tunnel.png" }), MEDBED(
			new String[] { "medbed.png" }), SPACESHIPCENTER(
			new String[] { "spaceship_center.png" }), SPACESHIPLIGHT(
			new String[] { "spaceship_light.png" }), SPACESHIPBODY(
			new String[] { "spaceship_body.png" }), SPACESHIPPARTBLOCK(
			new String[] { "space_ship_block.png" }), SEED(
			new String[] { "seed.png" }), ANTIMATTERDEFENESTRATOR(
			new String[] { "antimatter_defenestrator.png" }), BLACKHOLEGENERATOR(
			new String[] { "black_hole_generator.png" }), RETROENCABULATOR(
			new String[] { "retroencabulator.png" }), APPLEPIE(
			new String[] { "apple_pie.png" }), APPLESEED(
			new String[] { "apple_seed.png" }), MUSHROOMBLOCK(
			new String[] { "mushroomblock.png" }), MUSHROOMFRUITBLOCK(
			new String[] { "mushroomfruitblock.png" }), EGG(
			new String[] { "egg.png" }), MATERIALPILE(
		    new String[] { "materialPile.png" }), TRAPDOOR(
		    new String[] { "trapdoor.png" });

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
				File file = new File("src/resources/images/" + filename);
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
