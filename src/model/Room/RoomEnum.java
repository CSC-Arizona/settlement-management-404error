package model.Room;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import model.Actors.Position;

public enum RoomEnum {
	BEDROOM(2, 12, "Bedroom", BedRoom.class), ENTERTAINMENT(2, 10,
			"Entertainment room", EntertainmentRoom.class), FARM_ROOM(2, 8,
			"Farm room", FarmRoom.class), HORIZONTAL_TUNNEL(1, 2,
			"Horizontal tunnel", HorizontalTunnel.class), INCUBATION_ROOM(2,
			12, "Incubation room", IncubationRoom.class), INFIRMARY_ROOM(2, 4,
			"Infirmary", InfirmaryRoom.class), KITCHEN_ROOM(2, 6, "Kitchen",
			KitchenRoom.class), STORE_ROOM(2, 8, "Store room", StoreRoom.class), VERTICAL_TUNNEL(
			2, 1, "Vertical tunnel", VerticalTunnel.class);

	private int height;
	private int width;
	private String text;
	private Class roomClass;

	private RoomEnum(int height, int width, String text, Class roomClass) {
		this.height = height;
		this.width = width;
		this.text = text;
		this.roomClass = roomClass;

	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public String toString() {
		return text;
	}

	public static RoomEnum[] getAllRooms() {
		return RoomEnum.values();
	}

	public static String[] getAllRoomNames() {
		RoomEnum[] rooms = getAllRooms();
		String[] result = new String[rooms.length];

		for (int i = 0; i < rooms.length; i++) {
			result[i] = rooms[i].toString();
		}

		return result;
	}

	public static RoomEnum getRoomFromString(String roomName) {
		RoomEnum[] rooms = getAllRooms();
		String[] roomNames = getAllRoomNames();
		for (int i = 0; i < roomNames.length; i++) {
			if (roomNames[i].equals(roomName)) {
				return rooms[i];
			}
		}
		return rooms[0];
	}

	public Room constructObject(Position position) {
		try {
			Constructor<?> ctor = roomClass.getConstructor(Position.class);
			Object object = ctor.newInstance(new Object[] { position });
			return (Room) object;
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new BedRoom(position);
	}
}
