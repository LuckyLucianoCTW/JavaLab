package Telefon;

import java.util.Scanner;

public abstract class Meniu
{
    protected int iState;
    protected int iSelected_index;
    protected int iMax_Index;
    public Meniu()
    {
        iState = 0;
        iSelected_index = 0;
        iMax_Index = 0;
    }
    public Meniu(int iMax) {
        iState = 0;
        iSelected_index = 0;
        iMax_Index = iMax;
    }
    protected  void SetMaxIndex(int x)
    {
        iMax_Index = x;
    }
    public abstract boolean ControlPanel();
    public void scanIndex()
    {
        Scanner scan = new Scanner(System.in);
        iSelected_index = scan.nextInt();
    }

}
