package common;

import java.util.HashMap;

/**
 * A class to visually separate print statements by thread
 *
 */

public class ThreadPrinter {
	public static final int DEFAULT_COLUMN_SIZE = 30;
	
	private static HashMap<Long, Integer> threadToColumn = new HashMap<Long, Integer>();
	private static int numColumns = 0;
	private static int columnSize = DEFAULT_COLUMN_SIZE;
	
	
	private static void addColumns(StringBuilder builder, int numColumns) {
		for (int column = 0; column < numColumns; column++) {
			for (int space = 0; space < columnSize; space++) {
				builder.append(" ");
			}
			builder.append(" | ");
		}
	}
	
	private static void finishColumn(StringBuilder builder, int charsInCurrentColumn) {
		int spacesNeeded = columnSize - charsInCurrentColumn;
		for (int space = 0; space < spacesNeeded; space++) {
			builder.append(" ");
		}
		builder.append(" | ");
	}
	
	
	/**
	 * Prints the given object
	 * @param obj the object to print
	 */
	public synchronized static void print(Object obj) {
		long threadId = Thread.currentThread().getId();
		if (! threadToColumn.containsKey(threadId)) {
			threadToColumn.put(threadId, numColumns);
			numColumns ++;
		}
		
		String str = obj.toString();
		int columnNum = threadToColumn.get(threadId);
		int strPosition = 0;
		StringBuilder output = new StringBuilder();
		while (strPosition < str.length()) {
			addColumns(output, columnNum);
			int potentialEndPosition = strPosition + columnSize;
			int endPosition = potentialEndPosition < str.length() ? potentialEndPosition : str.length();
			output.append(str, strPosition, endPosition);
			finishColumn(output, endPosition - strPosition);
			strPosition += columnSize;
			addColumns(output, numColumns - (columnNum + 1));
			output.append('\n');
		}
		System.out.print(output.toString());
	}
	
	
	/**
	 * @param width the width of the columns
	 */
	public synchronized static void setColumnWidth(int width) {
		columnSize = width;
	}
	
}
