package brick.matching;

/*
* The pairs class will do the change of grid
* it contains a check method and a match method
*/
public  class pairs{
static int count=0;
/*
* the check method has three parameters : the co-ordinate of point is clicked
* and a grid parameter to update
* the flag variable checks if the input is even or odd
*/
     static void check(int x,int y,grid game){
		if(MainGame.flag==0)
		{
        		game.switchOn[x][y]=true;
			
		}
		else
		{
                   for(int i=0;i<4;i++)
                    for(int j=0;j<4;j++)
                    {

                            if(game.switchOn[i][j]==true && game.arr[i][j]!=-1)
                            {

                                    if(game.arr[i][j]==game.arr[x][y])
                                    {
                                    /*
                                     * if the previous value is matched with the current value
                                     * both are leveled as -1 and the switchOn becomes true
                                    */
                                    game.arr[i][j]=-1;
                                    game.arr[x][y]=-1;
                                    game.switchOn[i][j]=true;
                                    game.switchOn[x][y]=true;

                                    }
                                    else
                                    {
                                        game.switchOn[i][j]=false;
                                        game.switchOn[x][y]=false;
                                    }

                            }
						
			}
			
			
		}
                       
		
		MainGame.flag++;
		MainGame.flag%=2;
                //shows the changes in console
                for(int i=0;i<4;i++)
                {
                    for(int j=0;j<4;j++)
                    {
                    if(game.switchOn[i][j]==true)
                        System.out.print(game.arr[i][j]);
                    else
                        System.out.print("?");
                   
                        
                    
                    }
                    System.out.println("");
                
                
                }
   
	}
     /*
     * the match method checks if all the color is matched or not
     * if the whole grid is matched then it returns true as
     * the completion of the game otherwise it returns false
     */
     static boolean match(grid game)
     {
         count = 0;
         for(int i=0; i<4; i++)
         {
             for(int j=0; j<4; j++)
             {
                 if(game.arr[i][j]==-1)
                     count++;
             }
         }
         if(count==16)
         {
             return true;
             
         }
         else 
         {
             return false;
            
         }
         
     }
     
}

