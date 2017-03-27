import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


public class GameBoard implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static ArrayList<Path> allPaths = new ArrayList<Path>();
	public static ArrayList<Hex> allHexes = new ArrayList<Hex>();
	public static ArrayList<Joint> allJoints = new ArrayList<Joint>();
	public static ArrayList<HexType> hexInitArray = new ArrayList<HexType>();

public String toString(){
	String str = "";
	str = str + "allPaths size: "+allPaths.size()+" \n";
	str = str + "allHexes size: "+allHexes.size()+" \n";
	str = str + "allJoints size: "+allJoints.size()+" \n";
	
	return str;
}


public GameBoard(){
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
	
	
	Collections.shuffle(hexInitArray);//Scramble up the board pieces so that the Constructors of the Hex object can just pull the first object
	
	
	
	
	 allJoints.add(new Joint((0.5 ),(0.5749185667752443 )));
	 allJoints.add(new Joint((0.6382783882783882 ),(0.13517915309446255 )));
	 allJoints.add(new Joint((0.5641025641025641 ),(0.17100977198697068 )));
	 allJoints.add(new Joint((0.565018315018315 ),(0.25732899022801303 )));
	 allJoints.add(new Joint((0.641941391941392 ),(0.30130293159609123 )));
	 allJoints.add(new Joint((0.7106227106227107 ),(0.25895765472312704 )));
	 allJoints.add(new Joint((0.7078754578754579 ),(0.1791530944625407 )));
	 allJoints.add(new Joint((0.6437728937728938 ),(0.3957654723127036 )));
	 allJoints.add(new Joint((0.7225274725274725 ),(0.44462540716612375 )));
	 allJoints.add(new Joint((0.7902930402930403 ),(0.3941368078175896 )));
	 allJoints.add(new Joint((0.7884615384615384 ),(0.30130293159609123 )));
	 allJoints.add(new Joint((0.8663003663003663 ),(0.44299674267100975 )));
	 allJoints.add(new Joint((0.8672161172161172 ),(0.5260586319218241 )));
	 allJoints.add(new Joint((0.8012820512820513 ),(0.5798045602605864 )));
	 allJoints.add(new Joint((0.7234432234432234 ),(0.5342019543973942 )));
	 allJoints.add(new Joint((0.6529304029304029 ),(0.5830618892508144 )));
	 allJoints.add(new Joint((0.6538461538461539 ),(0.6710097719869706 )));
	 allJoints.add(new Joint((0.7307692307692307 ),(0.7231270358306189 )));
	 allJoints.add(new Joint((0.7985347985347986 ),(0.6661237785016286 )));
	 allJoints.add(new Joint((0.7261904761904762 ),(0.8143322475570033 )));
	 allJoints.add(new Joint((0.6575091575091575 ),(0.8615635179153095 )));
	 allJoints.add(new Joint((0.575091575091575 ),(0.8127035830618893 )));
	 allJoints.add(new Joint((0.5787545787545788 ),(0.7198697068403909 )));
	 allJoints.add(new Joint((0.4990842490842491 ),(0.6742671009771987 )));
	 allJoints.add(new Joint((0.5027472527472527 ),(0.8583061889250815 )));
	 allJoints.add(new Joint((0.4249084249084249 ),(0.8224755700325733 )));
	 allJoints.add(new Joint((0.423992673992674 ),(0.7296416938110749 )));
	 allJoints.add(new Joint((0.3434065934065934 ),(0.6775244299674267 )));
	 allJoints.add(new Joint((0.2683150183150183 ),(0.7296416938110749 )));
	 allJoints.add(new Joint((0.2692307692307692 ),(0.8094462540716613 )));
	 allJoints.add(new Joint((0.3516483516483517 ),(0.8599348534201955 )));
	 allJoints.add(new Joint((0.5695970695970696 ),(0.5325732899022801 )));
	 allJoints.add(new Joint((0.5714285714285714 ),(0.43485342019543977 )));
	 allJoints.add(new Joint((0.4899267399267399 ),(0.30944625407166126 )));
	 allJoints.add(new Joint((0.4945054945054945 ),(0.3957654723127036 )));
	 allJoints.add(new Joint((0.42032967032967034 ),(0.44625407166123776 )));
	 allJoints.add(new Joint((0.42216117216117216 ),(0.5276872964169381 )));
	 allJoints.add(new Joint((0.3443223443223443 ),(0.5863192182410424 )));
	 allJoints.add(new Joint((0.2673992673992674 ),(0.5407166123778502 )));
	 allJoints.add(new Joint((0.19322344322344323 ),(0.5879478827361564 )));
	 allJoints.add(new Joint((0.19230769230769232 ),(0.6726384364820847 )));
	 allJoints.add(new Joint((0.27197802197802196 ),(0.4560260586319218 )));
	 allJoints.add(new Joint((0.34523809523809523 ),(0.3973941368078176 )));
	 allJoints.add(new Joint((0.34523809523809523 ),(0.30944625407166126 )));
	 allJoints.add(new Joint((0.4175824175824176 ),(0.26058631921824105 )));
	 allJoints.add(new Joint((0.42032967032967034 ),(0.16938110749185667 )));
	 allJoints.add(new Joint((0.49175824175824173 ),(0.13355048859934854 )));
	 allJoints.add(new Joint((0.34706959706959706 ),(0.13029315960912052 )));
	 allJoints.add(new Joint((0.27380952380952384 ),(0.1791530944625407 )));
	 allJoints.add(new Joint((0.26556776556776557 ),(0.26384364820846906 )));
	 allJoints.add(new Joint((0.1987179487179487 ),(0.31107491856677527 )));
	 allJoints.add(new Joint((0.18772893772893773 ),(0.3957654723127036 )));
	 allJoints.add(new Joint((0.12362637362637363 ),(0.44788273615635177 )));
	 allJoints.add(new Joint((0.11996336996336997 ),(0.5342019543973942 )));
	//Put joints in first so that hexes and paths can do calculations
	
	
	 allHexes.add(new Hex((0.34706959706959706 ),(0.2247557003257329 )));
	 allHexes.add(new Hex((0.49175824175824173 ),(0.21986970684039087 )));
	 allHexes.add(new Hex((0.6391941391941391 ),(0.22312703583061888 )));
	 allHexes.add(new Hex((0.2664835164835165 ),(0.35993485342019543 )));
	 allHexes.add(new Hex((0.42032967032967034 ),(0.3583061889250814 )));
	 allHexes.add(new Hex((0.5677655677655677 ),(0.3583061889250814 )));
	 allHexes.add(new Hex((0.7197802197802198 ),(0.36156351791530944 )));
	 allHexes.add(new Hex((0.19139194139194138 ),(0.501628664495114 )));
	 allHexes.add(new Hex((0.34615384615384615 ),(0.496742671009772 )));
	 allHexes.add(new Hex((0.4981684981684982 ),(0.49185667752442996 )));
	 allHexes.add(new Hex((0.6501831501831502 ),(0.49185667752442996 )));
	 allHexes.add(new Hex((0.7994505494505495 ),(0.49185667752442996 )));
	 allHexes.add(new Hex((0.2683150183150183 ),(0.6335504885993485 )));
	 allHexes.add(new Hex((0.423992673992674 ),(0.6302931596091205 )));
	 allHexes.add(new Hex((0.5732600732600732 ),(0.6319218241042345 )));
	 allHexes.add(new Hex((0.7271062271062271 ),(0.6368078175895765 )));
	 allHexes.add(new Hex((0.34798534798534797 ),(0.7736156351791531 )));
	 allHexes.add(new Hex((0.5009157509157509 ),(0.7719869706840391 )));
	 allHexes.add(new Hex((0.6510989010989011 ),(0.7703583061889251 )));

	 allPaths.add(new Path((0.6529304029304029 ),(0.5814332247557004 ) ,(0.7243589743589743 ) , (0.5309446254071661 )  ));
	 allPaths.add(new Path((0.7243589743589743 ),(0.5309446254071661 ) ,(0.8012820512820513 ) , (0.5814332247557004 )  ));
	 allPaths.add(new Path((0.8012820512820513 ),(0.5814332247557004 ) ,(0.7994505494505495 ) , (0.6661237785016286 )  ));
	 allPaths.add(new Path((0.7994505494505495 ),(0.6661237785016286 ) ,(0.7307692307692307 ) , (0.7214983713355049 )  ));
	 allPaths.add(new Path((0.7298534798534798 ),(0.7214983713355049 ) ,(0.6529304029304029 ) , (0.6742671009771987 )  ));
	 allPaths.add(new Path((0.6529304029304029 ),(0.6742671009771987 ) ,(0.6510989010989011 ) , (0.5895765472312704 )  ));
	 allPaths.add(new Path((0.7206959706959707 ),(0.44462540716612375 ) ,(0.7921245421245421 ) , (0.3925081433224756 )  ));
	 allPaths.add(new Path((0.7921245421245421 ),(0.3925081433224756 ) ,(0.8663003663003663 ) , (0.43973941368078173 )  ));
	 allPaths.add(new Path((0.8663003663003663 ),(0.43973941368078173 ) ,(0.8672161172161172 ) , (0.5260586319218241 )  ));
	 allPaths.add(new Path((0.8672161172161172 ),(0.5260586319218241 ) ,(0.8076923076923077 ) , (0.5749185667752443 )  ));
	 allPaths.add(new Path((0.7225274725274725 ),(0.5374592833876222 ) ,(0.7216117216117216 ) , (0.44625407166123776 )  ));
	 allPaths.add(new Path((0.7225274725274725 ),(0.44299674267100975 ) ,(0.6437728937728938 ) , (0.3925081433224756 )  ));
	 allPaths.add(new Path((0.6437728937728938 ),(0.3925081433224756 ) ,(0.6428571428571429 ) , (0.30456026058631924 )  ));
	 allPaths.add(new Path((0.6428571428571429 ),(0.30456026058631924 ) ,(0.7133699633699634 ) , (0.25895765472312704 )  ));
	 allPaths.add(new Path((0.7133699633699634 ),(0.25895765472312704 ) ,(0.7875457875457875 ) , (0.30293159609120524 )  ));
	 allPaths.add(new Path((0.7875457875457875 ),(0.30293159609120524 ) ,(0.7893772893772893 ) , (0.38436482084690554 )  ));
	 allPaths.add(new Path((0.7087912087912088 ),(0.25895765472312704 ) ,(0.7078754578754579 ) , (0.1758957654723127 )  ));
	 allPaths.add(new Path((0.7078754578754579 ),(0.1758957654723127 ) ,(0.6355311355311355 ) , (0.13029315960912052 )  ));
	 allPaths.add(new Path((0.6336996336996337 ),(0.13517915309446255 ) ,(0.5641025641025641 ) , (0.17100977198697068 )  ));
	 allPaths.add(new Path((0.5641025641025641 ),(0.17100977198697068 ) ,(0.5659340659340659 ) , (0.26058631921824105 )  ));
	 allPaths.add(new Path((0.5659340659340659 ),(0.26058631921824105 ) ,(0.6401098901098901 ) , (0.30130293159609123 )  ));
	 allPaths.add(new Path((0.5641025641025641 ),(0.1742671009771987 ) ,(0.4926739926739927 ) , (0.13355048859934854 )  ));
	 allPaths.add(new Path((0.49175824175824173 ),(0.13355048859934854 ) ,(0.4166666666666667 ) , (0.1742671009771987 )  ));
	 allPaths.add(new Path((0.4166666666666667 ),(0.1742671009771987 ) ,(0.34523809523809523 ) , (0.13192182410423453 )  ));
	 allPaths.add(new Path((0.34523809523809523 ),(0.13192182410423453 ) ,(0.27197802197802196 ) , (0.1791530944625407 )  ));
	 allPaths.add(new Path((0.27197802197802196 ),(0.1791530944625407 ) ,(0.2692307692307692 ) , (0.26221498371335505 )  ));
	 allPaths.add(new Path((0.2692307692307692 ),(0.26221498371335505 ) ,(0.19688644688644688 ) , (0.31107491856677527 )  ));
	 allPaths.add(new Path((0.19688644688644688 ),(0.31107491856677527 ) ,(0.19322344322344323 ) , (0.3990228013029316 )  ));
	 allPaths.add(new Path((0.19322344322344323 ),(0.3990228013029316 ) ,(0.1227106227106227 ) , (0.44788273615635177 )  ));
	 allPaths.add(new Path((0.1227106227106227 ),(0.44788273615635177 ) ,(0.11904761904761904 ) , (0.5342019543973942 )  ));
	 allPaths.add(new Path((0.11904761904761904 ),(0.5342019543973942 ) ,(0.19230769230769232 ) , (0.5846905537459284 )  ));
	 allPaths.add(new Path((0.19230769230769232 ),(0.5846905537459284 ) ,(0.19230769230769232 ) , (0.6758957654723127 )  ));
	 allPaths.add(new Path((0.19230769230769232 ),(0.6758957654723127 ) ,(0.2683150183150183 ) , (0.7247557003257329 )  ));
	 allPaths.add(new Path((0.2683150183150183 ),(0.7247557003257329 ) ,(0.2692307692307692 ) , (0.8127035830618893 )  ));
	 allPaths.add(new Path((0.5631868131868132 ),(0.26058631921824105 ) ,(0.49175824175824173 ) , (0.30618892508143325 )  ));
	 allPaths.add(new Path((0.49175824175824173 ),(0.30618892508143325 ) ,(0.49175824175824173 ) , (0.3957654723127036 )  ));
	 allPaths.add(new Path((0.4945054945054945 ),(0.3957654723127036 ) ,(0.5695970695970696 ) , (0.4364820846905538 )  ));
	 allPaths.add(new Path((0.5705128205128205 ),(0.4381107491856677 ) ,(0.641941391941392 ) , (0.3925081433224756 )  ));
	 allPaths.add(new Path((0.5705128205128205 ),(0.44136807817589574 ) ,(0.5705128205128205 ) , (0.5276872964169381 )  ));
	 allPaths.add(new Path((0.5705128205128205 ),(0.5325732899022801 ) ,(0.6446886446886447 ) , (0.5732899022801303 )  ));
	 allPaths.add(new Path((0.5732600732600732 ),(0.5293159609120521 ) ,(0.5018315018315018 ) , (0.5765472312703583 )  ));
	 allPaths.add(new Path((0.5018315018315018 ),(0.5814332247557004 ) ,(0.49633699633699635 ) , (0.6726384364820847 )  ));
	 allPaths.add(new Path((0.49633699633699635 ),(0.6726384364820847 ) ,(0.5769230769230769 ) , (0.7214983713355049 )  ));
	 allPaths.add(new Path((0.6584249084249084 ),(0.6677524429967426 ) ,(0.5769230769230769 ) , (0.7198697068403909 )  ));
	 allPaths.add(new Path((0.5769230769230769 ),(0.7198697068403909 ) ,(0.5778388278388278 ) , (0.8143322475570033 )  ));
	 allPaths.add(new Path((0.5778388278388278 ),(0.8143322475570033 ) ,(0.6565934065934066 ) , (0.8631921824104235 )  ));
	 allPaths.add(new Path((0.6575091575091575 ),(0.8631921824104235 ) ,(0.7298534798534798 ) , (0.8175895765472313 )  ));
	 allPaths.add(new Path((0.7298534798534798 ),(0.8175895765472313 ) ,(0.7307692307692307 ) , (0.7214983713355049 )  ));
	 allPaths.add(new Path((0.4175824175824176 ),(0.17100977198697068 ) ,(0.4166666666666667 ) , (0.26058631921824105 )  ));
	 allPaths.add(new Path((0.4166666666666667 ),(0.26058631921824105 ) ,(0.4935897435897436 ) , (0.30944625407166126 )  ));
	 allPaths.add(new Path((0.4175824175824176 ),(0.26221498371335505 ) ,(0.3443223443223443 ) , (0.31433224755700323 )  ));
	 allPaths.add(new Path((0.34523809523809523 ),(0.31433224755700323 ) ,(0.3443223443223443 ) , (0.3957654723127036 )  ));
	 allPaths.add(new Path((0.3443223443223443 ),(0.3973941368078176 ) ,(0.4194139194139194 ) , (0.44625407166123776 )  ));
	 allPaths.add(new Path((0.4194139194139194 ),(0.44788273615635177 ) ,(0.4945054945054945 ) , (0.3957654723127036 )  ));
	 allPaths.add(new Path((0.42216117216117216 ),(0.44299674267100975 ) ,(0.42032967032967034 ) , (0.5342019543973942 )  ));
	 allPaths.add(new Path((0.5036630036630036 ),(0.5798045602605864 ) ,(0.42032967032967034 ) , (0.5293159609120521 )  ));
	 allPaths.add(new Path((0.42124542124542125 ),(0.5325732899022801 ) ,(0.34523809523809523 ) , (0.5846905537459284 )  ));
	 allPaths.add(new Path((0.3443223443223443 ),(0.5846905537459284 ) ,(0.2673992673992674 ) , (0.5358306188925082 )  ));
	 allPaths.add(new Path((0.2673992673992674 ),(0.5374592833876222 ) ,(0.27289377289377287 ) , (0.44788273615635177 )  ));
	 allPaths.add(new Path((0.27289377289377287 ),(0.44788273615635177 ) ,(0.18956043956043955 ) , (0.4022801302931596 )  ));
	 allPaths.add(new Path((0.27289377289377287 ),(0.44788273615635177 ) ,(0.34523809523809523 ) , (0.3973941368078176 )  ));
	 allPaths.add(new Path((0.3489010989010989 ),(0.31433224755700323 ) ,(0.26556776556776557 ) , (0.26547231270358307 )  ));
	 allPaths.add(new Path((0.2673992673992674 ),(0.5342019543973942 ) ,(0.18681318681318682 ) , (0.5928338762214984 )  ));
	 allPaths.add(new Path((0.3424908424908425 ),(0.5879478827361564 ) ,(0.3443223443223443 ) , (0.6742671009771987 )  ));
	 allPaths.add(new Path((0.3443223443223443 ),(0.6742671009771987 ) ,(0.2683150183150183 ) , (0.7247557003257329 )  ));
	 allPaths.add(new Path((0.34523809523809523 ),(0.6742671009771987 ) ,(0.4249084249084249 ) , (0.7280130293159609 )  ));
	 allPaths.add(new Path((0.4249084249084249 ),(0.7280130293159609 ) ,(0.49725274725274726 ) , (0.6758957654723127 )  ));
	 allPaths.add(new Path((0.423992673992674 ),(0.7263843648208469 ) ,(0.4249084249084249 ) , (0.8192182410423453 )  ));
	 allPaths.add(new Path((0.4249084249084249 ),(0.8192182410423453 ) ,(0.3534798534798535 ) , (0.8648208469055375 )  ));
	 allPaths.add(new Path((0.3534798534798535 ),(0.8648208469055375 ) ,(0.27197802197802196 ) , (0.8175895765472313 )  ));
	 allPaths.add(new Path((0.4258241758241758 ),(0.8175895765472313 ) ,(0.5054945054945055 ) , (0.8631921824104235 )  ));
	 allPaths.add(new Path((0.5045787545787546 ),(0.8599348534201955 ) ,(0.5778388278388278 ) , (0.8143322475570033 )  ));


	 
}


