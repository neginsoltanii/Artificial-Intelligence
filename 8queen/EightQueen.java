import java.util.Random;

public class EightQueen {
    public int queenCount = 8;
    public int queens[] = new int[8];

    //Evaluate Function
    public int calculateCost(int[] q) {
        int pairsThreat = 0;
        for(int i = 0;i< queenCount;i++)
        {
            for(int j = i+1;j< queenCount;j++){
                if((q[i] == q[j]) || (Math.abs(i-j) == (Math.abs(q[i] - q[j]))))
                {
                    pairsThreat++;
                }
            }
        }
        return pairsThreat;
    }

    public void simulatedAnnealing(){
        double tempreture = 20;
        boolean solved = false;
        int[] currentStare = new int[8];
        assignTwoArrays(currentStare,queens);
        int[] nextState  = new int[8];
        int iterate = (int)(tempreture);
            for(int i = 0 ; i< iterate ; i++){
                int randomColumn = new Random().nextInt(8);
                System.out.println(randomColumn);
                int randomRow =  new Random().nextInt(8);
                System.out.println(randomRow);
                    for(int y = 0;y<queenCount;y++){
                        if(y == randomColumn){
                            nextState[y] = randomRow;
                        }
                        else{
                            nextState[y] = currentStare[y];
                        }
                    }
                System.out.println("Current state");
                printQueens(currentStare);
                System.out.println("Next state");
                printQueens(nextState);
                System.out.println("Cost of current state");
                int costOfcurrentState = calculateCost(currentStare);
                System.out.println(costOfcurrentState);
                System.out.println("Cost of next state");
                int costOfnextState = calculateCost(nextState);
                System.out.println(costOfnextState);
                double deltaE = costOfcurrentState - costOfnextState;
                printValues(deltaE,"deltaE");
                printValues(tempreture,"Temperature");
                double tavan = (deltaE / tempreture);

            double probability = Math.pow(2.7 , tavan);
            printValues(probability,"Probability");
            if(deltaE>0)
            {
                assignTwoArrays(currentStare,nextState);
            }
            else {
                double probabilityToMove = Math.random();
                if(probabilityToMove <= probability)
                    assignTwoArrays(currentStare,nextState);
            }

            tempreture--;

            if(calculateCost(currentStare) == 0)
            {
                solved = true;
                break;
            }

        }

        if(solved){
            System.out.println("solution found");
            printQueens(currentStare);
        }
        else
            System.out.println("solution not found ");
    }
    public void initQueens(){
        for(int i =0;i<queenCount ; i++)
        {
            queens[i] = new Random().nextInt(8);
        }
    }

    public void assignTwoArrays(int[] q1 , int[] q2)
    {
        for(int i =0;i< queenCount;i++)
            q1[i] = q2[i];
    }
    public void printQueens(int[] q){
        for(int i =0;i<q.length;i++)
            System.out.println("( " + i + " , " + q[i] + " )");
        System.out.println("//////////////////");
    }
    public void printValues(double p , String S)
    {
        System.out.println();
        System.out.println("----------------------------------");
        System.out.println(S + ":");
        System.out.println(p);
        System.out.println("----------------------------------");
        System.out.println();
    }


    public static void main(String[] args){

        EightQueen eq = new EightQueen();
        eq.initQueens();
        eq.printQueens(eq.queens);
        eq.simulatedAnnealing();
    }
}


