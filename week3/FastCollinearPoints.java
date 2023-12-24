import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lines;

   public FastCollinearPoints(Point[] points){
    lines = new ArrayList<>();
    for(int p=0;p<points.length-2;p++){
        Point[] copy_points = points.clone();
        Arrays.sort(copy_points,p,points.length,points[p].slopeOrder());
        for(int q=p+1;q<points.length-1;q++){
            int j=q+1;
            while(points[p].slopeTo(points[q])==points[p].slopeTo(points[j])){
                j++;
            }
            if(j-p>=3){
                addLineSegment(copy_points,p,j);
            }
        }
    }

   }

   private void addLineSegment(Point[] copy_Points,int start,int end){
    Point[] linePoints = new Point[end-start];
    for(int i=start;i<=end;i++){
        linePoints[i-start]= copy_Points[i];
    }
    Arrays.sort(linePoints);
    lines.add(new LineSegment(linePoints[0],linePoints[linePoints.length-1]));

   }
   public int numberOfSegments(){

   }   
   public LineSegment[] segments(){

   }              
}