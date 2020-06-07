package Telefon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Scanner;

import Telefon.Render.Render;
public abstract class Meniu
{
    protected int iState;
    protected int iSelected_index;
    protected int iMax_Index;
    protected Render Renderer;
    public Meniu(Render theRender)
    {

        Renderer = theRender;
        iState = 0;
        iSelected_index = 0;
        iMax_Index = 0;
    }
    public Meniu(int iMax,Render theRender) {
        Renderer = theRender;
        iState = 0;
        iSelected_index = 0;
        iMax_Index = iMax;
    }

    protected  void SetMaxIndex(int x)
    {
        iMax_Index = x;
    }
    public abstract boolean ControlPanel();
    public abstract List GetList();
    public void scanIndex()
    {
        Scanner scan = new Scanner(System.in);
        iSelected_index = scan.nextInt();
    }
}
