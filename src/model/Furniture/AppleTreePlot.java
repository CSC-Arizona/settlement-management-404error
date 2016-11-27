package model.Furniture;

import java.util.LinkedList;
import java.util.List;

import model.Items.AntLarvaItem;
import model.Items.AppleSeedItem;
import model.Items.Item;

//Author: Maxwell Faridian
//This class defines the Apple Tree Plot, which can grow apple trees when placed in a Farm room
//Apple Tree Plant Plot: 2 Apple Seeds, 2 Ant Larva
public class AppleTreePlot extends Furniture {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1305489344104345880L;
	private static int capacity = 1;
	private List<Item> appleTreePlotList;

	public AppleTreePlot() {
		super(capacity, 0, "apple tree plot");
		appleTreePlotList = new LinkedList<>();
		appleTreePlotList.add(new AppleSeedItem());
		appleTreePlotList.add(new AppleSeedItem());
		appleTreePlotList.add(new AntLarvaItem());
		appleTreePlotList.add(new AntLarvaItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return appleTreePlotList;
	}

}
