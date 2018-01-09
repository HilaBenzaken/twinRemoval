package TwinRemoval;

import main.Protein;

public class ProteinComperatprByMonomerDirections implements ProteinComparator {
    private double similarityRateCFF;
    private double similarityRateBetweenTwoProteins;

    public ProteinComperatprByMonomerDirections(double cff){
        this.similarityRateCFF=cff;
    }

    public boolean compare(Protein p1, Protein p2){
        int totalEqualMonomers=0;
        for (int i=0;i<p1.size();i++){
            if(p1.get(i).getRelativeDirection()==p2.get(i).getRelativeDirection())
                totalEqualMonomers++;
        }
        this.similarityRateBetweenTwoProteins=(double)totalEqualMonomers/p1.sequence.size();
        return this.similarityRateBetweenTwoProteins>=this.similarityRateCFF;

    }
    public double getRateOfComparison(){
        return this.similarityRateBetweenTwoProteins;
    }
}
