package jtm.activity10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.IOCase;

/*-
 * This is simple text stream editor. It reads text from file/standard input,
 * changes/deletes passed line and writes everything into file/standard output.
 * 
 * Parameters:
 * 
 * 1. number of line.
 *    If positive number, line is replaced/added into text,
 *    if negative, line is deleted from the text.
 * 2. Text to add/replace into specified line.
 *    If number is negative, enter dash (or whatever) as a padding
 *    for this parameter as it will be ignored.
 * 3. input file.
 *    If dash is specified, editor reads from standard input, otherwise it is file name.
 * 4. output file.
 *    If dash is specified, editor writes to standard input, otherwise it is file name.
 * HINTS:
 * 1. to pass parameters, you can run editor in terminal, for example, with following command:
 *   java -cp target/classes jtm.activity10.StreamEditor 2 aaaa - -
 * 2. to pass parameters for command in Eclipse, select:
 *   a) menu: Run — Run configurations...
 *   b) tree: Java applications — StreamEditor
 *   c) tab arguments, and field Program arguments, enter parameters there
 *   d) press Run
 */
public class StreamEditor {

	public static void main(String[] args) throws Exception {
		String inFileName, outFileName; // Names of input and output files
		int inLineNo = 0; // Line number, which needs to be changed, deleted
		String content; // String content which needs to be put in line
		PrintWriter writer = null; // Buffered writer of characters
		BufferedReader reader = null; // Buffered reader of characters
		File inFile; // File for file system operations
		int curLineNo = 0; // Counter of current/read line of input file
		String curLineContent; // Content of current line of input file
		boolean delete = false; // Should delete line?

		/*-Check number of passed parameters. If they are null or number of
		 * them is not 4, write to standard error (System.err):
		 * Please use arguments: [-]lineNo (TextToAdd/Replace|-) (inputFile|-) (outputFile|-)
		 * and exit with System.exit(1); to pass error status of finished program.
		 */

		if (args == null || args.length != 4) {
			System.err.println("Please use arguments: [-]lineNo (TextToAdd/Replace|-) (inputFile|-) (outputFile|-)");
			System.exit(1);
		}

		// TODO Get integer from the 1st argument. Note that line should be
		// deleted if number is negative.
		// Hint. Use Integer.parseInt() to parse String into integer
		inLineNo = Integer.parseInt(args[0]);

		if (inLineNo < 0) {
			delete = true;
			inLineNo = Math.abs(inLineNo);
		}

		// set value of the string from 1st parameter into content
		content = args[1];

		/*- Initialize new buffered character reader (BufferedReader) and:
		 * 1. If input file name (3rd parameter) is "-", add reader to the Standard input (System.in).
		 * 2. Otherwise check if file exists (if it doesn't, create it) and 
		 *    add reader to this file.
		 */

		inFileName = args[2];
		if (inFileName.equals("-")) {

			reader = new BufferedReader(new InputStreamReader(System.in));

		} else {
			inFile = new File(inFileName);
			if (!inFile.exists()) {
				inFile.createNewFile();
			}
			reader = new BufferedReader(new FileReader(inFile));
		}

		/*- Initialize new buffered character writer (PrintWriter) and:
		 *  1. If output file name (4th parameter) is "-", add writer to the standard output (System.out)
		 *  2. Otherwise initialize writer to the file of given name.
		 */
		outFileName = args[3];

		if (outFileName.equals("-")) {
			writer = new PrintWriter(System.out);
		} else {
			writer = new PrintWriter(new FileWriter(outFileName));
		}

		// Read lines in loop from passed file/standard input till to the
		// end. Count number of read lines. Before appending line into writer
		// check, if it needs to be changed/deleted. Change its value to passed
		// content or just skip appending it to the writer.
		// NOTE: append break at the end of written line only if it is NOT null
		// or empty string!

		while ((curLineContent = reader.readLine()) != null && curLineContent.length() != 0) {
			curLineNo++;
			if (curLineNo == inLineNo) {
				if (delete) {
					continue;
				} else {
					writer.println(content);
				}
			} else {
				writer.println(curLineContent);
			}

		}

		// If number of input line is larger than number of lines in file,
		// pad file with empty lines before necessary line.

		if (curLineNo < inLineNo) {
			while (curLineNo < inLineNo - 1) {
				curLineNo++;
				writer.println();
			}
			writer.println(content);
		}

		// flush cache of the writer and close connections of the reader
		// and writer
		reader.close();
		writer.flush();
		writer.close();

	}
}
