import java.util.ArrayList;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lines;

    public BruteCollinearPoints(Point[] points){
        lines = new ArrayList<>();
        for(int p=0;p<points.length-3;p++){
            for(int q=p;q<points.length-2;q++){
                for(int r=q;r<points.length-1;r++){
                    for(int s=r;s<points.length;r++){
                        if(points[p].slopeTo(points[q])==points[p].slopeTo(points[r]) && points[p].slopeTo(points[r])==points[p].slopeTo(points[s])){
                            lines.add(new LineSegment(points[p],points[s]));
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
