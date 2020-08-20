package m;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import javax.imageio.ImageIO;

import io.nayuki.qrcodegen.*;



public class n {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Simple operation
		QrCode qr0 = QrCode.encodeText("Hello, world!", QrCode.Ecc.MEDIUM);
		String img = qr0.toSvgString(10);

		
		
		int QRCodeSizeX = getGridSize(img,1)+2;
		int QRCodeSizeY = getGridSize(img,0)+2;
		

		int d = getCodePoint(img, "d=\"");
		int e = getCodePoint(img, "z\" fill=\"");
		
		String headerData = img.substring(0, d+1);
		String footerData = img.substring(e- 7, img.length() );

		System.out.println(footerData);
		
		String[][] QRCodeDS =new String [QRCodeSizeY][QRCodeSizeX];
		String[][] QRCodeDSSplit =new String [QRCodeSizeY][QRCodeSizeX];
		
		for(int intial = 0; intial < QRCodeSizeY; intial++) {
			Arrays.fill(QRCodeDS[intial], "_");
			
		}
		for(int intial = 0; intial <= QRCodeSizeY-1; intial++) {
			if(intial == 0||intial == QRCodeSizeY-1) {
			Arrays.fill(QRCodeDS[intial], "=");
			
			}
			QRCodeDS[intial][0] = "=";
			QRCodeDS[intial][QRCodeSizeX-1] = "=";
		}
		
		
		String dataString = getData(img,'\"',d+1)+" ";
	//	String phrase = getData(dataString,' ',0);
		//System.out.println(phrase);
		
		///processing to find xy
		//int s1 = getData(phrase,0);
		//System.out.println(s1);
		
		
		//System.out.println(dataString);
		
		while (dataString.length() > 14) {
			String phrase = getData(dataString,' ',0);
			QRCodeDS[getData(phrase,1)][getData(phrase,0)] = phrase;
			/*
			System.out.println(" ");
			System.out.print(getData(phrase,1));
			System.out.println(" "+getData(phrase,0));
			System.out.println(" " + phrase);
			System.out.println(" "); */
			dataString = dataString.substring(phrase.length()+1);

		}
		
		for(int y = 0; y < QRCodeSizeY; y++) {
			
			for(int x = 0; x < QRCodeSizeX; x++) {
				
				if(QRCodeDS[y][x].contains("=")) continue;
				String arrayCont = QRCodeDS[y][x];
				if(x ==30 && y == 27) {
					
					int kkp = 0;
					
				}
				if(arrayCont.contains("_")) {
					
					int setupPaths [] = {0,0,0,0};
					boolean validPath = false;
					
					while(QRCodeDS[y+setupPaths[0]][x] != null && QRCodeDS[y+setupPaths[0]][x].length() < 3) {
						
						if(QRCodeDS[y+setupPaths[0]][x] == "=" ||QRCodeDS[y+setupPaths[0]][x] == "+") {
							validPath = true;
							break;
						}
						setupPaths[0]--;
						}
					while(QRCodeDS[y+setupPaths[1]][x] != null && QRCodeDS[y+setupPaths[1]][x].length() < 3) {
						
						if(QRCodeDS[y+setupPaths[1]][x] == "=" ||QRCodeDS[y+setupPaths[1]][x] == "+") {
							validPath = true;
							break;
						}
						setupPaths[1]++;
						}
					while(QRCodeDS[y][x+setupPaths[2]] != null && QRCodeDS[y][x+setupPaths[2]].length() < 3) {
						
						if(QRCodeDS[y][x+setupPaths[2]] == "=" ||QRCodeDS[y][x+setupPaths[2]] == "+") {
							validPath = true;
							break;
						}
						setupPaths[2]++;
						}
					while(QRCodeDS[y][x+setupPaths[3]] != null && QRCodeDS[y][x+setupPaths[3]].length() < 3) {
						
						if(QRCodeDS[y][x+setupPaths[3]] == "=" ||QRCodeDS[y][x+setupPaths[3]] == "+") {
							validPath = true;
							break;
						}
						setupPaths[3]--;
						}
					
					/*System.out.println(setupPaths[0]);
					System.out.println(setupPaths[1]);
					System.out.println(setupPaths[2]);
					System.out.println(setupPaths[3]); */
					
						// System.out.println(validPath);
					
						int chosenDirection =999999;
						int op = 0;
						
						if(validPath == true) {
							QRCodeDS[y][x] = "+";
						}
						
					/*
					for(int setupArray = 3; setupArray <5 ; setupArray++) {
						
						
						if(QRCodeDS[y-setupArray][x] == null||QRCodeDS[y-setupArray][x].length() > 3)break;
						if(QRCodeDS[y+setupArray][x] == null||QRCodeDS[y][x-setupArray].length() > 3)break;
						if(QRCodeDS[y][x+setupArray] == null|| QRCodeDS[y+setupArray][x].length() > 3)break;
						if(QRCodeDS[y][x-setupArray] == null||QRCodeDS[y][x+setupArray].length() > 3) break;
						
						if(QRCodeDS[y-1][x]==("=")||QRCodeDS[y][x-1]==("=")) QRCodeDS[y][x] = "+";
						if(QRCodeDS[y-1][x].contains("+")||QRCodeDS[y][x-1].contains("+")) QRCodeDS[y][x] = "+";
						if(QRCodeDS[y+1][x].contains("=")||QRCodeDS[y][x-1].contains("=")) QRCodeDS[y][x] = "+";
						if(QRCodeDS[y+1][x].contains("+")||QRCodeDS[y][x-1].contains("+")) QRCodeDS[y][x] = "+";	
						
					}
					*/
					
					
				}
			}
		}
		
		
			
	
		int g =0;
		
		  
		 	
