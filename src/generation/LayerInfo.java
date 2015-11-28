/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generation;

/**
 *
 * @author niko
 */
public class LayerInfo {
    private final double featureSize, factor;
    private final OpenSimplexNoise noise;
    
    public LayerInfo(long seed, double featureSize, double factor) {
        this.featureSize = featureSize;
        this.factor = factor;
        noise = new OpenSimplexNoise(seed);
    }
    
    public OpenSimplexNoise getNoise() {
        return noise;
    }
    
    public double getFeatureSize() {
        return featureSize;
    }
    
    public double getFactor() {
        return factor;
    }
    
    @Override
    public String toString() {
        return "noise: " + noise +
                "\nfeatureSize: " + featureSize +
                "\nfactor: " + factor;
    }
}
