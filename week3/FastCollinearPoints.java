import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lines;

   public FastCollinearPoints(Point[] points){
    if(points==null){
        throw new java.lang.IllegalArgumentException("points array can't be null");
    }

    lines = new ArrayList<>();
    // iterating through the points array with p
    checkForNull(points);
    for(int p=0;p<points.length;p++){
        // cloning the points array so we don't modify the original array
        Point[] copy_Points = points.clone();
        // sorting the copy_points with the slope they make with p
        Arrays.sort(copy_Points,points[p].slopeOrder());
        // checking adjacent points 
        int q = 1;
        while(q<points.length){
            // if(copy_Points[q]==null||points[p].compareTo(copy_Points[q])==0){
            //     throw new java.lang.IllegalArgumentException();
            // }
            int count=0;
            int j=q+1;
            // keep increasing j and count if p slope q == p slope
            while(j<points.length){
                // if(copy_Points[j]==null || points[p].compareTo(copy_Points[j])==0 || copy_Points[q].compareTo(copy_Points[j])==0){
                //     throw new java.lang.IllegalArgumentException();
                // }
                if(points[p].slopeTo(copy_Points[q])!=points[p].slopeTo(copy_Points[j])){
                    break;
                }
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

    private void checkForNull(Point[] point_array){
        for(int i=0;i<point_array.length;i++){
            if(point_array[i]==null){
                throw new java.lang.IllegalArgumentException();
            }
            for(int j=i+1;j<point_array.length;j++){
                if(point_array[j]==null || point_array[i].compareTo(point_array[j])==0){
                    throw new java.lang.IllegalArgumentException();
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

 

    // Arrays.sort(linePoints);
    Point min = linePoints[0];
    Point max = linePoints[0];
    for(Point i:linePoints){
        if(i.compareTo(max)>0){
            max=i;
        }
        if(i.compareTo(min)<0){
            min=i;
        }
    }
    if(points[p].compareTo(min)==0){
        lines.add(new LineSegment(min,max));
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