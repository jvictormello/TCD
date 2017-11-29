package br.com.tcd.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.tcd.telas.TelaApresentacao;

public class Util {

	public static String getBytesImage(String image) {
		return getExecutionPath() + "/images/" + image;
	}

	public static File arquivoResourcesAsFile(String arquivo) {
		return new File(getExecutionPath() + arquivo);
	}

	private static String getExecutionPath() {
		String absolutePath = Util.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
		absolutePath = absolutePath.replaceAll("%20", " ");
		return absolutePath;
	}

	public static void createAgreeFile() {
		File file = new File(getExecutionPath() + File.separator + "Concordo.txt");
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException ex) {
			Logger.getLogger(TelaApresentacao.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static File getAgreeFile() {
		return new File(getExecutionPath() + File.separator + "Concordo.txt");
	}
}
