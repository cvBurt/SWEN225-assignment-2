import java.util.*;

public class Cluedo {
	private List<Player> players;
	private List<Card> characters;
	private List<Card> weapons;
	private List<Card> rooms;
	private Card murderWeapon;
	private Card murderer;
	private Card murderRoom;
	private Board board;
	private Player currentPlayer;
	private String winningClaim;
	private boolean wonFromAccu;
	
	/**
	 * constructor used for testing purposes
	 * @param startingPlayer
	 * @param board
	 */
	public Cluedo (Player startingPlayer, Board board) {
		this.currentPlayer = startingPlayer;
		this.board = board;
	}
	
	/**
	 * normal constructor
	 */
	public Cluedo (){
		board = new Board();
		board.draw();
	}
	
	/**
	 * Sets up the start of the game by distinguishing the amount of players
	 * randomly generating a solution
	 * and then distributing the cards amongst the players
	 */
	public void setup(Scanner sc) {
		System.out.println("How many players are palying?\nEnter a number between 3-6:");
		int noPlayers;
		while(true) {
			if(sc.hasNext()) {
				noPlayers = sc.nextInt();
				if(noPlayers >= 3 && noPlayers <= 6) break;
				else {
					System.out.println("Please enter a number between 3-6:");
				}
			}
		}
		for(int i=0; i<noPlayers; i++) {
			Card character = characters.get(i);
			Cell startPos = board.getCell(character.getStartRow(), character.getStartCol());
			Player toAdd = new Player(character.getId(), startPos, character.getInitials());
			startPos.setPlayer(toAdd);
			players.add(toAdd);
		}
		setSolution();
		List<Card> allCards = new ArrayList<Card>();
		allCards.addAll(characters);
		allCards.addAll(weapons);
		allCards.addAll(rooms);
		
		allCards.remove(murderer);
		allCards.remove(murderRoom);
		allCards.remove(murderWeapon);
		
		while(!allCards.isEmpty()) {
			for(Player player : players) {
				Collections.shuffle(allCards);
				player.addCardToHand(allCards.get(0));
				allCards.remove(0);
			}
		}
		
		System.out.println("Take turns noting down the cards that have been delt to each player. (keep them secret to you!!");
		for(Player player : players) {
			System.out.println(player.getName() + " when ready enter any lettered key to display your hand");
			if(sc.next() != null) {
				System.out.println(player.showHand());
				System.out.println("(When ready enter any letered key to hide your hand again)");
				if(sc.next() != null) clearConsole();
			}
		}
		tick(sc);
	}
	
	/**
	 * main loop for running game, each loop turn (a tick)
	 * @param sc
	 */
	public void tick(Scanner sc) {
		int playerTurn = 0;
		while(true) {
			currentPlayer = players.get(playerTurn);
			if(!currentPlayer.getStatus()) {
				System.out.println("It is " + currentPlayer.getName() + "'s turn, enter any lettered key to continue");
				if(sc.next() != null) {
					board.draw();
					int roll = rollDice();
					System.out.println("You rolled " + roll + "! whats your move?");
					if(!currentPlayer.getLocation().getRoom().equals("Hallway")) {
						exitRoom(sc);
						System.out.println("You rolled " + roll + "! whats your move?");
					}
					validateMove(sc, roll);
					if(!currentPlayer.getLocation().getRoom().equals("Hallway")) {
						System.out.println("Enter 'y' to make an suggestion or enter any other letter to skip");
						if(sc.next().equalsIgnoreCase("y")) {
							if(checkSuggestion(sc)) {
								break;
							}
						}
						System.out.println("Enter 'y' to make an accusation or enter any other letter to skip");
						if(sc.next().equalsIgnoreCase("y")) {
							if(checkAccusation(sc)) {
								break;
							}
							enterRoom("None",currentPlayer);
							board.draw();
							System.out.println("=============================================================");
							System.out.println(currentPlayer.getName()+ " is now out of the game due to an incorrect accusation");
							currentPlayer.kill();
						}
					}
				}
			}
			playerTurn++;
			if(playerTurn == players.size()) playerTurn = 0;
		}
		if(wonFromAccu) System.out.println(currentPlayer.getName() + "won the game with the accusation: " + winningClaim);
		else System.out.println(currentPlayer.getName() + "won the game with the suggestion: " + winningClaim);
	}
	
