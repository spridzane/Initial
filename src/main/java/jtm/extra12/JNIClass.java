package jtm.extra12;

public class JNIClass {

	private String value; // variable to store value from native methods

	/*- TODO #1 declare following native methods:
	 * 1. public native String createArray(int size)
	 * 2. public native void printArray(String string)
	 * 3. public static native void printHello()
	 */


	/*- TODO #2
	 * Implement public getter and setter for value
	 */


	// TODO #3 compile this class and generate C header file from it

	/*-
	 * Following static block and main() method is for manual testing purposes only.
	 * It could be be implemented in other Java file.
	 */

	// Load shared native library
	static {
		try {
			System.load(System.getProperty("user.dir") + "/lib/jnifunctions.so");
		} catch (UnsatisfiedLinkError e) {
			System.out.println("Native code library failed to load.\n" + e);
			System.exit(1);
		}
	}

	// Call native methods from main method
	public static void main(String[] args) {
	}

}
