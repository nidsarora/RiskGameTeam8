package risk.helpers;

public final class Utility {
	
	public static String getImagePath(String imageNameWithExtention)
	{
		return "/risk/resources/images/" + imageNameWithExtention;
	}
	
	public static String getMapPath(String mapNameWithExtension)
	{
		return "/risk/resources/map/" + mapNameWithExtension;
	}
	
	public static String getMapPathforFile(String mapNameWithExtension)
	{
		return "src/risk/resources/map/" + mapNameWithExtension;
	}
}