	/**
	 * randomly selects a murderer, murderWeapon and murderRoom
	 * by shuffling the appropriate array list and getting the first element
	 * and removes them from the lists
	 */
	public void setSolution() {
		Collections.shuffle(characters);
		murderer = characters.get(0);
		
		Collections.shuffle(weapons);
		murderWeapon = weapons.get(0);
		
		Collections.shuffle(rooms);
		murderRoom = rooms.get(0);
	}
	
	/**
	 * generates the character array list which will be used to distribute cards later
	 */
	public void addCharacters() {
		characters = new ArrayList<Card>();
		characters.add(new Card("character","Miss Scarlet",7,24,new char[]{' ','M',' ',' ','S'}));
		characters.add(new Card("character","Colonel Mustard",0,17,new char[]{' ','C',' ',' ','M'}));
		characters.add(new Card("character","Mrs. White",9,0,new char[]{' ','M',' ',' ','W'}));
		characters.add(new Card("character","Mr. Green",14,0,new char[]{' ','M',' ',' ','G'}));
		characters.add(new Card("character","Mrs. Peacock",23,6,new char[]{' ','M',' ',' ','P'}));
		characters.add(new Card("character","Prof. Plum",23,19,new char[]{' ','P',' ',' ','P'}));
	}
	
	/**
	 * generates the weapons array list which will be used to distribute cards later
	 */
	public void addWeapons() {
		weapons = new ArrayList<Card>();
		weapons.add(new Card("weapon","Candelstick"));
		weapons.add(new Card("weapon","Dagger"));
		weapons.add(new Card("weapon","Lead Pipe"));
		weapons.add(new Card("weapon","Revolver"));
		weapons.add(new Card("weapon","Rope"));
		weapons.add(new Card("weapon","Spanner"));
	}
	
	/**
	 * generates the rooms array list which will be used to distribute cards later
	 */
	public void addRooms() {
		rooms = new ArrayList<Card>();
		rooms.add(new Card("room","Kitchen"));
		rooms.add(new Card("room","Ball Room"));
		rooms.add(new Card("room","Conservatory"));
		rooms.add(new Card("room","Dining Room"));
		rooms.add(new Card("room","Billiard Room"));
		rooms.add(new Card("room","Library"));
		rooms.add(new Card("room","Lounge"));
		rooms.add(new Card("room","Hall"));
		rooms.add(new Card("room","Study"));
	}
	
	/**
	 * generates to random integers between 1 and 6 and adds them together
	 * @return
	 */
	public int rollDice() {
		Random rand = new Random();
		return (rand.nextInt(6)+1) + (rand.nextInt(6)+1);
	}
	
	/**
	 * displays options for exiting room and moves player to selected exit 
	 * @param sc
	 */
	public void exitRoom(Scanner sc) {
		System.out.println(currentPlayer.getLocation().getRoom());
		Cell[] exits = board.roomDoors.get(currentPlayer.getLocation().getRoom());
		int numExits = exits.length;
		System.out.println("Select an exit from 0 - " + (numExits-1));
		for(int i=0; i<numExits; i++) {
			if(!exits[i].hasPlayer()) {
				exits[i].setExit(new char[] {' ',Character.forDigit(i, 10), ' ',' ',' ' });
			}
		}
		board.draw();
		int input;
		while(true) {
			input = sc.nextInt();
			if(input > -1 && input < numExits) {
				exits[input].removePlayer();
				currentPlayer.getLocation().removePlayer();
				currentPlayer.setLocation(exits[input]);
				exits[input].setPlayer(currentPlayer);
				break;
			}
		}
		for(int i=0; i<numExits; i++) {
			exits[i].removeExit();
		}
		board.draw();
	}
	
	/**
	 * makes the given player enter the room putting them in the first available display
	 * spot for that room
	 * @param room
	 * @param playerToEnter
	 */
	public void enterRoom(String room, Player playerToEnter) {
		Cell[] store = board.roomStore.get(room);
		for(int i=0; i<store.length; i++) {
			if(!store[i].hasPlayer()) {
				store[i].setPlayer(playerToEnter);
				playerToEnter.getLocation().removePlayer();
				playerToEnter.setLocation(store[i]);
			}
		}
		board.draw();
	}
	
