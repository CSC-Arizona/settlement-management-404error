package model.Furniture;

import java.util.LinkedList;
import java.util.List;

import model.Items.AntLarvaItem;
import model.Items.Item;
import model.Items.WheatKernelItem;

//Author: Maxwell Faridian
//This class defines the Wheat Plant Plot, which can grow Wheat when placed in a Farm room
//Wheat Plant Plot: 2 Wheat Kernel, 2 Ant Larva 
public class WheatPlantPlot extends Furniture {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4503399336706335364L;
	private static int capacity = 1;
	private List<Item> wheatPlantPlotList;

	public WheatPlantPlot() {
		super(capacity, 0, "wheat plot");
		wheatPlantPlotList = new LinkedList<>();
		wheatPlantPlotList.add(new WheatKernelItem());
		wheatPlantPlotList.add(new WheatKernelItem());
		wheatPlantPlotList.add(new AntLarvaItem());
		wheatPlantPlotList.add(new AntLarvaItem());
	}

	@Override
	public List<Item> getRequiredMaterials() {
		return wheatPlantPlotList;
	}

}
