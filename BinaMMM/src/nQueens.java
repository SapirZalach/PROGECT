
import java.util.*;

public final class nQueens {

    public static int n;
    public static int seed;
    public static int choice;
    public static int blind_configs_tested;
    public static int aStar_configs_tested;
    public static int FirstCH_configs_tested;
    Scanner scanner;

    public nQueens() {
        //Init variables
        getInput();
        blind_configs_tested = 0;
        aStar_configs_tested = 0;
        FirstCH_configs_tested=0;

        System.out.println("N: " + n + "\tSeed: " + seed + "\tChoice: " + choice + "\n");

        System.out.println("Blind Search");
        Board blind = new Board(n); //Creating new Board object
        blindSearch.performSearch(blind,choice);

        int[] board = new int[n]; //Array to represent seeded board
        Random rand = new Random(seed);
        //Filling array using Math.Random and seed value
        for (int i = 0; i < n; i++) {
            board[i] = rand.nextInt(n) + 1;
        }
        Board initial = new Board(board); //Creating new Board object
        PriorityQueue<Board> PQ; //PriorityQueue to store children of b
        PQ = initial.generateChildren();
        System.out.println("\nA* Search");
        System.out.println("Initial Board: ");
        AStar.performSearch(initial,choice);
        
        System.out.println("FirstChoiceHillClimbing Search");
        Board first=new Board(n);
		FirstChoiceHillClimbing.performSearch(first,choice);
		

    }

    private void generateNeighbours(Queen[] state) {
		// TODO Auto-generated method stub
		
	}

	/**
     * Method to get user input for problem size and seed.
     */
    private void getInput() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please enter the size of the problem (n >= 4): ");
            try {
                n = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("\nError: invalid input.");
            }
            if (n < 4) {
                System.out.println("Error: n must be greater than or equal to 4.");
            } else {
                break;
            }
        }
        while (true) {
            System.out.print("Please enter the seed: ");
            try {
                seed = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("\nError: invalid input.");
            }
            break;
        }
        while (true) {
        	System.out.println("Press 1 if you want the search route until the solution"+"\n"
                    +"Press 2 if you want the solution route");
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("\nError: invalid input.");
            }
            break;
        }
    }

    public static void main(String[] args) 
    {
        nQueens q = new nQueens();
    }
}