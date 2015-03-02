package shared.definitions;

import java.awt.Color;

public enum CatanColor
{
	RED, ORANGE, YELLOW, BLUE, GREEN, PURPLE, PUCE, WHITE, BROWN;
	
	private Color color;
	
	static
	{
		RED.color = new Color(227, 66, 52);
		ORANGE.color = new Color(255, 165, 0);
		YELLOW.color = new Color(253, 224, 105);
		BLUE.color = new Color(111, 183, 246);
		GREEN.color = new Color(109, 192, 102);
		PURPLE.color = new Color(157, 140, 212);
		PUCE.color = new Color(204, 136, 153);
		WHITE.color = new Color(223, 223, 223);
		BROWN.color = new Color(161, 143, 112);
	}
	
	public Color getJavaColor()
	{
		return color;
	}
	
	public static String getStringColor(CatanColor color) {
			switch(color) {
			case BLUE:
				return "blue";
			case BROWN:
				return "brown";
			case GREEN:
				return "green";
			case ORANGE:
				return "orange";
			case PUCE:
				return "puce";
			case PURPLE:
				return "purple";
			case RED:
				return "red";
			case WHITE:
				return "white";
			case YELLOW:
				return "yellow";
			default:
				return null;
		}
	}
	
	public static CatanColor getCatanColor(String color) {
		switch (color) {
		case "blue":
			return CatanColor.BLUE;
		case "brown":
			return CatanColor.BROWN;
		case "green":
			return CatanColor.GREEN;
		case "orange":
			return CatanColor.ORANGE;
		case "puce":
			return CatanColor.PUCE;
		case "purple":
			return CatanColor.PURPLE;
		case "red":
			return CatanColor.RED;
		case "white":
			return CatanColor.WHITE;
		case "yellow":
			return CatanColor.YELLOW;
		default:
			return null;
		}
	}
}

