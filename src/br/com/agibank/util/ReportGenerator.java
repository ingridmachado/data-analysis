package br.com.agibank.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.agibank.model.Client;
import br.com.agibank.model.Sale;
import br.com.agibank.model.Seller;

public class ReportGenerator {
	
	public void generateReport(Path path, String outputfolder) {
		FileReader fileReader = new FileReader();
		
		List<Seller> sellers = fileReader.getSellersFromFile(path);
		List<Client> clients = fileReader.getClientsFromFile(path);
		List<Sale> sales = fileReader.getSalesFromFile(path, sellers);
		
		try {
			File file = new File(new Utils().getOutputFileName(path, outputfolder));
			
			if(file.createNewFile()) {
				try(BufferedWriter bufferedWriter = Files.newBufferedWriter(file.toPath())){
					bufferedWriter.write("Number of clients: " + clients.size());
					bufferedWriter.newLine();
					bufferedWriter.write("Number of sellers: " + sellers.size());
					bufferedWriter.newLine();
					bufferedWriter.write("ID of the highest sale: " + getHighestSale(sales).getSaleId());
					bufferedWriter.newLine();
					bufferedWriter.write("Worst seller: " + getWorstSeller(sales).getName());
				}
			}
			
			System.out.println("Report created!");
		} catch (IOException e) {
			System.out.println("Missing output folder!");
		}
	}
	
	public Sale getHighestSale(List<Sale> sales) {
		Comparator<Sale> comparator = Comparator.comparing(Sale::getTotal);
		
		Sale highestSale = sales.stream().max(comparator).get();
		
		return highestSale;
	}
	
	private Seller getWorstSeller(List<Sale> sales) {
		Map<Seller, Double> sellersMap = sales.stream().collect(
                Collectors.groupingBy(Sale::getSeller,
                        Collectors.mapping(Sale::getTotal, Collectors.summingDouble(d -> d))
                )
        );
		
		return sellersMap.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
	}
}