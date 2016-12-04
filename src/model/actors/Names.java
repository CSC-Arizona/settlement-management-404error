package model.actors;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Generate text using a Markov process
 * 
 * @author Ethan Ward
 *
 */
public class Names {

	private static int[][] nameFrequencies;
	private static HashMap<Character, HashMap<Character, Double>> nameProbabilities;
	private static int[][] wordFrequencies;
	private static HashMap<Character, HashMap<Character, Double>> wordProbabilities;

	private static Random random = new Random();
	private static String letters = "abcdefghijklmnopqrstuvwxyz";

	public static void main(String[] args) {
		System.out.println(generateText(15));
	}

	/**
	 * @return A name with 3-8 characters
	 */
	public static String generateName() {
		if (nameFrequencies == null) {
			nameFrequencies = loadFrequencies("src/model/actors/dragon_names.txt");
		}
		if (nameProbabilities == null) {
			nameProbabilities = generateProbabilities(nameFrequencies);
		}

		String result = sampleWord(3, 8, nameProbabilities);
		return result.substring(0, 1).toUpperCase() + result.substring(1);
	}

	public static String generateText(int numberOfWords) {
		if (wordFrequencies == null) {
			wordFrequencies = loadFrequencies("src/model/actors/dragon_speech.txt");
		}
		if (wordProbabilities == null) {
			wordProbabilities = generateProbabilities(wordFrequencies);
		}

		String result = "";
		for (int i = 0; i < numberOfWords; i++) {
			result += " " + sampleWord(2, 15, wordProbabilities);
		}

		result = result.trim();
		return (result.substring(0, 1).toUpperCase() + result.substring(1));

	}

	/**
	 * @param values
	 * @param probabilities
	 * @return a random character from values, weighted according to the
	 *         probabilities
	 */
	private static char weightedRandom(char[] values, double[] probabilities) {
		double x = random.nextDouble();
		double cumulative = 0.0;
		char item = values[0];
		for (int i = 0; i < values.length; i++) {
			item = values[i];
			double p = probabilities[i];
			cumulative += p;
			if (x < cumulative)
				break;
		}
		return item;
	}

	/**
	 * Load letter transition frequencies from a text file
	 */
	private static int[][] loadFrequencies(String filename) {
		int[][] frequencies = new int[26][26];

		File namesFile = new File(filename);

		try (Stream<String> idsStream = Files.lines(namesFile.toPath(),
				StandardCharsets.US_ASCII)) {
			Object[][] ids = idsStream.map(String::trim)
					.map(line -> line.split("\\s+")).toArray(Object[][]::new);

			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < 26; j++) {
					frequencies[i][j] = Integer.parseInt((String) ids[i][j]);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return frequencies;
	}

	/**
	 * Transform frequencies into probabilities
	 * 
	 * @param frequencies
	 * @return
	 */
	private static HashMap<Character, HashMap<Character, Double>> generateProbabilities(
			int[][] frequencies) {
		HashMap<Character, HashMap<Character, Double>> probabilities = new HashMap<>();
		for (int i = 0; i < 26; i++) {
			int sum = IntStream.of(frequencies[i]).sum();
			if (sum != 0) {
				char c1 = letters.charAt(i);
				if (!probabilities.containsKey(c1)) {
					probabilities.put(c1, new HashMap<>());
				}
				for (int j = 0; j < 26; j++) {
					if (frequencies[i][j] != 0) {
						char c2 = letters.charAt(j);
						probabilities.get(c1).put(c2,
								((double) frequencies[i][j]) / sum);
					}
				}
			}
		}
		return probabilities;
	}

	/**
	 * Generate a word between min and max length, sampled from a particular set of
	 * transition probabilities
	 * 
	 * @param minLength
	 * @param maxLength
	 * @param probabilities
	 * @return
	 */
	private static String sampleWord(int minLength, int maxLength,
			HashMap<Character, HashMap<Character, Double>> probabilities) {
		int length = random.nextInt(maxLength - minLength) + minLength;

		ArrayList<Character> keys = new ArrayList<Character>(
				probabilities.keySet());

		ArrayList<Character> result_array = new ArrayList<>();

		result_array.add(keys.get(random.nextInt(keys.size())));

		for (int i = 0; i < length; i++) {
			char s = result_array.get(result_array.size() - 1);
			int n = probabilities.get(s).size();
			char[] values = new char[n];
			double[] probabilities_t = new double[n];
			int count = 0;
			for (char c : probabilities.get(s).keySet()) {
				values[count] = c;
				probabilities_t[count] = probabilities.get(s).get(c);
				count += 1;
			}
			result_array.add(weightedRandom(values, probabilities_t));
		}
		String result = "";
		for (int i = 0; i < result_array.size(); i++) {
			result += result_array.get(i);
		}
		return result;

	}
}
