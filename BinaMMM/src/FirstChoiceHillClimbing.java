
import java.util.ArrayList;
import java.util.Random;

public class FirstChoiceHillClimbing implements Strategy{

    private Queen[] startState;
    // start state
    private Node start;
    private int nodesGenerated;

    public FirstChoiceHillClimbing() {
        start = new Node();
        startState = new Queen[Node.getSize()];
        startState();
        nodesGenerated=0;
    }

    public FirstChoiceHillClimbing(Queen[] s) {
        start = new Node();
        startState = new Queen[Node.getSize()];
        for (int i = 0; i < s.length; i++){
            startState[i] = new Queen(s[i].getRow(), s[i].getCol());
        }
        start.setState(startState);
        start.computeHeuristic();

        nodesGenerated = 0;
    }

    public void startState() {
        Random random = new Random();
        for (int i = 0; i < Node.getSize(); i++){
            startState[i] = new Queen(random.nextInt(Node.getSize()), i);
        }
        start.setState(startState);
        start.computeHeuristic();
    }

    public Node hillClimbing() {

        Node currentNode = start;

        while (true) {
            ArrayList<Node> successors = currentNode.generateNeighbours(currentNode);
            nodesGenerated += successors.size();

            boolean existBetter = false;

            for (int i = 0; i < successors.size(); i++) {
                if (successors.get(i).compareTo(currentNode) < 0) {
                    currentNode = successors.get(i);
                    existBetter = true;
                    break;
                }
            }

            if(!existBetter) {
                return currentNode;
            }

        }
    }

    public Node getStartNode(){
        return start;
    }

    public int getNodesGenerated(){
        return nodesGenerated;
    }
    
    
    
    
    public static void performSearch(Board initial,int choice) {
        //Init variables
        int queen = 0, row = 1, row_main = row, temp;
        boolean solution_found = true;

        initial.boardState[queen++] = row_main; //Put first queen in row 1
        while (queen < nQueens.n) {
            while (row <= nQueens.n) {
                initial.boardState[queen] = row; //Put queen in this row
                //If this queen doesn't conflict with any others
                if (initial.checkConflicts(queen) == 0) {
                    queen++; //Go to next queen
                    row = 1;
                    break;
                } else { //If this queen conflicts with another
                    initial.boardState[queen] = 0; //Reset queen
                    row++; //Try next row
                }
                //If we've experienced conflicts in all rows
                if (row > nQueens.n) {
                    queen--; //Go to previous queen
                    while (queen != 0) {
                        temp = initial.boardState[queen] + 1; //Try next row
                        while (temp <= nQueens.n) {
                            initial.boardState[queen] = temp;
                            //If this queen doesn't conflict with any others
                            if (initial.checkConflicts(queen) == 0) {
                                queen++; //Go to next queen
                                row = 0;
                                break;
                            } else { //If this queen conflicts with another
                                initial.boardState[queen] = 0; //Reset queen
                                row = ++temp; //Try next row
                            }
                        }
                        //row = 0 iff we found a move that doesnt cause conflict
                        if (row == 0) {
                            row++;
                            break;
                        } else {
                            queen--; //Backtrack to the previous queen
                        }
                    }
                }
            }
            
            
            //If we've not found a move that doesn't cause conflict
            if (row > nQueens.n) {
                row_main++;
                if (row_main > nQueens.n) { //Don't go out of bounds
                    break;
                }
                initial.boardState[queen] = row_main; //Put queen here
                row = 1;
                queen++; //Go to next queen
              
            }
            if(choice==1){
            System.out.println("");
            initial.printBoard();
            System.out.println("");
            }
            nQueens.FirstCH_configs_tested++;
            
            
        }
        //Check if solution was found or not

        for (int i = 0; i < nQueens.n; i++) {
            if (initial.checkConflicts(i) == 0) {
                solution_found = true;
            } 
            else {
                solution_found = false;
                break;
            }
        }
        if (solution_found) {
            System.out.println("Solution found.");
            System.out.println("Configurations tested: " + nQueens.FirstCH_configs_tested);
            initial.printBoard();
        }
        
        else {
            System.out.println("No solution found.");
        }
    }

}