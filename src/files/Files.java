package files;

import File.File;

public enum Files {
	
	LANGUAGE_EN(new File("language/en")),
	UUID(new File("UUID/UUID"));
	
	private File file;
	
	private Files(File file){
		this.file = file;
	}

	public File getFile() {
		return file;
	}

}
