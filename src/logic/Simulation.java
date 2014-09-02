package logic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

public class Simulation {

	public Simulation(JTextArea area) {
		this.area = area;
	}

	public JTextArea area;

	// inicializacia siete
	public List<Uzol> netInit(int count, int maxFriendCount) {

		List<Uzol> nodeList = new ArrayList<>();

		for (int i = 0; i < count; i++)
			nodeList.add(new Uzol(i, maxFriendCount));

		return nodeList;
	}

	// nahodne spoznavanie
	public void spoznanie1(Uzol node1, Uzol node2) {

		// sam so sebou nie
		if (node1.id == node2.id)
			return;

		// mame uz taky kontakt?
		if (node1.friends.contains(node2.id) == true)
			return;

		// je este volne miesto v zozname kazdeho vrcholu z dvojice spajanych?
		if (node1.friendList.size() >= node1.kapacita
				|| node2.friendList.size() >= node2.kapacita)
			return;

		// pridame kontakt do zoznamu kazdeho vrcholu
		node1.friends.add(node2.id);
		node2.friends.add(node1.id);

		node1.friendList.add(node2.id);
		node2.friendList.add(node1.id);

		node1.friendOfFriendQueue.add(node2);
		node2.friendOfFriendQueue.add(node1);

	}

	// spoznavanie na zaklade odporucania priatelov
	public void spoznanie2(Uzol node1, List<Uzol> nodeList) {

		// nemame ziadny kontakt, ktory mozeme odporucit
		if (node1.friendList.size() == 0)
			return;

		// vyberieme niektory nahodne
		int contactId2 = node1.friendList
				.get((int) (Math.random() * (node1.friendList.size() - 1)));
		Uzol node2 = nodeList.get(contactId2);

		// nemame ziadny kontakt pre dalsie odporucenie
		if (node2.friendList.size() == 0)
			return;

		// vyberieme niektory nahodne
		int contactId3 = node2.friendList
				.get((int) (Math.random() * (node2.friendList.size() - 1)));
		Uzol node3 = nodeList.get(contactId3);

		// kontakt na seba sameho
		if (node1.id == node3.id)
			return;

		// mame uz taky kontakt?
		if (node1.friends.contains(node3.id) == true)
			return;

		// je este volne miesto v zozname kazdeho vrcholu z dvojice spajanych?
		if (node1.friendList.size() >= node1.kapacita
				|| node3.friendList.size() >= node3.kapacita)
			return;

		// pridame kontakt do zoznamu vrcholov
		node1.friends.add(node3.id);
		node3.friends.add(node1.id);

		node1.friendList.add(node3.id);
		node3.friendList.add(node1.id);

		node1.friendOfFriendQueue.add(node3);
		node3.friendOfFriendQueue.add(node1);

	}

	// spoznanie na zaklade najobludenejsich vrcholov
	public void spoznanie3(Uzol node1) {

		// nemame ziadny kontakt na spoznanie
		if (node1.friendList.size() == 0)
			return;

		// vyberieme niektory s najvacsim poctom priatelov
		Uzol node2 = node1.friendOfFriendQueue.peek();

		if (node2 == null)
			return;

		// vyberieme niektory nahodne
		Uzol node3 = node2.friendOfFriendQueue.peek();

		if (node3 == null)
			return;

		// kontakt na mna
		if (node1.id == node3.id)
			return;

		// mame uz taky kontakt?
		if (node1.friends.contains(node3.id) == true)
			return;

		// je este volne miesto v zozname kazdeho vrcholu z dvojice spajanych?
		if (node1.friendList.size() >= node1.kapacita
				|| node3.friendList.size() >= node3.kapacita)
			return;

		// pridame kontakt do zoznamu
		node1.friends.add(node3.id);
		node3.friends.add(node1.id);

		node1.friendList.add(node3.id);
		node3.friendList.add(node1.id);

		node1.friendOfFriendQueue.add(node3);
		node3.friendOfFriendQueue.add(node1);

	}

	// vypis pocetnosti vrcholov
	public void showstatistics(List<Uzol> nodeList) {

		int[] suma = new int[nodeList.size()];

		// dalsi vrchol s takymto poctom kontaktov
		for (Uzol node : nodeList)
			suma[node.friendList.size()]++;

		area.append("\n\npocet vrcholov a pocetnost vazieb\n{\n ");
		for (int i = 0; i < nodeList.size(); i++) {
			if (suma[i] == 0)
				continue;
			area.append(i + "-vrcholovy uzol. Pocet takychto uzlov: " + suma[i]
					+ "\n");
		}
		area.append("}\n");
	}