		for(int isY = 0; isY < QRCodeSizeY; isY++) {
			
			for(int isX = 0; isX < QRCodeSizeX; isX++) {
				
				int chosenPath = 9999999;

				
				if(QRCodeDS[isY][isX].contains("=")) continue;
				if(QRCodeDS[isY][isX]==("_")) { 
					
					int paths[] = {0,0,0,0};
					
					boolean foundP = false;
					
					g++;
					QRCodeDS[isY][isX] = "X";
					while(foundP == false) {
						if(QRCodeDS[isY][isX-paths[0]]=="+") {
							foundP = true;
							chosenPath = 0;
							break;
							//break;
						}else {paths[0] ++;}
						
						if(QRCodeDS[isY][isX+paths[1]]=="+") {
							foundP = true;
							chosenPath = 1;
							break;
							//break;
						}else {paths[1] ++;}


						if(QRCodeDS[isY-paths[2]][isX]=="+") {
							foundP = true;	
							chosenPath =2;
							break;
							//break;
						}else {paths[2] ++;}
						
						if(QRCodeDS[isY+paths[3]][isX]=="+") {
							foundP = true;
							chosenPath = 3;
							break;
							//break;
						}else {paths[3] ++;
						}
						/*
						System.out.println("????????????????????????");
						System.out.println(QRCodeDS[isY][isX+paths[0]]);
						System.out.println(QRCodeDS[isY][isX-paths[1]]);						
						System.out.println(QRCodeDS[isY+paths[2]][isX]);
						System.out.println(QRCodeDS[isY-paths[3]][isX]);
						*/
						
					}
					/*
					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<");
					System.out.println(chosenPath);
					System.out.println(paths[chosenPath]);	
					*/
					
					for(int storeArray = 0; storeArray < paths[chosenPath]; storeArray++) {
						if(chosenPath == 0) {
							QRCodeDSSplit [isY][isX-storeArray] = QRCodeDS[isY][isX-storeArray];
							QRCodeDS[isY][isX-storeArray]="+";
							
						}
						if(chosenPath == 1) {
							QRCodeDSSplit [isY][isX+storeArray] = QRCodeDS[isY][isX+storeArray];
							QRCodeDS[isY][isX+storeArray]="+";
							
						}
						
						if(chosenPath == 2) {
							QRCodeDSSplit [isY-storeArray][isX] = QRCodeDS[isY-storeArray][isX];
							QRCodeDS[isY-storeArray][isX]="+";
								
						}
						if(chosenPath == 3) {
							QRCodeDSSplit [isY+storeArray][isX] = QRCodeDS[isY+storeArray][isX];
							QRCodeDS[isY+storeArray][isX]="+";
							
						}
					}
					
					
					}
		
				}	
				
			}
		 
		
		
		
		for(int i =0; i <QRCodeSizeY; i++) {
			for (int p = 0; p < QRCodeSizeX; p++) {
		
		if(QRCodeDSSplit[i][p] == "X" || QRCodeDSSplit[i][p] == "_"  ) {		
			QRCodeDSSplit[i][p] = null;
		}
		if(QRCodeDS[i][p] == "X" || QRCodeDS[i][p] == "_" ||  QRCodeDS[i][p] == "+" || QRCodeDS [i][p]== "=" ) {		
			QRCodeDS[i][p] = null;
		}
			
			}

			}
		
