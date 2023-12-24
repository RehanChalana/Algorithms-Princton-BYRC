import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lines;

    public BruteCollinearPoints(Point[] points){
        if(points==null){
            throw new java.lang.IllegalArgumentException();
        }

        this.lines = new ArrayList<>();
        for(int p=0;p<points.length-3;p++){
            for(int q=p+1;q<points.length-2;q++){
                for(int r=q+1;r<points.length-1;r++){
                    for(int s=r+1;s<points.length;s++){
                        if(points[p]==null || points[q]==null || points[r]==null || points[s]==null){
                            throw new java.lang.IllegalArgumentException("can't have null values");
                        }
                        if(points[p].compareTo(points[q])==0 || points[p].compareTo(points[r])==0 || points[p].compareTo(points[s])==0){
                            throw new java.lang.IllegalArgumentException("can't have same points");
                        }
                        
                        if(points[p].slopeTo(points[q])==points[p].slopeTo(points[r]) && points[p].slopeTo(points[r])==points[p].slopeTo(points[s])){
                            Point[] pointsOnLine = {points[p],points[q],points[r],points[s]};
                            Arrays.sort(pointsOnLine);
                            lines.add(new LineSegment(pointsOnLine[0],pointsOnLine[3]));
                        }
                    }
                }
            }
        }
    }
 
    public int numberOfSegments(){
        return lines.size();
    }
     
    public LineSegment[] segments(){
        LineSegment[] lines_array = new LineSegment[lines.size()];
        for(int i=0;i<lines.size();i++){
            lines_array[i]=lines.get(i);
        }
        return lines_array;
    }
    
}
