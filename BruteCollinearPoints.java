
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final LineSegment[] segments;



    public BruteCollinearPoints(Point[] points){// finds all line segments containing 4 points
        if (points == null){
            throw new IllegalArgumentException("Empty list input ");
        }
        for (int i = 0; i < points.length ; i++) {

            if (points[i] == null){
                throw new IllegalArgumentException("one input is null");
            }

        }

        Point[] copy = points.clone();
        // check repeating;
        Arrays.sort(copy);

        if (copy.length > 1){

            for (int i = 1; i < copy.length ; i++) {
                if(copy[i].compareTo(copy[i-1]) == 0){
                    throw new IllegalArgumentException("repeating inputs");
                }
            }
        }
        ArrayList<LineSegment> res = new ArrayList<LineSegment>();
        if (copy.length > 3){
            Point[] temp = new Point[4];
            for (int a = 0; a < copy.length -3; a++) {
                temp[0] = copy[a];
                for (int b = a+1; b < copy.length -2; b++) {
                    temp[1] = copy[b];
                    for (int c = b+1; c < copy.length - 1 ; c++) {
                        temp[2] = copy[c];
                        for (int d = c+1; d < copy.length; d++) {
                            temp[3] = copy[d];
                            if (isColinear(temp)){
                                //if colinear{
                                // then add the sorted segment then add [0][3] to the res}
                                LineSegment line = makeSegment(temp);
                                res.add(line);
                            }

                        }
                    }
                }

            }
        }//end of if



        segments = res.toArray(new LineSegment[res.size()]);//a − This is the array into which the elements of the list are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose.

    }

    private boolean isColinear(Point[] lst){
        Point point1 = lst[0];
        int temp0 = point1.slopeOrder().compare(lst[1],lst[2]);
        int temp1 = point1.slopeOrder().compare(lst[1],lst[3]);
        if (temp0 == temp1 && temp0 == 0) return true;
        return false;
    }

    private LineSegment makeSegment(Point[] lst){
        Arrays.sort(lst);
        Point small = lst[0];
        Point big = lst[3];
        for(Point i : lst){
            if (i.compareTo(big) > 0 ){
                big =i;
            }if (i.compareTo(small) <0 ){
                small = i;
            }
        }


        LineSegment x = new LineSegment(small, big);

        return x;
    }


    public int numberOfSegments(){// the number of line segments
        return segments.length;
    }
    public LineSegment[] segments(){// the line segments
        LineSegment[] res = segments.clone();


        return res;
    }

    public static void main(String[] args) {


    }
}
