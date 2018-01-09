package TwinRemoval;
import java.util.Random;

import main.MonomerDirection;
import main.Population;
import main.Protein;

public class TwinRemover {

    private int rounds;
    private int removeEvery;
    private boolean [] deletedProtein;
    private int populationSize;
    private ProteinComparator comparator;
    private PopulationSimilarityRateMonitor monitor;

    public TwinRemover(int rounds, int populationSize, ProteinComparator comparator){

        this.deletedProtein = new boolean[populationSize];
        this.rounds = rounds;
        this.populationSize=populationSize;
        this.comparator=comparator;
        this.removeEvery=5;              //check
        this.monitor = new PopulationSimilarityRateMonitor(populationSize,10);
    }

    public Population RemoveTwins(Population population){

        this.resetDeletedProtein();
        for(int i = 0; i<populationSize; i++){
            if(!deletedProtein[i]){
                for(int j = i+1; j<populationSize; j++){
                    if(this.comparator.compare(population.get(i),population.get(j))){
                        if(population.get(i).getFitness() > population.get(j).getFitness()){
                            deletedProtein[j] = true;
                        }
                        else {
                            deletedProtein[i] = true;
                        }
                    }
                    monitor.addSimilarity(comparator.getRateOfComparison());
                }

            }
        }
        monitor.monitorSimilarity();
        //change deleted elements with randoms
        for(int i = 0; i< populationSize; i++){
            if(deletedProtein[i]){
                population.set(i,CreateNewProtein(population.get(i)));
            }
        }
        return population;
    }

    private void resetDeletedProtein(){
        for (int i=0;i<this.deletedProtein.length;i++){
            this.deletedProtein[i]=false;
        }
    }


    private Protein CreateNewProtein(Protein deletedProtein){
//        deletedProtein.reset();
        for(int i=0;i<deletedProtein.sequence.size();i++){
            if(i==0){
                deletedProtein.get(i).setRelativeDirection(MonomerDirection.FIRST);
            }
            else{
                deletedProtein.get(i).setRelativeDirection(MonomerDirection.getRandomDirection(deletedProtein.dimensions,new Random()));
            }

        }
    //    System.out.print(deletedProtein.toString());
        return deletedProtein;

    }
}
