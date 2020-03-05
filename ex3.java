import java.util.Arrays;

public class ex3 {
    public static void main(String [] args)
    {
        int[]  ints =  {2,3,4,5};
        int[] intCopy;
        intCopy = ints.clone();
        int [] anotherCopy = new int[5];
        System.arraycopy(ints,0,anotherCopy,0,3);
        System.out.println(Arrays.toString(anotherCopy));

    }
}
