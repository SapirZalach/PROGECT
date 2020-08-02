
public class Context 
{
	private Strategy strategy;

	public Context(Strategy strategy)
	{
		this.strategy = strategy;
	}

	public static void executeStrategy(Board initial,int choice)
	{
		 Strategy.performSearch(initial,choice);
	}
}