package square_method;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(final String[] args) {

		// PRZYPADEK, GDY NIE PODANO ARGUMENTOW
		if (args.length == 0) {
			// TWORZY LISTE PUNKTOW
			ArrayList<Point> points = new ArrayList<Point>();

			// POBIERA OD UZYTKOWNIKA LICZBE, KTÓRA OKRASLA ILE LOSOWYCH PUNKTOW
			// MA ZOSTAC STWORZONYCH I DODANYCH DO LISTY, LOSUJE I DOPISUJE
			// PUNKTY

			try (Scanner sc = new Scanner(System.in)) {
				System.out.println("Proszę podać liczbę punktów do wygenerowania: ");
				int numberOfPoints = sc.nextInt();
				for (int i = 0; i < numberOfPoints; i++) {
					double k = (double) (Math.random() * 10);
					double v = (double) (Math.random() * 10);
					Point point = new Point(k, v);
					points.add(point);
				}
			}

			// ZAPISUJE PUNKTY DO PLIKU
			try {
				System.out.println("Zapisywanie elementów do pliku.");
				writeFile(points);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// WCZYTUJE PUNKTY DO TABLICY I WYZNACZA ROWNANIE PROSTEJ
			try {
				System.out.println("Wczytywanie elementów z pliku.");
				readFile(points, "data.txt");
				for (int i = 0; i < points.size(); i++) {
					points.get(i).toString();
				}
				solve(points);
			} catch (FileNotFoundException e) {
				System.out.println("FILE NOT FOUND!");
				System.exit(1);
			}
		} else if (args.length < 2) {
			// TWORZY LISTE PUNKTOW
			ArrayList<Point> points = new ArrayList<Point>();

			// TWORZY ZMIENNA ZAWIERAJACA NAZWE PLIKU Z DANYMI
			String fileWithData = args[0];

			// WCZYTUJE PUNKTY DO TABLICY I WYZNACZA ROWNANIE PROSTEJ
			try {
				System.out.println("Wczytywanie elementów z pliku.");
				readFile(points, fileWithData);
				for (int i = 0; i < points.size(); i++) {
					points.get(i).toString();
				}
				solve(points);
			} catch (FileNotFoundException e) {
				System.out.println("FILE NOT FOUND!");
				System.exit(1);
			}
		} else {
			System.out.println("Nieprawidłowe uruchomienie programu!");
			System.exit(-1);
		}
	}

	// FUNKCJA ZAPISUJE PUNKTY DO PLIKU 'data.txt'
	public static void writeFile(ArrayList<Point> points) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter("data.txt"));
		for (Point i : points) {
			pw.write(i.getX() + " " + i.getY() + System.getProperty("line.separator"));
		}
		pw.close();
	}

	// FUNKCJA WCZYTUJE PUNKTY Z PLIKU
	public static void readFile(ArrayList<Point> points, String fileName) throws FileNotFoundException {

		// OTWIERANIE PLIKU Z DANYMI
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR WITH LOADING THE FILE!");
			System.exit(2);
		}

		// ODCZYT KOLEJNYCH DANYCH Z PLIKU I DOPISANIE PUNKTOW DO LISTY
		BufferedReader bfr = new BufferedReader(fr);
		try {
			String line;
			while ((line = bfr.readLine()) != null) {
				String[] values = line.split("\\s+");
				Point point = new Point(Double.parseDouble(values[0]), Double.parseDouble(values[1]));
				points.add(point);
			}
		} catch (IOException e) {
			System.out.println("ERROR WITH READING THE FILE!");
			System.exit(3);
		}

		// ZAMYKANIE PLIKU Z DANYMI
		try {
			fr.close();
		} catch (IOException e) {
			System.out.println("ERROR WITH CLOSING THE FILE!");
			System.exit(4);
		}
	}

	// FUNKCJA SUMUJE WSZYSTKIE WARTOSCI 'X' Z LISTY
	public static double SumX(ArrayList<Point> points) {
		double sum = 0;
		for (Point point : points) {
			sum += point.getX();
		}
		return sum;
	}

	// FUNKCJA SUMUJE WSZYSTKIE WARTOSCI 'Y' Z LISTY
	public static double SumY(ArrayList<Point> points) {
		double sum = 0;
		for (Point point : points) {
			sum += point.getY();
		}
		return sum;
	}

	// FUNKCJA SUMUJE WSZYSTKIE KWADRATY WARTOSCI 'X^2' Z LISTY
	public static double SumSqX(ArrayList<Point> points) {
		double sum = 0;
		for (Point point : points) {
			sum += point.getX() * point.getX();
		}
		return sum;
	}

	// FUNKCJA SUMUJE WSZYSTKIE ILOCZYNY WARTOSCI 'X * Y' Z LISTY
	public static double SumXY(ArrayList<Point> points) {
		double sum = 0;
		for (Point point : points) {
			sum += point.getX() * point.getY();
		}
		return sum;
	}

	// FUNKCJA WYZNACZA WSPOLCZYNNIK KIERUNKOWY PROSTEJ ('a' DLA 'y = a*x + b')
	public static double calcA(double Sx, double Sy, double Sxx, double Sxy, double n) {
		double a = (Sx * Sy - Sxy * n) / (Sx * Sx - Sxx * n);
		return a;
	}

	// FUNKCJA WYZNACZA WYRAZ WOLNY PROSTEJ ('b' DLA 'y = a*x + b')
	public static double calcB(double Sx, double Sy, double Sxx, double Sxy, double n) {
		double b = (Sx * Sxy - Sxx * Sy) / (Sx * Sx - Sxx * n);
		return b;
	}

	// FUNKCJA WYZNACZA ROWNANIE PROSTEJ
	public static void solve(ArrayList<Point> points) {
		double n, Sx, Sy, Sxx, Sxy;
		n = points.size();
		Sx = SumX(points);
		Sy = SumY(points);
		Sxx = SumSqX(points);
		Sxy = SumXY(points);
		System.out.println("Prosta ma następującą postać:");
		System.out.println("Y = " + calcA(Sx, Sy, Sxx, Sxy, n) + "*X + " + calcB(Sx, Sy, Sxx, Sxy, n));
	}
}