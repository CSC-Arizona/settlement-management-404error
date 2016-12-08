package model.items;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import model.armor.GreatChestPlate;
import model.armor.*;
import model.weapons.*;

public enum CraftableEnum {
    ANTLARVAPIE(AntLarvaPieCookable.getRequiredMaterials(), "Ant larva pie", AntLarvaPieCookable.class),
			    APPLEPIE(ApplePieCookable.getRequiredMaterials(), "Apple pie", ApplePieCookable.class),
			    BREAD(BreadCookable.getRequiredMaterials(), "Bread", BreadCookable.class),
			    GREATCHESTPLATE(GreatChestPlate.getRequiredMaterials(), "Great chestplate", GreatChestPlate.class),
			    GREATSHIELD(GreatShield.getRequiredMaterials(), "Greatshield", GreatShield.class), 
			    IRONCHESTPLATE(IronChestPlate.getRequiredMaterials(), "Iron chestplate", IronChestPlate.class),
			    IRONSHIELD(IronShield.getRequiredMaterials(), "Iron shield", IronShield.class),
			    STONECHESTPLATE(StoneChestPlate.getRequiredMaterials(), "Stone chestplate", StoneChestPlate.class),
			    STONESHIELD(StoneShield.getRequiredMaterials(), "Stone shield", StoneShield.class),
			    WOODCHESTPLATE(WoodChestPlate.getRequiredMaterials(), "Wood chestplate", WoodChestPlate.class),
			    WOODSHIELD(WoodShield.getRequiredMaterials(), "Wood shield", WoodShield.class),
			    BASICIRONAXE(BasicIronAxe.getRequiredMaterials(), "Basic iron axe", BasicIronAxe.class),
			    BASICSTONEAXE(BasicStoneAxe.getRequiredMaterials(), "Basic stone axe", BasicStoneAxe.class),
			    BASICSWORD(BasicSword.getRequiredMaterials(), "Basic sword", BasicSword.class),
			    FORTIFIEDIRONAXE(FortifiedIronAxe.getRequiredMaterials(), "Fortified iron axe", FortifiedIronAxe.class),
			    FORTIFIEDSTONEAXE(FortifiedStoneAxe.getRequiredMaterials(), "Fortified stone axe", FortifiedStoneAxe.class),
			    ULTRASWORD(UltraSword.getRequiredMaterials(), "Ultra sword", UltraSword.class);
    
    private List<Item> reqItems;
	private String text;
	private Class craftableClass;
	
    private CraftableEnum(List<Item> reqItems, String text, Class craftableClass) {
    	this.reqItems = reqItems;
    	this.text = text;
    	this.craftableClass = craftableClass;
    }
    
    public List<Item> getReqItems() {
    	return reqItems;
    }
    
    public String getText() {
    	return text;
    }
    
    public static CraftableEnum[] getAllCraftables() {
    	return CraftableEnum.values();
    }
    
    public static String[] getAllCraftableNames() {
    	CraftableEnum[] craftables = getAllCraftables();
    	String[] result = new String[craftables.length];
    	
    	for (int i = 0; i < craftables.length; i++) {
    		result[i] = craftables[i].getText();
    	}
    	
    	return result;
    }
    
    public static CraftableEnum getRoomFromString(String roomName) {
		CraftableEnum[] craftables = getAllCraftables();
		String[] craftableNames = getAllCraftableNames();
		for (int i = 0; i < craftableNames.length; i++) {
			if (craftableNames[i].equals(roomName)) {
				return craftables[i];
			}
		}
		return craftables[0];
	}

	public Item constructObject() {
		try {
			Constructor<?> ctor = craftableClass.getConstructor();
			Object object = ctor.newInstance(new Object[] {  });
			return (Item) object;
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
		return new AntLarvaPieCookable();
	}
}
