package com.load.compare;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CompareCSV {
public static void main(String args[]) throws IOException
{
	
	//Get the folder for each version
	//first directory
	File folder1 = new File("D:\\office_prd\\2020C");
	File folder2 = new File("D:\\office_prd\\2020D");
	//to compare the two folders to find the same name files and also display the different name files
	List<String> commonFiles = new ArrayList<String>();
	List<String> missingFilesA = new ArrayList<String>();
	List<String> missingFilesB = new ArrayList<String>();
	
	if(folder1.isDirectory() && folder2.isDirectory() ) {
		File[] listOfFiles1 = folder1.listFiles();
		File[] listOfFiles2 = folder2.listFiles();
		for(int i=0;i<listOfFiles1.length;i++) {
			boolean fileCheck = false;
			for(int j=0;j<listOfFiles2.length;j++) {
				if(listOfFiles1[i].getName().equals(listOfFiles2[j].getName())) {
					commonFiles.add(listOfFiles1[i].getName());
					fileCheck=true;
					break;
				}
			}
			if(!fileCheck) {
				missingFilesA.add(listOfFiles1[i].getName());
			}
		}
		
		for(int i=0;i<listOfFiles2.length;i++) {
			if(!commonFiles.contains(listOfFiles2[i].getName())) {
				missingFilesB.add(listOfFiles2[i].getName());
			}
		}
	}
	
	
	
	
	//Requirement 1: File header comparison
	
	for(String files: commonFiles) {
	
		System.out.println("File Name-"+files);
		
	File file1 = new File(folder1.getAbsolutePath()+File.separator+files); 
	File file2 = new File(folder2.getAbsolutePath()+File.separator+files);
	
	try {
		BufferedReader br1 = new BufferedReader(new FileReader(file1));
		BufferedReader br2 = new BufferedReader(new FileReader(file2));
		String line = null;
		String firstFileHeader = null;
		String secondFileHeader = null;
		int count = 0;
		while((line = br1.readLine()) != null) {
			if(count==1) {
				count=0;
				break;
			}else {
				firstFileHeader = line;
			count = count+1;
			}
		}
		
		while((line = br2.readLine()) != null) {
			if(count==1) {
				break;
			}else {
				secondFileHeader = line;
			count = count+1;
			}
		}
		
		//Compare headers
		if(firstFileHeader.equals(secondFileHeader)) {
			//write to export log
			System.out.println("Both headers are same");
			
			//find number of rows for each file
			
		}else {
			//split the each header component and compare
			String[] split1 = firstFileHeader.split(",");
			String[] split2 = secondFileHeader.split(",");
			
			if(split1.length!=split2.length) {
				System.out.println("Header count varies");
				/*
				 * Find out which file has new column
				 */
				if(split1.length>split2.length) {
					//first file has more columns
					for(int i=0;i<split2.length;i++) {
						if(!split1[i].equals(split2[i])) {
							//check for spacing
							//first file check
							if(split1[i].trim().equals(split2[i])) {
								System.out.println("Frist file "+split1[i]+" has space ");
							}else if(split1[i].equals(split2[i].trim())) {
								System.out.println("Second file "+split2[i]+" has space ");
							}
						}
					}
					int remainingColumns = split1.length-split2.length;
					for(int i=1;i<=remainingColumns;i++) {
					System.out.println(folder1.getAbsolutePath()+File.separator+files+" has extra column "+split1[split1.length-i]);//this will print in reverse order
					}
				}else {
					//second file has more columns
					for(int i=0;i<split1.length;i++) {
						if(!split1[i].equals(split2[i])) {
							//check for spacing
							//first file check
							if(split1[i].trim().equals(split2[i])) {
								System.out.println("Frist file "+split1[i]+" has space ");
							}else if(split1[i].equals(split2[i].trim())) {
								System.out.println("Second file "+split2[i]+" has space ");
							}
						}
					}
					int remainingColumns = split2.length-split1.length;
					for(int i=1;i<=remainingColumns;i++) {
					System.out.println(folder2.getAbsolutePath()+File.separator+files+" has extra column "+split2[split2.length-i]);//this will print in reverse order
					 }
				}
				
			}else {//here we compare the header elements for space constrains
				for(int i=0;i<split1.length;i++) {
					if(!split1[i].equals(split2[i])) {
						//check for spacing
						//first file check
						if(split1[i].trim().equals(split2[i])) {
							System.out.println("First file "+split1[i]+" has space ");
						}else if(split1[i].equals(split2[i].trim())) {
							System.out.println("Second file "+split2[i]+" has space ");
						}else {
							//other than space it seems the columns are out of order
							System.out.println("First file the position of "+split1[i]+" element is at "+(i+1));
							System.out.println("Second file the position of "+split2[i]+" element is at "+(i+1));
						}
					}else {
					}
				}
			}
			
		}
		
		//find number of rows in each file
		
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
   
}}