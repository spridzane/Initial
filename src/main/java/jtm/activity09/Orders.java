package jtm.activity09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Set;
/*-
 *  #1
 * Create data structure to hold:
 *   1. some kind of collection of Orders (e.g. some List)
 *   2. index to the current order for iterations through the Orders in Orders
 *   Hints:
 *   1. you can use your own implementation or rely on .iterator() of the List
 *   2. when constructing list of orders, set number of current order to -1
 *      (which is usual approach when working with iterateable collections).
 */
/*-  #2
 * Implement Iterator interface with Orders class
 * Hint! Use generic type argument of iterateable items in form: Iterator<Order>
 * 
 *  #3 Override/implement public methods for Orders class:
 * - Orders()                — create new empty Orders
 * - add(Order item)            — add passed order to the Orders
 * - List<Order> getItemsList() — List of all customer orders
 * - Set<Order> getItemsSet()   — calculated Set of Orders from list (look at description below)
 * - sort()                     — sort list of orders according to the sorting rules
 * - boolean hasNext()          — check is there next Order in Orders
 * - Order next()               — get next Order from Orders, throw NoSuchElementException if can't
 * - remove()                   — remove current Order (order got by previous next()) from list, throw IllegalStateException if can't
 * - String toString()          — show list of Orders as a String
 * 
 * Hints:
 * 1. To convert Orders to String, reuse .toString() method of List.toString()
 * 2. Use built in List.sort() method to sort list of orders
 * 
 * TODO #4
 * When implementing getItemsSet() method, join all requests for the same item from different customers
 * in following way: if there are two requests:
 *  - ItemN: Customer1: 3
 *  - ItemN: Customer2: 1
 *  Set of orders should be:
 *  - ItemN: Customer1,Customer2: 4
 */

public class Orders implements Iterator<Order> {
	private List<Order> orders;
	private ListIterator<Order> iterator;

	public Orders() {
		orders = new LinkedList<>();
		iterator = orders.listIterator();
	}

	public void add(Order item) {
		iterator.add(item);
		iterator.previous();
	}

	public List<Order> getItemsList() {
		return orders;
	}

	public Set<Order> getItemsSet() {
		Set<Order> orderSet = new HashSet<>(orders);
		return orderSet;
	}

	public void sort() {
		Collections.sort(orders);
	}

	public void remove() {
		try {
		iterator.remove();
		}catch(IllegalStateException e){
			throw e;
		}
	}

	public String toString() {
		return orders.toString();

	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Order next() {
		if(!iterator.hasNext()) {
			throw new NoSuchElementException();
		}else
		return iterator.next();
	}
	}


