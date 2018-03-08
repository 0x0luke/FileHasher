import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GUI extends Frame {
	
	public static void main(String args[]){  
	@SuppressWarnings("unused")
	GUI First = new GUI();
	}
		
	GUI(){
		
		Frame First = new Frame();
        
		
		Button StringOption = new Button("String Hasher");
		Button FileOption = new Button("File Hasher");
		Button Close = new Button("Close");
		
		StringOption.setBounds(100,75,100,50);
		FileOption.setBounds(400,75,100,50);
		Close.setBounds(250, 135, 100, 50);
		
		First.add(StringOption);
		First.add(FileOption);
		First.add(Close);
		First.setSize(600,200);
		First.setLayout(null);
		First.setVisible(true);
		First.setTitle("Main Menu");
		
		Close.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {     
	        	 System.exit(0);
	         }
	      });
		
		FileOption.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {     
	        	 	First.dispose();
	        	 
	        	 	Frame FileHasher = new Frame();
	 	    	
		    		Button TSHA1 = new Button("SHA1");
				Button TMD5 = new Button("MD5");
				Button Close = new Button("Close");
				Button ReturnStart = new Button("Main Menu");
				Label StringLabel = new Label("Choose the file to be hashed: ");
				TextField FileToHash = new TextField();
				Label AlreadyExists = new Label("Hash already exists!");
				
				//FileChooser.setMultiSelectionEnabled(false);
				
		    	
				TSHA1.setBounds(100,500,100,50);
				TMD5.setBounds(400,500,100,50);
				Close.setBounds(250, 550, 100, 50);
				ReturnStart.setBounds(250, 450, 100, 50);
				StringLabel.setBounds(50, 240, 200, 50);
				FileToHash.setBounds(250, 250, 200, 30);
				AlreadyExists.setBounds(250, 300, 200, 30);
				//FileChooser.setBounds(250,250,200,30);
				
				FileHasher.add(TSHA1);
				FileHasher.add(TMD5);
				FileHasher.add(Close);
				FileHasher.add(ReturnStart);
				FileHasher.add(StringLabel);
				FileHasher.add(FileToHash);
				//FileHasher.add(FileChooser);
				FileHasher.setSize(600,600);
				FileHasher.setLayout(null);
				FileHasher.setVisible(true);
				FileHasher.setTitle("File Hasher");
				
					Close.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {     
							System.exit(0);
						}
					});
					
				
					ReturnStart.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {    
							GUI First = new GUI();
						}
					});
				
					TMD5.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {     
			        			MessageDigest md = null;
			        				try {
			        					md = MessageDigest.getInstance("MD5");
			        				} catch (NoSuchAlgorithmException e5) {	
			        					e5.printStackTrace();
			        				}
			        		
			        		String PrepareHasher = FileToHash.getText();
			        		
			        		String currentdir = Paths.get(".").toAbsolutePath().normalize().toString();
			        		String filepath; 
			        		filepath = currentdir+"/"+PrepareHasher; 
			        		
			        		FileInputStream MD5File = null;
							try {
								MD5File = new FileInputStream(filepath);
							} catch (FileNotFoundException e4) {
								e4.printStackTrace();
							}
			        			byte[] dataBytes = new byte[1024];
			        			int nread = 0;
			        				try {
								while((nread = MD5File.read(dataBytes)) != -1) {
									md.update(dataBytes, 0, nread);
									
								}
							} catch (IOException e3) {
								e3.printStackTrace();
							}
			        		
			        		byte byteData[] = md.digest();
			        		
			        		StringBuffer hexString = new StringBuffer();
			        		for (int i=0;i<byteData.length;i++) {
			        			String hex = Integer.toHexString( byteData[i] & 0xff);
			        			if(hex.length()==1) hexString.append('0');
			        			hexString.append(hex);
			        		}
			        		
			        		String content = PrepareHasher + " : " + hexString.toString();
			        		String path = "MD5FileHashing.txt";
			        		File file = new File(path);
			        		
			        		// If the file above doesn't exist, then create it!
			        		if (!file.exists()) {
			        			try {
									file.createNewFile();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
			        		}
			        		
			        		FileWriter fw = null;
							try {
								fw = new FileWriter(file.getAbsoluteFile(),true);
							} catch (IOException e2) {
								e2.printStackTrace();
							}
							
							byte[] bytes = null;
							try {
								bytes = Files.readAllBytes(Paths.get(path));
							} catch (IOException e2) {
								e2.printStackTrace();
							}
							String TextExist = new String(bytes);

							// Check if the name is contained
							if(TextExist.indexOf(content) != -1){
								FileHasher.add(AlreadyExists);
							} else {
								
								FileHasher.remove(AlreadyExists);
								BufferedWriter bw = new BufferedWriter(fw);
								
								try {
									bw.write(content + "\n");
									bw.close();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
			         }
			      });
				
				TSHA1.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {    
			        	 
							MessageDigest sha = null;
							try {
								sha = MessageDigest.getInstance("SHA1");
							} catch (NoSuchAlgorithmException e4) {
								e4.printStackTrace();
							}
							
							String PrepareToHash = FileToHash.getText();
							
							FileInputStream SHAfile = null;
							try {
								SHAfile = new FileInputStream(PrepareToHash);
							} catch (FileNotFoundException e3) {
								e3.printStackTrace();
							}
							byte[] dataBytes = new byte[1024];
							int nread = 0;
							try {
								while((nread = SHAfile.read(dataBytes)) != -1) {
									sha.update(dataBytes, 0, nread);
								}
							} catch (IOException e2) {
								e2.printStackTrace();
							}
							
							byte byteData[] = sha.digest();
							
							StringBuffer hexString = new StringBuffer();
							for (int i=0;i<byteData.length;i++) {
								String hex = Integer.toHexString( byteData[i] & 0xff);
								if(hex.length()==1) hexString.append('0');
								hexString.append(hex);
							}
							String content = PrepareToHash + " : " + hexString.toString();
							String path = "SHA1FileHashing.txt";
							File file = new File(path);
							
							// If the file above doesn't exist, then create it!
							if (!file.exists()) {
								try {
									file.createNewFile();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
							
							FileWriter fw = null;
							try {
								fw = new FileWriter(file.getAbsoluteFile(), true);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							
							byte[] bytes = null;
							try {
								bytes = Files.readAllBytes(Paths.get(path));
							} catch (IOException e2) {
								e2.printStackTrace();
							}
							String TextExist = new String(bytes);

							// Check if the name is contained
							if(TextExist.indexOf(content) != -1){
								FileHasher.add(AlreadyExists);
							} else {
								
								FileHasher.remove(AlreadyExists);
								BufferedWriter bw = new BufferedWriter(fw);
								
								try {
									bw.write(content + "\n");
									bw.close();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
			         }
	      });
	     }
	   });
				
		
		StringOption.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 
	        	 	First.dispose();
	        	 	
	        	 	Frame TextHasher = new Frame();
	 	    	
		    		Button TSHA1 = new Button("SHA1");
				Button TMD5 = new Button("MD5");
				Button Close = new Button("Close");
				Button ReturnStart = new Button("Main Menu");
				Label StringLabel = new Label("Enter the String to be hashed: ");
				TextField StringToHash = new TextField();
				Label HashedString = new Label();
				Label AlreadyExists = new Label("Hash already exists!");
				
				
		    	
				TSHA1.setBounds(100,500,100,50);
				TMD5.setBounds(400,500,100,50);
				Close.setBounds(250, 550, 100, 50);
				ReturnStart.setBounds(250, 450, 100, 50);
				StringLabel.setBounds(50, 240, 200, 50);
				StringToHash.setBounds(250, 250, 200, 30);
				HashedString.setBounds(350 ,350,200,30);
				AlreadyExists.setBounds(250, 300, 200, 30);
				
				TextHasher.add(TSHA1);
				TextHasher.add(TMD5);
				TextHasher.add(Close);
				TextHasher.add(ReturnStart);
			    TextHasher.add(StringLabel);
			    TextHasher.add(StringToHash);
			    TextHasher.add(HashedString);
				TextHasher.setSize(600,600);
				TextHasher.setLayout(null);
				TextHasher.setVisible(true);
				TextHasher.setTitle("String Hasher");
				
				Close.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {     
			        	 System.exit(0);
			         }
			      });
				
				ReturnStart.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {     
			        	 GUI First = new GUI();
			         }
			      });
				
				TMD5.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) { 
			        	 
			        	 String TimeToHash = StringToHash.getText();
			    	
			        			MessageDigest md = null;
								try {
									md = MessageDigest.getInstance("MD5"); // using md5 hashing method.
								} catch (NoSuchAlgorithmException e1) {
									e1.printStackTrace();
								}
			        			
								// actual hashing algorithm
			        			md.update(TimeToHash.getBytes());
			        			byte byteData[] = md.digest();
			        			
			        			StringBuffer hexString = new StringBuffer();
			        			for (int i=0;i<byteData.length;i++) {
			        				String hex = Integer.toHexString( byteData[i] & 0xff);
			        				if(hex.length()==1) hexString.append('0');
			        				hexString.append(hex);
			        			}
			        			
			        			String content = TimeToHash + " : " + hexString.toString();
			        			String path = "MD5TextHashing.txt";
			        			File file = new File(path);
			        	
			        			Label HashedString = new Label(hexString.toString());
			        			
			        			// checks to see if the file exists, if it doesn't then create it.
			        			if (!file.exists()) {
			        				try {
										file.createNewFile();
									} catch (IOException e1) {
										e1.printStackTrace();
									}
			        			}
			        			
			        			FileWriter fw = null;
								try {
									fw = new FileWriter(file.getAbsoluteFile(), true);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								
								byte[] bytes = null;
								try {
									bytes = Files.readAllBytes(Paths.get(path));
								} catch (IOException e2) {
									e2.printStackTrace();
								}
								String TextExist = new String(bytes);

								// Check if the name is contained
								if(TextExist.indexOf(content) != -1){
									TextHasher.add(AlreadyExists);
								} else {
									
									TextHasher.remove(AlreadyExists);
									BufferedWriter bw = new BufferedWriter(fw);
									
									try {
										bw.write(content + "\n");
										bw.close();
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
				         }
			      });
				
				TSHA1.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {  
			        	 
			        	String TimeToHash = StringToHash.getText();
			        	 
			        	MessageDigest sha = null;
						try {
							sha = MessageDigest.getInstance("SHA1");
						} catch (NoSuchAlgorithmException e1) {
							e1.printStackTrace();
						}
			    		
			    		sha.update(TimeToHash.getBytes());
			    		byte byteData[] = sha.digest();
			    		
			    		StringBuffer hexString = new StringBuffer();
			    		for (int i=0;i<byteData.length;i++) {
			    			String hex = Integer.toHexString( byteData[i] & 0xff);
			    			if(hex.length()==1) hexString.append('0');
			    			hexString.append(hex);
			    		}
			    		
			    		String content = TimeToHash + " : " + hexString.toString();
			    		String path = "SHA1TextHashing.txt";
			    		File file = new File(path);
			    		
			    		// checks to see if the file above exists, if it doesn't then create it.
			    		if (!file.exists()) {
			    			try {
								file.createNewFile();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
			    		}
			    		
			    		FileWriter fw = null;
						try {
							fw = new FileWriter(file.getAbsoluteFile(), true);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						byte[] bytes = null;
						try {
							bytes = Files.readAllBytes(Paths.get(path));
						} catch (IOException e2) {
							e2.printStackTrace();
						}
						String TextExist = new String(bytes);

						// Check if the name is contained
						if(TextExist.indexOf(content) != -1){
							TextHasher.add(AlreadyExists);
						} else {
							
							TextHasher.remove(AlreadyExists);
							BufferedWriter bw = new BufferedWriter(fw);
							
							try {
								bw.write(content + "\n");
								bw.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
		         }
				});
	         }
		});
	}
}
		


