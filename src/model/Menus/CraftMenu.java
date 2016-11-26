package model.Menus;

import java.util.HashMap;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.Armor.GreatChestPlate;
import model.Armor.GreatShield;
import model.Armor.IronChestPlate;
import model.Armor.IronShield;
import model.Armor.StoneChestPlate;
import model.Armor.StoneShield;
import model.Armor.WoodChestPlate;
import model.Armor.WoodShield;
import model.Items.AntLarvaPieCookable;
import model.Items.ApplePieCookable;
import model.Items.BreadCookable;
import model.Weapons.BasicIronAxe;
import model.Weapons.BasicStoneAxe;
import model.Weapons.BasicSword;
import model.Weapons.FortifiedIronAxe;
import model.Weapons.FortifiedStoneAxe;
import model.Weapons.LureAxe;
import model.Weapons.UltraSword;

//Author: Maxwell Faridian
//This class will be used in the GUI to allow the user to see what items can be crafted,
//and what items they need to craft said item
public class CraftMenu implements TableModel {
	
	private HashMap<String, List> craftableItems;
	private static CraftMenu craftMenu;
	
	private CraftMenu() {
		craftableItems = new HashMap<>();
		addCraftableItems();
	}
	
	public static synchronized CraftMenu getInstance() {
		if(craftMenu == null) {
			craftMenu = new CraftMenu();
		}
		return craftMenu;
	}
	
	//TODO: Figure out if we want cookable items to be added to this list, or if they should have their own menu/process of creation
	private void addCraftableItems() {
		//Cookable Items
		craftableItems.put("Ant Larva Pie", new AntLarvaPieCookable().getRequiredIngredients());
		craftableItems.put("Apple Pie", new ApplePieCookable().getRequiredIngredients());
		craftableItems.put("Bread", new BreadCookable().getRequiredIngredients());
		
		//Armor
		craftableItems.put("Wood Shield", new WoodShield().getRequiredMaterials());
		craftableItems.put("Stone Shield", new StoneShield().getRequiredMaterials());
		craftableItems.put("Iron Shield", new IronShield().getRequiredMaterials());
		craftableItems.put("Great Shield", new GreatShield().getRequiredMaterials());
		craftableItems.put("Wood Chest Plate", new WoodChestPlate().getRequiredMaterials());
		craftableItems.put("Stone Chest Plate", new StoneChestPlate().getRequiredMaterials());
		craftableItems.put("Iron Chest Plate", new IronChestPlate().getRequiredMaterials());
		craftableItems.put("Great Chest Plate", new GreatChestPlate().getRequiredMaterials());
		
		//Weapons
		craftableItems.put("Basic Stone Axe", new BasicStoneAxe().getRequiredMaterials());
		craftableItems.put("Fortified Stone Axe", new FortifiedStoneAxe().getRequiredMaterials());
		craftableItems.put("Basic Iron Axe", new BasicIronAxe().getRequiredMaterials());
		craftableItems.put("Fortified Iron Axe", new FortifiedIronAxe().getRequiredMaterials());
		craftableItems.put("Lure Axe", new LureAxe().getRequiredMaterials());
		craftableItems.put("Basic Sword", new BasicSword().getRequiredMaterials());
		craftableItems.put("Ultra Sword", new UltraSword().getRequiredMaterials());
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0)
			return String.class;
		else 
			return List.class;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public String getColumnName(int columnIndex) {
		if (columnIndex == 0)
			return "Craftable Item";
		else
			return "Required Materials";
	}

	@Override
	public int getRowCount() {
		return 18;
	}

	@Override
	public Object getValueAt(int row, int col) {
		String selected;
		if (row == 0)
			selected = "Ant Larva Pie";
		else if (row == 1)
			selected = "Apple Pie";
		else if (row == 2)
			selected = "Bread";
		else if (row == 3)
			selected = "Wood Shield";
		else if (row == 4)
			selected = "Stone Shield";
		else if (row == 5)
			selected = "Iron Shield";
		else if (row == 6)
			selected = "Great Shield";
		else if (row == 7)
			selected = "Wood Chest Plate";
		else if (row == 8)
			selected = "Stone Chest Plate";
		else if (row == 9)
			selected = "Iron Chest Plate";
		else if (row == 10)
			selected = "Great Chest Plate";
		else if (row == 11)
			selected = "Basic Stone Axe";
		else if (row == 12)
			selected = "Fortified Stone Axe";
		else if (row == 13)
			selected = "Basic Iron Axe";
		else if (row == 14)
			selected = "Fortifed Iron Axe";
		else if (row == 15)
			selected = "Lure Axe";
		else if (row == 16)
			selected = "Basic Sword";
		else
			selected = "Ultra Sword";
		if (col == 0)
			return selected;
		else
			return craftableItems.get(selected);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {	
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

}