	/**
	 * makes sure that the given move is a valid one, ie follows correct format
	 * @param sc
	 * @param roll
	 */
	public void validateMove(Scanner sc, int roll) {
		String[] move;
		List<String> validMoves = new ArrayList<String>(Arrays.asList("n","e","s","w"));
		while(true) {
			if(sc.nextLine() != null) {
				boolean validMove = true;
				move = sc.nextLine().split(",");
				for(int i=0; i <move.length; i++) {
					String character = move[i];
					if(!validMoves.contains(character.toLowerCase())) {
						validMove = false;
					}
				}
				if(validMove) {
					if(applyMove(move,roll)) {
						board.draw();
						break;
					}
				}
				else {
					for(int i=0; i <move.length; i++) {
						System.out.print(move[i]);
					}
					System.out.print("\n");
					System.out.println("Please make sure you type only 'n','e','s' or 'w' and that each letter is sepearted by commas");
				}
			}
		}
	}
	
	/**
	 * applies the given move to the current character, checking along the way if it is valid
	 * (doesn't go through walls, over other people, visit the same square twice in one move)
	 * return player to starting cell of move if move is invalid
	 * @param move
	 * @param roll
	 * @return with move was successful or not
	 */
	public boolean applyMove(String[] move, int roll) {
		Cell proposedLoc = null;
		Cell currentLoc = currentPlayer.getLocation();
		Cell originLoc = currentPlayer.getLocation();
		String prevRoundRoom = currentPlayer.getPrevRoundRoom();
		Set<Cell> visited = new HashSet<Cell>();
		for(int i=0; i<move.length; i++) {
			visited.add(currentLoc);
			if(move[i].equalsIgnoreCase("n")) {
				if(currentLoc.getNorthNeighbour()) {
					proposedLoc = board.getCell(currentLoc.getX(),currentLoc.getY()-1);
					if(!prevRoundRoom.equals("Hallway") && prevRoundRoom.equals(proposedLoc.getRoom())){
						System.out.println("You cannot re-enter a room in the same turn\n"
								+ "(Please enter a new move)");
						return false;
					}
					if(visited.contains(proposedLoc)) {
						System.out.println("Your move " + Arrays.toString(move) + " loops back on itself\n"
								+ "(Please enter a new move)");
						return false;
					}
					if(proposedLoc.hasPlayer()) {
						System.out.println("Your move tries to enter a cell with another player in it\n"
								+ "(Please enter a new move)");
						return false;
					}
					if(proposedLoc.getSouthNeighbour()) {
						currentLoc = proposedLoc;
						if(!currentLoc.getRoom().equals("Hallway")) {
							enterRoom(currentLoc.getRoom(), currentPlayer);
							return true;
						}
					}
					else {
						System.out.println("Your move crashes into a wall\n"
								+ "(Please enter a new move)");
						return false;
					}
					
				}
			}
			else if(move[i].equalsIgnoreCase("s")) {
				if(currentLoc.getSouthNeighbour()) {
					proposedLoc = board.getCell(currentLoc.getX(),currentLoc.getY()+1);
					if(!prevRoundRoom.equals("Hallway") && prevRoundRoom.equals(proposedLoc.getRoom())){
						System.out.println("You cannot re-enter a room in the same turn\n"
								+ "(Please enter a new move)");
						return false;
					}
					if(visited.contains(proposedLoc)) {
						System.out.println("Your move " + Arrays.toString(move) + " loops back on itself\n"
								+ "(Please enter a new move)");
						return false;
					}
					if(proposedLoc.hasPlayer()) {
						System.out.println("Your move tries to enter a cell with another player in it\n"
								+ "(Please enter a new move)");
						return false;
					}
					if(proposedLoc.getNorthNeighbour()) {
						currentLoc = proposedLoc;
						if(!currentLoc.getRoom().equals("Hallway")) {
							enterRoom(currentLoc.getRoom(), currentPlayer);
							return true;
						}
					}
					else {
						System.out.println("Your move crashes into a wall\n"
								+ "(Please enter a new move)");
						return false;
					}
					
				}
			}
			else if(move[i].equalsIgnoreCase("w")) {
				if(currentLoc.getWestNeighbour()) {
					proposedLoc = board.getCell(currentLoc.getX()-1,currentLoc.getY());
					if(!prevRoundRoom.equals("Hallway") && prevRoundRoom.equals(proposedLoc.getRoom())){
						System.out.println("You cannot re-enter a room in the same turn\n"
								+ "(Please enter a new move)");
						return false;
					}
					if(visited.contains(proposedLoc)) {
						System.out.println("Your move " + Arrays.toString(move) + " loops back on itself\n"
								+ "(Please enter a new move)");
						return false;
					}
					if(proposedLoc.hasPlayer()) {
						System.out.println("Your move tries to enter a cell with another player in it\n"
								+ "(Please enter a new move)");
						return false;
					}
					if(proposedLoc.getEastNeighbour()) {
						currentLoc = proposedLoc;
						if(!currentLoc.getRoom().equals("Hallway")) {
							enterRoom(currentLoc.getRoom(), currentPlayer);
							return true;
						}
					}
					else {
						System.out.println("Your move crashes into a wall\n"
								+ "(Please enter a new move)");
						return false;
					}
					
				}
			}
			else if(move[i].equalsIgnoreCase("e")) {
				if(currentLoc.getEastNeighbour()) {
					proposedLoc = board.getCell(currentLoc.getX()+1,currentLoc.getY());
					if(!prevRoundRoom.equals("Hallway") && prevRoundRoom.equals(proposedLoc.getRoom())){
						System.out.println("You cannot re-enter a room in the same turn\n"
								+ "(Please enter a new move)");
						return false;
					}
					if(visited.contains(proposedLoc)) {
						System.out.println("Your move " + Arrays.toString(move) + " loops back on itself\n"
								+ "(Please enter a new move)");
						return false;
					}
					if(proposedLoc.hasPlayer()) {
						System.out.println("Your move tries to enter a cell with another player in it\n"
								+ "(Please enter a new move)");
						return false;
					}
					if(proposedLoc.getWestNeighbour()) {
						currentLoc = proposedLoc;
						if(!currentLoc.getRoom().equals("Hallway")) {
							enterRoom(currentLoc.getRoom(),currentPlayer);
							return true;
						}
					}
					else {
						System.out.println("Your move crashes into a wall\n"
								+ "(Please enter a new move)");
						return false;
					}
					
				}
			}
		}
		if(move.length == roll) {
			currentPlayer.setLocation(currentLoc);
			currentLoc.setPlayer(currentPlayer);
			originLoc.removePlayer();
			currentPlayer.setPrevRoundRoom(currentLoc.getRoom());
			return true;
		}
		System.out.println("You did not move right amount of spaces\n"
				+ "Please enter a new move:");
		return false;
	}
	
