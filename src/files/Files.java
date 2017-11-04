package files;

import File.File;

public enum Files {
	
	TEST(new File("Test/test")),
	UUID(new File("UUID/UUID"));
	
	private File file;
	
	private Files(File file){
		this.file = file;
	}

	public File getFile() {
		return file;
	}

}
