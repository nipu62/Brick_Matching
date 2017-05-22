package brick.matching;

import java.util.Scanner;
/*
* The grid class will initialize the grid with color
* the parameter of the constructor is used for the level change
*/

class grid {
    /*
    * the integer array is used to put the value of the color
    * the boolean switchOn array will check if the color is to show or not to
    */
     int arr[][] = new int[4][4];
     boolean switchOn[][] = new boolean[4][4];
    
     /*
     * initialization of array
     */
     grid(int n)
    {
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
            {
                arr[i][j]=0;
                switchOn[i][j] = false;
            }
        /*
        * initialization of level
        */
     if(n==1)   
     {
         arr[0][0] = arr[0][1] = arr[1][3] = arr[2][1] = 
         arr[0][3] = arr[1][0] = arr[1][2] = arr[3][2] = 1;
         arr[1][1] = arr[2][0] = arr[3][1] = arr[3][3] = 
         arr[0][2] = arr[2][2] = arr[2][3] = arr[3][0] = 2;
     }
        
      else if(n==2)   
     {
         arr[0][0] = arr[0][1] = arr[1][3] = arr[2][1] = 1;
         arr[0][3] = arr[1][0] = arr[1][2] = arr[3][2] = 2;
         arr[1][1] = arr[2][0] = arr[3][1] = arr[3][3] = 3;
         arr[0][2] = arr[2][2] = arr[2][3] = arr[3][0] = 4;
     }
    
    }

    

 }


public class Board {
int number;    
}

