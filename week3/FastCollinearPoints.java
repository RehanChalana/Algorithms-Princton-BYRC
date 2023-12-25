import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lines;

   public FastCollinearPoints(Point[] points){
    lines = new ArrayList<>();
    // iterating through the points array with p 
    for(int p=0;p<points.length-2;p++){
        // creating a copy of the points array
        Point[] copy_points = points.clone();
        // sorting the copy_points array from p+1  with the slope the points makes with p
        Arrays.sort(copy_points,p,points.length,points[p].slopeOrder());
        for(int q=p+1;q<points.length-1;q++){
            int j=q+1;
            int count=2;
            while(j<points.length && copy_points[p].slopeTo(copy_points[q])==copy_points[p].slopeTo(copy_points[j])){
                j++;
                count++;
            }
            if(count>3){
                addLineSegment(copy_points,p,q,j-1);
            }
        }
    }

   }

   private void addLineSegment(Point[] copy_Points,int p,int start,int end){
    Point[] linePoints = new Point[end-start+2];
    linePoints[0] = copy_Points[p];
    for(int i=start;i<=end;i++){
        linePoints[i-start+1]= copy_Points[i];
    }
    Arrays.sort(linePoints);
    lines.add(new LineSegment(linePoints[0],linePoints[linePoints.length-1]));

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