package m;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

		//System.out.println("hi");
		
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
				
				if(arrayCont.contains("_")) {
					
					if(QRCodeDS[y-1][x].contains("=")||QRCodeDS[y][x-1].contains("=")) QRCodeDS[y][x] = "+";
					if(QRCodeDS[y-1][x].contains("+")||QRCodeDS[y][x-1].contains("+")) QRCodeDS[y][x] = "+";
					if(QRCodeDS[y+1][x].contains("=")||QRCodeDS[y][x-1].contains("=")) QRCodeDS[y][x] = "+";
					if(QRCodeDS[y+1][x].contains("+")||QRCodeDS[y][x-1].contains("+")) QRCodeDS[y][x] = "+";
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
						if(QRCodeDS[isY][isX+paths[0]]=="+") {
							foundP = true;
							chosenPath = 0;
							continue;
							//break;
						}else {paths[0] +=1;}
						
						if(QRCodeDS[isY][isX-paths[1]]=="+") {
							foundP = true;
							chosenPath = 1;
							continue;
							//break;
						}else {paths[1] +=1;}


						if(QRCodeDS[isY+paths[2]][isX]=="+") {
							foundP = true;	
							chosenPath =2;
							continue;
							//break;
						}else {paths[2] +=1;}
						
						if(QRCodeDS[isY-paths[3]][isX]=="+") {
							foundP = true;
							chosenPath = 3;
							continue;
							//break;
						}else {paths[3] +=1;
						}
						
						System.out.println("????????????????????????");
						System.out.println(QRCodeDS[isY][isX+paths[0]]);
						System.out.println(QRCodeDS[isY][isX-paths[1]]);						
						System.out.println(QRCodeDS[isY+paths[2]][isX]);
						System.out.println(QRCodeDS[isY-paths[3]][isX]);
						
						
					}
					
					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<");
					System.out.println(chosenPath);
					System.out.println(paths[chosenPath]);	
					
					
					for(int storeArray = 0; storeArray < paths[chosenPath]; storeArray++) {
						if(chosenPath == 0) {
							QRCodeDSSplit [isY][isX+storeArray] = QRCodeDS[isY][isX+storeArray];
							QRCodeDS[isY][isX+storeArray]="+";
							
						}
						if(chosenPath == 1) {
							QRCodeDSSplit [isY][isX-storeArray] = QRCodeDS[isY][isX-storeArray];
							QRCodeDS[isY][isX-storeArray]="+";
							
						}
						
						if(chosenPath == 2) {
							QRCodeDSSplit [isY+storeArray][isX] = QRCodeDS[isY+storeArray][isX];
							QRCodeDS[isY][isX-storeArray]="+";
								
						}
						if(chosenPath == 3) {
							QRCodeDSSplit [isY+storeArray][isX] = QRCodeDS[isY+storeArray][isX];
							QRCodeDS[isY+storeArray][isX]="+";
							
						}
					}
					
					
					}
		
				}	
				
			}
		 
		
		/*
		 
		QRCodeDS[currenty][currentx] = "+";
		
		while(QRCodeDS[currenty+1][currentx] != "=") {
			while(QRCodeDS[currenty][currentx+1] != "=") {
				
				if(QRCodeDS[currenty][currentx] == "+" || QRCodeDS[currenty][currentx-1] == "=") {
				if(QRCodeDS[currenty][currentx+1] == "_") {
					
					QRCodeDS[currenty][currentx+1] = "+";
					
				}
				if(QRCodeDS[currenty][currentx-1] == "=") {QRCodeDS[currenty][currentx] = "+";}

			}
				currentx++;
			}
		currentx = 1;
		currenty++;
		}
		
		
		for(int emptySy =0; emptySy <(QRCodeSizeX*QRCodeSizeY); emptySy++ ) {
			while(QRCodeDS[emptySy][currenty] != "=" && QRCodeDS[currentx][currenty] != "=") {
			
			for(int emptySx =0; emptySx <(QRCodeSizeX*QRCodeSizeY); emptySx++ ) {
				
				
		
			if(QRCodeDS[currentx][currenty].contains("+")) {
				currentx++;
				if(QRCodeDS[currentx][currenty].contains("_"))
				QRCodeDS[currentx][currenty] = "+";
				
			}
				}
			currenty++;	
		}
		
		}
		*/
		
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
		
		for(int i =0; i <QRCodeSizeY; i++) {
			for (int p = 0; p < QRCodeSizeX; p++) {
		
		if(QRCodeDS[i][p] != null) {		
		System.out.print(QRCodeDS[i][p]);
		}
			}
		System.out.println();
			}
		
		System.out.println(" ");


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