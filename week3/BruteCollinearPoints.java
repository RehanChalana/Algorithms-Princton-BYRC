import java.util.ArrayList;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lines;

    public BruteCollinearPoints(Point[] points){
        lines = new ArrayList<>();
        for(int p=0;p<points.length-3;p++){
            for(int q=p+1;q<points.length-2;q++){
                for(int r=q+1;r<points.length-1;r++){
                    for(int s=r+1;s<points.length;s++){
                        if(points[p].slopeTo(points[q])==points[p].slopeTo(points[r]) && points[p].slopeTo(points[r])==points[p].slopeTo(points[s])){
                            lines.add(new LineSegment(points[p],points[s]));
                            // System.out.println(points[p]+"    "+points[s]);
                            // p++;
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
