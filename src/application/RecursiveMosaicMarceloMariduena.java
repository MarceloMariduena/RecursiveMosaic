package application;

// @author: Marcelo Mariduena

import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RecursiveMosaicMarceloMariduena extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/* User input */
			Scanner input = new Scanner(System.in);
			System.out.print("Enter the number of levels (Preferably less than 5): ");
			int level = input.nextInt();
			if (level > 5) {
				System.out.println("Danger! That amount of levels may consume too much memory. Dropping to level 3.");
				level = 3;
			}
			input.close();
			
			/* Variables */
			int iterator = 1;
			int numTiles = 6;
			double length = 600;
			double secondLength = 200;
			
			int[][] map1 = new int[][]{
				  { 1, 1, 1, 1, 1, 1, 1 },
				  { 0, 1, 0, 0, 0, 0, 0 },
				  { 0, 1, 0, 1, 1, 1, 1 },
				  { 0, 1, 0, 0, 0, 0, 1 },
				  { 0, 1, 1, 1, 1, 0, 1 },
				  { 0, 0, 0, 0, 0, 0, 1 },
				  { 1, 1, 1, 1, 1, 1, 1 }
			};
			
			int[][] map2 = new int[][]{
				  { 1, 0, 0, 0, 0, 0, 1 },
				  { 1, 0, 1, 1, 1, 1, 1 },
				  { 1, 0, 1, 0, 0, 0, 1 },
				  { 1, 0, 1, 0, 1, 0, 1 },
				  { 1, 0, 1, 0, 1, 0, 1 },
				  { 1, 0, 0, 0, 1, 0, 1 },
				  { 1, 1, 1, 1, 1, 1, 1 }
			};
			
			/* Display */
			Pane pane = new Pane();
			drawMosaicSquare(pane, level, iterator, numTiles, map1, map2, 0, 0, length, secondLength, Color.WHITE, Color.NAVY);
			Scene scene = new Scene(pane, 600, 600);
			scene.setFill(Color.LIGHTGREY);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void drawMosaicSquare(Pane pane, int level, int iterator, int numTiles, int[][] map1, int[][] map2,  double x0, double y0, double length, double secondLength, Color strokeColor, Color fillColor) {
		if (level < 0) return;
		
		double blockLength = secondLength / 2 / 7;
		
		for (double x = x0; x < length + x0; x += (length/numTiles)) {
			/* Draws horizontal mosaic squares on bottom */
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 7; j++) {
					Rectangle rect = new Rectangle((blockLength * j) + x, (blockLength * i) + 500 - (y0/2), blockLength, blockLength);
					if (map1[i][j] == 1) {
						rect.setFill(fillColor);
						rect.setStroke(strokeColor);
					}
					if (map1[i][j] == 0) {
						rect.setFill(Color.LIGHTGREY);
						rect.setStroke(Color.LIGHTGREY);
					}
					pane.getChildren().add(rect);
				}
			}
			
			/* Draws vertical mosaic squares on right */
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 7; j++) {
					Rectangle rect = new Rectangle(blockLength * j + 500 - (x0/2), blockLength * i + x, blockLength, blockLength);
					if (map2[i][j] == 1) {
						rect.setFill(fillColor);
						rect.setStroke(strokeColor);
					}
					if (map2[i][j] == 0) {
						rect.setFill(Color.LIGHTGREY);
						rect.setStroke(Color.LIGHTGREY);
					}
					pane.getChildren().add(rect);
				}
			}
			
			/* Draws vertical mosaic squares on left */
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 7; j++) {
					Rectangle rect = new Rectangle(blockLength * j + x0, blockLength * i + x, blockLength, blockLength);
					if (map2[i][j] == 1) {
						rect.setFill(fillColor);
						rect.setStroke(strokeColor);
					}
					if (map2[i][j] == 0) {
						rect.setFill(Color.LIGHTGREY);
						rect.setStroke(Color.LIGHTGREY);
					}
					pane.getChildren().add(rect);
				}
			}
			
			/* Draws horizontal mosaic squares on top */
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 7; j++) {
					Rectangle rect = new Rectangle((blockLength * j) + x, (blockLength * i) + y0, blockLength, blockLength);
					if (map1[i][j] == 1) {
						rect.setFill(fillColor);
						rect.setStroke(strokeColor);
					}
					if (map1[i][j] == 0) {
						rect.setFill(Color.LIGHTGREY);
						rect.setStroke(Color.LIGHTGREY);
					}
					pane.getChildren().add(rect);
				}
			}
		}
		
		level--;
		x0 += length / numTiles;
		y0 += length / numTiles;
		iterator = iterator * 2;
		numTiles += iterator;
		length -= secondLength;
		secondLength /= 2;
		
		/* Recursive call */
		drawMosaicSquare(pane, level, iterator, numTiles, map1, map2, x0, y0, length, secondLength, Color.WHITE, Color.NAVY);
	}
}
