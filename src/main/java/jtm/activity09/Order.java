package jtm.activity09;

import java.util.Objects;

/*- #1
 * Implement Comparable interface with Order class
 * Hint! Use generic type of comparable items in form: Comparable<Order>
 * 
 * 
 * 
 *  #2 Override/implement necessary methods for Order class:
 * - public Order(String orderer, String itemName, Integer count) — constructor of the Order
 * - public int compareTo(Order order) — comparison implementation according to logic described below
 * - public boolean equals(Object object) — check equality of orders
 * - public int hashCode() — to be able to handle it in some hash... collection 
 * - public String toString() — string in following form: "ItemName: OrdererName: Count"
 * 
 * Hints:
 * 1. When comparing orders, compare their values in following order:
 *    - Item name
 *    - Customer name
 *    - Count of items
 * If item or customer is closer to start of alphabet, it is considered "smaller"
 * 
 * 2. When implementing .equals() method, rely on compareTo() method, as for sane design
 * .equals() == true, if compareTo() == 0 (and vice versa).
 * 
 * 3. Also Ensure that .hashCode() is the same, if .equals() == true for two orders.
 * 
 */

public class Order implements Comparable<Order> {

	String customer; // Name of the customer
	String name; // Name of the requested item
	int count; // Count of the requested items

	public Order(String orderer, String itemName, Integer count) {
		this.customer = orderer;
		this.name = itemName;
		this.count = count;
	}

	@Override
	public int compareTo(Order o) {

		if (!this.name.equals(o.name)) {
			if (this.name.compareTo(o.name) > 0) {
				return 1;
			} else if (this.name.compareTo(o.name) < 0) {
				return -1;
			}
		}

		if (!this.customer.equals(o.customer)) {
			if (this.customer.compareTo(o.customer) > 0) {
				return 1;
			} else if (this.customer.compareTo(o.customer) < 0) {
				return -1;
			}
		}

		if (this.count != o.count) {
			if (this.count > o.count) {
				return 1;
			} else if (this.count < o.count) {
				return -1;
			}
		}

		return 0;
	}

	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if (o != null && o instanceof Order) {
			Order o1 = (Order) o;
			if ((customer == o1.customer) && (name == o1.name) && (count == o1.count)) {
				result = true;
			}
		}
		return result;

	}

	@Override
	public int hashCode() {
		// return name.hashCode() + customer.hashCode() + new Integer(count).hashCode();
		return Objects.hash(customer, name, count);

	}

	@Override
	public String toString() {
		// "ItemName: OrdererName: Count"
		return "ItemName: " + name + " OrdererName: " + customer + " Count: " + count;
	}

}
