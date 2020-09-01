
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final LineSegment[] segments;
    public FastCollinearPoints(Point[] points){
        if (points == null){ //validation
            throw new IllegalArgumentException("Empty list input ");
        }
        for (int i = 0; i < points.length ; i++) {// validation

            if (points[i] == null){
                throw new IllegalArgumentException("one input is null");
            }
        }

        Point[] copy = points.clone();
        Arrays.sort(copy); //pioritize sorting Point_Y;
        if (copy.length > 1){ //checking repeating items
            for (int i = 1; i < copy.length ; i++) {
                if(copy[i].compareTo(copy[i-1]) == 0){
                    throw new IllegalArgumentException("repeating inputs");
                }
            }
        }

        ArrayList<LineSegment> res = new ArrayList<>();

        if (copy.length >3){
            Point[] temp = copy.clone();
            for (Point p : copy){
                Arrays.sort(temp, p.slopeOrder());  //sort slop from small to big;
                helper(temp, p, res);
            }
        }
        segments = res.toArray(new LineSegment[res.size()]);

    }     // finds all line segments containing 4 or more points
    private void helper(Point[] points, Point p, ArrayList<LineSegment> res){
        // position 0 is p, start from position 1;
        int start = 1;
        double slop = p.slopeTo(points[1]);

        for (int i =2; i <points.length ; i++){
            double tempslop = p.slopeTo(points[i]);
            if (Double.compare(tempslop, slop) != 0){ //colinear

                if (i - start >=3 ){  // i - start == 3; means i= 4; already moving on to the 5th point;
                    Point[] lst = get_seg( points, p, start, i);

                    if (lst[0].compareTo(p) == 0){
                        res.add(new LineSegment(lst[0], lst[1]));
                    }
                }

                start = i;

                slop = tempslop;


            }
        }

        if (points.length - start >= 3 ){
            Point[] lastpoints = get_seg(points,p,start,points.length);
            if (lastpoints[0].compareTo(p) == 0){
                res.add(new LineSegment(lastpoints[0],lastpoints[1]));
            }
        }

    }
    private Point[] get_seg(Point[] points, Point p, int start, int end){
        ArrayList<Point> lst = new ArrayList<>();
        lst.add(p);
        for (int i = start; i < end ; i++) {
            lst.add(points[i]);
        }
        lst.sort(null); //When arraylist is sorted with null, natrual ordering is used;

        return new Point[] {lst.get(0),lst.get(lst.size() -1 )};
    }

    public           int numberOfSegments(){
        return segments.length;
    }        // the number of line segments
    public LineSegment[] segments(){
        return segments.clone();
    }                // the line segments

    public static void main(String[] args) {


    }
}
