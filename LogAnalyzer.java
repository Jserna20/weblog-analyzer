/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    
    private String filename;
    

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
        
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    
    public int numberOfAccesses()
    {
        int total = 0;
        for(int hour = 0; hour < hourCounts.length; hour++)
        {
            total = total + hourCounts[hour];
        }
        return total;
    }
    
    public int FindBusiestHour()
    {
        int busy = 0;
        for(int hour = 0; hour < hourCounts.length; hour++)
        {
            if(hourCounts[busy] < hourCounts[hour])
            {
                busy = hour;
            }
        }
        return busy;
    }
    
    public int FindQuietestHour()
   {
        int quiet = 0;
        for(int hour = 0; hour < hourCounts.length; hour++)
        {
            if(hourCounts[quiet] > hourCounts[hour])
            {
                quiet = hour;
            }
        }
        return quiet;
    }
   
    public void PrintBusiestHour()
    {
        System.out.println("The busiest hour is: hour " + FindBusiestHour());
    }
    
    public void PrintQuiestHour()
    {
        System.out.println("The quietest hour is hour " + FindQuietestHour());
    }
        
    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