public boolean isJointSelect(double x, double y) {
	// TODO Auto-generated method stub
	
	for(int i = 0; i < GameBoard.allJoints.size(); i++){//Add all the joints corresponding to this hex
		double dist = Math.sqrt((x - GameBoard.allJoints.get(i).xLoc)*(x - GameBoard.allJoints.get(i).xLoc)
				+(y - GameBoard.allJoints.get(i).yLoc)*(y - GameBoard.allJoints.get(i).yLoc));
		

		if(dist < .05){//theoretically should only happen once per location
			if(!UserInterface.selectedJoints.contains(i))
				UserInterface.selectedJoints.add(i);//Adds the UserInteface arraylist joint position 
			else{
						UserInterface.selectedJoints.remove(i);
			}
			return true;
		}
	
	
	
	
}
	
	return false;
	
	}


public boolean doAction(HashSet<Integer> selectedJoints) {
	
	for(int i = 0; i < allHexes.size(); i++){
		if(allHexes.get(i).joints.equals(selectedJoints)){//Need to add more conditions and actions
			System.out.println("robber placed at hex location");
			UserInterface.emptySelectedJoints();
			return true;//Can return after finding the right thing
			}
	}
	for(int i = 0; i < allPaths.size(); i++){
		if(allPaths.get(i).joints.equals(selectedJoints)){//Need to add more conditions and actions
			System.out.println("Path added to this location");
			UserInterface.emptySelectedJoints();
			return true;//Can return after finding the right thing
			}
	}
	
	if(selectedJoints.size()!=1)
		return false;
	
	
	
	for(int i = 0; i < allJoints.size(); i++){
		
		if(selectedJoints.remove(i)){//Need to add more conditions and actions
			System.out.println("House placed");
			UserInterface.emptySelectedJoints();
			return true;}
		
			}
	
	
	return false;
	}
	
	
	
}





