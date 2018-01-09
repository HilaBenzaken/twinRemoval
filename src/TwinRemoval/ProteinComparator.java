package TwinRemoval;

import main.Protein;

public interface ProteinComparator {

    public boolean compare(Protein p1, Protein p2);

    public double getRateOfComparison();

}