	// nahodna cesta z vrcholu
	public int goingfrom(int start, int maxFriendCount, List<Uzol> nodeList) {
		int pocet;

		// kolko ciest vychadza z vrcholu?
		pocet = nodeList.get(start).friendList.size();

		if (pocet == 0)
			return (0); // ziadna
		if (pocet == 1)
			return (nodeList.get(start).friendList.get(0)); // iba jedna

		// viac, takze vyberieme jednu nahodnu
		return (nodeList.get(start).friendList
				.get((int) (Math.random() * (pocet))));
	}

	// aproximacia priemernej dlzky cesty
	public void mediumpath(int nodeCount, int maxFriendCount,
			List<Uzol> nodeList) {
		int v1, v2, vx, vy, count, length, statistics, i, k, m, cesty;
		int pokusy = 2000;
		int vylety = 1000;
		int bludenie = 100;

		statistics = 0;
		cesty = 0;
		for (m = 0; m < pokusy; m++) {
			// 2 nahodne vrcholy
			v1 = (int) (Math.random() * nodeCount);
			v2 = (int) (Math.random() * nodeCount);

			// rovnake vrcholy vybrate nahdone
			if (v1 == v2)
				continue;

			// inicializovanie najdlhsej cesty
			length = nodeCount;

			// robime opakovane vylety hladajuce najkratsiu cestu medzi v1 a v2
			for (k = 0; k < vylety; k++) {

				vx = v1;
				count = 0;
				for (i = 0; i < bludenie; i++) {
					// nahodne bludenie z pociatocneho vrcholu
					vy = goingfrom(vx, maxFriendCount, nodeList);
					vx = vy;
					count++;

					// nasli sme konecny vrchol
					if (vx == v2)
						break;

					// slepa cesta
					if (vx == 0)
						break;
				}

				// nenasli sme cielovy vrchol
				if (vx != v2)
					continue;

				// nasli sme koniec, zaznamename dlzku bludiveho pokusu
				if (count < length)
					length = count;
			}

			// cesta asi neexistuje alebo je dlhsia nez pocet uzlov
			if (length == nodeCount)
				continue;
			statistics = statistics + length;
			cesty++;
		}
		area.append("Priemerna zistena dlzka cesty - \n"
				+ "Pocet prehladanych vrcholov: " + statistics + "\n"
				+ "Pocet najdenych ciest: " + cesty + "\n");
		area.append("Priemerna dlzka cesty: " + (float) statistics
				/ (float) cesty + "\n");
	}

	public void simulate(int nodeCount, int maxFriendCount,
			int pocetRandomSpoznavani, int pocetVzajomnychSpoznavani,
			int pocetNajviacPriatelovSpoznavani, boolean cezPriatelov,
			boolean cezNajobludenejsichPriatelov) {

		List<Uzol> nodeList = netInit(nodeCount, maxFriendCount);

		// tvorba nahodnych kontaktov v sieti
		for (int i = 0; i < pocetRandomSpoznavani; i++)
			spoznanie1(nodeList.get((int) (Math.random() * (nodeCount - 1))),
					nodeList.get((int) (Math.random() * (nodeCount - 1))));

		// tvorba kontaktov v sieti po znamosti
		if (cezPriatelov == true)
			for (int i = 0; i < pocetVzajomnychSpoznavani; i++)
				spoznanie2(
						nodeList.get((int) (Math.random() * (nodeCount - 1))),
						nodeList);

		// tvorba nahodnych kontaktov v sieti
		if (cezNajobludenejsichPriatelov == true)
			for (int i = 0; i < pocetRandomSpoznavani; i++)
				spoznanie1(
						nodeList.get((int) (Math.random() * (nodeCount - 1))),
						nodeList.get((int) (Math.random() * (nodeCount - 1))));

		for (int i = 0; i < pocetNajviacPriatelovSpoznavani; i++)
			spoznanie3(nodeList.get((int) (Math.random() * (nodeCount - 1))));

		// ukazanie statistickych vysledkov
		showstatistics(nodeList);

		// pokus o zmeranie priemernej dlzky cesty
		mediumpath(nodeCount, maxFriendCount, nodeList);

		return;
	}

}
