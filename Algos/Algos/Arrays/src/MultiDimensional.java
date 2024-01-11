import java.util.Arrays;

public class MultiDimensional {

    public static void main(String[] args){
        MultiDimensional Obj1 = new MultiDimensional();
        int[][] value = Obj1.initialize();
        System.out.println(Arrays.deepToString(value));
    }

    public int[][] initialize(){
        int[][] grades = {{67, 87, 90, 98}, {89,45, 66}};
        return grades;
    }
}


