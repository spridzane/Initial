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
	public int compareTo(Order item) {

//		if (!this.name.equals(item.name)) {
//			if (this.name.compareTo(item.name) > 0) {
//				return 1;
//			} else if (this.name.compareTo(item.name) < 0) {
//				return -1;
//			}
//		}
//
//		if (!this.customer.equals(item.customer)) {
//			if (this.customer.compareTo(item.customer) > 0) {
//				return 1;
//			} else if (this.customer.compareTo(item.customer) < 0) {
//				return -1;
//			}
//		}
//
//		if (this.count != item.count) {
//			if (this.count > item.count) {
//				return 1;
//			} else if (this.count < item.count) {
//				return -1;
//			}
//		}
//
//		return 0;
//	}
		int tmp = 0;
		tmp = this.name.compareTo(item.name);
		if (tmp == 0) {
			tmp = this.customer.compareTo(item.customer);
			if (tmp == 0) {
				tmp = this.count - item.count;
			}
		}
		if (tmp < 0) {
			tmp = -1;
		}
		if (tmp > 0) {
			tmp = 1;
		}
		return tmp;

	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Order) {
			Order order = (Order) object;
			if (this.compareTo(order) == 0)
				return true;
		}
		return false;
	}
	

	@Override
	public int hashCode() {
		// return name.hashCode() + customer.hashCode() + new Integer(count).hashCode();
		return toString().hashCode();

	} 

	@Override
	public String toString() {
		// "ItemName: OrdererName: Count"
		return name + ": " + customer + ": " + count;
	}

}
