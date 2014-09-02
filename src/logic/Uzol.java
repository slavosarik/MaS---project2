package logic;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class Uzol {

	public int kapacita;

	public int id;

	public List<Integer> friendList;

	Set<Integer> friends;

	Comparator<Uzol> comparator;

	// ukladam si tam uzly usporiadane podla ich poctu priatelov
	public PriorityQueue<Uzol> friendOfFriendQueue;

	public Uzol(int id, int kapacita) {
		this.id = id;
		this.kapacita = kapacita;
		comparator = new FriendCountComparator();
		friendOfFriendQueue = new PriorityQueue<Uzol>(kapacita, comparator);
		friendList = new LinkedList<>();
		friends = new TreeSet<Integer>();
	}

}