	/**
	 * checks whether the given suggestion is correct or not, making sure that the suggestion is done in
	 * the correct format at the same time
	 * @param sc
	 * @return
	 */
	public boolean checkSuggestion(Scanner sc) {
		System.out.println("(Please make the suggestion in the format \"Person,Weapon,Room\")");
		while(true) {
			if(sc.nextLine() != null) {
				String[] suggestion = sc.nextLine().split(",");
				for(Player player : this.players) {
					if(player.getName().equalsIgnoreCase(suggestion[0])) {
						for(Card room : rooms) {
							if(room.getId().equalsIgnoreCase(suggestion[2]))
							enterRoom(room.getId(),player);
						}
					}
				}
				if(isValidSuggestion(suggestion)) {
					if(murderer.getId().equalsIgnoreCase(suggestion[0])) {
						if(murderWeapon.getId().equalsIgnoreCase(suggestion[1])) {
							if(murderRoom.getId().equalsIgnoreCase(suggestion[2])) {
								winningClaim = Arrays.toString(suggestion);
								wonFromAccu = false;
								return true;
							}
							else {
								refuteRoom(suggestion[2], sc);
								break;
							}
						}
						else {
							refuteWeapon(suggestion[1], sc);
							break;
						}
					}
					else {
						refutePerson(suggestion[0], sc);
						break;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * checks whether the given accusation is correct or not, making sure that the accusation is done in
	 * the correct format at the same time
	 * @param sc
	 * @return if accusation is true or false
	 */
	public boolean checkAccusation(Scanner sc) {
		System.out.println("(Please make the accusation in the format \"Person,Weapon,Room\")");
		while(true) {
			if(sc.nextLine() != null) {
				String[] accusation = sc.nextLine().split(",");
				if(isValidAccusation(accusation)) {
					if(murderer.getId().equalsIgnoreCase(accusation[0])) {
						if(murderWeapon.getId().equalsIgnoreCase(accusation[1])) {
							if(murderRoom.getId().equalsIgnoreCase(accusation[2])) {
								winningClaim = Arrays.toString(accusation);
								wonFromAccu = true;
								return true;
							}
						}
					}
					break;
				}
			}
		}
		return false;
	}
	
	public void checkForHelp(Scanner sc) {
		if(sc.next() == "/4") {
			System.out.print("Every character, weapon and room is represented by a card in the game. Before the game starts, one\n" + 
					"character, one weapon, and one room card are selected at random by the program. This selection represents\n" + 
					"the murder circumstances, i.e., the solution that players need to figure out during game play.\n" + 
					"The remaining weapon, room and character cards are then randomly distributed to the players.\n" + 
					"Some players may end up with more cards than others. At the start of the game, each player is asked\n" +
					"to look at their hand, while the other players look away. As the only other time a player's cards are\n"+
					"when a suggestion is made, each player should take note of what they have."+
					"\n" + 
					"Players then take it in turns to roll from 2-12 and type the direction the player wants to move\n" + 
					"in, equal to the total rolled. Diagonal movement is not allowed and no space may be used\n" + 
					"twice during one turn. When a player enters a room, they do not need to use any remaining moves\n" + 
					"they have left. They may then hypothesise about the murder circumstances by making a suggestion\n" + 
					"which comprises the room they are in, a character and a weapon. This is typed in the fashion of (character),(weapon),(room).\n" + 
					"If the character and weapon named in the suggestion are not in that room yet, they are now moved into the room.\n" + 
					"When a suggestion is made, the program checks, in a clockwise fashion, starting from the current player,\n" + 
					"for players who can refute the suggestion. A suggestion is refuted by producing a card that matches one of\n" + 
					"the named murder circumstances (as such a card cannot be in the solution envelope). A refutation\n" + 
					"has all the other players look away from the screen while the player shows the card which matches. If a player has\n" + 
					"multiple refutation cards, it is their choice which one they pick. If no player can produce a refutation, the named\n" + 
					"murder circumstances are a potential solution candidate that may or may not be used to make an accusation\n" + 
					"later on (by any player).\n" + 
					"\n" + 
					"An accusation comprises a character, a weapon, and a room (which can be any room, not just the\n" + 
					"one the player making the accusation may be in). It is typed in the exact same way as a suggestion.\n" + 
					"If the accusation made by a player exactly matches the actual murder circumstances (the program checks\n" + 
					"if the accusation and solution match) the player wins, otherwise the player is excluded from making\n" + 
					"further suggestions or accusations. This means the player will continue to refute suggestions by others\n" +
					"but cannot win the game anymore.\n");
		}
	}
	
	/**
	 * finds which player can refute the suggested person
	 * @param suggestedPerson
	 */
	public void refutePerson(String suggestedPerson, Scanner sc) {
		Player canRefute = null;
		for(Player player : players) {
			for(Card card : player.getHand()) {
				if(card.getId().equalsIgnoreCase(suggestedPerson)){
					canRefute = player;
					break;
				}
			}
		}
		System.out.println(canRefute.getName() + " can refute, type any key to see hand and let " + currentPlayer.getName() +
		" know what card you can refute secretly");
		while(true) {
			if(sc.next() != null) {
				System.out.println(canRefute.showHand());
				System.out.println("(press any key to hide your hand and start the next turn)");
				if(sc.next() != null) clearConsole();
				break;
			}
		}
		board.draw();
	}
	
	/**
	 * finds which player can refute the suggested Weapon
	 * @param suggestedPerson
	 */
	public void refuteWeapon(String suggestedWeapon, Scanner sc) {
		Player canRefute = null;
		for(Player player : players) {
			if(player != currentPlayer) {
				for(Card card : player.getHand()) {
					if(card.getId().equalsIgnoreCase(suggestedWeapon)){
						canRefute = player;
						break;
					}
				}
			}
		}
		System.out.println(canRefute.getName() + " can refute, type 'y' to see hand and let " + currentPlayer.getName() +
		" know what card you can refute secretly");
		while(true) {
			if(sc.next() != null) {
				System.out.println(canRefute.showHand());
				System.out.println("(press any key to hide your hand and start the next turn)");
				if(sc.next() != null) clearConsole();
				break;
			}
		}
		board.draw();
	}
	
	/**
	 * finds which player can refute the suggested Room
	 * @param suggestedPerson
	 */
	public void refuteRoom(String suggestedRoom, Scanner sc) {
		Player canRefute = null;
		for(Player player : players) {
			if(player != currentPlayer) {
				for(Card card : player.getHand()) {
					if(card.getId().equalsIgnoreCase(suggestedRoom)){
						canRefute = player;
						break;
					}
				}
			}
		}
		System.out.println(canRefute.getName() + " can refute, type any key to see hand and let " + currentPlayer.getName() +
		" know what card you can refute secretly");
		while(true) {
			if(sc.next() != null) {
				System.out.println(canRefute.showHand());
				System.out.println("(press any key to hide your hand and start the next turn)");
				if(sc.next() != null) clearConsole();
				break;
			}
		}
		board.draw();
	}
	
	/**
	 * checks that the provided suggestion is valid, each card suggested exits and that the room a person
	 * suggest is the room that they are in
	 * @param suggestion
	 * @return
	 */
	public boolean isValidSuggestion(String[] suggestion) {
		boolean validCharacter = false;
		boolean validWeapon = false;
		boolean validRoom = false;
		for(Card character: characters) {
			if(character.getId().equalsIgnoreCase(suggestion[0])) validCharacter = true;
		}
		if(!validCharacter) {
			System.out.println("The person you have suggested \"" + suggestion[0] +"\" does not exist");
			return false;
		}
		
		for(Card weapon: weapons) {
			if(weapon.getId().equalsIgnoreCase(suggestion[1])) validWeapon = true;
		}
		if(!validWeapon) {
			System.out.println("The weapon you have suggested \"" + suggestion[1] +"\" does not exist");
			return false;
		}
		
		for(Card room: rooms) {
			if(room.getId().equalsIgnoreCase(suggestion[2])) validRoom = true;
		}
		if(!validRoom) {
			System.out.println("The room you have suggested \"" + suggestion[2] +"\" does not exist");
			return false;
		}
		
		if(!suggestion[2].equalsIgnoreCase(currentPlayer.getLocation().getRoom())) {
			System.out.println("The room you have suggested \"" + suggestion[2] +"\" is not the room that you are currently in\n"
					+ "you are in " + currentPlayer.getLocation().getRoom());
			return false;
		}
		
		return true;
	}
	
	/**
	 * checks that the provided accusation is valid, each card suggested exits.
	 * @param suggestion
	 * @return
	 */
	public boolean isValidAccusation(String[] suggestion) {
		boolean validCharacter = false;
		boolean validWeapon = false;
		boolean validRoom = false;
		for(Card character: characters) {
			if(character.getId().equalsIgnoreCase(suggestion[0])) validCharacter = true;
		}
		if(!validCharacter) {
			System.out.println("The person you have suggested \"" + suggestion[0] +"\" does not exist");
			return false;
		}
		
		for(Card weapon: weapons) {
			if(weapon.getId().equalsIgnoreCase(suggestion[1])) validWeapon = true;
		}
		if(!validWeapon) {
			System.out.println("The weapon you have suggested \"" + suggestion[1] +"\" does not exist");
			return false;
		}
		
		for(Card room: rooms) {
			if(room.getId().equalsIgnoreCase(suggestion[2])) validRoom = true;
		}
		if(!validRoom) {
			System.out.println("The room you have suggested \"" + suggestion[2] +"\" does not exist");
			return false;
		}
		
		return true;
	}
	
	/**
	 * clears console
	 */
	public void clearConsole() {
		for(int i=0; i<90;i++) {
			if(i == 50) {
				System.out.println("---------------------------Stop Cheating, Scum---------------------------");
			}
			System.out.print("\n");
		}
	}

	/**
	 * returns all characters for testing purposes
	 * @return
	 */
	public List<Card> getCharacters() {
		return characters;
	}

	/**
	 * returns all characters for testing purposes
	 * @return
	 */
	public List<Card> getWeapons() {
		return weapons;
	}

	/**
	 * returns all characters for testing purposes
	 * @return
	 */
	public List<Card> getRooms() {
		return rooms;
	}
	
	public static void main(String[] args) {
		new Cluedo();
	}
}