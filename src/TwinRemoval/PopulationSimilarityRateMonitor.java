package TwinRemoval;

import gui.GrapgView;
import org.jfree.ui.RefineryUtilities;

public class PopulationSimilarityRateMonitor {

    private int[] similarityRateArr; // accumulates the num of pairs that have a specific similarity rate
   //redundent?
    private double[] similarityRateInPopulationArr; //eventually holds the rate of pairs that have a spesific similarity rate out the total num of pairs in population (n choose 2)
    private int populationSize;
    private int buckets;
    private GrapgView graph;

    /*
     * @param buckets
     *            number of buckets for similarity rates, default is 10
     */
    public PopulationSimilarityRateMonitor(int populationSize) {
        this(populationSize, 10);
    }

    /*
     * @param buckets
     *            number of buckets for similarity rates, default is 10
     */
    public PopulationSimilarityRateMonitor(int populationSize, int buckets){

        similarityRateArr = new int[buckets];
        similarityRateInPopulationArr = new double[buckets]; //?

        this.buckets = buckets;
        this.populationSize = populationSize;

        initGraph();
    }

    public void addSimilarity(double similarity){ //?
        if(similarity==1){
            similarityRateArr[buckets-1] ++;
        }
        else{
            similarityRateArr[(int)(similarity*buckets)] ++;
        }
    }

    public void monitorSimilarity(){
        for(int i = 0; i<buckets; i++){
            similarityRateInPopulationArr[i] = similarityRateArr[i]/populationSize;
        }
        System.out.println("avrage similarity rate of a pair of proteins: " + calculateAvragesimilarity()); //run num or somemore details
        updateGraph();
        resetSimilarityArrays();
    }

    private double calculateAvragesimilarity() {
        int sum = 0;
        int numOfPairs = 0;
        for(int i = 0; i<buckets; i++){
            sum += similarityRateArr[i]*i;
            numOfPairs += similarityRateArr[i];
        }
        return ((double)sum / (numOfPairs)) / buckets; //review the calculation
    }

    /*
     * analyzes similarity rate in current population,
     * compares n^2 proteins couples
     * for use independently from twin remover
     */
    /*
    public double monitorSimilatiry(Population population){

        for(int i = 0; i<populationSize; i++)
        {

        }
        return 0;
    }
    */





    private void resetSimilarityArrays(){
        for(int i = 0; i<buckets; i++){
            similarityRateArr[i] = 0;
            similarityRateInPopulationArr[i] = 0;
        }
    }


    /**
     * creates a new graph and add it to the system
     */

    private void initGraph() {
        graph = new GrapgView("similarity rate in population", "similarity rate","rate of couples in population" );
        graph.pack();
        RefineryUtilities.centerFrameOnScreen(graph);
        graph.setVisible(true);
        for(int i = 0; i<buckets; i++){
            graph.getDataSet().addValue(i, "", Integer.toString(i)); //??
        }


    }


    private void updateGraph() {
        for(int i = 0; i<buckets; i++){
            graph.getDataSet().setValue(similarityRateInPopulationArr[i], "", Integer.toString(i)); //??
        }
    }

}
