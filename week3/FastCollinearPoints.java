import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lines;

   public FastCollinearPoints(Point[] points){
    lines = new ArrayList<>();
    for(int p=0;p<points.length;p++){
        Point[] copy_Points = points.clone();
        Arrays.sort(copy_Points,points[p].slopeOrder());
        int q = 0;
        while(q<points.length){
            int count=0;
            int j=q+1;
            while(j<points.length && points[p].slopeTo(copy_Points[q])==points[p].slopeTo(copy_Points[j])){
                count++;
                j++;
            }
            if(count==0){
                q++;
            } else{
                q+=count;
            }
            if(count>1){
                addLineSegment(copy_Points,points, p, q-count, j-1);
            }
        }
    }
    }

   

   private void addLineSegment(Point[] copy_Points,Point[] points,int p,int start,int end){
    Point[] linePoints = new Point[end-start+2];
    linePoints[0] = points[p];
    for(int i=start;i<=end;i++){
        linePoints[i-start+1]= copy_Points[i];
    }
    Arrays.sort(linePoints);
    if(points[p].compareTo(linePoints[0])==0){
        lines.add(new LineSegment(linePoints[0],linePoints[linePoints.length-1]));
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