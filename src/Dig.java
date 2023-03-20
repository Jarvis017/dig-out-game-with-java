import java.util.Scanner;


import java.util.Random;
public class Dig {
	

	public static void main(String[] args) {
		
		game();
		
		
		
		
       
    }
	static void game()
	{
		System.out.print("Let's get the treasure\n");
		System.out.print("hunter,please inter your name :\n");
		Scanner input=new Scanner(System.in);
		String name=input.next();
		System.out.println("please inter game dimensions(between 4 to 10)");
		int length=input.nextInt();
		int width=input.nextInt();
		System.out.println("please inter the number of coin");
		int coin=input.nextInt();
		System.out.println("\n\n\n\n\n\n\n");
		
		String[] game_space=new String[length*width];
		 for(int i=0;i<length*width;i++)
			 game_space[i]=".";
		game_space[0]="!";                                                         	//player start place
		int player_place=0;
		int length_count=0;
		int width_count=width;
		Random r=new Random();
		int random_number;
		 for (int i=0;i<coin;i++)                                                 // making coin on board
		 {
			  random_number=r.nextInt(length*width);
			 if (game_space[random_number].equals("."))
				 game_space[random_number]="$";
			 else
				 i--;			 	 
		 }
		 for (int i=0;i<((length*width-coin-1)*0.85);)								   //making earth
		 {
			 random_number=r.nextInt(length*width);
			 if(game_space[random_number].equals("."))
				 game_space[random_number]="#";
			 else 
				 i++;
		 }
		 for (int i=0;i<((length*length)*0.10);i++) 									 //making stone(10% of board is make from stone)
		 {
			 random_number=r.nextInt(length*width);
			 if(game_space[random_number].equals("."))
			 {
				 game_space[random_number]="@";
				 
				
			 }
			 else
				 i--;
		 }
		 System.out.println("please see the moving and destroying button");
		 System.out.println("w=up\ta=left\ts=down\td=right");
		 System.out.println("u=destroy up\tj=destroy down\th=destroy left\tk=destroy right");
		
		int coin_counter=0;
		for(int j=1,p=0;j<length*width;j++) {
			if (game_space[j].equals("@"))
					{
						int stone_position=j;
						for(;;) {
							if ((stone_position+length)/3<=width-2) {
										if(game_space[stone_position+length].equals(".")) {						
										game_space[stone_position+length]="@";
										game_space[stone_position]=".";
										stone_position=stone_position+length;
									    }
										else
											break;
							}
							else
								break;
						}

					}						
		}
			
		boolean gravity_kill=false;
		for(;;)																		 	 //game algorithm
		{
			for(int e=0;e<48+width;e++)
				System.out.println("\n");		
			board( game_space,length,width);
			 String in=input.next();
			if(in.equals("w"))															 //moving up
			{
				if(width_count<width)
				{
				if(game_space[player_place-length].equals("$"))
						coin_counter++;
				if(game_space[player_place-length].equals("$") || game_space[player_place-length].equals("."))
				{	
					game_space=up(game_space,player_place,length);
					player_place=(player_place-length);
					width_count++;
				}
				}			
			}
			
			if (in.equals("s"))																//moving down
			{
				if(width_count>0)
				{
				if(game_space[player_place+length].equals("$"))
						coin_counter++;
				if(game_space[player_place+length].equals("$") || game_space[player_place+length].equals("."))
				{
					game_space=down(game_space,player_place,length);
					player_place=(player_place+length);
					width_count--;
				}
				}	
						
			}
			if(in.equals("d"))																//moving right
			{
				if(length_count<length)
				{
				if(game_space[player_place+1].equals("$"))
						coin_counter++;
				if(game_space[player_place+1].equals("$") || game_space[player_place+1].equals("."))
				{		
					game_space=left(game_space,player_place);
					player_place=(player_place+1);
					length_count++;
				}
				}	
						
			}
			if(in.equals("a"))																//moving left
			{
				if(length_count>0)
				{
				if(game_space[player_place-1].equals("$"))
						coin_counter++;
				if(game_space[player_place-1].equals("$") || game_space[player_place-1].equals("."))
				{	
					game_space=right(game_space,player_place);
					player_place=(player_place-1);
					length_count--;
				}
				}						
			}
			if(coin_counter==coin)
			{
				board( game_space,length,width);
				System.out.println("congratulation "+" hunter you win");
				break;
			}
			if(in.equals("u"))																 //destroying up 
			{
					if(width_count<width)
					{
						game_space=destroy_up(game_space, player_place, length);
						gravity_kill=death_gravity(game_space, player_place, length, gravity_kill);
					}
					if(gravity_kill)
					{	board( game_space,length,width);
						System.out.println("oh "+name+"...you got killed with stone gravity!!! ");
						break;
					}
			}	
			if (in.equals("j"))																//destroying down
			{
				if(width_count>0)
				{
					game_space=destroy_down(game_space, player_place, length,width);
				}
			}	
			if(in.equals("h"))																//destroying left
			{
				if(length_count>0)
				{
					game_space=destroy_left(game_space, player_place);
					
				}
			}
			if (in.equals("k"))																//destroying right
			{
				if(length_count<length)
					game_space=destroy_right(game_space, player_place);
			}	
			for(int j=1;j<length*width;j++) {
				if (game_space[j].equals("@"))
						{
							int stone_position=j;
							for(;;) {
								if ((stone_position+length)/length<=width-1) {
											if(game_space[stone_position+length].equals(".")) {						
											game_space[stone_position+length]="@";
											game_space[stone_position]=".";
											stone_position=stone_position+length;
										    }
											else
												break;
								}
								else
									break;
							}

						}						
			}
		}
		System.out.println("Do you want to hunt again?");
		System.out.println("1)yes    2)no");
		int ini=input.nextInt();
		if(ini==1)
			game();
		if(ini==2)
			System.out.println("Good luck");
	}
		static void board(String[] game_space, int length,int width)
		{
			boolean flag=true; 
			for(int i=0;i<length;i++)
			{
			if (flag)
				{
				System.out.print(" -");
				flag=false;
				}
			else
			System.out.print("-");
			}
			
			System.out.print("\n");
			for (int i=0,p=0;i<width;i++)
			{
			System.out.print("|");
			for(int j=0;j<length;j++)
			{
				{
					System.out.print(game_space[p]);
					p++;
				}
			}
			System.out.println("|");
		}
			 flag=true; 
			for(int i=0;i<length;i++)
			{
			if (flag)
				{
				System.out.print(" -");
				flag=false;
				}
			else
			System.out.print("-");
			}
		}
		static String[] up(String[] game_space,int player_place,int length)
		{
			if(game_space[player_place-length].equals("."))
			{
				game_space[player_place]=".";
				game_space[player_place-length]="!";
			}	
			if (game_space[player_place-length].equals("$"))
			{
				game_space[player_place]=".";
				game_space[player_place-length]="!";
		    }
			if(game_space[player_place-length].equals("X"))
				System.out.println("oops");
			if(game_space[player_place-length].equals("@"))
				System.out.println("ouch");
			return game_space;
		}
		static String[] down(String[] game_space,int player_place,int length)
		{
			if(game_space[player_place+length].equals("."))
			{
						game_space[player_place]=".";
						game_space[player_place+length]="!";
			}	
					if (game_space[player_place+length].equals("$"))
					{
						game_space[player_place]=".";
						game_space[player_place+length]="!";
					}
					if(game_space[player_place+length].equals("X"))
						System.out.println("oops");
					if(game_space[player_place+length].equals("@"))
						System.out.println("ouch");
					return game_space;
		}
		static String[] left(String[] game_space,int player_place)
		{
			if(game_space[player_place+1].equals(".") || game_space[player_place+1].equals("$"))
			{
				game_space[player_place]=".";
				game_space[player_place+1]="!";
			}
			if(game_space[player_place+1].equals("$"))
			{
				game_space[player_place]=".";
				game_space[player_place+1]="!";
			}
			if(game_space[player_place+1].equals("X"))
				System.out.println("oops");
			if(game_space[player_place+1].equals("@"))
				System.out.println("ouch");
			return game_space;
		}
		static String[] right(String[] game_space,int player_place)
		{
			if(game_space[player_place-1].equals(".") || game_space[player_place-1].equals("$"))
			{
				game_space[player_place]=".";
				game_space[player_place-1]="!";
			}
			if(game_space[player_place-1].equals("$"))
			{
				game_space[player_place]=".";
				game_space[player_place-1]="!";
			}
			if(game_space[player_place-1].equals("X"))
				System.out.println("oops");
			if(game_space[player_place-1].equals("@"))
				System.out.println("ouch");
			return game_space;
		}
		static String[] destroy_up(String[] game_space,int player_place,int length)
		{
			if(game_space[player_place-length].equals("#"))
				game_space[player_place-length]=".";	
			return game_space;
		}
		static String[] destroy_down(String[] game_space,int player_place,int length,int width)
		{
			
				if(game_space[player_place+length].equals("#"))
				game_space[player_place+length]=".";
			
			
			return game_space;
		}
		static String[] destroy_left(String[] game_space,int player_place)
		{
			if(game_space[player_place-1].equals("#"))
			{
					game_space[player_place-1]=".";
			}
			return game_space;
		}
		static String[] destroy_right(String[] game_space,int player_place)
		{
				if(game_space[player_place+1].equals("#"))
					game_space[player_place+1]=".";
			return game_space;
		}		
		static boolean death_gravity(String[] game_space,int player_space,int length,boolean kill)
		{
			if(player_space-2*length>=0) {
			if(game_space[player_space-2*length].equals("@"))
				kill=true;
			}
			return kill;
		}
		static String[] gravity(String[] game_space,int length,int width) {
			for(int j=1;j<length*width;j++) {
				if (game_space[j].equals("@"))
						{
							int stone_position=j;
							for(;;) {
								if (stone_position+length<=width) {
											if(game_space[stone_position+length].equals(".")) {						
											game_space[stone_position+length]="@";
											game_space[stone_position]=".";
											stone_position=stone_position+length;
										}
									else
										break;
								}
								else
									break;
							
							}

						}						
			}
			return game_space;
			
		}
	
	
	
	
}

	

























