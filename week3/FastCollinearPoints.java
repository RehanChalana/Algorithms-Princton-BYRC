import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    // finds all line segments containing 4 or more points
    private ArrayList<LineSegment> lines;

    public FastCollinearPoints(Point[] points){
    this.lines = new ArrayList<>();
    for(int p=0;p<points.length-2;p++){
        Arrays.sort(points,p+1,points.length,points[p].slopeOrder());
        int j=0;
        while(p+2<points.length-2 && points[p].slopeTo(points[p+1])==points[p].slopeTo(points[p+2])){
            j++;
        }
        if(j-p>=3){
            // Point q = points[p+1];
            // Point r = points[p+2];
            Point[] line_points = new Point[j];
            for(int t=p;t<=p+j;t++){
                line_points[t-p]=points[t];
            }
            Arrays.sort(line_points);
            lines.add(new LineSegment(line_points[0], line_points[line_points.length-1]));
        }
    }
   }
    
    // the number of line segments
   public int numberOfSegments(){
   }
   
    // the line segments
   public LineSegment[] segments(){

   }               
}