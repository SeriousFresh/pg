package model;

import java.io.File;
import java.util.ArrayList;

public class Model {
	
	private ArrayList<String> soundPaths;

	public Model()
	{
		super();
		soundPaths = new ArrayList<String>();
		File dir = new File("src/sounds");
		File[] files = dir.listFiles();
		
		for(int j = 0; j < files.length; j++)
		{
			String name = files[j].getName().substring(0, files[j].getName().lastIndexOf("."));
			this.soundPaths.add(name);
		}
	}
	
	public ArrayList<String> getSoundPaths() {
		return soundPaths;
	}

	public void setSoundPaths(ArrayList<String> soundPaths) {
		this.soundPaths = soundPaths;
	}
	
	
	
}
