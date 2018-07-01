package br.com.agibank.util;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.agibank.model.Client;
import br.com.agibank.model.Item;
import br.com.agibank.model.Sale;
import br.com.agibank.model.Seller;

public class FileReader {
	
	public List<Path> getFilesList(String folder, String extension) {
		List<Path> filesList = new ArrayList<>();
		
		try(DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(folder), path -> path.toString().endsWith(extension))){
			stream.forEach(path -> filesList.add(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return filesList;
	}
	
	public List<Seller> getSellersFromFile(Path path) {
		List<Seller> sellers = new ArrayList<>();
		
		try(Stream<String> stream = Files.lines(path)){
			sellers = (List<Seller>) stream.filter(line -> line.startsWith("001"))
							.map(temp -> {
								String[] values = temp.split("ç"); 
								
								Seller seller = new Seller();
								seller.setCpf(values[1]);
								seller.setName(values[2]);
								seller.setSalary(Double.parseDouble(values[3]));
								
								return seller;
							}).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sellers;
	}
	
	public List<Client> getClientsFromFile(Path path) {
		List<Client> clients = new ArrayList<>();
				
		try(Stream<String> stream = Files.lines(path)){
			clients = (List<Client>) stream.filter(line -> line.startsWith("002"))
					.map(temp -> {
						String[] values = temp.split("ç"); 
						
						Client client = new Client();
						client.setCnpj(values[1]);
						client.setName(values[2]);
						client.setBusinessArea(values[3]);
						
						return client;
					}).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return clients;
	}
	
	public List<Sale> getSalesFromFile(Path path) {
		List<Sale> sales = new ArrayList<>();
		
		try(Stream<String> stream = Files.lines(path)){
			sales = (List<Sale>) stream.filter(line -> line.startsWith("003"))
					.map(temp -> {
						String[] values = temp.split("ç"); 
						
						Sale sale = new Sale();
						sale.setSaleId(values[1]);
						sale.setSalesmanName(values[3]);
						
						List<Item> items = new ArrayList<>();
						String list = values[2].substring(1, values[2].length() - 1);
						String[] listItems = list.split(",");
						
						for (String current : listItems) {
							String[] itemValues = current.split("-");
							
							Item item = new Item();
							item.setId(itemValues[0]);
							item.setQuantity(Integer.parseInt(itemValues[1]));
							item.setPrice(Double.parseDouble(itemValues[2]));
							
							items.add(item);
						}
						
						sale.setItems(items);	
						
						return sale;
					}).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sales;
	}
}