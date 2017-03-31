package core;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class GameBoard implements Serializable {

	/**
	 * 
	 */
	public int player = 0;
	private static final long serialVersionUID = 1L;
	public ArrayList<Path> allPaths = new ArrayList<Path>();
	public ArrayList<Hex> allHexes = new ArrayList<Hex>();
	public ArrayList<Joint> allJoints = new ArrayList<Joint>();
	public Robber rob;
	public ArrayList<Player> allPlayers = new ArrayList<Player>();
	public int currentRoll = -1;
	public int round = 1;
	//public boolean seeMap = true;

	public String toString() {
		String str = "";
		str = str + "allPaths size: " + allPaths.size() + " \n";
		str = str + "allHexes size: " + allHexes.size() + " \n";
		str = str + "allJoints size: " + allJoints.size() + " \n";

		return str;
	}

	public boolean nextTurn() {

		if (round == 1 && (!CurrentPlayerHasRoad() || !CurrentPlayerHasHouse())) {
			return false;
		} else if (round == 2 && (!CurrentPlayerHasTwoRoads() || !CurrentPlayerHasTwoHouses())) {
			return false;
		}

		if (player > allPlayers.size() - 2 && round == 1) {
			player = allPlayers.size() - 1;
			round++;
		} else if (player > allPlayers.size() - 2 && round != 2) {
			player = 0;
			round++;
		} else if (round == 2 && player == 0) {
			round++;
		} else if (round == 2)
			player--;
		else
			player++;

		if (round > 2) {
			int min = 1;
			int max = 7;// pretty sure this is non inclusive.. could be wrong
			Random rand = new Random();

			currentRoll = rand.nextInt(max - min) + min + rand.nextInt(max - min) + min;

			if (currentRoll == 7) {
				// handle robber situation

				for (int i = 0; i < allPlayers.size(); i++) {
					if (allPlayers.get(i).cards.size() >= 7) {
						Collections.shuffle(allPlayers.get(i).cards);

						int amount = allPlayers.get(i).cards.size() / 2;

						for (int x = 0; x < amount; x++) {
							allPlayers.get(i).cards.remove(0);
						}

					}
				}

			}

			for (int i = 0; i < allHexes.size(); i++) {

				if (allHexes.get(i).number == currentRoll && rob.location != i) {

					for (Integer num : allHexes.get(i).joints) {

						for (int y = 0; y < allPlayers.size(); y++) {
							if (allJoints.get(num).color.equals(allPlayers.get(y).color)) {
								if (allHexes.get(i).hexType == HexType.BRICK) {
									allPlayers.get(y).cards.add(ResourceCardType.BRICKRC);
									if (allJoints.get(num).city)
										allPlayers.get(y).cards.add(ResourceCardType.BRICKRC);
								} else if (allHexes.get(i).hexType == HexType.LUMBER) {
									allPlayers.get(y).cards.add(ResourceCardType.FORESTRC);
									if (allJoints.get(num).city)
										allPlayers.get(y).cards.add(ResourceCardType.FORESTRC);
								} else if (allHexes.get(i).hexType == HexType.SHEEP) {
									allPlayers.get(y).cards.add(ResourceCardType.SHEEPRC);
									if (allJoints.get(num).city)
										allPlayers.get(y).cards.add(ResourceCardType.SHEEPRC);
								} else if (allHexes.get(i).hexType == HexType.STONE) {
									allPlayers.get(y).cards.add(ResourceCardType.STONERC);
									if (allJoints.get(num).city)
										allPlayers.get(y).cards.add(ResourceCardType.STONERC);
								} else if (allHexes.get(i).hexType == HexType.WHEAT) {
									allPlayers.get(y).cards.add(ResourceCardType.WHEATRC);
									if (allJoints.get(num).city)
										allPlayers.get(y).cards.add(ResourceCardType.WHEATRC);
								}
							}
						}
					}
				}
			}

		}
		return true;

	}

	public Player GetWinner() {
		for (int i = 0; i < allPlayers.size(); i++) {

			int total = 0;

			for (int x = 0; x < allJoints.size(); x++) {

				if (allJoints.get(x).color.equals(allPlayers.get(i).color)) {
					total++;

					if (allJoints.get(x).city)
						total++;

				}
			}
			if (total >= 10) {
				return allPlayers.get(i);
			}
		}
		return null;
	}

	private boolean CurrentPlayerHasTwoRoads() {
		int road = 0;

		for (int i = 0; i < allPaths.size(); i++) {
			if (allPaths.get(i).color.equals(allPlayers.get(player).color))
				road++;
		}

		if (road == 2) {
			return true;
		}

		return false;

	}

	private boolean CurrentPlayerHasRoad() {
		int road = 0;

		for (int i = 0; i < allPaths.size(); i++) {
			if (allPaths.get(i).color.equals(allPlayers.get(player).color))
				road++;
		}

		System.out.println("road " + road);

		if (road == 1) {
			return true;
		}

		return false;

	}

	private boolean CurrentPlayerHasTwoHouses() {

		int house = 0;

		for (int i = 0; i < allJoints.size(); i++) {
			if (allJoints.get(i).color.equals(allPlayers.get(player).color))
				house++;
		}

		if (house == 2) {
			return true;
		}

		return false;
	}

	private boolean CurrentPlayerHasHouse() {

		int house = 0;

		for (int i = 0; i < allJoints.size(); i++) {
			if (allJoints.get(i).color.equals(allPlayers.get(player).color))
				house++;
		}

		if (house == 1) {
			return true;
		}

		return false;
	}

	public Player getCurrentPlayer() {
		return allPlayers.get(player);
	}

	public void scrambleNumbers() {
		for (int i = 0; i < allHexes.size(); i++) {
			if (allHexes.get(i).number != -1) {
				int min = 2;
				int max = 13;// pretty sure this is non inclusive.. could be
								// wrong
				Random rand = new Random();

				allHexes.get(i).number = rand.nextInt(max - min) + min;

				if (allHexes.get(i).number == 7)
					allHexes.get(i).number = 8;

			}
		}
	}

	public void scrambleHexes() {
         List<HexType> hexInitArray = new ArrayList<HexType>();
         hexInitArray.add(HexType.SAND);
		hexInitArray.add(HexType.BRICK);
		hexInitArray.add(HexType.BRICK);
		hexInitArray.add(HexType.BRICK);
		hexInitArray.add(HexType.STONE);
		hexInitArray.add(HexType.STONE);
		hexInitArray.add(HexType.STONE);
		hexInitArray.add(HexType.LUMBER);
		hexInitArray.add(HexType.LUMBER);
		hexInitArray.add(HexType.LUMBER);
		hexInitArray.add(HexType.LUMBER);
		hexInitArray.add(HexType.WHEAT);
		hexInitArray.add(HexType.WHEAT);
		hexInitArray.add(HexType.WHEAT);
		hexInitArray.add(HexType.WHEAT);
		hexInitArray.add(HexType.SHEEP);
		hexInitArray.add(HexType.SHEEP);
		hexInitArray.add(HexType.SHEEP);
		hexInitArray.add(HexType.SHEEP);

		Collections.shuffle(hexInitArray);// Scramble up the board pieces so
											// that the Constructors of the Hex
											// object can just pull the first
											// object
		
		for (int i = 0; i < allHexes.size(); i++) {
			if (allHexes.get(i).hexType != HexType.SAND) {
				allHexes.get(i).hexType = hexInitArray.remove(0);
				
			}
		}

	}
    public void SetHexTypeAndNumber(int index, HexType type, int number)
    {
    	Hex hex = allHexes.get(index);
    	hex.hexType = type;
    	hex.number = number;
    }
    
    private void InitJoints()
    {
    	// Joints from left to right, top to bottom
    			// Row 1
    			allJoints.add(new Joint((0.560546875), 0.1882716049382716, this));
    			allJoints.add(new Joint((0.6061197916666666), 0.147119341563786, this));
    			allJoints.add(new Joint((0.6477864583333334), 0.18209876543209877, this));
    			allJoints.add(new Joint((0.6920572916666666), 0.1440329218106996, this));
    			allJoints.add(new Joint((0.7350260416666666), 0.18209876543209877, this));
    			allJoints.add(new Joint((0.7799479166666666), 0.14094650205761317, this));
    			allJoints.add(new Joint((0.82421875), 0.18621399176954734, this));
    			// Row 2
    			allJoints.add(new Joint((0.5143229166666666), (0.31584362139917693), this));
    			allJoints.add(new Joint((0.5611979166666666), (0.2716049382716049), this));
    			allJoints.add(new Joint((0.6028645833333334), (0.3096707818930041), this));
    			allJoints.add(new Joint((0.6471354166666666), (0.26954732510288065), this));
    			allJoints.add(new Joint((0.69140625), (0.3117283950617284), this));
    			allJoints.add(new Joint((0.7369791666666666), (0.26954732510288065), this));
    			allJoints.add(new Joint((0.78125), (0.3117283950617284), this));
    			allJoints.add(new Joint((0.8255208333333334), (0.2705761316872428), this));
    			allJoints.add(new Joint((0.869140625), (0.30864197530864196), this));

    			// Row 3
    			allJoints.add(new Joint((0.4680989583333333), (0.4454732510288066), this));
    			allJoints.add(new Joint((0.5143229166666666), (0.3991769547325103), this));
    			allJoints.add(new Joint((0.5579427083333334), (0.4403292181069959), this));
    			allJoints.add(new Joint((0.6028645833333334), (0.3991769547325103), this));
    			allJoints.add(new Joint((0.6471354166666666), (0.4403292181069959), this));
    			allJoints.add(new Joint((0.693359375), (0.4022633744855967), this));
    			allJoints.add(new Joint((0.7389322916666666), (0.44238683127572015), this));
    			allJoints.add(new Joint((0.7819010416666666), (0.4022633744855967), this));
    			allJoints.add(new Joint((0.826171875), (0.43930041152263377), this));
    			allJoints.add(new Joint((0.8723958333333334), (0.39814814814814814), this));
    			allJoints.add(new Joint((0.9166666666666666), (0.43621399176954734), this));

    			// Row 4
    			allJoints.add(new Joint((0.470703125), (0.5318930041152263), this));
    			allJoints.add(new Joint((0.5130208333333334), (0.5771604938271605), this));
    			allJoints.add(new Joint((0.5592447916666666), (0.5318930041152263), this));
    			allJoints.add(new Joint((0.603515625), (0.5740740740740741), this));
    			allJoints.add(new Joint((0.6484375), (0.529835390946502), this));
    			allJoints.add(new Joint((0.6953125), (0.573045267489712), this));
    			allJoints.add(new Joint((0.7389322916666666), (0.5329218106995884), this));
    			allJoints.add(new Joint((0.7838541666666666), (0.573045267489712), this));
    			allJoints.add(new Joint((0.8294270833333334), (0.5277777777777778), this));
    			allJoints.add(new Joint((0.8776041666666666), (0.5689300411522634), this));
    			allJoints.add(new Joint((0.9205729166666666), (0.5246913580246914), this));

    			// Row 5
    			allJoints.add(new Joint((0.51171875), (0.6656378600823045), this));
    			allJoints.add(new Joint((0.5579427083333334), (0.7098765432098766), this));
    			allJoints.add(new Joint((0.6041666666666666), (0.6635802469135802), this));
    			allJoints.add(new Joint((0.6497395833333334), (0.7057613168724279), this));
    			allJoints.add(new Joint((0.6953125), (0.6646090534979424), this));
    			allJoints.add(new Joint((0.7408854166666666), (0.7067901234567902), this));
    			allJoints.add(new Joint((0.7884114583333334), (0.6635802469135802), this));
    			allJoints.add(new Joint((0.833984375), (0.7057613168724279), this));
    			allJoints.add(new Joint((0.8776041666666666), (0.6584362139917695), this));

    			// Row 6
    			allJoints.add(new Joint((0.5592447916666666), (0.8034979423868313), this));
    			allJoints.add(new Joint((0.6048177083333334), (0.845679012345679), this));
    			allJoints.add(new Joint((0.650390625), (0.8024691358024691), this));
    			allJoints.add(new Joint((0.697265625), (0.8415637860082305), this));
    			allJoints.add(new Joint((0.7434895833333334), (0.8024691358024691), this));
    			allJoints.add(new Joint((0.7903645833333334), (0.8395061728395061), this));
    			allJoints.add(new Joint((0.8346354166666666), (0.7962962962962963), this));
    }
    
	public GameBoard() {

		// Patrick put all the constructors below this comment

		InitJoints();

		// Tiles from left to right, top to bottom
		// Row 1
		allHexes.add(new Hex((0.607421875), (0.2345679012345679), this));
		allHexes.add(new Hex((0.6946614583333334), (0.23353909465020575), this));
		allHexes.add(new Hex((0.7819010416666666), (0.23148148148148148), this));
		// Row 2
		allHexes.add(new Hex((0.5618489583333334), (0.36213991769547327), this));
		allHexes.add(new Hex((0.6497395833333334), (0.3611111111111111), this));
		allHexes.add(new Hex((0.7395833333333334), (0.3611111111111111), this));
		allHexes.add(new Hex((0.8274739583333334), (0.3631687242798354), this));
		// Row 3
		allHexes.add(new Hex((0.5149739583333334), (0.492798353909465), this));
		allHexes.add(new Hex((0.6041666666666666), (0.49176954732510286), this));
		allHexes.add(new Hex((0.6966145833333334), (0.49382716049382713), this));
		allHexes.add(new Hex((0.78515625), (0.49382716049382713), this));
		allHexes.add(new Hex((0.875), (0.4876543209876543), this));
		// Row 4
		allHexes.add(new Hex((0.5579427083333334), (0.6306584362139918), this));
		allHexes.add(new Hex((0.6516927083333334), (0.6193415637860082), this));
		allHexes.add(new Hex((0.7421875), (0.6244855967078189), this));
		allHexes.add(new Hex((0.8326822916666666), (0.6193415637860082), this));
		// Row 5
		allHexes.add(new Hex((0.6048177083333334), (0.7582304526748971), this));
		allHexes.add(new Hex((0.6966145833333334), (0.7551440329218106), this));
		allHexes.add(new Hex((0.7884114583333334), (0.757201646090535), this));

		// Horizontal paths, from left to right, top to bottom
		// Row 1
		allPaths.add(
				new Path((0.5638020833333334), (0.19135802469135801), (0.6061197916666666), (0.15020576131687244), this));
		allPaths.add(new Path((0.6067708333333334), (0.15020576131687244), (0.6484375), (0.18724279835390947), this));
		allPaths.add(new Path((0.6484375), (0.18724279835390947), (0.6927083333333334), (0.14814814814814814), this));
		allPaths.add(new Path((0.6927083333333334), (0.14814814814814814), (0.7356770833333334), (0.1831275720164609), this));
		allPaths.add(new Path((0.7356770833333334), (0.1831275720164609), (0.7779947916666666), (0.14609053497942387), this));
		allPaths.add(
				new Path((0.7779947916666666), (0.14609053497942387), (0.8235677083333334), (0.18518518518518517), this));
		// Row 2
		allPaths.add(new Path((0.517578125), (0.31584362139917693), (0.5611979166666666), (0.2736625514403292), this));
		allPaths.add(new Path((0.5611979166666666), (0.2726337448559671), (0.6067708333333334), (0.31378600823045266), this));
		allPaths.add(new Path((0.6067708333333334), (0.31378600823045266), (0.650390625), (0.2716049382716049), this));
		allPaths.add(new Path((0.650390625), (0.2705761316872428), (0.6940104166666666), (0.31275720164609055), this));
		allPaths.add(new Path((0.6940104166666666), (0.3117283950617284), (0.73828125), (0.26954732510288065), this));
		allPaths.add(new Path((0.73828125), (0.26954732510288065), (0.7825520833333334), (0.3117283950617284), this));
		allPaths.add(new Path((0.7838541666666666), (0.3148148148148148), (0.8268229166666666), (0.2726337448559671), this));
		allPaths.add(new Path((0.8307291666666666), (0.2726337448559671), (0.869140625), (0.31069958847736623), this));

		// Row 3
		allPaths.add(new Path((0.4700520833333333), (0.4444444444444444), (0.515625), (0.4022633744855967), this));
		allPaths.add(new Path((0.515625), (0.40329218106995884), (0.5592447916666666), (0.44238683127572015), this));
		allPaths.add(new Path((0.5592447916666666), (0.44238683127572015), (0.60546875), (0.4012345679012346), this));
		allPaths.add(new Path((0.60546875), (0.4012345679012346), (0.650390625), (0.44238683127572015), this));
		allPaths.add(new Path((0.6497395833333334), (0.4444444444444444), (0.6953125), (0.4002057613168724), this));
		allPaths.add(new Path((0.6953125), (0.4002057613168724), (0.7395833333333334), (0.43930041152263377), this));
		allPaths.add(new Path((0.7395833333333334), (0.43930041152263377), (0.783203125), (0.39609053497942387), this));
		allPaths.add(new Path((0.783203125), (0.39609053497942387), (0.8287760416666666), (0.43930041152263377), this));
		allPaths.add(new Path((0.8287760416666666), (0.43930041152263377), (0.873046875), (0.3940329218106996), this));
		allPaths.add(new Path((0.873046875), (0.3940329218106996), (0.9186197916666666), (0.43930041152263377), this));
		// Row 4
		allPaths.add(new Path((0.4700520833333333), (0.5329218106995884), (0.5123697916666666), (0.5771604938271605), this));
		allPaths.add(new Path((0.5123697916666666), (0.5771604938271605), (0.5592447916666666), (0.5318930041152263), this));
		allPaths.add(new Path((0.5592447916666666), (0.5318930041152263), (0.6028645833333334), (0.5761316872427984), this));
		allPaths.add(new Path((0.6041666666666666), (0.5740740740740741), (0.650390625), (0.5318930041152263), this));
		allPaths.add(new Path((0.650390625), (0.5318930041152263), (0.6946614583333334), (0.5740740740740741), this));
		allPaths.add(new Path((0.6946614583333334), (0.5740740740740741), (0.7408854166666666), (0.5288065843621399), this));
		allPaths.add(new Path((0.7408854166666666), (0.5288065843621399), (0.7858072916666666), (0.5699588477366255), this));
		allPaths.add(new Path((0.7858072916666666), (0.5699588477366255), (0.830078125), (0.5277777777777778), this));
		allPaths.add(new Path((0.830078125), (0.5277777777777778), (0.8763020833333334), (0.5689300411522634), this));
		allPaths.add(new Path((0.8763020833333334), (0.5689300411522634), (0.9186197916666666), (0.5277777777777778), this));
		// Row 5
		allPaths.add(new Path((0.5130208333333334), (0.6676954732510288), (0.556640625), (0.7098765432098766), this));
		allPaths.add(new Path((0.556640625), (0.7098765432098766), (0.603515625), (0.6656378600823045), this));
		allPaths.add(new Path((0.603515625), (0.6656378600823045), (0.6490885416666666), (0.7098765432098766), this));
		allPaths.add(new Path((0.6490885416666666), (0.7098765432098766), (0.6953125), (0.6625514403292181), this));
		allPaths.add(new Path((0.6953125), (0.6625514403292181), (0.7421875), (0.7067901234567902), this));
		allPaths.add(new Path((0.7421875), (0.7067901234567902), (0.7884114583333334), (0.6635802469135802), this));
		allPaths.add(new Path((0.7884114583333334), (0.6635802469135802), (0.8346354166666666), (0.7067901234567902), this));
		allPaths.add(new Path((0.8346354166666666), (0.7067901234567902), (0.8782552083333334), (0.6594650205761317), this));
		// Row 6
		allPaths.add(new Path((0.5579427083333334), (0.8045267489711934), (0.6028645833333334), (0.8477366255144033), this));
		allPaths.add(new Path((0.6028645833333334), (0.8477366255144033), (0.6510416666666666), (0.8004115226337448), this));
		allPaths.add(new Path((0.6510416666666666), (0.8004115226337448), (0.6979166666666666), (0.8467078189300411), this));
		allPaths.add(new Path((0.6979166666666666), (0.8467078189300411), (0.744140625), (0.8024691358024691), this));
		allPaths.add(new Path((0.744140625), (0.8024691358024691), (0.7903645833333334), (0.845679012345679), this));
		allPaths.add(new Path((0.7903645833333334), (0.845679012345679), (0.8359375), (0.8004115226337448), this));

		// Vertical Paths, left to right, top to bottom
		// Row 1
		allPaths.add(new Path((0.5611979166666666), (0.18930041152263374), (0.5611979166666666), (0.2736625514403292), this));
		allPaths.add(new Path((0.650390625), (0.18621399176954734), (0.6510416666666666), (0.2705761316872428), this));
		allPaths.add(
				new Path((0.7369791666666666), (0.18415637860082304), (0.7369791666666666), (0.26954732510288065), this));
		allPaths.add(
				new Path((0.8235677083333334), (0.18518518518518517), (0.8255208333333334), (0.26440329218106995), this));
		// Row 2
		allPaths.add(new Path((0.5162760416666666), (0.31893004115226337), (0.5162760416666666), (0.3991769547325103), this));
		allPaths.add(new Path((0.6061197916666666), (0.3117283950617284), (0.6061197916666666), (0.3991769547325103), this));
		allPaths.add(new Path((0.6946614583333334), (0.31069958847736623), (0.6953125), (0.3991769547325103), this));
		allPaths.add(new Path((0.78125), (0.30864197530864196), (0.7825520833333334), (0.39300411522633744), this));
		allPaths.add(new Path((0.8697916666666666), (0.30761316872427985), (0.873046875), (0.3950617283950617), this));
		// Row 3
		allPaths.add(new Path((0.4694010416666667), (0.44650205761316875), (0.4694010416666667), (0.5339506172839507), this));
		allPaths.add(new Path((0.5598958333333334), (0.44238683127572015), (0.5592447916666666), (0.5288065843621399), this));
		allPaths.add(new Path((0.650390625), (0.44135802469135804), (0.6510416666666666), (0.5308641975308642), this));
		allPaths.add(new Path((0.7395833333333334), (0.4403292181069959), (0.7421875), (0.5288065843621399), this));
		allPaths.add(new Path((0.8287760416666666), (0.43930041152263377), (0.8307291666666666), (0.5267489711934157), this));
		allPaths.add(new Path((0.91796875), (0.43930041152263377), (0.9192708333333334), (0.522633744855967), this));
		// Row 4
		allPaths.add(new Path((0.513671875), (0.5771604938271605), (0.5130208333333334), (0.6656378600823045), this));
		allPaths.add(new Path((0.603515625), (0.5740740740740741), (0.6041666666666666), (0.6646090534979424), this));
		allPaths.add(new Path((0.6959635416666666), (0.5740740740740741), (0.6966145833333334), (0.6625514403292181), this));
		allPaths.add(new Path((0.787109375), (0.5699588477366255), (0.7877604166666666), (0.661522633744856), this));
		allPaths.add(new Path((0.8756510416666666), (0.5689300411522634), (0.87890625), (0.6563786008230452), this));
		// Row 5
		allPaths.add(new Path((0.5579427083333334), (0.7129629629629629), (0.5579427083333334), (0.8004115226337448), this));
		allPaths.add(new Path((0.6516927083333334), (0.7088477366255144), (0.6510416666666666), (0.801440329218107), this));
		allPaths.add(new Path((0.7421875), (0.7078189300411523), (0.7447916666666666), (0.7993827160493827), this));
		allPaths.add(new Path((0.8333333333333334), (0.7078189300411523), (0.833984375), (0.7973251028806584), this));

		for (int i = 0; i < allJoints.size(); i++) {
			allJoints.get(i).setAdjacent();
		}

		
		this.scrambleHexes();
		this.scrambleNumbers();
		this.placeRobberOnSand();
	}

	
    private void placeRobberOnSand()
    {
    	int sandIndex = 0;
		int sevenIndex = 0;
		
		for (int i = 0; i < allHexes.size(); i++) {
			if (allHexes.get(i).hexType == HexType.SAND)
			{
				sandIndex = i;
			}
			if(allHexes.get(i).number == 7)
			{
				sevenIndex = i;
			}
														
		}
		if(sandIndex != sevenIndex)
		{
			
			Hex temp = allHexes.get(sandIndex);
			allHexes.get(sandIndex).number = 7;
		}
		this.rob = new Robber(sandIndex);
    }

	public String getPlacePath(HashSet<Integer> selectedJoints) {
		// TODO Auto-generated method stub

		int sheepCount = 0;
		int wheatCount = 0;
		int woodCount = 0;
		int stoneCount = 0;
		int brickCount = 0;

		for (int i = 0; i < allPlayers.get(player).cards.size(); i++) {
			if (allPlayers.get(player).cards.get(i) == ResourceCardType.SHEEPRC)
				sheepCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.BRICKRC)
				brickCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.FORESTRC)
				woodCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.STONERC)
				stoneCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.WHEATRC)
				wheatCount++;

		}

		if (round == 1) {

			for (int i = 0; i < allPaths.size(); i++) {
				if (allPaths.get(i).joints.equals(selectedJoints)) {// Need to
																	// add more
																	// conditions
																	// and
																	// actions
					if (isGoodPathPlacement(i) && !CurrentPlayerHasRoad()) {
						return "Place Road";// Can return after finding the
											// right thing

					} else {

						return "End Turn";
					}
				}
			}
		} else if (round == 2) {

			for (int i = 0; i < allPaths.size(); i++) {
				if (allPaths.get(i).joints.equals(selectedJoints)) {// Need to
																	// add more
																	// conditions
																	// and
																	// actions
					if (isGoodPathPlacement(i) && !CurrentPlayerHasTwoRoads()) {
						return "Place Road";// Can return after finding the
											// right thing

					} else {
						return "End Turn";
					}
				}
			}

		} else {
			for (int i = 0; i < allPaths.size(); i++) {
				if (allPaths.get(i).joints.equals(selectedJoints)) {// Need to
																	// add more
																	// conditions
																	// and
																	// actions
					if (isGoodPathPlacement(i) && woodCount >= 1 && brickCount >= 1) {

						return "Place Road";// Can return after finding the
											// right thing

					} else {
						return "End Turn";
					}
				}
			}
		}

		return "End Turn";// if you weren't supposed to place a path
	}

	public boolean placePath(HashSet<Integer> selectedJoints) {
		// TODO Auto-generated method stub

		int sheepCount = 0;
		int wheatCount = 0;
		int woodCount = 0;
		int stoneCount = 0;
		int brickCount = 0;

		for (int i = 0; i < allPlayers.get(player).cards.size(); i++) {
			if (allPlayers.get(player).cards.get(i) == ResourceCardType.SHEEPRC)
				sheepCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.BRICKRC)
				brickCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.FORESTRC)
				woodCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.STONERC)
				stoneCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.WHEATRC)
				wheatCount++;

		}

		if (round == 1) {

			for (int i = 0; i < allPaths.size(); i++) {
				if (allPaths.get(i).joints.equals(selectedJoints)) {// Need to
																	// add more
																	// conditions
																	// and
																	// actions
					if (isGoodPathPlacement(i) && !CurrentPlayerHasRoad()) {
						allPaths.get(i).color = allPlayers.get(player).color;
						return true;// Can return after finding the right thing

					}
				}
			}
		} else if (round == 2) {

			for (int i = 0; i < allPaths.size(); i++) {
				if (allPaths.get(i).joints.equals(selectedJoints)) {// Need to
																	// add more
																	// conditions
																	// and
																	// actions
					if (isGoodPathPlacement(i) && !CurrentPlayerHasTwoRoads() && settlementDoesntHaveRoad(i)) {
						allPaths.get(i).color = allPlayers.get(player).color;
						return true;// Can return after finding the right thing

					}
				}
			}

		} else {
			for (int i = 0; i < allPaths.size(); i++) {
				if (allPaths.get(i).joints.equals(selectedJoints)) {// Need to
																	// add more
																	// conditions
																	// and
																	// actions
					if (isGoodPathPlacement(i) && woodCount >= 1 && brickCount >= 1) {
						allPaths.get(i).color = allPlayers.get(player).color;
						allPlayers.get(player).cards.remove(ResourceCardType.FORESTRC);
						allPlayers.get(player).cards.remove(ResourceCardType.BRICKRC);

						return true;// Can return after finding the right thing

					}
				}
			}
		}

		return false;// if you weren't supposed to place a path
	}

	private boolean settlementDoesntHaveRoad(int i) {
		// TODO Auto-generated method stub

		int jointIndex = -1;

		for (Integer num : allPaths.get(i).joints) {
			if (allJoints.get(num).color.equals(allPlayers.get(player)))// city
																		// road
																		// is
																		// gonna
																		// be
																		// attatched
																		// to
				jointIndex = num;
		}

		for (int x = 0; x < allPaths.size(); x++) {
			if (allPaths.get(x).color.equals(allPlayers.get(player).color)
					&& allPaths.get(x).joints.contains(jointIndex))
				return false;
		}

		return true;
	}

	public boolean placeRobber(HashSet<Integer> selectedJoints) {
		// TODO Auto-generated method stub

		for (int i = 0; i < allHexes.size(); i++) {
			if (allHexes.get(i).joints.equals(selectedJoints)) {// Need to add
																// more
																// conditions
																// and actions
				if (currentRoll == 7) {
					stealACard(i);

					rob.move(i);
					return true;// Can return after finding the right thing
				}
			}
		}

		return false;// If it wasn't supposed to place robber
	}

	public String getPlaceRobber(HashSet<Integer> selectedJoints) {
		// TODO Auto-generated method stub

		for (int i = 0; i < allHexes.size(); i++) {
			if (allHexes.get(i).joints.equals(selectedJoints)) {// Need to add
																// more
																// conditions
																// and actions
				if (currentRoll == 7) {
					return "Move Robber";// Can return after finding the right
											// thing
				}
			}
		}

		return "End Turn";// If it wasn't supposed to place robber
	}

	public String getPlaceSettlementOrCity(HashSet<Integer> selectedJoints) {
		// TODO Auto-generated method stub

		int sheepCount = 0;
		int wheatCount = 0;
		int woodCount = 0;
		int stoneCount = 0;
		int brickCount = 0;

		for (int i = 0; i < allPlayers.get(player).cards.size(); i++) {
			if (allPlayers.get(player).cards.get(i) == ResourceCardType.SHEEPRC)
				sheepCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.BRICKRC)
				brickCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.FORESTRC)
				woodCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.STONERC)
				stoneCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.WHEATRC)
				wheatCount++;

		}

		if (round == 1) {

			for (int i = 0; i < allJoints.size(); i++) {// houses

				if (selectedJoints.contains(i)) {// Need to add more conditions
													// and actions
					if (isGoodHousePlacement(i) && !CurrentPlayerHasHouse()) {
						return "Place Settlement";
					}

					else {
						return "Next Turn";
					}

				}
			}

		} else if (round == 2) {
			for (int i = 0; i < allJoints.size(); i++) {// houses

				if (selectedJoints.contains(i)) {// Need to add more conditions
													// and actions
					if (isGoodHousePlacement(i) && !CurrentPlayerHasTwoHouses()) {
						return "Place Settlement";
					}

					else {
						return "End Turn";
					}
				}

			}

		} else {

			for (int i = 0; i < allJoints.size(); i++) {// houses

				if (selectedJoints.contains(i)) {// Need to add more conditions
													// and actions
					if (isGoodHousePlacement(i) && brickCount >= 1 && woodCount >= 1 && wheatCount >= 1
							&& sheepCount >= 1) {
						return "Place Settlement";
					} else if (isGoodCityPlacement(i) && wheatCount >= 2 && stoneCount >= 3) {
						return "Place City";
					} else {
						return "End Turn";
					}

				}

			}
		}

		return "End Turn";
	}

	public boolean placeSettlementOrCity(HashSet<Integer> selectedJoints) {
		// TODO Auto-generated method stub

		int sheepCount = 0;
		int wheatCount = 0;
		int woodCount = 0;
		int stoneCount = 0;
		int brickCount = 0;

		for (int i = 0; i < allPlayers.get(player).cards.size(); i++) {
			if (allPlayers.get(player).cards.get(i) == ResourceCardType.SHEEPRC)
				sheepCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.BRICKRC)
				brickCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.FORESTRC)
				woodCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.STONERC)
				stoneCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.WHEATRC)
				wheatCount++;

		}

		if (round == 1) {

			for (int i = 0; i < allJoints.size(); i++) {// houses

				if (selectedJoints.contains(i)) {// Need to add more conditions
													// and actions
					if (isGoodHousePlacement(i) && !CurrentPlayerHasHouse()) {
						allJoints.get(i).color = allPlayers.get(player).color;
					}

					return true;
				}
			}

		} else if (round == 2) {
			for (int i = 0; i < allJoints.size(); i++) {// houses

				if (selectedJoints.contains(i)) {// Need to add more conditions
													// and actions
					if (isGoodHousePlacement(i) && !CurrentPlayerHasTwoHouses()) {
						allJoints.get(i).color = allPlayers.get(player).color;
					}

					else {
					}

					return true;
				}

			}

		} else {

			for (int i = 0; i < allJoints.size(); i++) {// houses

				if (selectedJoints.contains(i)) {// Need to add more conditions
													// and actions
					if (isGoodHousePlacement(i) && brickCount >= 1 && woodCount >= 1 && wheatCount >= 1
							&& sheepCount >= 1) {
						allPlayers.get(player).cards.remove(ResourceCardType.BRICKRC);
						allPlayers.get(player).cards.remove(ResourceCardType.FORESTRC);
						allPlayers.get(player).cards.remove(ResourceCardType.WHEATRC);
						allPlayers.get(player).cards.remove(ResourceCardType.SHEEPRC);
						allJoints.get(i).color = allPlayers.get(player).color;
					} else if (isGoodCityPlacement(i) && wheatCount >= 2 && stoneCount >= 3) {
						allPlayers.get(player).cards.remove(ResourceCardType.WHEATRC);
						allPlayers.get(player).cards.remove(ResourceCardType.WHEATRC);
						allPlayers.get(player).cards.remove(ResourceCardType.STONERC);
						allPlayers.get(player).cards.remove(ResourceCardType.STONERC);
						allPlayers.get(player).cards.remove(ResourceCardType.STONERC);
						allJoints.get(i).city = true;
					} else {
					}

					return true;
				}

			}
		}

		return false;
	}

	private void stealACard(int i) {
		// TODO Auto-generated method stub

		for (Integer num : allHexes.get(i).joints) {
			for (int y = 0; y < allPlayers.size(); y++) {
				if (allJoints.get(num).color.equals(allPlayers.get(y).color) && y != player) {
					if (allPlayers.get(y).cards.size() > 0) {
						Collections.shuffle(allPlayers.get(player).cards);
						allPlayers.get(player).cards.add(allPlayers.get(y).cards.remove(0));
						return;
					}

				}
			}
		}

	}

	private boolean isGoodCityPlacement(int i) {
		// TODO Auto-generated method stub
		if (!allJoints.get(i).color.equals(allPlayers.get(player).color))
			return false;

		return true;
	}

	private boolean isGoodHousePlacement(int i) {
		// TODO Auto-generated method stub

		if (!allJoints.get(i).color.equals(new Color(237, 201, 175))) // blank
																		// circle
			return false;

		for (int x = 0; x < allJoints.get(i).adjacent.size(); x++) { // away
																		// from
																		// everything
																		// else
			if (!allJoints.get(i).adjacent.get(x).color.equals(new Color(237, 201, 175)))
				return false;
		}

		if (round < 3)// Good for the beginners
			return true;

		for (int x = 0; x < allPaths.size(); x++) {
			if (allPaths.get(x).joints.contains(i) && allPaths.get(x).color.equals(allPlayers.get(player).color))
				return true;
		}

		return false;
	}

	private boolean isGoodPathPlacement(int i) {

		if (!allPaths.get(i).color.equals(new Color(129, 98, 78)))
			return false;

		for (Integer num : allPaths.get(i).joints) {

			if (allJoints.get(num).color.equals(allPlayers.get(player).color))
				return true;

		}

		int first = -1;
		int second = -1;

		for (Integer num : allPaths.get(i).joints) {

			if (first == -1)
				first = num;
			else
				second = num;

		}

		for (int x = 0; x < allPaths.size(); x++) {
			if (allPaths.get(x).joints.contains(first) || allPaths.get(x).joints.contains(second)) {
				if (allPaths.get(x).color.equals(allPlayers.get(player).color))
					return true;
			}

		}

		return false;

	}

	public void tradeWithBank(int playerCardSelect, int cardSelect) {

		int sheepCount = 0;
		int wheatCount = 0;
		int woodCount = 0;
		int stoneCount = 0;
		int brickCount = 0;

		System.out.println("player card: " + playerCardSelect);
		System.out.println("card select: " + cardSelect);

		for (int i = 0; i < allPlayers.get(player).cards.size(); i++) {
			if (allPlayers.get(player).cards.get(i) == ResourceCardType.SHEEPRC)
				sheepCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.BRICKRC)
				brickCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.FORESTRC)
				woodCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.STONERC)
				stoneCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.WHEATRC)
				wheatCount++;

		}

		int sheep = 0;
		int wood = 0;
		int wheat = 0;
		int stone = 0;
		int brick = 0;

		if (playerCardSelect == 1) {
			sheep = 4;
		} else if (playerCardSelect == 2) {
			wheat = 4;
		} else if (playerCardSelect == 3) {
			wood = 4;
		} else if (playerCardSelect == 4) {
			stone = 4;
		} else if (playerCardSelect == 5) {
			brick = 4;
		} else {
			return;
		}

		if (sheepCount >= sheep && woodCount >= wood && wheatCount >= wheat && stoneCount >= stone
				&& brickCount >= brick) {
			if (sheep + wood + wheat + stone + brick == 4) {

				if (cardSelect == 1) {
					for (int i = 0; i < sheep; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.SHEEPRC);
					}
					for (int i = 0; i < wheat; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.WHEATRC);
					}
					for (int i = 0; i < wood; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.FORESTRC);
					}
					for (int i = 0; i < stone; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.STONERC);
					}
					for (int i = 0; i < brick; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.BRICKRC);
					}

					allPlayers.get(player).cards.add(ResourceCardType.SHEEPRC);
				} else if (cardSelect == 2) {
					for (int i = 0; i < sheep; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.SHEEPRC);
					}
					for (int i = 0; i < wheat; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.WHEATRC);
					}
					for (int i = 0; i < wood; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.FORESTRC);
					}
					for (int i = 0; i < stone; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.STONERC);
					}
					for (int i = 0; i < brick; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.BRICKRC);
					}

					allPlayers.get(player).cards.add(ResourceCardType.WHEATRC);
				} else if (cardSelect == 3) {
					for (int i = 0; i < sheep; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.SHEEPRC);
					}
					for (int i = 0; i < wheat; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.WHEATRC);
					}
					for (int i = 0; i < wood; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.FORESTRC);
					}
					for (int i = 0; i < stone; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.STONERC);
					}
					for (int i = 0; i < brick; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.BRICKRC);
					}

					allPlayers.get(player).cards.add(ResourceCardType.FORESTRC);
				} else if (cardSelect == 4) {
					for (int i = 0; i < sheep; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.SHEEPRC);
					}
					for (int i = 0; i < wheat; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.WHEATRC);
					}
					for (int i = 0; i < wood; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.FORESTRC);
					}
					for (int i = 0; i < stone; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.STONERC);
					}
					for (int i = 0; i < brick; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.BRICKRC);
					}

					allPlayers.get(player).cards.add(ResourceCardType.STONERC);
				} else if (cardSelect == 5) {
					for (int i = 0; i < sheep; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.SHEEPRC);
					}
					for (int i = 0; i < wheat; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.WHEATRC);
					}
					for (int i = 0; i < wood; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.FORESTRC);
					}
					for (int i = 0; i < stone; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.STONERC);
					}
					for (int i = 0; i < brick; i++) {
						allPlayers.get(player).cards.remove(ResourceCardType.BRICKRC);
					}

					allPlayers.get(player).cards.add(ResourceCardType.BRICKRC);
				} else {
					System.out.println("please select a card to buy");
					return;
				}

			} else {

				return;

			}
			return;
		}

		return;
	}

	public String getTradeWithBank(int playerCardSelect, int cardSelect) {

		int sheepCount = 0;
		int wheatCount = 0;
		int woodCount = 0;
		int stoneCount = 0;
		int brickCount = 0;

		for (int i = 0; i < allPlayers.get(player).cards.size(); i++) {
			if (allPlayers.get(player).cards.get(i) == ResourceCardType.SHEEPRC)
				sheepCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.BRICKRC)
				brickCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.FORESTRC)
				woodCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.STONERC)
				stoneCount++;

			if (allPlayers.get(player).cards.get(i) == ResourceCardType.WHEATRC)
				wheatCount++;

		}

		int sheep = 0;
		int wood = 0;
		int wheat = 0;
		int stone = 0;
		int brick = 0;

		if (playerCardSelect == 1) {
			sheep = 4;
		} else if (playerCardSelect == 2) {
			wheat = 4;
		} else if (playerCardSelect == 3) {
			wood = 4;
		} else if (playerCardSelect == 4) {
			stone = 4;
		} else if (playerCardSelect == 5) {
			brick = 4;
		}

		if (sheepCount >= sheep && woodCount >= wood && wheatCount >= wheat && stoneCount >= stone
				&& brickCount >= brick) {
			if (sheep + wood + wheat + stone + brick == 4) {

				if (cardSelect == 1) {
					return "Trade With Bank";
				} else if (cardSelect == 2) {
					return "Trade With Bank";
				} else if (cardSelect == 3) {
					return "Trade With Bank";
				} else if (cardSelect == 4) {
					return "Trade With Bank";
				} else if (cardSelect == 5) {
					return "Trade With Bank";
				} else {
					return "End Turn";
				}

			} else {
				return "End Turn";

			}

		}

		return "End Turn";
	}

}
