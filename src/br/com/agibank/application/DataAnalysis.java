package br.com.agibank.application;

import java.nio.file.Path;
import java.util.List;

import br.com.agibank.util.FileReader;
import br.com.agibank.util.ReportGenerator;

public class DataAnalysis {

	public static void main(String[] args) {
		FileReader fileReader = new FileReader();
		List<Path> filesList = null;
		
		String inputFolder = System.getProperty("user.home") + "/data/in";
		String outputFolder = System.getProperty("user.home") + "/data/out";
		
		try {
			filesList = fileReader.getFilesList(inputFolder, ".dat");
		} catch (Exception e) {
			System.out.println("Missing input folder!");
		}
		
		ReportGenerator reportGenerator = new ReportGenerator();
		
		if(filesList != null) {
			for (Path path : filesList) {
				reportGenerator.generateReport(path, outputFolder);
			}
		}
		
	}
}