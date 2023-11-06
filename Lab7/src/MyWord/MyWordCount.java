package MyWord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class MyWordCount {
	// public static final String fileName = "data/hamlet.txt";
	public static final String fileName = "data/fit.txt";

	private static final int TreeSet = 0;

	private List<String> words = new ArrayList<>();

	public MyWordCount() {
		try {
			this.words.addAll(Utils.loadWords(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Prints out the number of times each unique token appears in the file //số lần
	// mã thông báo duy nhất
	// data/hamlet.txt (or fit.txt)
	// In this method, we do not consider the order of tokens.
	public List<WordCount> getWordCounts() {

		Set<String> uniqueWords = new HashSet<>(words);
		List<WordCount> wordCouts = new ArrayList<>();
		for (String word : uniqueWords) {
			// int count = Collections.frequency(words, word);
			int count = counts(words, word);

			WordCount wCount = new WordCount(word, count);
			wordCouts.add(wCount);
		}
		return wordCouts;
	}

	private int counts(List<String> list, String word) {
		int count = 0;
		for (String element : list) {
			if (element.equals(word)) {
				count++;
			}
		}
		return count;
	}

	// Returns the words that their appearance are 1, do not consider duplidated

	public Set<String> getUniqueWords() {

		Set<String> uniqueWords = new HashSet<>();
		for (WordCount wc : getWordCounts()) {
			if (wc.getCount() == 1) {
				uniqueWords.add(wc.getWord());
			}
		}
		return uniqueWords;
	}

	// Returns the words in the text file, duplicated words appear once in the
	// result
	// tra ve tu ko trung lap
	public Set<String> getDistinctWords() {
		Set<String> distinctWords = new HashSet<>(words);
		return distinctWords;
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt) according ascending order of tokens //theo thu
	// tu tang dan
	// Example: An - 3, Bug - 10, ...
	public Set<WordCount> printWordCounts() {
		Set<WordCount> wordCounts = new TreeSet<>(new Comparator<WordCount>() {

			@Override
			public int compare(WordCount o1, WordCount o2) {
				// return o1.getWord().compareTo(o2.getWord());
				int re = o1.getCount() - o2.getCount();
				if (re == 0) {
					return o1.getWord().compareTo(o2.getWord());
				}
				return re;
			}
		});
		wordCounts.addAll(this.getWordCounts());
		return wordCounts;
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt) according descending order of occurences
	// Example: Bug - 10, An - 3, Nam - 2.
	public Set<WordCount> exportWordCountsByOccurence() {
		Set<WordCount> wordsCouts = new TreeSet<>(new Comparator<WordCount>() {

			@Override
			public int compare(WordCount o1, WordCount o2) {
				int re = o2.getCount() - o1.getCount();
				if (re == 0) {
					return o2.getWord().compareTo(o1.getWord());
				}
				return re;
			}

		});
		wordsCouts.addAll(this.getWordCounts());
		return wordsCouts;

	}

	// delete words begining with the given pattern (i.e., delete words begin with
	// 'A' letter
	// xoa cac tu bat dau bang tu da cho la 'A'
	public Set<String> filterWords(char pattern) {
		// Set<WordCount> filterWords = new HashSet<>();
		Set<String> filterWords = new HashSet<>();
		for (String word : words) {

			if (!(word.charAt(0) == pattern))
				filterWords.add(word);

		}
		return filterWords;
	}

	// public class MainTest {
	public static void main(String[] args) {
		MyWordCount wordCount = new MyWordCount();

		System.out.println("Word Counts: " + wordCount.getWordCounts());
		System.out.println();

		// Test getUniqueWords()
		Set<String> uniqueWords = wordCount.getUniqueWords();
		System.out.println("Unique Words:");
		for (String word : uniqueWords) {
			System.out.println(word);
		}
		System.out.println();

		// Test getDistinctWords()
		System.out.println("Distinct Words: " + wordCount.getDistinctWords());
		System.out.println();

		// Test printWordCounts
		System.out.println("PrintWordCounts: " + wordCount.printWordCounts());
		System.out.println();

		// Test exportWordCountsOrderByOccurence()
		System.out.println("ExportWordCountsOrderByOccurence: " + wordCount.exportWordCountsByOccurence());
		System.out.println();

		System.out.println("FilterWords: " + wordCount.filterWords('L'));
	}
}
