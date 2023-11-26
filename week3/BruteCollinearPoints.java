import java.util.ArrayList;

public class BruteCollinearPoints {
    private LineSegment[] lines;

public BruteCollinearPoints(Point[] points){
    if(points==null){
        throw new IllegalArgumentException();
    }
    ArrayList<LineSegment> tempLines = new ArrayList<>();
    for(int i=0;i<points.length;i++){
        for(int j=i+1;j<points.length;j++){
            for(int k=j+1;k<points.length;k++){
                for(int l=k+1;l<points.length;j++){
                    if(points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) &&  points[i].slopeTo(points[j])==points[i].slopeTo(points[l])){
                        LineSegment line = new LineSegment(points[i], points[l]);
                        tempLines.add(line);
                    }
                }
            }
        }
    }
    this.lines=new LineSegment[tempLines.size()];
    for(int x=0;x<tempLines.size();x++){
        lines[x]=tempLines.get(x);
    }


} 
   // finds all line segments containing 4 points
   public int numberOfSegments(){
    return lines.length;
   }        // the number of line segments
   public LineSegment[] segments(){
    return lines;
   } 
}