		String OriginalImage = headerData;
		String SeperatedImage = headerData;
		
		for(int i =0; i <QRCodeSizeY; i++) {
			for (int p = 0; p < QRCodeSizeX; p++) {
		
		if(QRCodeDS[i][p] != null) {
			
		OriginalImage = OriginalImage +	QRCodeDS[i][p];
		//System.out.print(QRCodeDS[i][p]);
			}
		}
		//System.out.println();
		}
		
		for(int c =0; c <QRCodeSizeY; c++) {
			for (int j = 0; j < QRCodeSizeX; j++) {
		
		if(QRCodeDSSplit[c][j] != null) {
			
			SeperatedImage = SeperatedImage +	QRCodeDSSplit[c][j];
			//System.out.print(QRCodeDSSplit[c][j]);
		}
			}
		//System.out.println();
			}
		
		//System.out.println(OriginalImage);
		//System.out.println(SeperatedImage);
		
		OriginalImage = OriginalImage + footerData;
		SeperatedImage = SeperatedImage + footerData;

		System.out.println(OriginalImage);
		System.out.println(SeperatedImage);


		File OGsvgFile = new File("Full-QR.svg");   // File path for output
		try {
			Files.write(OGsvgFile.toPath(),                    // Write image to file
					OriginalImage.getBytes(StandardCharsets.UTF_8));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("error");
		}
		
		File SPsvgFile = new File("Split-QR.svg");   // File path for output
		try {
			Files.write(SPsvgFile.toPath(),                    // Write image to file
					SeperatedImage.getBytes(StandardCharsets.UTF_8));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("error");
		}

	} 
		

		



	


	
	public static int getData(String data, int xy) {
		int flag1 =0;
		int flag2 = 0;
		int flag3 = 0;
		
		
		for(int d =0; d < data.length(); d++) {
			
			if(data.charAt(d)== 'M') {flag1 = d;}
			if(data.charAt(d)== ',') {flag2 = d;}
			if(data.charAt(d)== 'h') {flag3 = d; break;}
			
		}
		if (xy ==1) {
			return Integer.parseInt(data.substring(flag1+1, flag2));
		}
		if(xy ==0) {
			return Integer.parseInt(data.substring(flag2+1,flag3));
		}
		return 0;
	}
	
	public static int getGridSize (String file, int xy) {
		
		int c = getCodePoint(file, "viewBox=\"");
		String[] values =new String [4];
		
		String sizeString = getData(file, '\"', c+1)+" ";
		
		for(int q = 0; q < 4; q++) {
			
			values[q] = getData(sizeString, ' ', 0);
			
			sizeString = sizeString.substring(values[q].length()+1);
			
			
		}
		
		if(xy == 1) {
			return Integer.parseInt(values[2]);
					}
		
		if(xy == 0) {
			return Integer.parseInt(values[3]);
					}
		
		return 0;
		
	}
	
	public static String getData(String file,char endSymbol, int position) {
		
		String returnString ="";
		
		while(file.charAt(position) != endSymbol) {
			
			returnString = returnString + file.charAt(position);	
			
			position++;
		}
		return returnString;
	}
	
	public static int getCodePoint(String file, String phrase) {
		
		int position = 0;
		String isItThePhrase = "";
		
		for(int i =0; i < file.length();i++) {
			
			
			char c = file.charAt(i);
			if(c == phrase.charAt(position)) {
				position++;
				isItThePhrase = isItThePhrase+c;
			
		}else {position = 0; isItThePhrase ="";}
		

		if(isItThePhrase.length() == phrase.length()) {
			//i++; 
			return i;
			}
		
	}
		return 0;
		
	}
	
}
