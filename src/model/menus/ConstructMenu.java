package model.menus;

import java.util.HashMap;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.actors.Position;
import model.room.BedRoom;
import model.room.EntertainmentRoom;
import model.room.FarmRoom;
import model.room.HorizontalTunnel;
import model.room.InfirmaryRoom;
import model.room.StoreRoom;
import model.room.VerticalTunnel;

/**
 * This Construction Menu is meant to be used in the GUI so that when a user selects a square,
 * they can see all of the different options to build there. When I have more time I'm planning
 * on making a toString type method in Room that displays the required material in "# Material1s, 
 * # Material2s, etc required". 
 * 
 * @author Katherine Walters
 *
 */
public class ConstructMenu implements TableModel {
	
	private HashMap<String, String> rooms;
	private static ConstructMenu constructMenu;
	
	public ConstructMenu() {
		rooms = new HashMap<>();
		addRooms();
	}

	public static synchronized ConstructMenu getInstance() {
		if(constructMenu == null) {
			constructMenu = new ConstructMenu();
		}
		return constructMenu;
	}
	
	private void addRooms() {
		rooms.put("Bedroom", new BedRoom(new Position(0, 0)).reqMaterialsToString());
		rooms.put("Entertainment Room", new EntertainmentRoom(new Position(0,0)).reqMaterialsToString());
		rooms.put("Farm Room", new FarmRoom(new Position(0,0)).reqMaterialsToString());
		rooms.put("Horizontal Tunnel", new HorizontalTunnel(new Position(0,0)).reqMaterialsToString());
		rooms.put("Infirmary", new InfirmaryRoom(new Position(0,0)).reqMaterialsToString());
		rooms.put("Storeroom", new StoreRoom(new Position(0,0)).reqMaterialsToString());
		rooms.put("Vertical Tunnel", new VerticalTunnel(new Position(0,0)).reqMaterialsToString());
	}
	
	public HashMap<String, String> getRooms() {
		return rooms;
	}

	@Override
	public void addTableModelListener(TableModelListener l) {}

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
			return "Room";
		else
			return "Required Materials";
	}

	@Override
	public int getRowCount() {
		return 8;
	}

	@Override
	public Object getValueAt(int row, int col) {
		String selected;
		if (row == 0)
			selected = "Bedroom";
		else if (row == 1)
			selected = "Entertainment Room";
		else if (row == 2)
			selected = "Farm Room";
		else if (row == 3)
			selected = "Horizontal Tunnel";
		else if (row == 4)
			selected = "Infirmary";
		else if (row == 5)
			selected = "Kitchen";
		else if (row == 6)
			selected = "Storeroom";
		else
			selected = "Vertical Tunnel";
		if (col == 0)
			return selected;
		else
			return rooms.get(selected);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}

}
