package submission.codeforces.adhoc;

/*
 * http://codeforces.com/contest/658/problem/B
 */

import java.util.PriorityQueue;
import java.util.Scanner;

public class BearAndDisplayedFriends {

	public static void main(String[] args) {
		BearAndDisplayedFriends sol = new BearAndDisplayedFriends();
		sol.run();
	}
	
	class Friend implements Comparable<Friend> {
		int id;
		int relation;
		public Friend(int id, int relation) {
			this.id = id;
			this.relation = relation;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj == null) return false;			
			if(obj == this) return true;			
			if((obj instanceof Friend)) {
				Friend other = (Friend) obj;				
				if(other.id == this.id && other.relation == this.relation) {
					return true;
				}
			}			
			return false;
		}

		@Override
		public int compareTo(Friend other) {
			return other.relation - this.relation;
		}
	}

	int n, k, q;
	PriorityQueue<Friend> onlineFriendList = new PriorityQueue<>();
	Friend[] friends;
	
	private void run() {
		Scanner in = new Scanner(System.in);		
		n = in.nextInt();
		k = in.nextInt();
		q = in.nextInt();
		friends = new Friend[n + 1]; // 1 indexed
		onlineFriendList.clear();
		for(int id = 1; id <= n; id++) {
			int relation = in.nextInt();
			Friend frnd = new Friend(id, relation);
			friends[id] = frnd;
		}
		
		for(int qi = 0; qi < q; qi++) {
			int cmd = in.nextInt();
			int id = in.nextInt();
			
			if(cmd == 1) {
				onlineFriendList.add(friends[id]);
			}else if(cmd == 2) {
				if(isOnDisplay(id)) {
					System.out.println("YES");
				}else {
					System.out.println("NO");
				}
			}
		}		
		in.close();
	}

	private boolean isOnDisplay(int id) {
		boolean onDisplay = false;
		if(onlineFriendList.size() > 0) {
			PriorityQueue<Friend> displayList = new PriorityQueue<>();
			int lim = k;
			while(!onlineFriendList.isEmpty() & (lim-- > 0)) {
				Friend frnd = onlineFriendList.poll();
				displayList.add(frnd);
				if(frnd.id == id) {
					onDisplay = true;
					break;
				}
			}			
			while(!displayList.isEmpty()) {
				onlineFriendList.add(displayList.poll());
			}
		}				
		return onDisplay;
	}

}
