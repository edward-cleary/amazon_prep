import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        // create a variable for running total of points, initialize as 0
        int numPoints = 0;
        // foreach loop foreach point of Shape
        for (Point p : s.getPoints()) {
        // increment running total for each iteration
            numPoints++;
        }
        // return running total when points are complete
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        // get the number of points
        int numPoints = getNumPoints(s);
        // get the total length
        double perim = getPerimeter(s);
        // divide
        double avgLength = perim / numPoints;
        // return
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        // initialize variable for longest side;
        double largestSide = 0;
        Point prevPoint = s.getLastPoint();
        // for each loop and find length of each side
        for (Point currPoint : s.getPoints()) {
            double currLength = prevPoint.distance(currPoint);
            
            if (currLength > largestSide) {
                largestSide = currLength;
            }
            
            prevPoint = currPoint;
        }
        
        return largestSide;
        // compare the current length to the longest side
        // if current length is > longest side, set longest side = to current length
        // return longest side
    }

    public double getLargestX(Shape s) {
        // Put code here
        // initialize largestX = 0;
        double largestX = 0.0;
        // iterate over each point in shape, get x, compare x to largest x
        for (Point currPoint : s.getPoints()){
            double currX = currPoint.getX();
            
            if (currX > largestX) {
                largestX = currX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        // Iterate over multiple files
        double largestPerimMultiple = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerim = getPerimeter(s);
            
            if(currPerim > largestPerimMultiple){
                largestPerimMultiple = currPerim;
            }
        }
        // Within each iteration calculate the perimeter of the shape
        // Compare perimeter to previous, set largest perimeter
        // Return value of largest perimeter
        return largestPerimMultiple;
    }

    public File getFileWithLargestPerimeter() {
        // Put code here
        double largestPerimMultiple = 0;
        DirectoryResource dr = new DirectoryResource();
        File temp = null;    // replace this code
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerim = getPerimeter(s);
            
            if (currPerim > largestPerimMultiple) {
                largestPerimMultiple = currPerim;
                temp = f;
            }
        }
        return temp;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numPoints = getNumPoints(s);
        System.out.println("number points = " + numPoints);
        double averageLength = getAverageLength(s);
        System.out.println("average length of points is " + averageLength);
        double largestSide = getLargestSide(s);
        System.out.println("largest side is " + largestSide);
        double largestX = getLargestX(s);
        System.out.println("largest X is " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerimMultiple = getLargestPerimeterMultipleFiles();
        System.out.println("the largest perimeter from those files is " + largestPerimMultiple);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        File fileLargestPerimeter = getFileWithLargestPerimeter();
        System.out.println("Name of file with largest perimeter " + fileLargestPerimeter.getName());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
        int numPoints = getNumPoints(triangle);
        System.out.println("number of points = " + numPoints);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
