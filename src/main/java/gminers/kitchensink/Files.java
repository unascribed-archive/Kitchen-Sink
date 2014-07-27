package gminers.kitchensink;


import java.io.File;
import java.io.IOException;


/**
 * Utilities for creating and deleting files that convert the boolean return values into exceptions if needed.
 * 
 * @author Aesen Vismea
 * 
 */
public class Files {
	public static void mkdirs(final File f)
			throws IOException {
		if (!f.mkdirs()) {
			File cursor = f.getAbsoluteFile();
			while (cursor.getParent() != null) {
				cursor = cursor.getParentFile();
				if (!cursor.exists()) {
					throw new IOException("Expected path " + cursor.getAbsolutePath()
							+ " to be created, but it wasn't!");
				}
			}
		}
	}
	
	public static void createNewFile(final File f)
			throws IOException {
		if (!f.createNewFile()) {
			if (!f.exists()) {
				throw new IOException("Could not create file " + f.getAbsolutePath());
			}
		}
	}
	
	public static void deleteNowOrOnExit(final File f) {
		if (f.exists()) {
			if (!f.delete()) {
				System.err.println("Failed to delete file " + f.getAbsolutePath() + " - deleting on exit instead.");
				f.deleteOnExit();
			}
		}
	}
	
	public static void delete(final File f)
			throws IOException {
		if (f.exists()) {
			if (!f.delete()) {
				throw new IOException("Failed to delete file " + f.getAbsolutePath());
			}
		}
	}
}
