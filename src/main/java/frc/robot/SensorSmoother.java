package frc.robot;

import java.util.ArrayList; // import the ArrayList class
import java.util.Collections;
import java.util.List;

import javax.lang.model.util.ElementScanner6;

/**This class is used when you want to average out the values being returned from a sensor.
 * It is used by adding sample data to the object and requesting Averages which looks at the
 * last sampleMS of time to calculate the average.   
 * 
 * @author David Heinz
 * 
 */
public class SensorSmoother {

    private int sampleMS = 100;
    private ArrayList<sample> sampleList = new ArrayList<sample>( );
 
    /** Class constructor not specifying milliseconds to use for the sample duration.
     */
    public SensorSmoother(){

    }

    /** Class constructor specifying number of milliseconds to use for the sample duration
     * @param SampleMS The number of MS to sample from 
     */
    public SensorSmoother(int SampleMS){
        this.sampleMS = SampleMS;
    }

    public void SetSampleMS(int ms){
        this.sampleMS = ms;

    }
    
    /** This method allows you to pass sample values into the SensorSmoother object.  
     * @param value Sample value to be added to the list of samples collected over time.
     * @return boolean
     */
    public boolean AddSample (double value){
        sample s = new sample(value);
        sampleList.add(s);

        return true;
    }

    
    /** This method returns the current sample size stored within the sampleMS duration.
     * @return int
     */
    public int GetSampleSize(){
        purgeExpired();
        return sampleList.size();
    }

    
    /** This functions calculates the average of the values collected in the last milliseconds.
     * @return double
     */
    public double GetAverage(){
        double sum = 0;

        purgeExpired();
        if (sampleList.size() == 0)
            return 0;
        removeOutliers();
        for (int x = 0; x < sampleList.size(); x++){
            sum = sum + sampleList.get(x).Value;
        }

        return sum / sampleList.size();
    }

    /** This functions allows you to pass in a sample value, then calculates the average of the values collected in the last milliseconds.
     * @param value Sample value to be added to the list of samples collected over time.
     * @return double
     */
    public double GetAverage(double value){
        AddSample(value);
        return GetAverage();
    }

     /**Prints the current list of values to standard out.
     * 
     */
    public void printList(){
        for (int x=0; x < sampleList.size(); x++)
            System.out.println(sampleList.get(x).Value);
    }


    /** This private method removes any sample values that have expired */
    private void purgeExpired() {
        while ((sampleList.size() > 0) && 
            (sampleList.get(0).TimeStamp <= (System.currentTimeMillis() - sampleMS))){
            sampleList.remove(0);
        }
    }
    
    /** This class is used to store values and a timestamp so that we can keep track of and remove old data. */
    private class sample {
        public double Value;
        public Long TimeStamp;
        
        public sample(double value){
            this.Value = value;
            this.TimeStamp = System.currentTimeMillis();
        }
    }

    /** This method calculates and removes outlyers from your data.  See the folling sites for details on methods used. 
       @see <a href="http://www.mathwords.com/o/outlier.htm">Outlier.htm</a> and
            <a href="https://stackoverflow.com/questions/18805178/how-to-detect-outliers-in-an-arraylist">Detecting Outliers</a>
        for background information where these routines came from.   Modified for our use.
    */
    private  void removeOutliers() {
        if (sampleList.size() < 2)
            return;
        List<Double> data1 = new ArrayList<Double>();
        List<Double> data2 = new ArrayList<Double>();
        List<Double> input = new ArrayList<Double>();

        for (int x=0; x < sampleList.size(); x++)
            input.add(sampleList.get(x).Value);
        
        Collections.sort(input);

        if (input.size() % 2 == 0) {
            data1 = input.subList(0, input.size() / 2);
            data2 = input.subList(input.size() / 2, input.size());
        } else {
            data1 = input.subList(0, input.size() / 2);
            data2 = input.subList(input.size() / 2 + 1, input.size());
        }
        double q1 = getMedian(data1);
        double q3 = getMedian(data2);
        double iqr = q3 - q1;
        double lowerFence = q1 - .5 * iqr;
        double upperFence = q3 + .5 * iqr;

        // Remove outlyers
        int i = 0;
        while (i < sampleList.size()) {
            if (sampleList.get(i).Value < lowerFence || sampleList.get(i).Value > upperFence){
                System.out.print("SensorSmoother -> Removing outlier:  ");
                System.out.println(sampleList.get(i).Value);
                sampleList.remove(i);
            }
            else
                i++;
        }
    }

    
    /** return the median of a list of values
     * @param data A List of Data values
     * @return double
     */
    private  double getMedian(List<Double> data) {
        if (data.size() % 2 == 0)
            return (data.get(data.size() / 2) + data.get(data.size() / 2 - 1)) / 2;
        else
            return data.get(data.size() / 2);
    }
}
