package jtm.extra06;

import java.util.LinkedList;

// #1
// import statically jtm.extra06.GenericsTest.log StringBuilder object
// from the closed source unit test
// Note that necessary messages should be written there
import static jtm.extra06.GenericsTest.log;

public class Generics<E extends Number> extends LinkedList<E> {

	// #2
	// Use Eclipse code generation prompter to add generated serial version ID
	// for this class to avoid warning
	private static final long serialVersionUID = 7708818085602746688L;

	// #3
	// Select Source— Generate Constructors from Superclass... and select
	// LinkedList<E>().
	// And implement extended constructor that after new Generics object is
	// created, log has appended line "Generics instance created"
	public Generics() {
		super();
		if (log == null)
			log = new StringBuilder();
		log.append("Generics instance created\n");
	}

	// #4
	// Select menu: Source— Override/Implement methods..., extend LinkedList<E>
	// class, and select push(E) and pop() methods to override them.

	@Override
	public E pop() {
		log.append(this.peek().getClass().getName() + ": " + this.peek() + " popped\n");
		return super.pop();
	}

	@Override
	public void push(E e) {
		log.append(e.getClass().getName() + ": " + e + " pushed\n");
		super.push(e);
	}

	// #4.1
	// Override pop() method of the list, that besides popping element from it
	// log has appended line: "java.lang.Integer: 1 popped", where:
	// java.lang.Integer is its class name, 1 is its actual value
	// HINT:
	// You can use this.peek() method to refer to any object being popped from
	// list

	// #4.2
	// override push() method that besides pushing new element into list
	// log has appended line: "java.lang.Double: 2 pushed", where:
	// java.lang.Double is actual class name of the value
	// 2 is its actual value
}
