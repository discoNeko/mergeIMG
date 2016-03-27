import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class Main {
	public static void main(String[] args){
		System.out.print("input :");
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		int row = Integer.parseInt(sc.next());
		merge(n,row);
	}

	public static void merge(int n, int row){
		BufferedImage[] img = new BufferedImage [n];
		int[] w = new int [n];
		int[] h = new int [n];
		int bw = 0, bh = 0;
		for(int i = 0; i < n; i++){
			try {
				img[i] = ImageIO.read(new File(i+".png"));
				w[i] = img[i].getWidth();
				h[i] = img[i].getHeight();
				bw += w[i];
				bh += h[i];
			} catch (Exception e) {
				e.printStackTrace();
				img[i] = null;
				return;
			}
		}
 		if(row==0){
 			bh = h[0];
 			Arrays.fill(h,0);
 		}else{
 			bw = w[0];
 			Arrays.fill(w,0);
 		}

 		BufferedImage bond_img = new BufferedImage(bw, bh, BufferedImage.TYPE_INT_ARGB);
 		int sw = 0, sh = 0;
		Graphics g = bond_img.getGraphics();
		for(int i = 0; i < n; i++){
			g.drawImage(img[i], sw, sh, null);
			sw += w[i];
			sh += h[i];
		}

		//output
		try {
			ImageIO.write(bond_img, "png", new File("merged.png"));
			System.out.println("done.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}