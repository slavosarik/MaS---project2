package logic;

import java.util.Comparator;

public class FriendCountComparator implements Comparator<Uzol> {
	@Override
	public int compare(Uzol u1, Uzol u2) {

		if (u1.friendList.size() > u2.friendList.size()) {
			return -1;
		}
		if (u1.friendList.size() < u2.friendList.size()) {
			return 1;
		}
		return 0;
	}
}
