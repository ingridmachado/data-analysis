package br.com.agibank.util;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import br.com.agibank.model.Seller;

public class Utils {
	
	public Seller getSellerByName(List<Seller> sellers, String salesmanName) {
		List<Seller> seller = sellers.stream().filter(item -> item.getName().equals(salesmanName)).collect(Collectors.toList());
		
		return seller.get(0);
	}
	
	public String getOutputFileName(Path path, String outputFolder) {
		String outputFile = path.getFileName().toString().substring(0, path.getFileName().toString().indexOf('.')) + ".done";
		String extension = path.getFileName().toString().substring(path.getFileName().toString().indexOf('.'));
		
		return outputFolder + File.separator + outputFile + extension;
	}
}